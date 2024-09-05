package vn.hoidanit.laptopshop.controller.client;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UserService;
import vn.hoidanit.laptopshop.service.dto.UserDTO;
import vn.hoidanit.laptopshop.service.mapper.UserMapper;

@Controller
public class HomePageController {

    private final ProductService productService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public HomePageController(ProductService productService, UserService userService, UserMapper userMapper,
            PasswordEncoder passwordEncoder) {
        this.productService = productService;
        this.userService = userService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        List<Product> products = this.productService.getAllProducts();
        model.addAttribute("products", products);
        return "client/homepage/show";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "client/auth/register";
    }

    @PostMapping("/register")
    public String postRegisterUser(Model model, @ModelAttribute("userDTO") @Valid UserDTO userDTO,
            BindingResult bindingResult) {

        if (bindingResult.hasFieldErrors()) {
            return "client/auth/register";
        }
        User user = this.userMapper.userDTOToUser(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        this.userService.createOrUpdateUser(user);
        return "redirect:/login?successRegister";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return "client/auth/login";
    }
}