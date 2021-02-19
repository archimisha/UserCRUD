package org.example.crud.dao;

import org.example.crud.models.User;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class UserDAOImpl {

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
}
