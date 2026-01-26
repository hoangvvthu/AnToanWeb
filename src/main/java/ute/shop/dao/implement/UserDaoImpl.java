package ute.shop.dao.implement;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import ute.shop.config.JPAConfig;
import ute.shop.dao.IUserDao;
import ute.shop.entity.Product;
import ute.shop.entity.User;

public class UserDaoImpl implements IUserDao {

    @Override
    public void insert(User user) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(user);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw new RuntimeException("Error inserting user", e);
        } finally {
            em.close();
        }
    }

    @Override
    public void update(User user) {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(user);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw new RuntimeException("Error updating user", e);
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(int userId) throws Exception {
        EntityManager em = JPAConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            User user = em.find(User.class, userId);
            if (user != null) {
                em.remove(user);
            } else {
                throw new Exception("User not found for deletion");
            }
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            throw new RuntimeException("Error deleting user", e);
        } finally {
            em.close();
        }
    }

    @Override
    public List<User> findAll() {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding all users", e);
        } finally {
            em.close();
        }
    }

    @Override
    public User findByEmail(String email) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (jakarta.persistence.NoResultException e) {
            return null; // Không tìm thấy email, trả về null
        } catch (Exception e) {
            throw new RuntimeException("Error finding user by email", e);
        } finally {
            em.close();
        }
    }

    @Override
    public User findById(int userId) {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            return em.find(User.class, userId);
        } catch (Exception e) {
            throw new RuntimeException("Error finding user by ID", e);
        } finally {
            em.close();
        }
    }
    @Override
    public List<Object[]> findTopUser() {
        EntityManager em = JPAConfig.getEntityManager();
        try {
            // JPQL truy vấn thông tin người dùng có nhiều đơn hàng nhất và amountFromUser từ bảng Order
        	String jpql = "SELECT u.firstname, COUNT(o.user._id), SUM(o.amountFromUser) " + 
                    "FROM Order o " +
                    "JOIN o.user u " +
                    "GROUP BY o.user._id, u.firstname " + 
                    "ORDER BY COUNT(o.user._id) DESC";
            
            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            query.setMaxResults(7); // Chỉ lấy người dùng có số lượng đơn hàng nhiều nhất
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding top user", e);
        } finally {
            em.close();
        }
    }
    
    
    
    
    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl();

        try {
            List<Object[]> topUsers = userDao.findTopUser();

            // Kiểm tra nếu danh sách trả về không rỗng
            if (topUsers.isEmpty()) {
                System.out.println("No top users found.");
            } else {
                // Hiển thị kết quả
                System.out.println("Top Users:");
                for (Object[] user : topUsers) {
                    String userName = (String) user[0];
                    Long orderCount = (Long) user[1];
                    Number amountFromUser = (Number) user[2];
                    
                    System.out.println("User: " + userName);
                    System.out.println("Order Count: " + orderCount);
                    System.out.println("Amount from User: " + amountFromUser);
                    System.out.println("------------------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occurred while fetching top users: " + e.getMessage());
        }
    }
    
    
}
