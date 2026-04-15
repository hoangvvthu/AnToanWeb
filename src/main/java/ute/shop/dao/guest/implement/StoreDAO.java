package ute.shop.dao.guest.implement;

import java.util.Collections;
import java.util.List;

import jakarta.persistence.EntityManager;
import ute.shop.config.JPAConfig;
import ute.shop.entity.Store;

public class StoreDAO {
    public StoreDAO() {
    }

    public StoreDAO(EntityManager em) {
    }

    public List<Store> searchStores(String keywords, int page, int pageSize) {
        if (keywords == null || keywords.trim().isEmpty()) {
            return Collections.emptyList();
        }

        EntityManager em = JPAConfig.getEntityManager();
        try {
            String query = "SELECT s FROM Store s WHERE LOWER(s.name) LIKE :keywords AND s.isActive = true";
            return em.createQuery(query, Store.class)
                    .setParameter("keywords", "%" + keywords.toLowerCase() + "%")
                    .setFirstResult((page - 1) * pageSize)
                    .setMaxResults(pageSize)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public long countSearchResults(String keywords) {
        if (keywords == null || keywords.trim().isEmpty()) {
            return 0L;
        }

        EntityManager em = JPAConfig.getEntityManager();
        try {
            String query = "SELECT COUNT(s) FROM Store s WHERE LOWER(s.name) LIKE :keywords AND s.isActive = true";
            return em.createQuery(query, Long.class)
                    .setParameter("keywords", "%" + keywords.toLowerCase() + "%")
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public Store findStoreById(int id) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.find(Store.class, id);
        } finally {
            em.close();
        }
    }
}
