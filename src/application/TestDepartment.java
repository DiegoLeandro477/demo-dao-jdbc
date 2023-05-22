package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

/**
 * @author ferruje
 */
public class TestDepartment {

    public static void main(String[] args) {

        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        Department dep = departmentDao.findById(7);
        System.out.println("=== BUSCANDO DEPARTMENT FOR ID =====");
        System.out.println(dep);
        
        System.out.println("=== DELETANDO DEPARTMENT =====");
        dep.setName("TestComcluido");
        departmentDao.deleteById(dep.getId());
        System.out.println("Done! Department atualizado");

        System.out.println("=== BUSCANDO DEPARTMENT ALL =====");
        departmentDao.findAll().forEach(System.out::println);
//
//        System.out.println("=== INSERINDO DEPARTMENT =====");
//        departmentDao.insert(dep);
//        System.out.println("Done! Department inserido");
//
//        System.out.println("=== ATUALIZANDO DEPARTMENT =====");
//        dep.setName("TestComcluido");
//        departmentDao.update(dep);
//        System.out.println("Done! Department atualizado");

    }

}
