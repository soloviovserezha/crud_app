package web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.app.model.User;
import web.app.service.UserService;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String getIndex() {
        return "home";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getUserList());
        return "users";
    }

    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("user_id", userService.getUserById(id));
        return "users-details";
    }

    @GetMapping("/users/add")
    public String userAddGet() {
        userService.getUserList();
        return "user-add";
    }

    @PostMapping("/users/add")
    public String userAddPost(@RequestParam(name = "userName") String name,
                              @RequestParam(name = "userSurname") String surname) {
        userService.addUser(new User(name, surname));
        return "redirect:/users";
    }

    @GetMapping("/users/{id}/edit")
    public String userEdit(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user-edit";
    }

    @PostMapping("/users/{id}/edit")
    public String userEditUpd(@PathVariable(value = "id") Long id,
                              @RequestParam(name = "userName") String name,
                              @RequestParam(name = "userSurname") String surname,
                              Model model) {
        User user = userService.getUserById(id);
        user.setName(name);
        user.setSurname(surname);
        model.addAttribute("user", userService.changeUser(user));
        return "redirect:/users";
    }

    @PostMapping("/users/{id}/remove")
    public String userDeleteById(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("id", userService.deleteUserById(id));
        return "redirect:/users";
    }

    @PostMapping("/users/remove")
    public String userDelete() {
        userService.deleteAllUsers();
        return "redirect:/users";
    }
}
