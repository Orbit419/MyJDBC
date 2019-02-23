package mate.academy.myJdbc.dao;

import mate.academy.myJdbc.model.Role;

import java.util.List;

public interface RoleDao {
    Long create(Role role);

    Role find(Long id);

    List<Role> findAllByUserId(Long id);

    void update(Role role);

    void delete(Long id);

    void deleteAllRolesFromUser(Long userId);
}
