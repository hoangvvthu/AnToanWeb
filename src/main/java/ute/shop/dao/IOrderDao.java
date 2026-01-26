package ute.shop.dao;

import java.util.List;

import ute.shop.entity.Order;
import ute.shop.entity.OrderItem;
import ute.shop.entity.OrderStatus;

public interface IOrderDao {

	List<Order> getAllOrdersByUser(int userId);

	boolean updateOrderStatus(int orderId, OrderStatus status);

	boolean cancelOrder(int orderId);

	Order findById(int orderId);

	boolean placeOrder(Order order);

	boolean makePayment(int orderId);

	void saveOrderItem(OrderItem orderItem);

	Order save(Order order);
	
	long countTotalOrders();
	
	//admin
	List<Order> findLatestOrders();

}
