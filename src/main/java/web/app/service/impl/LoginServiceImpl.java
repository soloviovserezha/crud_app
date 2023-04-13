package web.app.service.impl;

import org.springframework.stereotype.Service;
import web.app.dto.LoginForm;
import web.app.service.LoginService;

import java.util.logging.Logger;

@Service
public class LoginServiceImpl implements LoginService {

    private final Logger logger = Logger.getLogger(LoginServiceImpl.class.getName());
    @Override
    public boolean authenticate(LoginForm loginForm) {
        logger.info("try auth with " + loginForm);
        return loginForm.getUserName().equals("root") && loginForm.getPassword().equals("1234");
    }
}
