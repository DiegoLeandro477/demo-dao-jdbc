package application;

import static java.lang.System.out;
import java.util.Date;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

/**
 * @author ferruje
 */
public class Program {

    public static void main(String[] args) {
        
        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println("=== TEST 1: seller findById =====");
        Seller seller = sellerDao.findById(3);
        
        System.out.println(seller);
        
    }
    
}
