package ute.shop.dao;

import java.util.List;

import ute.shop.entity.User;

public interface IUserDao {
	void insert(User user);
	
	void update(User user);
	
	void delete(int user_id) throws Exception;
	
	List<User> findAll();
	
	User findByEmail(String email);
	
	User findById(int userId);
	
	//admin
	List<Object[]> findTopUser();
}
