package mate.academy.myJdbc.util;

import mate.academy.myJdbc.MyConnectionUtil;
import mate.academy.myJdbc.dao.DeveloperDao;
import mate.academy.myJdbc.dao.DeveloperDaoImpl;
import mate.academy.myJdbc.dao.ProjectDao;
import mate.academy.myJdbc.dao.ProjectDaoImpl;
import mate.academy.myJdbc.dao.SkillDao;
import mate.academy.myJdbc.dao.SkillDaoImpl;
import mate.academy.myJdbc.service.DeveloperService;
import mate.academy.myJdbc.service.DeveloperServiceImpl;
import mate.academy.myJdbc.service.ProjectService;
import mate.academy.myJdbc.service.ProjectServiceImpl;
import mate.academy.myJdbc.service.SkillService;
import mate.academy.myJdbc.service.SkillServiceImpl;

import java.sql.Connection;

public class JdbcFactory {
    private final static Connection connection;

    static {
        connection = MyConnectionUtil.getConnection();
    }

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

    public static DeveloperDao getDeveloperDao() {
        return new DeveloperDaoImpl(connection);
    }

    public static ProjectDao getProjectDao() {
        return new ProjectDaoImpl(connection);
    }

    public static SkillDao getSkillDao() {
        return new SkillDaoImpl(connection);
    }
}
