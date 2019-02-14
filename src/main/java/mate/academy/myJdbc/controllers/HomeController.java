package mate.academy.myJdbc.controllers;

import mate.academy.myJdbc.web.ViewModel;

public class HomeController implements Controller {
    @Override
    public ViewModel processGet() {
        return ViewModel.of("home");
    }

    @Override
    public ViewModel processPost() {
        return null;
    }
}
