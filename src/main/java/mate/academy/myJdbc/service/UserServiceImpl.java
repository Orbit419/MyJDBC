package mate.academy.myJdbc.service;

import mate.academy.myJdbc.dao.UserDao;
import mate.academy.myJdbc.model.User;

public class UserServiceImpl implements UserService {
    private final RoleService roleService;
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao, RoleService roleService) {
        this.userDao = userDao;
        this.roleService = roleService;
    }

    @Override
    public Long create(User user) {
        return userDao.create(user);
    }

    @Override
    public User findById(Long id) {
        User user = userDao.findById(id);
        user.setRoles(roleService.findAllByUserId(id));
        return user;
    }

    @Override
    public User findByToken(String token) {
        User user = userDao.findByToken(token);
        user.setRoles(roleService.findAllByUserId(user.getId()));
        return user;
    }

    @Override
    public User findByUsername(String username) {
        User user = userDao.findByUsername(username);
        user.setRoles(roleService.findAllByUserId(user.getId()));
        return user;
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }
}
