package org.example.crud.dao;

import org.example.crud.models.Role;
import org.example.crud.models.User;

import java.util.List;

public interface UserDAO {
    List<User> index();
    User show(int id);
    void save(User user);
    void update(int id, User updatedUser);
    void delete(int id);
    User findUserByName(String name);
    Role findRoleByName(String roleName);
}
