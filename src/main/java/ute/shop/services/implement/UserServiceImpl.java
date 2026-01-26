package ute.shop.services.implement;

import java.util.List;

import ute.shop.utils.BCryptUtils;
import ute.shop.dao.IUserDao;
import ute.shop.dao.implement.UserDaoImpl;
import ute.shop.entity.User;
import ute.shop.services.IUserService;

public class UserServiceImpl implements IUserService {

	IUserDao userDao = new UserDaoImpl();

	@Override
	public User login(String email, String password) {
		try {
			User user = userDao.findByEmail(email);
			if (user == null) {
				throw new RuntimeException("Email does not exist");
			}
			if (!user.getPassword().equals(password)) {
				throw new RuntimeException("Invalid password");
			}
			return user; // Successful login
		} catch (Exception e) {
			throw new RuntimeException("Error during login", e);
		}
	}

	@Override
	public void update(User user) {
		// Update the user information in the database
		userDao.update(user);

	}

	@Override
	public void delete(int userId) throws Exception {
		// Delete the user by ID
		userDao.delete(userId);

	}

	@Override
	public List<User> findAll() {
		// Return all users from the database
		return userDao.findAll();
	}

	@Override
	public User findByEmail(String email) {
		// Find and return a user by email
		return userDao.findByEmail(email);
	}

	@Override
	public User findById(int userId) {
		// Find and return a user by ID
		return userDao.findById(userId);
	}

	@Override
	public List<Object[]> findTopUser() {
		
		return userDao.findTopUser();
	}
/*
	@Override
	public User login1(String email, String rawPassword) {
		try {
			User user = userDao.findByEmail(email);
			if (user == null) {
				throw new RuntimeException("Email does not exist");
			}
			if (!BCryptUtils.checkPassword(rawPassword, user.getPassword())) {
				throw new RuntimeException("Invalid password");
			}
			return user; // Successful login
		} catch (Exception e) {
			throw new RuntimeException("Error during login", e);
		}
	}
*/
}
