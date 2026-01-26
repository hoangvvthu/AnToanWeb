package ute.shop.dao.implement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import ute.shop.config.JPAConfig;
import ute.shop.dao.IReviewDao;
import ute.shop.entity.Order;
import ute.shop.entity.Review;
import ute.shop.entity.Store;

import java.util.List;

public class ReviewDaoImpl implements IReviewDao {

	@Override
	public List<Review> findByProductId(int productId) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			TypedQuery<Review> query = em.createQuery("SELECT r FROM Review r WHERE r.product._id = :productId",
					Review.class);
			query.setParameter("productId", productId);
			return query.getResultList();
		} catch (Exception e) {
			throw new RuntimeException("Error fetching reviews for product ID: " + productId, e);
		} finally {
			em.close();
		}
	}

	@Override
	public List<Review> findByUserId(int userId) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			TypedQuery<Review> query = em.createQuery("SELECT r FROM Review r WHERE r.user._id = :userId",
					Review.class);
			query.setParameter("userId", userId);
			return query.getResultList();
		} catch (Exception e) {
			throw new RuntimeException("Error fetching reviews for user ID: " + userId, e);
		} finally {
			em.close();
		}
	}

	@Override
	public boolean addReview(Review review) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(review);
			trans.commit();
			return true;
		} catch (Exception e) {
			trans.rollback();
			throw new RuntimeException("Error adding review", e);
		} finally {
			em.close();
		}
	}

	@Override
	public List<Review> getReviewsByOrderAndStore(int orderId, int storeId) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			TypedQuery<Review> query = em.createQuery(
					"SELECT r FROM Review r WHERE r.order._id = :orderId AND r.store._id = :storeId", Review.class);
			query.setParameter("orderId", orderId);
			query.setParameter("storeId", storeId);
			return query.getResultList();
		} catch (Exception e) {
			throw new RuntimeException("Error fetching reviews by order and store", e);
		} finally {
			em.close();
		}
	}

}
