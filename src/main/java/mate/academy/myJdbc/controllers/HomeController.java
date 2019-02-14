package mate.academy.myJdbc.controllers;

import mate.academy.myJdbc.web.ViewModel;

public class HomeController implements Controller {
    @Override
    public ViewModel process() {
        return ViewModel.of("home");
    }
}
