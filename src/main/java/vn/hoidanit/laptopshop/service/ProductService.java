package vn.hoidanit.laptopshop.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.CartDetailRepository;
import vn.hoidanit.laptopshop.repository.CartRepository;
import vn.hoidanit.laptopshop.repository.ProductRepository;
import vn.hoidanit.laptopshop.repository.UserRepository;
import vn.hoidanit.laptopshop.service.dto.ProductCriterialDTO;
import vn.hoidanit.laptopshop.service.specification.ProductSpecs;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final HttpServletRequest request;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;

    public ProductService(ProductRepository productRepository, HttpServletRequest request,
            UserRepository userRepository, CartRepository cartRepository, CartDetailRepository cartDetailRepository) {
        this.productRepository = productRepository;
        this.request = request;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
    }

    public Product createOrUpdateProduct(Product product) {
        return this.productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Product getProductById(long id) {
        return this.productRepository.findById(id);
    }

    public Boolean deleteProductById(long id) {
        return this.productRepository.deleteById(id);
    }

    public void addProductToCart(Product product) {
        HttpSession session = this.request.getSession(false);
        User user = this.userRepository.findByEmail((String) session.getAttribute("email"));
        if (user != null) {
            Cart cart = cartRepository.findByUser(user);
            if (cart == null) {
                cart = new Cart();
                cart.setUser(user);
                cart.setSum(1L);

                Cart currentCart = this.cartRepository.save(cart);

                CartDetail cartDetail = new CartDetail();
                cartDetail.setPrice(product.getPrice());
                cartDetail.setProduct(product);
                cartDetail.setQuantity(1L);
                cartDetail.setCart(currentCart);
                this.cartDetailRepository.save(cartDetail);

                session.setAttribute("sum", 1);
            } else {
                // to do
                CartDetail oldDetail = this.cartDetailRepository.findByCartAndProduct(cart, product);
                if (oldDetail != null) {
                    oldDetail.setQuantity(oldDetail.getQuantity() + 1);
                    oldDetail.setPrice(oldDetail.getQuantity() * product.getPrice());
                    this.cartDetailRepository.save(oldDetail);
                } else {
                    cart.setSum(cart.getSum() + 1);
                    this.cartRepository.save(cart);
                    session.setAttribute("sum", cart.getSum());
                    CartDetail cartDetail = new CartDetail();
                    cartDetail.setPrice(product.getPrice());
                    cartDetail.setProduct(product);
                    cartDetail.setQuantity(1L);
                    cartDetail.setCart(cart);
                    this.cartDetailRepository.save(cartDetail);
                }
            }
        }
    }

    public Long getCountAllProducts() {
        return this.productRepository.count();
    }

    public Page<Product> fetchProducts(Pageable pageable) {
        return this.productRepository.findAll(pageable);
    }

    // Case 6: Multi Range Price
    public Page<Product> fetchProductsByMultiPrice(Pageable pageable, List<String> price) {
        Specification<Product> combinedSpec = (root, query, builder) -> builder.disjunction();
        int count = 0;
        for (String p : price) {
            double min = 0;
            double max = 0;
            switch (p) {
                case "10-toi-15-trieu":
                    min = 10000000;
                    max = 15000000;
                    count++;
                    break;

                case "15-toi-20-trieu":
                    min = 15000000;
                    max = 20000000;
                    count++;
                    break;

                case "20-toi-30-trieu":
                    min = 20000000;
                    max = 30000000;
                    count++;
                    break;
            }
            if (min != 0 && max != 0) {
                Specification<Product> rangeSpec = ProductSpecs.matchMultiPrice(min, max);
                combinedSpec = combinedSpec.or(rangeSpec);
            }
        }

        if (count == 0) {
            return this.productRepository.findAll(pageable);
        }

        return this.productRepository.findAll(combinedSpec, pageable);
    }

    public Page<Product> fetchProductsWithSpecs(Pageable pageable, ProductCriterialDTO productCriterialDTO) {
        if (productCriterialDTO.getPrice() == null &&
                productCriterialDTO.getFactory() == null &&
                productCriterialDTO.getTarget() == null) {
            return this.productRepository.findAll(pageable);
        }

        Specification<Product> combinedSpec = Specification.where(null);

        if (productCriterialDTO.getFactory() != null && productCriterialDTO.getFactory().isPresent()) {
            List<String> factory = Arrays.asList(productCriterialDTO.getFactory().get().split(","));
            Specification<Product> currentSpec = ProductSpecs.queryByMultiFactory(factory);
            combinedSpec = combinedSpec.and(currentSpec);
        }

        if (productCriterialDTO.getTarget() != null && productCriterialDTO.getTarget().isPresent()) {
            List<String> target = Arrays.asList(productCriterialDTO.getTarget().get().split(","));
            Specification<Product> currentSpec = ProductSpecs.queryByMultiTarget(target);
            combinedSpec = combinedSpec.and(currentSpec);
        }

        if (productCriterialDTO.getPrice() != null &&
                productCriterialDTO.getPrice().isPresent()) {
            List<String> price = Arrays.asList(productCriterialDTO.getPrice().get().split(","));
            Specification<Product> priceSpec = Specification.where(null);

            for (String p : price) {
                double min = 0;
                double max = 0;
                switch (p) {
                    case "duoi-10-trieu":
                        max = 10000000;
                        break;

                    case "10-15-trieu":
                        min = 10000000;
                        max = 15000000;
                        break;
                    case "15-20-trieu":
                        min = 15000000;
                        max = 20000000;
                        break;
                    case "tren-20-trieu":
                        min = 20000000;
                        break;
                }
                if (min != 0 && max == 0) {
                    Specification<Product> currentSpec = ProductSpecs.queryByMinPrice(min);
                    priceSpec = priceSpec.or(currentSpec);
                } else if (min == 0 && max != 0) {
                    Specification<Product> currentSpec = ProductSpecs.queryByMaxPrice(max);
                    priceSpec = priceSpec.or(currentSpec);
                } else if (min != 0 && max != 0) {
                    Specification<Product> currentSpec = ProductSpecs.matchMultiPrice(min, max);
                    priceSpec = priceSpec.or(currentSpec);
                }
            }

            combinedSpec = combinedSpec.and(priceSpec);
        }

        return this.productRepository.findAll(combinedSpec, pageable);
    }
}
