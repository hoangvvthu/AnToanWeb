package ute.shop.dao.implement;

import java.util.List;

import jakarta.persistence.EntityManager;
import ute.shop.config.JPAConfig;
import ute.shop.dao.IDeliveryDao;
import ute.shop.entity.Delivery;

public class DeliveryDaoImpl implements IDeliveryDao {

	@Override
	public Delivery findById(int deliveryId) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			return em.find(Delivery.class, deliveryId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	@Override
	public List<Delivery> findAll() {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			// Sử dụng JPQL để lấy tất cả các bản ghi từ bảng Delivery
			return em.createQuery("SELECT d FROM Delivery d WHERE d.isDeleted = false", Delivery.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

}
