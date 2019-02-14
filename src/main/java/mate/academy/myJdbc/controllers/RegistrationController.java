package mate.academy.myJdbc.controllers;

import mate.academy.myJdbc.web.ViewModel;

public class RegistrationController implements Controller {
    @Override
    public ViewModel processGet() {
        return ViewModel.of("registration");
    }

    @Override
    public ViewModel processPost() {
        return null;
    }
}
