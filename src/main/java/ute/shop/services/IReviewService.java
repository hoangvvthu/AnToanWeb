package ute.shop.services;

import ute.shop.entity.Review;

import java.util.List;

public interface IReviewService {
	List<Review> getReviewsByProduct(int productId);

	boolean addReview(Review review);

	List<Review> getReviewsByOrderAndStore(int orderId, int storeId);
}
