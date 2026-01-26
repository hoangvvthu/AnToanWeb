package ute.shop.dao;

import ute.shop.entity.Review;
import java.util.List;

public interface IReviewDao {
	List<Review> findByProductId(int productId);

	List<Review> findByUserId(int userId);

	boolean addReview(Review review);

	List<Review> getReviewsByOrderAndStore(int orderId, int storeId);
}
