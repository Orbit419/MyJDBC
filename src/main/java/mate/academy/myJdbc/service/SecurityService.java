package mate.academy.myJdbc.service;

import mate.academy.myJdbc.model.User;
import mate.academy.myJdbc.web.Request;

public interface SecurityService {
    User doLogin(Request request);

    boolean doRegistration(User user);
}
