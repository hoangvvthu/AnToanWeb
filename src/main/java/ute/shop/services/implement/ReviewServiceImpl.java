package ute.shop.services.implement;

import ute.shop.dao.IReviewDao;
import ute.shop.dao.implement.ReviewDaoImpl;
import ute.shop.entity.Review;
import ute.shop.services.IReviewService;

import java.util.List;

public class ReviewServiceImpl implements IReviewService {

	private final IReviewDao reviewDao = new ReviewDaoImpl();

	@Override
	public List<Review> getReviewsByProduct(int productId) {
		try {
			return reviewDao.findByProductId(productId);
		} catch (Exception e) {
			throw new RuntimeException("Error fetching reviews for product ID: " + productId, e);
		}
	}

	@Override
	public List<Review> getReviewsByOrderAndStore(int orderId, int storeId) {
		return reviewDao.getReviewsByOrderAndStore(orderId, storeId);
	}

	@Override
	public boolean addReview(Review review) {
		return reviewDao.addReview(review);
	}

}
