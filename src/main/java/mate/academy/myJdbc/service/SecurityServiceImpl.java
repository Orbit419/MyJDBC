package mate.academy.myJdbc.service;

import mate.academy.myJdbc.model.User;
import mate.academy.myJdbc.web.PasswordEncoder;
import mate.academy.myJdbc.web.Request;
import mate.academy.myJdbc.web.ViewModel;

import javax.servlet.http.Cookie;

public class SecurityServiceImpl implements SecurityService {
    private final UserService userService;

    public SecurityServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ViewModel doLogin(Request request) {
        String usernameInp = request.getParamByName("username");
        String passwordInp = PasswordEncoder.encode(request.getParamByName("password"));
        User user = userService.findByUsername(usernameInp);
        if(user == null)
            processUnauthorized();
        if(user.getPassword().equals(passwordInp))
            return processAuthorized(user);
        else
            return processUnauthorized();
    }

    @Override
    public boolean doRegistration(User user) {
        userService.create(user);
        return true;
    }

    private ViewModel processAuthorized(User user) {
        String userToken = user.getToken();
        System.out.println("ProcessAuthorized in doLogin. We extract user token: " + userToken);
        Cookie cookie = new Cookie("MATE", userToken);
        ViewModel vm = ViewModel.of("home");
        vm.addCookie(cookie);
        return vm;
    }

    private ViewModel processUnauthorized() {
        return ViewModel.of("login");
    }
}
