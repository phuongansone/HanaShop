package dao;

import constants.RequestParameter.CartParam;
import dto.CartItem;
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
public class OrderDetailDAO {
    private static final String INSERT_ORDER_DETAIL = "INSERT INTO orderdetail "
            + "(foodId, foodPrice, quantity, orderId) VALUES (?, ?, ?, ?)";
    
    public int insertOrderDetail(CartItem cartItem, int orderId) 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        int insertedId = -1;
        
        try {
            conn = DatabaseUtils.makeConnection();
            
            if (conn != null) {
                ps = conn.prepareStatement(INSERT_ORDER_DETAIL, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, cartItem.getFood().getFoodId());
                ps.setInt(2, cartItem.getFood().getFoodPrice());
                ps.setInt(3, cartItem.getQuantity());
                ps.setInt(4, orderId);
                
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
