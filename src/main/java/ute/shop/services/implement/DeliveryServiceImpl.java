package ute.shop.services.implement;

import ute.shop.services.IDeliveryService;

import java.util.List;

import ute.shop.dao.IDeliveryDao;
import ute.shop.dao.implement.DeliveryDaoImpl;
import ute.shop.entity.Delivery;

public class DeliveryServiceImpl implements IDeliveryService {

	IDeliveryDao deliveryDao = new DeliveryDaoImpl();

	@Override
	public List<Delivery> getAllDeliveries() {
		return deliveryDao.findAll();
	}

	@Override
	public Delivery findById(int deliveryId) {
		try {
			return deliveryDao.findById(deliveryId); // Assuming the `DeliveryDaoImpl` class has a method `findById(int
														// deliveryId)`
		} catch (Exception e) {
			throw new RuntimeException("Error finding delivery by ID: " + deliveryId, e);
		}
	}
}
