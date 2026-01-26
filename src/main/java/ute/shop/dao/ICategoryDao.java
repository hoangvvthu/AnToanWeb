package ute.shop.dao;

import java.util.List;

import ute.shop.entity.Category;

public interface ICategoryDao {
	List<Category> findAll();
	Category findById(int id);
}
