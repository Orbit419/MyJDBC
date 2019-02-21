package mate.academy.myJdbc.service;

import mate.academy.myJdbc.dao.RoleDao;
import mate.academy.myJdbc.model.Role;

import java.util.List;

public class RoleServiceImpl implements  RoleService {
    private RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Long create(Role role) {
        return roleDao.create(role);
    }

    @Override
    public Role find(Long id) {
        return roleDao.find(id);
    }

    @Override
    public List<Role> findAllByUserId(Long id) {
        return roleDao.findAllByUserId(id);
    }

    @Override
    public void update(Role role) {
        roleDao.update(role);
    }

    @Override
    public void delete(Long id) {
        roleDao.delete(id);
    }

    @Override
    public void addRoleToUser(Long userId, Long roleId) {
        roleDao.addRoleToUser(userId, roleId);
    }

    @Override
    public void deleteAllRolesFromUser(Long userId) {
        roleDao.deleteAllRolesFromUser(userId);
    }
}
