package mate.academy.myJdbc.dao;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import mate.academy.myJdbc.model.Skill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SkillDaoImpl extends AbstractDao implements SkillDao  {
    public SkillDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public int create(Skill skill) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO developing.skills(skill_branch, skill_level" +
                    "VALUES(?, ?))");
            statement.setString(1, skill.getBranch());
            statement.setString(2, skill.getLevel());
            statement.execute();

            statement = connection.prepareStatement("SELECT MAX(skill_id)FROM developing.skills");
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getInt("skill_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Skill find(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT skill_id, skill_branch, skill_level" +
                    "FROM developing.skills WHERE skill_id = ?");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return new Skill(
                    rs.getInt("skill_id"),
                    rs.getString("skill_branch"),
                    rs.getString("skill_level"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Skill skill) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE developing.skills " +
                    "SET skill_branch = ?, skill_level = ? WHERE skill_id = ?");
            statement.setString(1, skill.getBranch());
            statement.setString(2, skill.getLevel());
            statement.setInt(3, skill.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM developing.skills WHERE skill_id = ?");
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Set<Skill> getSkillsByDeveloperId(int id) {
        Set<Skill> list = new HashSet<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT skills.skill_id, skills.skill_branch, skills.skill_level, developers.developer_id\n" +
                    "FROM skills\n" +
                    "JOIN developers_skills ON (skills.skill_id = developers_skills.skill_id)\n" +
                    "JOIN developers ON (developers.developer_id = developers_skills.developer_id)\n" +
                    "HAVING developer_id = ?;");
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Skill skill = new Skill(rs.getInt("skill_id"),
                        rs.getString("skill_branch"),
                        rs.getString("skill_level"));
                list.add(skill);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
