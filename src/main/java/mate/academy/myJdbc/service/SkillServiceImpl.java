package mate.academy.myJdbc.service;

import mate.academy.myJdbc.dao.SkillDao;
import mate.academy.myJdbc.model.Skill;

public class SkillServiceImpl implements SkillService {
    private SkillDao skillDao;

    public SkillServiceImpl(SkillDao skillDao) {
        this.skillDao = skillDao;
    }

    @Override
    public int create(Skill skill) {
        return skillDao.create(skill);
    }

    @Override
    public Skill find(int id) {
        return skillDao.find(id);
    }

    @Override
    public void update(Skill skill) {
        skillDao.update(skill);
    }

    @Override
    public void delete(int id) {
        skillDao.delete(id);
    }
}
