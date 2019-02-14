package mate.academy.myJdbc.service;

import mate.academy.myJdbc.model.User;

public interface SecurityService {
    boolean doLogin(String username, String password);

    boolean doRegistration(User user);
}
