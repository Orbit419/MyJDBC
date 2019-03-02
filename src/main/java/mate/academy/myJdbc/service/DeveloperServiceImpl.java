package mate.academy.myJdbc.service;

import mate.academy.myJdbc.dao.DeveloperDao;
import mate.academy.myJdbc.dao.DeveloperDaoImpl;
import mate.academy.myJdbc.dao.ProjectDao;
import mate.academy.myJdbc.dao.SkillDao;
import mate.academy.myJdbc.model.Developer;

import java.util.Objects;
import java.util.Set;

public class DeveloperServiceImpl implements DeveloperService {
    private final ProjectDao projectDao;
    private final SkillDao skillDao;
    private final DeveloperDao developerDao;

    public DeveloperServiceImpl(ProjectDao projectDao, SkillDao skillDao, DeveloperDao developerDao) {
        this.projectDao = projectDao;
        this.skillDao = skillDao;
        this.developerDao = developerDao;
    }

    public Set<Developer> getAllJavaDevs() {
        return developerDao.getAllJavaDevs();
    }

    public Set<Developer> getAllMiddleDevs() {
        return developerDao.getAllMiddleDevs();
    }

    @Override
    public void addNewDeveloper(Developer developer) {
        int id = developerDao.addNewDeveloper(developer);
        developer.getSkills().stream()
                .filter(Objects::nonNull)
                .forEach(skill -> developerDao.insertDevelopersSkillsRelation(id, developerDao.getSkillId(skill)));
        developer.getProjects().stream()
                .filter(Objects::nonNull)
                .forEach(project -> developerDao.insertDevelopersProjectsRelation(developerDao.getProjectId(project), id));
    }

    @Override
    public Developer findDeveloper(int id) {
        Developer developer = developerDao.findDeveloper(id);
        developer.setSkills(skillDao.getSkillsByDeveloperId(id));
        developer.setProjects(projectDao.getProjectsByDeveloperId(id));
        return developer;
    }

    @Override
    public void updateDeveloper(Developer developer) {
        developerDao.updateDeveloper(developer);
        if (!developer.getSkills().isEmpty()) {
            developerDao.deleteDeveloperSkillRelations(developer.getId());
            developer.getSkills().stream()
                    .filter(Objects::nonNull)
                    .forEach(skill -> developerDao.insertDevelopersSkillsRelation(developer.getId(), developerDao.getSkillId(skill)));
        }
        if (!developer.getProjects().isEmpty()) {
            developerDao.deleteDeveloperProjectRelations(developer.getId());
            developer.getProjects().stream()
                    .filter(Objects::nonNull)
                    .forEach(project -> developerDao.insertDevelopersProjectsRelation(developerDao.getProjectId(project), developer.getId()));
        }
    }

    @Override
    public void deleteDeveloper(int id) {
        developerDao.delete(id);
    }
}
