package ute.shop.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class Test {
    public static void main(String[] args) {

        EntityManager enma = JPAConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();

        try {
            // Begin transaction
            trans.begin();

            // Execute a simple query to test the connection
            Query query = enma.createQuery("SELECT 1");
            query.getSingleResult(); // This will execute the query

            // Commit the transaction
            trans.commit();
            System.out.println("Database connection is OK!");
        } catch (Exception e) {
            // Handle exceptions and rollback transaction
            e.printStackTrace();
            if (trans.isActive()) {
                trans.rollback();
            }
            System.out.println("Database connection failed: " + e.getMessage());
        } finally {
            // Close EntityManager
            if (enma != null && enma.isOpen()) {
                enma.close();
            }
        }
    }
}
