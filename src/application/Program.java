package application;

import java.util.Date;
import java.util.List;
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
        
        System.out.println("=== TEST 2: seller findByDepartment =====");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(department);
        list.forEach(System.out::println);
        
        System.out.println("=== TEST 3: seller findAll =====");
        list = sellerDao.findAll();
        list.forEach(System.out::println);
        
        System.out.println("=== TEST 4: seller insert =====");
        Seller newSeller = new Seller(null, "Diego", "diego@gmail.com", new Date(), 6000.0, department);
        sellerDao.insert(newSeller);
        System.out.println("Novo dado colocado no banco de dados: \n" + sellerDao.findById(newSeller.getId()));
        
        System.out.println("=== TEST 5: seller update =====");
        seller = sellerDao.findById(newSeller.getId());
        seller.setName("MARTINHA WAINE");
        sellerDao.update(seller);
        System.out.println("Novo dado colocado no banco de dados: \n" + sellerDao.findById(seller.getId()));
        
        System.out.println("=== TEST 6: seller delete =====");
        sellerDao.deleteById(newSeller.getId());
        System.out.println("Seller apagado: "+ seller.getId());
        
        
    }
    
}
