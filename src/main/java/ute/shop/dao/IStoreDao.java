package ute.shop.dao;

import java.util.List;

import ute.shop.entity.Commission;
import ute.shop.entity.Store;

public interface IStoreDao {
	Store findById(int storeId);

	Store findByOrderId(int orderId);
	
	List<Store> findAll();

	
	List<Commission> findAllComission();
	
	void updateStore(Store store);
	Commission findComById(int comId);
	
	void addStore(Store store);

	Integer findStoreIdByUserId(int user_id);
}
