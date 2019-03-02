package mate.academy.myJdbc;

import mate.academy.myJdbc.dao.DeveloperDao;
import mate.academy.myJdbc.dao.DeveloperDaoImpl;
import mate.academy.myJdbc.dao.ProjectDao;
import mate.academy.myJdbc.dao.ProjectDaoImpl;
import mate.academy.myJdbc.dao.RoleDao;
import mate.academy.myJdbc.dao.RoleDaoImpl;
import mate.academy.myJdbc.dao.SkillDao;
import mate.academy.myJdbc.dao.SkillDaoImpl;
import mate.academy.myJdbc.model.Role;
import mate.academy.myJdbc.service.DeveloperService;
import mate.academy.myJdbc.service.DeveloperServiceImpl;
import mate.academy.myJdbc.service.ProjectService;
import mate.academy.myJdbc.service.ProjectServiceImpl;
import mate.academy.myJdbc.service.RoleService;
import mate.academy.myJdbc.service.RoleServiceImpl;
import mate.academy.myJdbc.service.SkillService;
import mate.academy.myJdbc.service.SkillServiceImpl;

import java.sql.Connection;

public class ServiceUtil {
    private final static Connection connection = MyConnectionUtil.getConnection();

    public static DeveloperService getDeveloperService() {
        return new DeveloperServiceImpl(
                getProjectDao(),
                getSkillDao(),
                getDeveloperDao());
    }

    public static ProjectService getProjectService() {
        return new ProjectServiceImpl(
                getDeveloperDao(),
                getProjectDao());
    }

    public static SkillService getSkillService() {
        return new SkillServiceImpl(getSkillDao());
    }

    public static RoleService getRoleService() {
        return new RoleServiceImpl(getRoleDao());
    }

    public static DeveloperDao getDeveloperDao() {
        return new DeveloperDaoImpl(connection);
    }

    public static ProjectDao getProjectDao() {
        return new ProjectDaoImpl(connection);
    }

    public static SkillDao getSkillDao() {
        return new SkillDaoImpl(connection);
    }

    public static RoleDao getRoleDao() {
        return new RoleDaoImpl(connection);
    }
}
