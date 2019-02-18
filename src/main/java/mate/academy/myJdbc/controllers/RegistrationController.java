package mate.academy.myJdbc.controllers;

import mate.academy.myJdbc.model.User;
import mate.academy.myJdbc.service.SecurityService;
import mate.academy.myJdbc.web.Request;
import mate.academy.myJdbc.web.ViewModel;

public class RegistrationController implements Controller {
    private SecurityService securityService;

    public RegistrationController(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public ViewModel process(Request request) {
        String login = request.getParamByName("username");
        String password = request.getParamByName("password");
        String mail = request.getParamByName("mail");
        User user = new User.Builder()
                .username(login)
                .password(password)
                .mail(mail)
                .build();
        System.out.println(user);
        securityService.doRegistration(user);
        return ViewModel.of("login");
    }
}
