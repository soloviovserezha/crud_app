package web.app.service;

import web.app.dto.LoginForm;

public interface LoginService {
    boolean authenticate(LoginForm loginForm);
}
