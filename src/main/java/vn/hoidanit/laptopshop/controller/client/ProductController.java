package vn.hoidanit.laptopshop.controller.client;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.Product_;
import vn.hoidanit.laptopshop.service.CartDetailService;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.dto.ProductCriterialDTO;
import vn.hoidanit.laptopshop.service.exception.HandleExceptionPaginationService;

@Controller("clientProductController")
public class ProductController {
    private final ProductService productService;
    private final CartDetailService cartDetailService;
    private final HandleExceptionPaginationService hExceptionPaginationService;

    public ProductController(ProductService productService, CartDetailService cartDetailService,
            HandleExceptionPaginationService hExceptionPaginationService) {
        this.productService = productService;
        this.cartDetailService = cartDetailService;
        this.hExceptionPaginationService = hExceptionPaginationService;
    }

    @GetMapping("/products")
    public String getProductsPage(Model model, ProductCriterialDTO productCriterialDTO, HttpServletRequest request) {

        int page = this.hExceptionPaginationService.isExistsPageParameter(productCriterialDTO);

        Pageable pageable = PageRequest.of(page - 1, 3);
        // Check Sort
        if (productCriterialDTO.getSort() != null && productCriterialDTO.getSort().isPresent()) {
            String sort = productCriterialDTO.getSort().get();
            if (sort.equals("gia-tang-dan")) {
                pageable = PageRequest.of(page - 1, 3, Sort.by(Product_.PRICE).ascending());
            } else if (sort.equals("gia-giam-dan")) {
                pageable = PageRequest.of(page - 1, 3, Sort.by(Product_.PRICE).descending());
            }
        }

        String qs = request.getQueryString();
        if (qs != null && !qs.isBlank()) {
            qs = qs.replace("page=" + page, "");
        }
        Page<Product> prs = this.productService.fetchProductsWithSpecs(pageable, productCriterialDTO);

        List<Product> products = prs.getContent();

        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", prs.getTotalPages());
        model.addAttribute("products", products);
        model.addAttribute("queryString", qs);
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
