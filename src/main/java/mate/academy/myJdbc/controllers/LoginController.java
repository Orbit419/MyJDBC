package mate.academy.myJdbc.controllers;

import mate.academy.myJdbc.web.ViewModel;

public class LoginController implements Controller {
    @Override
    public ViewModel processGet() {
        return ViewModel.of("login");
    }

    @Override
    public ViewModel processPost() {
        return ViewModel.of("registration");
    }
}
