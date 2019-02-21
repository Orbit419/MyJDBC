package mate.academy.myJdbc.service;

import mate.academy.myJdbc.model.User;

public interface UserService {
    Long create(User user);

    User findById(Long id);

    User findByToken(String token);

    User findByUsername(String username);

    void deleteById(Long id);

    void update(User user);
}
