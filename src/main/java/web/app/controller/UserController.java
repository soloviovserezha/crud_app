package web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.app.model.User;
import web.app.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String getHomePage() {
        return "home";
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getUserList());
        return "users";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("user_id", userService.getUserById(id));
        return "users-details";
    }

    @GetMapping("/add")
    public String saveUser(Model model) {
        model.addAttribute("userForm", new User());
        userService.getUserList();
        return "user-add";
    }

    @PostMapping("/add")
    public String saveUserPost(User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user-edit";
    }

    @PatchMapping("/{id}/edit")
    public String editUserPost(User user, Model model) {
        model.addAttribute("user", userService.changeUser(user));
        return "redirect:/users";
    }

    @DeleteMapping("/{id}/remove")
    public String deleteUserById(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("id", userService.deleteUserById(id));
        return "redirect:/users";
    }

    @DeleteMapping("/remove")
    public String deleteAllUsers() {
        userService.deleteAllUsers();
        return "redirect:/users";
    }
}
