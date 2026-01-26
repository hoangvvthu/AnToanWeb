package ute.shop.services;


import java.util.List;

import ute.shop.entity.Product;

public interface IProductService {


	// Tìm sản phẩm theo ID
	Product findProductById(int productId);

	// Kiểm tra xem sản phẩm có đủ tồn kho không
	boolean isStockAvailable(int productId, int count);

	Product findById(int productId);

	//admin tim tan ca san pham
	List<Product> findAll();
	
	//admin tim san pham ban chay nhat
	List<Product> findTopSelling();
	
	//admin update product
	void updateProduct(Product product);
	void deleteProduct(int id);
	void addProduct(Product product);

	List<Product> findByStoreId(int storeId);

}
