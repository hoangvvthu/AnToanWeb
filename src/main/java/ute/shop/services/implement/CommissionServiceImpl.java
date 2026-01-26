package ute.shop.services.implement;

import ute.shop.dao.IStoreDao;
import ute.shop.dao.implement.StoreDaoImpl;
import ute.shop.entity.Commission;
import ute.shop.entity.Store;
import ute.shop.services.ICommissonService;

public class CommissionServiceImpl implements ICommissonService {

	IStoreDao storeDao = new StoreDaoImpl();

	@Override
	public int findCommissionIdByStoreId(int storeId) {
		Store store = storeDao.findById(storeId);
		return store.getCommission().get_id();
	}

	@Override
	public Commission findCommissionByStore(int storeId) {
		// Fetch the store by storeId
		Store store = storeDao.findById(storeId);

		if (store == null) {
			throw new IllegalArgumentException("Store not found for the provided ID: " + storeId);
		}

		// Return the commission associated with the store
		return store.getCommission();
	}
}
