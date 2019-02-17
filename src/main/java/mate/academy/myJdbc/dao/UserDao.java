package mate.academy.myJdbc.dao;

import mate.academy.myJdbc.model.User;

public interface UserDao {
    Long create(User user);

    User findByUsername(String username);

    User findById(Long id);

    User findByToken(String token);

    void deleteById(Long id);

    void update(User user);
}
