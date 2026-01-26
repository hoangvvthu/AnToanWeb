package ute.shop.services;

import java.util.List;

import ute.shop.entity.Category;

public interface ICategoryService {
	List<Category> findAll();
	Category findById(int id);
}
