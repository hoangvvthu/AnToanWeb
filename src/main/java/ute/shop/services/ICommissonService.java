package ute.shop.services;

import ute.shop.entity.Commission;

public interface ICommissonService {

	int findCommissionIdByStoreId(int storeId);

	Commission findCommissionByStore(int storeId);

}
