package vn.hoidanit.laptopshop.service;

import java.util.List;

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
}
