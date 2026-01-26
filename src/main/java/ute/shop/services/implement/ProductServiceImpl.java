package ute.shop.services.implement;

import java.util.List;

import ute.shop.dao.IProductDao;
import ute.shop.dao.implement.ProductDaoImpl;
import ute.shop.entity.Product;
import ute.shop.services.IProductService;

public class ProductServiceImpl implements IProductService {

	private final IProductDao productDao = new ProductDaoImpl();

	@Override
	public Product findProductById(int productId) {
		// Lấy thông tin sản phẩm từ tầng DAO
		return productDao.findById(productId);
	}

	@Override
	public boolean isStockAvailable(int productId, int count) {
		// Kiểm tra tồn kho
		Product product = productDao.findById(productId);
		if (product == null) {
			return false;
		}
		return product.getQuantity() >= count;
	}

	@Override
	public Product findById(int productId) {
		return findProductById(productId);
	}

	@Override
	public List<Product> findAll() {
		return productDao.findAll();
	}

	@Override
	public List<Product> findByStoreId(int storeId) {
		return productDao.findByStoreId(storeId);
	}

	public List<Product> findTopSelling() {
		return productDao.findTopSelling();
	}

	@Override
	public void updateProduct(Product product) {
		productDao.updateProduct(product);
	}

	@Override
	public void deleteProduct(int id) {
		productDao.deleteProduct(id);
	}

	@Override
	public void addProduct(Product product) {
		productDao.addProduct(product);	
	}
}
