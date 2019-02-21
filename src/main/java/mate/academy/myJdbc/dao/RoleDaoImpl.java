package mate.academy.myJdbc.dao;

import mate.academy.myJdbc.model.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl extends AbstractDao implements RoleDao {
    public RoleDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Long create(Role role) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO developing.roles(role_name)" +
                    "VALUES ?");
            statement.setString(1, role.getRole());
            statement.execute();

            statement = connection.prepareStatement("SELECT MAX(role_id) FROM developing.roles");
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getLong("role_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Role find(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT role_id, role_name " +
                    "FROM developing.roles WHERE role_id = ?");
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return new Role(rs.getLong("role_id"), rs.getString("role_name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Role> findAllByUserId(Long id) {
        List<Role> list = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT developing.users.user_id, developing.roles.role_id, developing.roles.role_name " +
                            "FROM developing.roles " +
                            "JOIN developing.user_role ON (developing.roles.role_id = developing.user_role.role_id) " +
                            "JOIN developing.users ON (developing.users.user_id = developing.user_role.user_id) " +
                            "HAVING user_id = ?");
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                list.add(new Role(rs.getLong("role_id"), rs.getString("role_name")));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Role role) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE developing.roles" +
                    "SET role_name = ? WHERE role_id = ?");
            statement.setString(1, role.getRole());
            statement.setLong(2, role.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM developing.roles " +
                    "WHERE role_id = ?");
            statement.setLong(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addRoleToUser(Long userId, Long roleId) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO developing.user_role" +
                    " VALUES (?, ?)");
            statement.setLong(1, userId);
            statement.setLong(2, roleId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAllRolesFromUser(Long userId) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM developing.user_role" +
                    "WHERE user_id = ?");
            statement.setLong(1, userId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
