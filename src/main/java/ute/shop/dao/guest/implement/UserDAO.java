package ute.shop.dao.guest.implement;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import ute.shop.config.JPAConfig;
import ute.shop.entity.User;

public class UserDAO {
    public UserDAO() {
    }

    public UserDAO(EntityManager em) {
    }

    public void saveUser(User user) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public User findUserByEmail(String email) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public User findUserByPhone(String phone) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM User u WHERE u.phone = :phone", User.class)
                    .setParameter("phone", phone)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<User> findUsersByKeywords(String keywords, boolean onlyVerified, int page, int pageSize) {
        String queryStr = "SELECT u FROM User u WHERE (u.firstname LIKE :keywords OR u.lastname LIKE :keywords)";
        if (onlyVerified) {
            queryStr += " AND u.isEmailActive = true AND u.isPhoneActive = true";
        }

        EntityManager em = JPAConfig.getEntityManager();
        try {
            TypedQuery<User> query = em.createQuery(queryStr, User.class);
            query.setParameter("keywords", "%" + keywords + "%");
            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public int countUsersByKeywords(String keywords, boolean onlyVerified) {
        String queryStr = "SELECT COUNT(u) FROM User u WHERE (u.firstname LIKE :keywords OR u.lastname LIKE :keywords)";
        if (onlyVerified) {
            queryStr += " AND u.isEmailActive = true AND u.isPhoneActive = true";
        }

        EntityManager em = JPAConfig.getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(queryStr, Long.class);
            query.setParameter("keywords", "%" + keywords + "%");
            return query.getSingleResult().intValue();
        } finally {
            em.close();
        }
    }

    public User findById(int id) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }
}
