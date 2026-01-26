package ute.shop.dao.guest.implement;

import java.util.List;

import jakarta.persistence.EntityManager;
import ute.shop.entity.Product;
import ute.shop.entity.Review;

public class ReviewDAO {
    private EntityManager em;

    public ReviewDAO(EntityManager em) {
        this.em = em;
    }

    public List<Review> findByProduct(Product product) {
        return em.createQuery("SELECT r FROM Review r WHERE r.product = :product ORDER BY r.createdAt DESC", Review.class)
                 .setParameter("product", product)
                 .getResultList();
    }
}
