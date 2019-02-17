package mate.academy.myJdbc.controllers;

import mate.academy.myJdbc.service.SecurityService;
import mate.academy.myJdbc.web.Request;
import mate.academy.myJdbc.web.ViewModel;

public class LoginController implements Controller {
    private SecurityService securityService;

    public LoginController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public ViewModel process(Request request) {
        return securityService.doLogin(request);
    }
}
