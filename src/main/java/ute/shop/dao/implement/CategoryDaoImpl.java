package ute.shop.dao.implement;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import ute.shop.config.JPAConfig;
import ute.shop.dao.ICategoryDao;
import ute.shop.entity.Category;
import ute.shop.entity.User;

public class CategoryDaoImpl implements ICategoryDao{

	@Override
	public List<Category> findAll() {
		EntityManager em = JPAConfig.getEntityManager();
        try {
            TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c", Category.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding all category", e);
        } finally {
            em.close();
        }
	}
	
	@Override
	public Category findById(int id) {
		EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.find(Category.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Error finding user by ID", e);
        } finally {
            em.close();
        }
	}

}
