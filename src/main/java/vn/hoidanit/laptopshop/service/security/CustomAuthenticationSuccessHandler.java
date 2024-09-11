package vn.hoidanit.laptopshop.service.security;

import java.io.IOException;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.CartService;
import vn.hoidanit.laptopshop.service.UserService;

@Service
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;
    private final CartService cartService;

    public CustomAuthenticationSuccessHandler(UserService userService, CartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        String targetUrl = "";

        if (roles.contains("ROLE_USER")) {
            targetUrl = "/";
        } else if (roles.contains("ROLE_ADMIN")) {
            targetUrl = "/admin";
        } else {
            targetUrl = "/login?error=no_role";
        }

        clearAuthenticationAttributes(request, authentication);

        if (response.isCommitted()) {
            return;
        }

        response.sendRedirect(targetUrl);
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, Authentication authentication) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        User user = this.userService.getUserByEmail(authentication.getName());
        if (user != null) {
            Cart cart = this.cartService.getCartByUser(user);
            if (cart != null) {
                session.setAttribute("sum", cart.getSum());
            } else {
                session.setAttribute("sum", 0);
            }
            session.setAttribute("fullName", user.getFullName());
            session.setAttribute("avatar", user.getAvatar());
            session.setAttribute("role", user.getRole().getName());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("id", user.getId());
        }
    }
}
