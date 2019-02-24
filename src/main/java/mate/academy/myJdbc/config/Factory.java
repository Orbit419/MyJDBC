package mate.academy.myJdbc.config;

import mate.academy.myJdbc.MyConnectionUtil;
import mate.academy.myJdbc.controllers.LoginController;
import mate.academy.myJdbc.controllers.LogoutController;
import mate.academy.myJdbc.controllers.RegistrationController;
import mate.academy.myJdbc.dao.RoleDao;
import mate.academy.myJdbc.dao.RoleDaoImpl;
import mate.academy.myJdbc.dao.UserDao;
import mate.academy.myJdbc.dao.UserDaoImpl;
import mate.academy.myJdbc.service.RoleService;
import mate.academy.myJdbc.service.RoleServiceImpl;
import mate.academy.myJdbc.service.SecurityService;
import mate.academy.myJdbc.service.SecurityServiceImpl;
import mate.academy.myJdbc.service.UserService;
import mate.academy.myJdbc.service.UserServiceImpl;

import java.sql.Connection;

public class Factory {
    private final static Connection connection;

    static {
        connection = MyConnectionUtil.getConnection();
    }

    private Factory(){}

    public static RegistrationController getRegistrationController() {
        return new RegistrationController(getSecurityService());
    }

    public static LoginController getLoginController() {
        return new LoginController(getSecurityService());
    }

    public static LogoutController getLogoutController() {
        return new LogoutController();
    }

    public static SecurityService getSecurityService() {
        return new SecurityServiceImpl(getUserService());
    }

    public static UserService getUserService() {
        return new UserServiceImpl(getUserDao(), getRoleService());
    }

    public static UserDao getUserDao() {
        return new UserDaoImpl(connection);
    }

    public static RoleService getRoleService() {
        return new RoleServiceImpl(getRoleDao());
    }

    public static RoleDao getRoleDao() {
        return new RoleDaoImpl(connection);
    }
}
