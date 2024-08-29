package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UploadFileService;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class UserController {

    // DI: Dependency Injection
    private final UserService userService;
    private final UploadFileService fileService;

    public UserController(UserService userService, UploadFileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        return "hello";
    }

    @GetMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/show";
    }

    @GetMapping("/admin/user/{id}")
    public String getUserDetailPage(Model model, @PathVariable long id) {
        User user = this.userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        return "admin/user/detail";
    }

    @GetMapping("/admin/user/create")
    public String getCreateUserPage(Model model) {
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @PostMapping("/admin/user/create")
    public String createUserPage(Model model, @ModelAttribute("newUser") User toilamdev,
            @RequestParam("avatarFile") MultipartFile avatarFile) {
        // this.userService.handleSaveUser(toilamdev);
        String fileNameAvatar = this.fileService.handleSaveUploadFile(avatarFile, "avatar");
        System.out.println(fileNameAvatar);
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/update/{id}")
    public String getUserUpdatePage(Model model, @PathVariable long id) {
        User currentUser = this.userService.getUserById(id);
        System.out.println(currentUser);
        model.addAttribute("newUser", currentUser);
        return "/admin/user/update";
    }

    @PostMapping("/admin/user/update")
    public String postUpdateUser(Model model, @ModelAttribute("newUser") User newUser) {
        User currentUser = this.userService.getUserById(newUser.getId());
        if (currentUser != null) {
            currentUser.setAddress(newUser.getAddress());
            currentUser.setPhone(newUser.getPhone());
            currentUser.setFullName(newUser.getFullName());
            this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String getDeleteUserPage(Model model, @PathVariable long id) {
        User currentUser = this.userService.getUserById(id);
        model.addAttribute("currentUser", currentUser);
        return "admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String postDeleteUser(Model model, @ModelAttribute("currentUser") User currentUser) {
        this.userService.handleDeleteUser(currentUser.getId());
        return "redirect:/admin/user";
    }
}
