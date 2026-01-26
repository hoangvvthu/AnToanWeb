package ute.shop.dao.implement;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import ute.shop.config.JPAConfig;
import ute.shop.dao.ITransactionDao;

public class TransactionDaoImpl implements ITransactionDao {

	@Override
	public double caculatedTotalSale() {
	    EntityManager em = JPAConfig.getEntityManager();
	    try {
	        TypedQuery<Double> query = em.createQuery(
	                "SELECT SUM(t.amount) FROM Transaction t WHERE t.isUp = true", 
	                Double.class);
	        return query.getSingleResult() != null ? query.getSingleResult() : 0.0;
	    } catch (Exception e) {
	        throw new RuntimeException("Error at transaction dao", e);
	    } finally {
	        em.close();
	    }
	}


}
