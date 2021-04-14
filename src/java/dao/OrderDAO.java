package dao;

import constants.RequestParameter.OrderParam;
import dto.OrderDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import utils.DatabaseUtils;

/**
 *
 * @author andtpse62827
 */
public class OrderDAO implements Serializable {
    /** Insert order sql */
    private static final String INSERT_ORDER = "INSERT INTO orders (userId, totalPrice, phone, email, "
            + "address, username, paymentMethod) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    /**
     * Insert order
     * @param order
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public int insertOrder(OrderDTO order) 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        int insertedId = -1;
        
        try {
            conn = DatabaseUtils.makeConnection();
            if (conn != null) {
                ps = conn.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
                
                ps.setInt(1, order.getUserId());
                ps.setInt(2, order.getTotalPrice());
                ps.setString(3, order.getPhone());
                ps.setString(4, order.getEmail());
                ps.setString(5, order.getAddress());
                ps.setString(6, order.getUsername());
                ps.setInt(7, order.getPaymentMethod());
                
                if (ps.executeUpdate() > 0) {
                    rs = ps.getGeneratedKeys();
                    if (rs.next()) {
                        insertedId = rs.getInt(1);
                    }
                }
            }
        } finally {
            DatabaseUtils.closeConnection(conn, ps, rs);
        }
        
        return insertedId;
    }
}
