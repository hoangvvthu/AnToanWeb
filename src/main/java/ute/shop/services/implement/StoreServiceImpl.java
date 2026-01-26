package ute.shop.services.implement;

import java.util.List;

import ute.shop.dao.IStoreDao;
import ute.shop.dao.implement.StoreDaoImpl;
import ute.shop.entity.Commission;
import ute.shop.entity.Store;
import ute.shop.services.IStoreService;

public class StoreServiceImpl implements IStoreService {

	IStoreDao storeDao = new StoreDaoImpl();

	@Override
	public Store findById(int storeId) {
		try {
			return storeDao.findById(storeId);
		} catch (Exception e) {
			throw new RuntimeException("Error finding order by ID: " + storeId, e);
		}
	}

	@Override
	public Store getStoreByOrder(int orderId) {
		try {
			return storeDao.findByOrderId(orderId);
		} catch (Exception e) {
			throw new RuntimeException("Error finding store by order ID: " + orderId, e);
		}
	}

	@Override
	public List<Store> findAll() {
		return storeDao.findAll();
	}

	@Override
	public Integer getStoreIdByUserId(int userId) {
		try {
			return storeDao.findStoreIdByUserId(userId);
		} catch (Exception e) {
			throw new RuntimeException("Error finding store ID by user ID: " + userId, e);
		}
	}
	
	

	@Override
	public List<Commission> findAllComission() {
		return storeDao.findAllComission();
	}

	@Override
	public void updateStore(Store store) {
		storeDao.updateStore(store);
		
	}

	@Override
	public Commission findComById(int comId) {
		return storeDao.findComById(comId);
	}

	@Override
	public void addStore(Store store) {
		storeDao.addStore(store);
		
	}
	

}
