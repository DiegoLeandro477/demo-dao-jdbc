package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ferruje
 *
 */
public class DB {

    private static Connection conn = null;

    // Estabelecendo conexão com o banco de dados postgresSQL
    public static Connection getConnection() {
        if (conn == null) {
            try {
                // Instancia um properties pegando os dados do arquivo db.properties
                Properties props = loadProperties();
                String url = props.getProperty("dburl");    // Separa a url do properties
                conn = DriverManager.getConnection(url, props);   // Instancia a conexão com o BD
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    // Lendo o arquivo properties para retornar os dados necessários para conexão ao BD
    private static Properties loadProperties() {
        // Lendo arquivo em src/bd.properties
        try (FileInputStream fs = new FileInputStream("src/bd.properties")) {
            Properties props = new Properties();    // Instanciando uma nova properties vazia
            props.load(fs);     // lendo o arquivo com o objeto
            return props;
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }

    // Desconectando com o BD
    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    public static void close(AutoCloseable obj1, AutoCloseable obj2) {
        try {
            if (obj1 != null) {
                obj1.close();
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } catch (Exception ex) {
            throw new DbException(ex.getMessage());
        }
        try {
            if (obj2 != null) {
                obj2.close();
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } catch (Exception ex) {
            throw new DbException(ex.getMessage());
        }

    }
}
