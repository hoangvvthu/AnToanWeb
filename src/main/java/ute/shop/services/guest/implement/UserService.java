package ute.shop.services.guest.implement;

import java.util.Date;
import java.util.List;

import ute.shop.dao.guest.implement.UserDAO;
import ute.shop.entity.User;
//import ute.shop.utils.PasswordUtil;

public class UserService {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean registerUser(User user) throws IllegalArgumentException {
        // Kiểm tra nếu email đã tồn tại
        if (userDAO.findUserByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Email đã tồn tại.");
        }

		/*
		 * // Kiểm tra nếu số điện thoại đã tồn tại if
		 * (userDAO.findUserByPhone(user.getPhone()) != null) { throw new
		 * IllegalArgumentException("Số điện thoại đã tồn tại."); }
		 */

        // Gán thời gian tạo mới
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());

		/*
		 * // Mã hóa mật khẩu (nếu cần) String salt = PasswordUtil.generateSalt();
		 * user.setSalt(salt);
		 * user.setHashed_password(PasswordUtil.hashPassword(user.getHashed_password(),
		 * salt));
		 */
        userDAO.saveUser(user);
        return true;
    }
    
    public List<User> searchUsers(String keywords, boolean onlyVerified, int page, int pageSize) {
        return userDAO.findUsersByKeywords(keywords, onlyVerified, page, pageSize);
    }

    public int countUsers(String keywords, boolean onlyVerified) {
        return userDAO.countUsersByKeywords(keywords, onlyVerified);
    }

    public User getUserById(int id) {
        return userDAO.findById(id);
    }
}
