package ute.shop.dao.implement;

import jakarta.persistence.EntityManager;
import ute.shop.config.JPAConfig;
import ute.shop.dao.ICommissionDao;
import ute.shop.entity.Commission;
import ute.shop.entity.Product;

public class CommissionDaoImpl implements ICommissionDao {

	@Override
	public Commission findById(int commissionId) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			return em.find(Commission.class, commissionId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
		}
	}

}
