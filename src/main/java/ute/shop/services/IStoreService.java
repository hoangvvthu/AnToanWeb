package ute.shop.services;


import ute.shop.entity.Commission;
import ute.shop.entity.Store;
import java.util.List;

public interface IStoreService {

	Store findById(int storeId);

	Store getStoreByOrder(int orderId);
	List<Store> findAll();
	
	List<Commission> findAllComission();
	
	Commission findComById(int comId);
	
	void updateStore(Store store);
	
	void addStore(Store store);

	Integer getStoreIdByUserId(int user_id);
}


