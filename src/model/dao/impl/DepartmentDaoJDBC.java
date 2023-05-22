package model.dao.impl;

import model.dao.DepartmentDao;
import db.DB;
import db.DbException;
import java.sql.Connection;
import java.util.List;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.entities.Department;

/**
 * @author ferruje
 */
public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {
        String sql = "INSERT INTO department "
                + "(Name) "
                + "VALUES "
                + "(?)";
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, obj.getName());
            
            int i = st.executeUpdate();
            
            if (i > 0){
                rs = st.getGeneratedKeys();
                if (rs.next()){
                    obj.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.close(st, rs);
        }
    }

    @Override
    public void update(Department obj) {
        PreparedStatement st = null;
        String sql = "UPDATE department "
                + "SET Name = ? "
                + "WHERE Id = ?";
        
        try {
            st = conn.prepareStatement(sql);
            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());
            
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.close(st, null);
        }
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM department WHERE Id = ?";
        PreparedStatement st = null;
        
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.close(st, null);
        }
    }

    @Override
    public Department findById(Integer id) {

        String sql = "SELECT * FROM department WHERE Id = ?";
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, id);

            rs = st.executeQuery();

            if (rs.next()) {
                return getDepartment(rs);
            }

            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.close(st, rs);
        }
    }

    @Override
    public List<Department> findAll() {

        String sql = "SELECT * FROM department";
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Department> list = new ArrayList<>();
        try {
            st = conn.prepareStatement(sql);

            rs = st.executeQuery();

            while (rs.next()) {
                list.add(getDepartment(rs));
            }

            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.close(st, rs);
        }

    }

    private Department getDepartment(ResultSet rs) throws SQLException {
        return new Department(rs.getInt("Id"), rs.getString("Name"));
    }

}
