package mate.academy.myJdbc.controllers;

import mate.academy.myJdbc.web.Request;
import mate.academy.myJdbc.web.ViewModel;

import javax.servlet.http.Cookie;

public class LogoutController implements Controller {
    @Override
    public ViewModel process(Request request) {
        Cookie cookie = new Cookie("MATE", "");
        cookie.setMaxAge(0);
        ViewModel vm = ViewModel.of("login");
        vm.addCookie(cookie);
        return vm;
    }
}
