package ute.shop.dao;

import java.util.List;

import ute.shop.entity.UserFollowProduct;

public interface IUserFollowProductDao {

	UserFollowProduct findById(int followProductId);

	List<UserFollowProduct> findAll();

	List<UserFollowProduct> findByUserId(int userId);

	void save(UserFollowProduct userFollowProduct);

	void delete(UserFollowProduct userFollowProduct);

	boolean isProductFollowedByUser(int userId, int productId);

}
