package ute.shop.services.implement;
import ute.shop.dao.ITransactionDao;
import ute.shop.dao.implement.TransactionDaoImpl;
import ute.shop.services.ITransactionService;

public class TransactionServiceImpl implements ITransactionService{
	
	ITransactionDao tranDao = new TransactionDaoImpl();
	@Override
	public double caculatedTotalSale() {
       
		
        return tranDao.caculatedTotalSale();
	}
	
	
	//Kiem tra ham tinh totalSales
	public static void main(String[] args) {
        // Tạo đối tượng của Service
        ITransactionService service = new TransactionServiceImpl();

        // Kiểm tra với các khoảng thời gian khác nhau
       
        double totalSale = service.caculatedTotalSale();

        // In kết quả ra console
        System.out.println("Tổng doanh thu: " + totalSale);
       
    }

}
