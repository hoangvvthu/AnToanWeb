package ute.shop.dao.implement;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import ute.shop.config.JPAConfig;
import ute.shop.dao.IUserFollowProductDao;
import ute.shop.entity.UserFollowProduct;

public class UserFollowProductDao implements IUserFollowProductDao {
	// Tìm UserFollowProduct theo ID
	@Override
	public UserFollowProduct findById(int followProductId) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			return em.find(UserFollowProduct.class, followProductId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	// Tìm tất cả các UserFollowProduct
	@Override
	public List<UserFollowProduct> findAll() {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			TypedQuery<UserFollowProduct> query = em.createQuery("SELECT ufp FROM UserFollowProduct ufp",
					UserFollowProduct.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	// Tìm tất cả sản phẩm yêu thích của một người dùng theo userId
	@Override
	public List<UserFollowProduct> findByUserId(int userId) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			String query = "SELECT ufp FROM UserFollowProduct ufp WHERE ufp.user._id = :userId";
			return em.createQuery(query, UserFollowProduct.class).setParameter("userId", userId).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	@Override
	public void save(UserFollowProduct userFollowProduct) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(userFollowProduct); // Thêm mới đối tượng vào DB
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public void delete(UserFollowProduct userFollowProduct) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			em.getTransaction().begin();

			// Truy vấn chính xác đối tượng trong database
			UserFollowProduct entity = em.createQuery(
					"SELECT ufp FROM UserFollowProduct ufp WHERE ufp.user._id = :userId AND ufp.product._id = :productId",
					UserFollowProduct.class).setParameter("userId", userFollowProduct.getUser().get_id())
					.setParameter("productId", userFollowProduct.getProduct().get_id()).getSingleResult();

			// Nếu tìm thấy, xóa đối tượng
			if (entity != null) {
				em.remove(entity);
			}

			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	@Override
	public boolean isProductFollowedByUser(int userId, int productId) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			String query = "SELECT COUNT(ufp) FROM UserFollowProduct ufp WHERE ufp.user._id = :userId AND ufp.product._id = :productId";
			Long count = em.createQuery(query, Long.class).setParameter("userId", userId)
					.setParameter("productId", productId).getSingleResult();
			return count > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			em.close();
		}
	}

}
