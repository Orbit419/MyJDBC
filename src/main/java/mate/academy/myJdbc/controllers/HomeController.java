package mate.academy.myJdbc.controllers;

import mate.academy.myJdbc.web.Request;
import mate.academy.myJdbc.web.ViewModel;

public class HomeController implements Controller {
    @Override
    public ViewModel process(Request request) {
        return ViewModel.of("home");
    }
}
