package ute.shop.services;

import java.util.List;

import ute.shop.entity.Delivery;

public interface IDeliveryService {

	List<Delivery> getAllDeliveries();

	Delivery findById(int deliveryId);

}
