package ute.shop.dao;

import java.util.List;

import ute.shop.entity.Delivery;

public interface IDeliveryDao {

	Delivery findById(int deliveryId);

	List<Delivery> findAll();

}
