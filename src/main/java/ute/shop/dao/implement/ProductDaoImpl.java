package ute.shop.dao.implement;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import ute.shop.config.JPAConfig;
import ute.shop.dao.IProductDao;
import ute.shop.entity.Category;
import ute.shop.entity.Product;
import ute.shop.entity.Store;
import ute.shop.services.ICategoryService;
import ute.shop.services.IStoreService;
import ute.shop.services.implement.CategoryServiceImpl;
import ute.shop.services.implement.StoreServiceImpl;

public class ProductDaoImpl implements IProductDao {

	@Override
	public Product findById(int productId) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			return em.find(Product.class, productId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

	// find all product
	@Override
	public List<Product> findAll() {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class);
			return query.getResultList();
		} catch (Exception e) {
			throw new RuntimeException("Error finding all product", e);
		} finally {
			em.close();
		}
	}

	@Override
	public List<Product> findByStoreId(int storeId) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			String query = "SELECT p FROM Product p WHERE p.store._id = :storeId";
			return em.createQuery(query, Product.class).setParameter("storeId", storeId).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Product> findTopSelling() {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p ORDER BY p.sold DESC", Product.class);
			query.setMaxResults(7);
			return query.getResultList();
		} catch (Exception e) {
			throw new RuntimeException("Error finding top selling product", e);
		} finally {
			em.close();
		}
	}

	@Override
	public void updateProduct(Product product) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(product);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw new RuntimeException("Error updating product", e);
		} finally {
			em.close();
		}
	}

	@Override
	public void deleteProduct(int id) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();

			Product product = em.find(Product.class, id);

			if (product != null) {
				product.setIsActive(false);
				em.merge(product);
			}
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw new RuntimeException("Error deleting product", e);
		} finally {
			em.close();
		}
	}

	@Override
	public void addProduct(Product product) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();

		try {
			trans.begin();
				em.persist(product);
			trans.commit();
		} catch (Exception e) {
			trans.rollback();
			throw new RuntimeException("Error adding product", e);
		} finally {
			em.close();
		}
	}
	
	public static void main(String[] args) {
        
		IProductDao producDao = new ProductDaoImpl();
		
        // Tạo Product giả định để thêm vào cơ sở dữ liệu
        Product product = new Product();
        product.setName("Sample Product");
        product.setSlug("sample-product");
        product.setDescription("This is a sample product for testing.");
        product.setPrice(99.99);
        product.setPromotionalPrice(79.99);
        product.setQuantity(100);
        product.setIsActive(true);
        product.setIsSelling(true);
        product.setRating(4);

        // Tạo đối tượng Category và Store giả định
        ICategoryService categoryService = new CategoryServiceImpl();
        Category category = categoryService.findById(11); // Giả sử có category với ID = 1
        if (category != null) {
            product.setCategory(category);
        }

      
        IStoreService storeService = new StoreServiceImpl();
        Store store = storeService.findById(41); // Giả sử có store với ID = 1
        if (store != null) {
            product.setStore(store);
        }

        // Gọi phương thức addProduct() để thêm sản phẩm
        try {
        	producDao.addProduct(product); // Bạn có thể thay đổi cách gọi phương thức này nếu cần.
            System.out.println("Product added successfully!");
        } catch (Exception e) {
            System.out.println("Error adding product: " + e.getMessage());
        }
    }

}