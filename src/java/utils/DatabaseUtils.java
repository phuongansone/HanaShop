package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Database Utilities
 * @author andtpse62827
 */
public class DatabaseUtils {
    /** Database URL */
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hanashop";
    
    /** Username */
    private static final String USERNAME = "root";
    
    /** Password */
    private static final String PASSWORD = "andtpp";
    
    /** Driver class */
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    
    /**
     * Create connection to database
     * @return connection
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static Connection makeConnection() 
            throws ClassNotFoundException, SQLException {
        Connection conn;
        
        Class.forName(DRIVER_CLASS);
        conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        
        return conn;
    }
    
    /**
     * Close connection to database
     * @param conn connection
     * @param ps prepared statement
     * @param rs result set
     * @throws SQLException 
     */
    public static void closeConnection(Connection conn, PreparedStatement ps, 
            ResultSet rs) throws SQLException {
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
        
        if (ps != null && !ps.isClosed()) {
            ps.close();
        }
        
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }
}
