package vn.hoidanit.laptopshop.controller.client;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import vn.hoidanit.laptopshop.service.exception.HandleExceptionPaginationService;

@Controller("clientProductController")
public class ProductController {
    private final ProductService productService;
    private final CartDetailService cartDetailService;
    private final HandleExceptionPaginationService hPaginationService;

    public ProductController(ProductService productService, CartDetailService cartDetailService,
            HandleExceptionPaginationService hPaginationService) {
        this.productService = productService;
        this.cartDetailService = cartDetailService;
        this.hPaginationService = hPaginationService;
    }

    @GetMapping("/products")
    public String getProductsPage(Model model, @RequestParam("page") Optional<String> pageOptional,
            @RequestParam("min-price") Optional<String> minOptional,
            @RequestParam("max-price") Optional<String> maxOptional,
            @RequestParam("factory") Optional<String> factoryOptional,
            @RequestParam("price") Optional<String> priceOptional,
            @RequestParam("target") Optional<String> targetOptional) {

        int page = this.hPaginationService.isExistsPageParameter(pageOptional);

        // Case 1: min-price
        // Double minPrice = minOptional.isPresent() ?
        // Double.parseDouble(minOptional.get()) : 0;

        // Case 2: max-price
        // Double maxPrice = maxOptional.isPresent() ?
        // Double.parseDouble(maxOptional.get()) : 0;

        // Case 3: factory
        // String factory = factoryOptional.isPresent() ? factoryOptional.get() : "";

        // Case 4: Multi factory
        // List<String> factory = factoryOptional.isPresent() ?
        // Arrays.asList(factoryOptional.get().split(",")) : null;

        // Case 5: 10-toi-15-trieu && 15-toi-20-trieu
        // String price = priceOptional.isPresent() ? priceOptional.get() : "";

        // Case 6: Multi Range Price
        List<String> price = priceOptional.isPresent() ? Arrays.asList(priceOptional.get().split(",")) : null;

        Pageable pageable = PageRequest.of(page - 1, 60);
        Page<Product> prs = this.productService.fetchProductsByMultiPrice(pageable, price);

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
