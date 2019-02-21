package mate.academy.myJdbc.controllers;

import mate.academy.myJdbc.model.User;
import mate.academy.myJdbc.service.SecurityService;
import mate.academy.myJdbc.web.Request;
import mate.academy.myJdbc.web.ViewModel;

import javax.servlet.http.Cookie;

public class LoginController implements Controller {
    private SecurityService securityService;

    public LoginController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public ViewModel process(Request request) {
        User user = securityService.doLogin(request);
        if (user == null)
            return ViewModel.of("login");
        String userToken = user.getToken();
        Cookie cookie = new Cookie("MATE", userToken);
        ViewModel vm = ViewModel.of("loggedIn");
        vm.addCookie(cookie);
        return vm;
    }
}
