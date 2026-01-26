package ute.shop.services;

import java.util.List;

import ute.shop.entity.User;

public interface IUserService {
	// Login user with email and password
	User login(String email, String password);
	/*
	 User login1(String email, String rawPassword);
	  */

	// Update existing user
	void update(User user);

	// Delete a user by their ID
	void delete(int userId) throws Exception;

	// Get all users
	List<User> findAll();

	// Find a user by their email
	User findByEmail(String email);

	// Find a user by their ID
	User findById(int userId);
	
	//find top user
	List<Object[]> findTopUser();
}
