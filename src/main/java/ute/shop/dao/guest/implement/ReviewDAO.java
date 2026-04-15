package ute.shop.dao.guest.implement;

import java.util.List;

import jakarta.persistence.EntityManager;
import ute.shop.config.JPAConfig;
import ute.shop.entity.Product;
import ute.shop.entity.Review;

public class ReviewDAO {
    public ReviewDAO() {
    }

    public ReviewDAO(EntityManager em) {
    }

    public List<Review> findByProduct(Product product) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT r FROM Review r WHERE r.product = :product ORDER BY r.createdAt DESC",
                    Review.class
            ).setParameter("product", product).getResultList();
        } finally {
            em.close();
        }
    }
}
