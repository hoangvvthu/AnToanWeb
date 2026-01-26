package ute.shop.services.guest.implement;

import java.util.List;

import ute.shop.dao.guest.implement.CategoryDAO;
import ute.shop.entity.Category;

public class CategoryService {
    private CategoryDAO categoryDAO;

    public CategoryService(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public List<Category> getAllCategoriesWithProducts() {
        return categoryDAO.findAllWithProducts();
    }
    
    public List<Category> getRandomCategories(int limit) {
        return categoryDAO.getRandomCategories(limit);
    }

}