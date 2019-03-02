package mate.academy.myJdbc.dao;

import mate.academy.myJdbc.model.Skill;

import java.util.Set;

public interface SkillDao {
    int create(Skill skill);

    Skill find(int id);

    void update(Skill skill);

    void delete(int id);

    Set<Skill> getSkillsByDeveloperId(int id);
}
