package ute.shop.services.implement;

import java.util.List;

import ute.shop.dao.IUserFollowProductDao;
import ute.shop.dao.implement.UserFollowProductDao;
import ute.shop.entity.UserFollowProduct;
import ute.shop.services.IUserFollowProductService;

public class UserFollowProductServiceImpl implements IUserFollowProductService {

	IUserFollowProductDao userFollowProductDao = new UserFollowProductDao();

	@Override
	public List<UserFollowProduct> getFollowedProductsByUserId(int userId) {
		return userFollowProductDao.findByUserId(userId);
	}

	@Override
	public void followProduct(UserFollowProduct userFollowProduct) {
		userFollowProductDao.save(userFollowProduct);
	}

	@Override
	public void unfollowProduct(UserFollowProduct userFollowProduct) {
		userFollowProductDao.delete(userFollowProduct);
	}

	@Override
	public boolean isProductFollowedByUser(int userId, int productId) {
		return userFollowProductDao.isProductFollowedByUser(userId, productId);
	}

}
