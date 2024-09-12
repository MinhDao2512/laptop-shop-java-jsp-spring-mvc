package vn.hoidanit.laptopshop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.CartDetailRepository;
import vn.hoidanit.laptopshop.repository.CartRepository;
import vn.hoidanit.laptopshop.repository.UserRepository;

@Service
public class CartDetailService {
    private final CartDetailRepository cartDetailRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    public CartDetailService(CartDetailRepository cartDetailRepository, UserRepository userRepository,
            CartRepository cartRepository) {
        this.cartDetailRepository = cartDetailRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    public List<CartDetail> getCartDetailsByCart(Cart cart) {
        return this.cartDetailRepository.findByCart(cart);
    }

    public void deleteCartDetailById(long id, HttpSession session) {
        this.cartDetailRepository.deleteById(id);

        User user = this.userRepository.findByEmail((String) session.getAttribute("email"));
        Cart cart = this.cartRepository.findByUser(user);

        if (cart.getSum() > 1) {
            cart.setSum(cart.getSum() - 1);
            this.cartRepository.save(cart);
        } else {
            this.cartRepository.deleteById(cart.getId());
        }
    }
}
