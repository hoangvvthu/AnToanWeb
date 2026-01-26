package ute.shop.services;

import ute.shop.entity.UserFollowProduct;

import java.util.List;

public interface IUserFollowProductService {

	// Lấy danh sách sản phẩm mà người dùng theo dõi
	List<UserFollowProduct> getFollowedProductsByUserId(int userId);

	// Lưu theo dõi sản phẩm mới
	void followProduct(UserFollowProduct userFollowProduct);

	// Xóa theo dõi sản phẩm
	void unfollowProduct(UserFollowProduct userFollowProduct);

	boolean isProductFollowedByUser(int userId, int productId); // Thêm phương thức kiểm tra

}
