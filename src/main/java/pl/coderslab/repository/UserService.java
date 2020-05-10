package pl.coderslab.repository;

import pl.coderslab.entity.User;

public interface UserService {

    User findByUserName(String name);

    void saveUser(User user);
}
