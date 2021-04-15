package dao;

import constants.RequestParameter.OrderParam;
import dto.OrderDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
    
    /** Get all order */
    private static final String GET_ALL_ORDER = "SELECT o.orderId, o.username, o.totalPrice, "
            + "o.createOrderAt, p.name AS paymentMethodName "
            + "FROM orders o "
            + "INNER JOIN payment_method p ON o.paymentMethod = p.id "
            + "WHERE userId = ? AND createOrderAt ORDER BY createOrderAt DESC";
    
    /** Get order by date */
    private static final String GET_ORDER_BY_DATE = "SELECT o.orderId, o.username, o.totalPrice, "
            + "o.createOrderAt, p.name AS paymentMethodName "
            + "FROM orders o "
            + "INNER JOIN payment_method p ON o.paymentMethod = p.id "
            + "WHERE userId = ? "
            + "AND createOrderAt BETWEEN ? AND ? "
            + "ORDER BY createOrderAt DESC";
    
    /** Get order by name */
    private static final String GET_ORDER_BY_NAME = "SELECT o.orderId, o.username, o.totalPrice, "
            + "o.createOrderAt, p.name AS paymentMethodName "
            + "FROM orders o "
            + "INNER JOIN payment_method p "
            + "ON o.paymentMethod = p.id "
            + "WHERE userId = ? "
            + "AND username LIKE ? "
            + "ORDER BY createOrderAt DESC";
    
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
    
    public List<OrderDTO> getAllOrders(int userId)
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<OrderDTO> orders = new ArrayList<>();

        try {
            conn = DatabaseUtils.makeConnection();

            if (conn != null) {
                ps = conn.prepareStatement(GET_ALL_ORDER);
                ps.setInt(1, userId);

                rs = ps.executeQuery();

                while (rs.next()) {
                    orders.add(mapResultSetToOrderDTO(rs));
                }
            }
        } finally {
            DatabaseUtils.closeConnection(conn, ps, rs);
        }

        return orders;
    }
   
    public List<OrderDTO> getOrdersByDate(int userId, String startTime, String endTime)
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<OrderDTO> orders = new ArrayList<>();

        try {
            conn = DatabaseUtils.makeConnection();

            if (conn != null) {
                ps = conn.prepareStatement(GET_ORDER_BY_DATE);
                ps.setInt(1, userId);
                ps.setString(2, startTime);
                ps.setString(3, endTime);

                rs = ps.executeQuery();

                while (rs.next()) {
                    orders.add(mapResultSetToOrderDTO(rs));
                }
            }
        } finally {
            DatabaseUtils.closeConnection(conn, ps, rs);
        }

        return orders;
    }
    
    public List<OrderDTO> getOrdersByName(int userId, String keyword) 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<OrderDTO> orders = new ArrayList<>();
        
        try {
            conn = DatabaseUtils.makeConnection();
            
            if (conn != null) {
                ps = conn.prepareStatement(GET_ORDER_BY_NAME);
                ps.setInt(1, userId);
                ps.setString(2, "%" + keyword + "%");
                
                rs = ps.executeQuery();
                
                while (rs.next()) {
                    orders.add(mapResultSetToOrderDTO(rs));
                }
            }
        } finally {
            DatabaseUtils.closeConnection(conn, ps, rs);
        }
        
        return orders;
    }
    
    private OrderDTO mapResultSetToOrderDTO(ResultSet rs) throws SQLException {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setOrderId(rs.getInt(OrderParam.ORDER_ID));
        orderDTO.setUsername(rs.getString(OrderParam.USERNAME));
        orderDTO.setTotalPrice(rs.getInt(OrderParam.TOTAL_PRICE));
        orderDTO.setCreateOrderAt(rs.getDate(OrderParam.CREATE_ORDER_AT));
        orderDTO.setPaymentMethodName(rs.getString(OrderParam.PAYMENT_METHOD_NAME));

        return orderDTO;
    }
}
