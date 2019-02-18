package mate.academy.myJdbc.controllers;

import mate.academy.myJdbc.web.Request;
import mate.academy.myJdbc.web.ViewModel;

public interface Controller {
    ViewModel process(Request request);
}
