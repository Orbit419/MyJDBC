package mate.academy.myJdbc.service;

import mate.academy.myJdbc.model.Skill;

public interface SkillService {
    int create(Skill skill);

    Skill find(int id);

    void update(Skill skill);

    void delete(int id);
}
