package mate.academy.myJdbc.service;

import mate.academy.myJdbc.model.Role;

import java.util.List;

public interface RoleService {
    Long create(Role role);

    Role find(Long id);

    List<Role> findAllByUserId(Long id);

    void update(Role role);

    void delete(Long id);

    void addRoleToUser(Long userId, Long roleId);

    void deleteAllRolesFromUser(Long userId);
}
