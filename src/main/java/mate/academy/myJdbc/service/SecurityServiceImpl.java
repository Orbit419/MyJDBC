package mate.academy.myJdbc.service;

import mate.academy.myJdbc.model.User;
import mate.academy.myJdbc.web.PasswordEncoder;
import mate.academy.myJdbc.web.Request;

public class SecurityServiceImpl implements SecurityService {
    private final UserService userService;

    public SecurityServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public User doLogin(Request request) {
        String usernameInp = request.getParamByName("username");
        String passwordInp = PasswordEncoder.encode(request.getParamByName("password"));
        User user = userService.findByUsername(usernameInp);
        if(user == null)
            return null;
        if(user.getPassword().equals(passwordInp))
            return user;
        else
            return null;
    }

    @Override
    public boolean doRegistration(User user) {
        userService.create(user);
        return true;
    }
}
