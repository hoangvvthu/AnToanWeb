package ute.shop.dao.guest.implement;


import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import ute.shop.entity.Store;

public class StoreDAO {
    private final EntityManager em;

    public StoreDAO(EntityManager em) {
        this.em = em;
    }

    // Tìm kiếm cửa hàng theo từ khóa
    public List<Store> searchStores(String keywords, int page, int pageSize) {
        String query = "SELECT s FROM Store s WHERE LOWER(s.name) LIKE :keywords AND s.isActive = true";
        return em.createQuery(query, Store.class)
                .setParameter("keywords", "%" + keywords.toLowerCase() + "%")
                .setFirstResult((page - 1) * pageSize)
                .setMaxResults(pageSize)
                .getResultList();
    }

    // Đếm tổng số cửa hàng phù hợp với từ khóa
    public long countSearchResults(String keywords) {
        String query = "SELECT COUNT(s) FROM Store s WHERE LOWER(s.name) LIKE :keywords AND s.isActive = true";
        return em.createQuery(query, Long.class)
                .setParameter("keywords", "%" + keywords.toLowerCase() + "%")
                .getSingleResult();
    }

    // Lấy chi tiết cửa hàng theo ID
    public Store findStoreById(int id) {
        return em.find(Store.class, id);
    }
}
