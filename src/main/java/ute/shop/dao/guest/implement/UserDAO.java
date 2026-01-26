package ute.shop.dao.guest.implement;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import ute.shop.entity.User;

public class UserDAO {
	private EntityManager em;

    public UserDAO(EntityManager em) {
        this.em = em;
    }

    public void saveUser(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    public User findUserByEmail(String email) {
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                     .setParameter("email", email)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public User findUserByPhone(String phone) {
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.phone = :phone", User.class)
                     .setParameter("phone", phone)
                     .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public List<User> findUsersByKeywords(String keywords, boolean onlyVerified, int page, int pageSize) {
        String queryStr = "SELECT u FROM User u WHERE (u.firstname LIKE :keywords OR u.lastname LIKE :keywords)";
        if (onlyVerified) {
            queryStr += " AND u.isEmailActive = true AND u.isPhoneActive = true";
        }

        TypedQuery<User> query = em.createQuery(queryStr, User.class);
        query.setParameter("keywords", "%" + keywords + "%");
        query.setFirstResult((page - 1) * pageSize);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    public int countUsersByKeywords(String keywords, boolean onlyVerified) {
        String queryStr = "SELECT COUNT(u) FROM User u WHERE (u.firstname LIKE :keywords OR u.lastname LIKE :keywords)";
        if (onlyVerified) {
            queryStr += " AND u.isEmailActive = true AND u.isPhoneActive = true";
        }

        TypedQuery<Long> query = em.createQuery(queryStr, Long.class);
        query.setParameter("keywords", "%" + keywords + "%");

        return query.getSingleResult().intValue();
    }

    public User findById(int id) {
        return em.find(User.class, id);
    }
}
