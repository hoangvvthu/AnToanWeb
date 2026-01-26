package ute.shop.dao.implement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.Date;
import java.util.List;
import jakarta.persistence.TypedQuery;
import ute.shop.config.JPAConfig;
import ute.shop.dao.IStoreDao;
import ute.shop.entity.Commission;
import ute.shop.entity.Product;
import ute.shop.entity.Store;
import ute.shop.entity.StoreLevel;
import ute.shop.entity.User;

public class StoreDaoImpl implements IStoreDao {

	@Override
	public Store findById(int storeId) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			return em.find(Store.class, storeId);
		} catch (Exception e) {
			throw new RuntimeException("Error finding order by ID", e);
		} finally {
			em.close();
		}
	}

	// find all store
	@Override
	public List<Store> findAll() {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			TypedQuery<Store> query = em.createQuery("SELECT s FROM Store s", Store.class);
			return query.getResultList();
		} catch (Exception e) {
			throw new RuntimeException("Error finding all store", e);

		} finally {
			em.close();
		}
	}

	@Override
	public Store findByOrderId(int orderId) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			// Sử dụng JPQL với JOIN FETCH để tải luôn orderItems
			String jpql = "SELECT o.store FROM Order o JOIN FETCH o.orderItems WHERE o.id = :orderId";
			return em.createQuery(jpql, Store.class).setParameter("orderId", orderId).getSingleResult();
		} catch (Exception e) {
			throw new RuntimeException("Error finding store by order ID", e);

		} finally {
			em.close();
		}
	}

	@Override
	public List<Commission> findAllComission() {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			TypedQuery<Commission> query = em.createQuery("SELECT c FROM Commission c", Commission.class);
			return query.getResultList();
		} catch (Exception e) {
			throw new RuntimeException("Error finding all commission", e);

		} finally {
			em.close();
		}
	}

	
	
	public Integer findStoreIdByUserId(int userId) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			// Truy vấn storeId từ Cart dựa trên userId
			String jpql = "SELECT c.store._id FROM Cart c WHERE c.user._id = :userId";
			return em.createQuery(jpql, Integer.class).setParameter("userId", userId).getSingleResult();
		} catch (Exception e) {
			throw new RuntimeException("Error finding store ID by user ID: " + userId, e);
		} finally {
			em.close();
		}
	}

	@Override
	public void updateStore(Store store) {

		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(store);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw new RuntimeException("Error updating store", e);
		} finally {
			em.close();
		}
	}

	@Override
	public Commission findComById(int comId) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			return em.find(Commission.class, comId);
		} catch (Exception e) {
			throw new RuntimeException("Error finding order by ID", e);
		} finally {
			em.close();
		}
	}

	@Override
	public void addStore(Store store) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
			em.persist(store);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw new RuntimeException("Error adding store", e);
		} finally {
			em.close();
		}
	}

	
	
	public static void main(String[] args) {
        // Tạo đối tượng StoreDAO để gọi hàm addStore
        IStoreDao storeDAO = new StoreDaoImpl();

        // Tạo một đối tượng Store mới để thêm vào cơ sở dữ liệu
        Store store = new Store();
        store.setName("Tech Store");
        store.setBio("A store specializing in tech products.");
        store.setSlug("tech-store");
        store.setIsActive(true);
        store.setIsOpen(true);
        store.setAvatar("avatar.jpg");
        store.setCover("cover.jpg");
        store.setFeaturedImages(List.of("feature1.jpg", "feature2.jpg"));
        store.setCommissionSold(10.0);
        store.setPoint(100);
        store.setRating(4.5);
        store.setE_wallet(5000.0);
        //store.setCreatedAt(new Date());

        // Tạo một đối tượng User làm chủ sở hữu của Store
        User owner = new User();
        owner.set_id(10); // Giả sử user ID 1 đã tồn tại trong cơ sở dữ liệu
        store.setOwner(owner);

        // Tạo StoreLevel và Commission giả định (phải tồn tại trong cơ sở dữ liệu)
        StoreLevel storeLevel = new StoreLevel();
        storeLevel.set_id(16); // ID đã tồn tại
        store.setStoreLevel(storeLevel);

        Commission commission = new Commission();
        commission.set_id(16); // ID đã tồn tại
        store.setCommission(commission);

        // Gọi phương thức thêm Store
        try {
            storeDAO.addStore(store);
            System.out.println("Store added successfully!");
        } catch (Exception e) {
            System.err.println("Error adding store: " + e.getMessage());
        }
    }
}
