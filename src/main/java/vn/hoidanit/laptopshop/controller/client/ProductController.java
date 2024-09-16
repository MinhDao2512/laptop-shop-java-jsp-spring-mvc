package vn.hoidanit.laptopshop.controller.client;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.CartDetailService;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.dto.ProductCriterialDTO;

@Controller("clientProductController")
public class ProductController {
    private final ProductService productService;
    private final CartDetailService cartDetailService;

    public ProductController(ProductService productService, CartDetailService cartDetailService) {
        this.productService = productService;
        this.cartDetailService = cartDetailService;
    }

    @GetMapping("/products")
    public String getProductsPage(Model model, ProductCriterialDTO productCriterialDTO) {

        int page = 1;
        try {
            if (productCriterialDTO.getPage().isPresent()) {
                page = Integer.parseInt(productCriterialDTO.getPage().get());
            } else {

            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        Pageable pageable = PageRequest.of(page - 1, 60);
        Page<Product> prs = this.productService.fetchProductsWithSpecs(pageable, productCriterialDTO);

        List<Product> products = prs.getContent();

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", prs.getTotalPages());
        model.addAttribute("products", products);
        return "client/product/show";
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

}
