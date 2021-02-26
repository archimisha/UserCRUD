package org.example.crud.dao;

import org.example.crud.models.Role;
import org.example.crud.models.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
@Component
@Transactional
@Repository
public class UserDAOImpl {

    private final Map<String, User> userMap = new HashMap<>();



    @PersistenceContext
    private EntityManager em;


    public List<User> index() {
        return em.createQuery("SELECT u FROM User u", User.class).getResultList();
    }


    public User show(int id) {
        TypedQuery<User> q = em.createQuery("SELECT u FROM User u where u.id = :id", User.class);
        q.setParameter("id", id);
        return q.getResultList().stream().findAny().orElse(null);
    }


    public void save(User user) {
        em.persist(user);
    }


    public void update(int id, User updatedUser) {
        em.merge(updatedUser);
    }


    public void delete(int id) {
        User u = show(id);
        em.remove(u);
    }

    public User getUserByName(String name) {
        userMap.put("user",
                new User("user", "ab", 1, "12345", Collections.singleton(new Role(1L, "ROLE_USER"))));
        userMap.put("admin",
                new User("admin", "ad", 2, "admin", Collections.singleton(new Role(2L, "ROLE_ADMIN"))));

        if (!userMap.containsKey(name)) {
            return null;
        }

        return userMap.get(name);
    }
}
