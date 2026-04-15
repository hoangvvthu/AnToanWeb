package ute.shop.dao.guest.implement;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import ute.shop.config.JPAConfig;
import ute.shop.entity.Category;

public class CategoryDAO {
    public CategoryDAO() {
    }

    public CategoryDAO(EntityManager em) {
    }

    public List<Category> findAll() {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Category c WHERE c.isDeleted = false", Category.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Category> findAllWithProducts() {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            String query = "SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.products WHERE c.isDeleted = false";
            return em.createQuery(query, Category.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Category> getRandomCategories(int limit) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            TypedQuery<Category> query = em.createQuery(
                    "SELECT c FROM Category c WHERE c.isDeleted = false ORDER BY function('RAND')",
                    Category.class
            );
            query.setMaxResults(limit);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Category findById(Long id) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.find(Category.class, id);
        } finally {
            em.close();
        }
    }
}
