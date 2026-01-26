package ute.shop.services.implement;

import java.util.List;

import ute.shop.dao.ICategoryDao;
import ute.shop.dao.implement.CategoryDaoImpl;
import ute.shop.entity.Category;
import ute.shop.services.ICategoryService;

public class CategoryServiceImpl implements ICategoryService {
	ICategoryDao cateDao = new CategoryDaoImpl();
	
	@Override
	public List<Category> findAll() {
		return cateDao.findAll();
	}

	@Override
	public Category findById(int id) {
		return cateDao.findById(id);
	}
	
}
