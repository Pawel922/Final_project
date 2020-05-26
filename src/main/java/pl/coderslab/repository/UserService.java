package pl.coderslab.repository;

import pl.coderslab.entity.User;

public interface UserService {

    User findByUsername(String name);

    void saveUser(User user);
}
