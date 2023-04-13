package web.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import web.app.dto.LoginForm;
import web.app.service.LoginService;

import java.util.logging.Logger;

@Controller
public class LoginController {

    private final Logger logger = Logger.getLogger(LoginController.class.getName());

    private LoginService loginService;

    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        logger.info("GET /login returns login-page.html");
        model.addAttribute("loginForm", new LoginForm());
        return "login-page";
    }

    @PostMapping("/login/auth")
    public String authenticate(LoginForm loginForm) {
        if (loginService.authenticate(loginForm)) {
            logger.info("login OK redirect to users");
            return "redirect:/users";
        } else {
            logger.info("login FAIL redirect back to login");
            return "redirect:/login";
        }
    }
}
