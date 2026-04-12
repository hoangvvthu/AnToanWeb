package ute.shop.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAConfig {
	// Tạo 1 biến static final để lưu chung 1 EntityManagerFactory cho toàn ứng dụng
	private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("dataSource");

	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
}
