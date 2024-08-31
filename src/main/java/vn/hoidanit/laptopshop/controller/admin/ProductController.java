package vn.hoidanit.laptopshop.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("adminProductController")
public class ProductController {

    @GetMapping("/admin/product")
    public String getDashboard() {
        return "admin/product/show";
    }
}
