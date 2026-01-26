package ute.shop.dao.implement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import ute.shop.config.JPAConfig;
import ute.shop.dao.ICartDao;
import ute.shop.entity.Cart;
import ute.shop.entity.CartItem;
import ute.shop.entity.User;

public class CartDaoImpl implements ICartDao {

	@Override
	public Cart findByUserId(int userId) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			return em.createQuery("SELECT c FROM Cart c WHERE c.user._id = :userId", Cart.class)
					.setParameter("userId", userId).getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}

	@Override
	public Cart getCartByUser(User user) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			return em.createQuery("SELECT c FROM Cart c LEFT JOIN FETCH c.cartItems WHERE c.user = :user", Cart.class)
					.setParameter("user", user).getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}

	@Override
	public Cart save(Cart cart) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.persist(cart);
			transaction.commit();
			return cart;
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
			return null;
		} finally {
			em.close();
		}
	}

	@Override
	public Cart update(Cart cart) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			Cart updatedCart = em.merge(cart);
			transaction.commit();
			return updatedCart;
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
			return null;
		} finally {
			em.close();
		}
	}

	@Override
	public Cart findById(int cartId) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			return em.find(Cart.class, cartId);
		} finally {
			em.close();
		}
	}

	@Override
	public CartItem findCartItemByCartAndProduct(int cartId, int productId) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			return em
					.createQuery(
							"SELECT ci FROM CartItem ci WHERE ci.cart._id = :cartId AND ci.product._id = :productId",
							CartItem.class)
					.setParameter("cartId", cartId).setParameter("productId", productId).getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}

	@Override
	public CartItem addOrUpdateCartItem(CartItem cartItem) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			CartItem savedItem = em.merge(cartItem);
			transaction.commit();
			return savedItem;
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
			return null;
		} finally {
			em.close();
		}
	}

	@Override
	public boolean removeCartItem(CartItem cartItem) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		try {
			transaction.begin();
			em.remove(em.contains(cartItem) ? cartItem : em.merge(cartItem));
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction.isActive()) {
				transaction.rollback();
			}
			return false;
		} finally {
			em.close();
		}
	}
}
