package ute.shop.dao.guest.implement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import ute.shop.entity.Category;

import java.util.List;

public class CategoryDAO {
    private final EntityManager em;

    public CategoryDAO(EntityManager em) {
        this.em = em;
    }

    // Phương thức tìm tất cả danh mục
    public List<Category> findAll() {
        return em.createQuery("SELECT c FROM Category c WHERE c.isDeleted = false", Category.class)
                 .getResultList();
    }
    
    public List<Category> findAllWithProducts() {
        String query = "SELECT DISTINCT c FROM Category c LEFT JOIN FETCH c.products WHERE c.isDeleted = false";
        return em.createQuery(query, Category.class).getResultList();
    }
    
    public List<Category> getRandomCategories(int limit) {
        TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c ORDER BY function('RAND')", Category.class);
        query.setMaxResults(limit);
        return query.getResultList();
    }
    
    public Category findById(Long id) {
        return em.find(Category.class, id);
    }

}
