package vn.hoidanit.laptopshop.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.CartDetailService;
import vn.hoidanit.laptopshop.service.ProductService;

@Controller("clientProductController")
public class ProductController {
    private final ProductService productService;
    private final CartDetailService cartDetailService;

    public ProductController(ProductService productService, CartDetailService cartDetailService) {
        this.productService = productService;
        this.cartDetailService = cartDetailService;
    }

    @GetMapping("/product/{id}")
    public String getDetailPage(Model model, @PathVariable Long id) {
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
        return "client/product/detail";
    }

    @PostMapping("/add-product-to-cart/{id}")
    public String postAddProductToCart(@PathVariable Long id) {
        Product product = this.productService.getProductById(id);
        productService.addProductToCart(product);
        return "redirect:/";
    }

    @PostMapping("/delete-cart-detail/{id}")
    public String postDeleteCartDetail(@PathVariable long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        this.cartDetailService.deleteCartDetailById(id, session);
        return "redirect:/cart-detail";
    }

    @PostMapping("/place-order")
    public String postPlaceOrderProduct(
            HttpServletRequest request,
            @RequestParam("receiverName") String receiverName,
            @RequestParam("receiverAddress") String receiverAddress,
            @RequestParam("receiverPhone") String receiverPhone) {

        return "redirect:/";
    }
}
