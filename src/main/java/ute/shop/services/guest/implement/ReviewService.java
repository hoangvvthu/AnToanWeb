package ute.shop.services.guest.implement;

import java.util.List;

import ute.shop.dao.guest.implement.ReviewDAO;
import ute.shop.entity.Product;
import ute.shop.entity.Review;

public class ReviewService {
    private ReviewDAO reviewDAO;

    public ReviewService(ReviewDAO reviewDAO) {
        this.reviewDAO = reviewDAO;
    }

    public List<Review> findByProduct(Product product) {
        return reviewDAO.findByProduct(product);
    }
}