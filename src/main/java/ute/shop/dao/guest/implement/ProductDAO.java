package ute.shop.dao.guest.implement;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import ute.shop.entity.Category;
import ute.shop.entity.Product;

public class ProductDAO {
    private EntityManager em;

    public ProductDAO(EntityManager em) {
        this.em = em;
    }
    
    public Product findById(int id) {
        return em.find(Product.class, id);
    }


    public List<Product> searchProducts(String keywords, Integer categoryId, Double minPrice, Double maxPrice, int page, int pageSize) {
        StringBuilder queryStr = new StringBuilder("SELECT p FROM Product p WHERE p.isSelling = true");

        if (keywords != null && !keywords.isEmpty()) {
            queryStr.append(" AND p.name LIKE :keywords");
        }
        if (categoryId != null) {
            queryStr.append(" AND p.category._id = :categoryId");
        }
        if (minPrice != null) {
            queryStr.append(" AND p.price >= :minPrice");
        }
        if (maxPrice != null) {
            queryStr.append(" AND p.price <= :maxPrice");
        }

        TypedQuery<Product> query = em.createQuery(queryStr.toString(), Product.class);

        if (keywords != null && !keywords.isEmpty()) {
            query.setParameter("keywords", "%" + keywords + "%");
        }
        if (categoryId != null) {
            query.setParameter("categoryId", categoryId);
        }
        if (minPrice != null) {
            query.setParameter("minPrice", minPrice);
        }
        if (maxPrice != null) {
            query.setParameter("maxPrice", maxPrice);
        }

        query.setFirstResult((page - 1) * pageSize);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    public int countSearchResults(String keywords, Integer categoryId, Double minPrice, Double maxPrice) {
        StringBuilder queryStr = new StringBuilder("SELECT COUNT(p) FROM Product p WHERE p.isSelling = true");

        if (keywords != null && !keywords.isEmpty()) {
            queryStr.append(" AND p.name LIKE :keywords");
        }
        if (categoryId != null) {
            queryStr.append(" AND p.category._id = :categoryId");
        }
        if (minPrice != null) {
            queryStr.append(" AND p.price >= :minPrice");
        }
        if (maxPrice != null) {
            queryStr.append(" AND p.price <= :maxPrice");
        }

        TypedQuery<Long> query = em.createQuery(queryStr.toString(), Long.class);

        if (keywords != null && !keywords.isEmpty()) {
            query.setParameter("keywords", "%" + keywords + "%");
        }
        if (categoryId != null) {
            query.setParameter("categoryId", categoryId);
        }
        if (minPrice != null) {
            query.setParameter("minPrice", minPrice);
        }
        if (maxPrice != null) {
            query.setParameter("maxPrice", maxPrice);
        }

        return query.getSingleResult().intValue();
    }
    
    public List<Product> getProductsBySales(int limit) {
        String jpql = "SELECT p FROM Product p WHERE p.sold >= 10 ORDER BY p.sold DESC";
        return em.createQuery(jpql, Product.class)
                 .setMaxResults(limit)
                 .getResultList();
    }
    
    public List<Product> getProductsByCategory(int categoryId) {
        TypedQuery<Product> query = em.createQuery(
            "SELECT p FROM Product p WHERE p.category._id = :categoryId",
            Product.class
        );
        query.setParameter("categoryId", categoryId);
        return query.getResultList();
    }

    public List<Product> getProductsSortedByPriceAsc() {
        return em.createQuery("SELECT p FROM Product p ORDER BY p.price ASC", Product.class)
                 .getResultList();
    }

    public List<Product> getProductsSortedByPriceDesc() {
        return em.createQuery("SELECT p FROM Product p ORDER BY p.price DESC", Product.class)
                 .getResultList();
    }

    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        return em.createQuery("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice", Product.class)
                 .setParameter("minPrice", minPrice)
                 .setParameter("maxPrice", maxPrice)
                 .getResultList();
    }

   
}

