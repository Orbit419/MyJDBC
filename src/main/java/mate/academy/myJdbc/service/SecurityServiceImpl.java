package mate.academy.myJdbc.service;

import mate.academy.myJdbc.model.User;

public class SecurityServiceImpl implements SecurityService {
    private final UserService userService;

    public SecurityServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean doLogin(String username, String password) {
        User user = userService.findByUsername(username);
        return user.getPassword().equals(password);
    }

    @Override
    public boolean doRegistration(User user) {
        userService.create(user);
        return true;
    }
}
