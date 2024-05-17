package Koneksi;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import com.mysql.cj.jdbc.MysqlDataSource;

public class Connector {
    static Connection con;

    public static Connection getConnection() {
                if (con == null) {
            MysqlDataSource ds = new MysqlDataSource();
            ds.setUser("root");
            ds.setPassword("");
            ds.setDatabaseName("movie_db");
            try {
                con = ds.getConnection();
            } catch(SQLException ex) {
                ex.printStackTrace();
                System.out.println("Koneksi Connection error");
            }
        }
        return con;
    }

}
