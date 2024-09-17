package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.FileService;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.dto.ProductCriterialDTO;
import vn.hoidanit.laptopshop.service.exception.HandleExceptionPaginationService;

@Controller("adminProductController")
public class ProductController {

    private final ProductService productService;
    private final FileService fileService;
    private final HandleExceptionPaginationService hPaginationService;

    public ProductController(ProductService productService, FileService fileService,
            HandleExceptionPaginationService hPaginationService) {
        this.productService = productService;
        this.fileService = fileService;
        this.hPaginationService = hPaginationService;
    }

    @GetMapping("/admin/product")
    public String getProductPage(Model model, ProductCriterialDTO productCriterialDTO) {
        // page = 1 & limit = 10
        int page = this.hPaginationService.isExistsPageParameter(productCriterialDTO);

        Pageable pageable = PageRequest.of(page - 1, 2);

        Page<Product> prs = this.productService.fetchProducts(pageable);
        List<Product> products = prs.getContent();
        long totalPages = prs.getTotalPages();

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("products", products);
        return "admin/product/show";
    }

    @GetMapping("/admin/product/{id}")
    public String getDetailProductPage(Model model, @PathVariable Long id) {
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
        return "admin/product/detail";
    }

    @GetMapping("/admin/product/create")
    public String getCreateProductPage(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping("/admin/product/create")
    public String postCreateProduct(Model model, @ModelAttribute("newProduct") @Valid Product product,
            BindingResult newProductBindingResult,
            @RequestParam("imageFile") MultipartFile imageFileMultipartFile) {

        // Validation
        List<FieldError> errors = newProductBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(">>>>>" + error.getField() + "-" + error.getDefaultMessage());
        }
        if (newProductBindingResult.hasFieldErrors()) {
            return "admin/product/create";
        }
        // validation
        product.setImage(this.fileService.handleSaveUploadFile(imageFileMultipartFile, "product"));
        product.setSold(0L);
        this.productService.createOrUpdateProduct(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/update/{id}")
    public String getUpdateProductPage(Model model, @PathVariable Long id) {
        Product product = this.productService.getProductById(id);
        model.addAttribute("newProduct", product);
        return "admin/product/update";
    }

    @PostMapping("/admin/product/update")
    public String postUpdateProduct(Model model, @ModelAttribute("newProduct") @Valid Product newProduct,
            BindingResult newProductBindingResult, @RequestParam("imageFile") MultipartFile multipartFile) {

        Product currentProduct = this.productService.getProductById(newProduct.getId());
        newProduct.setImage(currentProduct.getImage());
        // Validation Start
        if (newProductBindingResult.hasFieldErrors()) {
            return "admin/product/update";
        }
        // Validation End

        if (multipartFile.getOriginalFilename() != "") {
            this.fileService.handleDeleteUploadFile(currentProduct.getImage(), "product");
            newProduct.setImage(this.fileService.handleSaveUploadFile(multipartFile, "product"));
        }
        newProduct.setSold(currentProduct.getSold());
        this.productService.createOrUpdateProduct(newProduct);
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String getDeleteProductPage(Model model, @ModelAttribute("currentProduct") Product currentProduct) {
        Product product = this.productService.getProductById(currentProduct.getId());
        model.addAttribute("currentProduct", product);
        return "admin/product/delete";
    }

    @PostMapping("/admin/product/delete")
    public String postDeleteProduct(Model model, @ModelAttribute("currentProduct") Product currentProduct) {
        Product product = this.productService.getProductById(currentProduct.getId());
        fileService.handleDeleteUploadFile(product.getImage(), "product");
        this.productService.deleteProductById(product.getId());
        return "redirect:/admin/product";
    }
}
