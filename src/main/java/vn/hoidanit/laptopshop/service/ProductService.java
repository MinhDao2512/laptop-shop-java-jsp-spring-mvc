package vn.hoidanit.laptopshop.service;

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

    // Case 1: Min-Price
    public Page<Product> fetchProductsByMinPrice(Pageable pageable, Double price) {
        return this.productRepository.findAll(ProductSpecs.queryByMinPrice(price), pageable);
    }

    // Case 2: Max-Price
    public Page<Product> fetchProductsByMaxPrice(Pageable pageable, Double price) {
        return this.productRepository.findAll(ProductSpecs.queryByMaxPrice(price), pageable);
    }

    // Case 3: Factory
    public Page<Product> fetchProductsByFactory(Pageable pageable, String factory) {
        return this.productRepository.findAll(ProductSpecs.queryByFactory(factory), pageable);
    }

    // Case 4: Multiple Factory
    public Page<Product> fetchProductsByMultiFactory(Pageable pageable, List<String> factory) {
        return this.productRepository.findAll(ProductSpecs.queryByMultiFactory(factory), pageable);
    }

    // Case 5: 10-toi-15-trieu && 15-toi-20-trieu
    public Page<Product> fetchProductsByPrice(Pageable pageable, String price) {
        if (price.equals("10-toi-15-trieu")) {
            double min = 10000000;
            double max = 15000000;
            return this.productRepository.findAll(ProductSpecs.matchPrice(min, max), pageable);
        } else if (price.equals("15-toi-30-trieu")) {
            double min = 15000000;
            double max = 30000000;
            return this.productRepository.findAll(ProductSpecs.matchPrice(min, max), pageable);
        } else {
            return this.productRepository.findAll(pageable);
        }
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
}
