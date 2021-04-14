package dao;

import dto.CategoryDTO;
import dto.FoodDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DatabaseUtils;

import constants.RequestParameter.*;
import constants.RequestParameter;
import dto.UserDTO;
import java.sql.Date;

/**
 * Food Data Access Object
 * @author andtpse62827
 */
public class FoodDAO {
    
    private static final String UPDATE_FOOD_STATUS = "UPDATE food SET status = ?"
            + ", userUpdated = ? WHERE foodId = ?";
    
    private static final String UPDATE_FOOD_QUANTITY = "UPDATE food SET foodQuantity = ? "
            + "WHERE foodId = ?";
    
    /**
     * Add new food to the DB
     * @param foodDTO
     * @return adding result
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public boolean addFood(FoodDTO foodDTO) 
            throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        boolean result = false;
        
        try {
            conn = DatabaseUtils.makeConnection();
            if (conn != null) {
                String sql = "INSERT INTO food "
                        + "(foodName, foodImage, description, foodPrice, "
                        + "categoryId, status, foodQuantity, userCreated) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                ps = conn.prepareStatement(sql);
                ps.setString(1, foodDTO.getFoodName());
                ps.setString(2, foodDTO.getFoodImage());
                ps.setString(3, foodDTO.getDescription());
                ps.setInt(4, foodDTO.getFoodPrice());
                ps.setInt(5, foodDTO.getCategory() != null ? 
                        foodDTO.getCategory().getCategoryId() : null);
                ps.setBoolean(6, foodDTO.isStatus());
                ps.setInt(7, foodDTO.getFoodQuantity());
                ps.setInt(8, foodDTO.getUserCreated().getId());
                result = ps.executeUpdate() > 0;
            }
        } finally {
            DatabaseUtils.closeConnection(conn, ps, rs);
        }
        
        return result;
    }
    
    /**
     * Get total number of active food records from database
     * @return number of records
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public int getTotalNumberOfActiveFood() 
            throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        int noOfRecords = 0;
        try {
            conn = DatabaseUtils.makeConnection();
            
            if (conn != null) {
                String sql = "SELECT count(*) as total FROM food "
                        + "WHERE foodQuantity > 0 and status = 1";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    noOfRecords = rs.getInt(RequestParameter.TOTAL);
                }
            }
        } finally {
            DatabaseUtils.closeConnection(conn, ps, rs);
        }
        
        return noOfRecords;
    }
    
    /**
     * Search for foods available in stock
     * @param off offset
     * @param len number of records returned
     * @return list of food
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public List<FoodDTO> getAllAvailableFood(int off, int len) 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<FoodDTO> foodLst = new ArrayList<>();

        try {
            conn = DatabaseUtils.makeConnection();
            if (conn != null) {
                String sql = "SELECT foodId, foodName, foodImage, description, "
                        + "foodPrice, createAt, status, foodQuantity, updateAt, "
                        + "category.categoryId, categoryName, "
                        + "u1.username AS createUser, u2.username AS updateUser "
                        + "FROM food "
                        + "INNER JOIN category USING (categoryId) "
                        + "INNER JOIN user u1 ON food.userCreated = u1.id "
                        + "LEFT JOIN user u2 ON food.userUpdated = u2.id "
                        + "WHERE foodQuantity > ? and status = ? "
                        + "ORDER BY createAt "
                        + "DESC LIMIT ?, ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, 0);
                ps.setBoolean(2, true);
                ps.setInt(3, off);
                ps.setInt(4, len);
                rs = ps.executeQuery();

                while (rs.next()) {
                    foodLst.add(mapResultSetToFoodDTO(rs));
                }                
            }
        } finally {
            DatabaseUtils.closeConnection(conn, ps, rs);
        }
        
        return foodLst;
    }
    
    /**
     * Get total number of active food by category id
     * @param categoryId
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public int getTotalNumberOfActiveFoodByCategoryId(int categoryId) 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        int total = 0;
        
        try {
            conn = DatabaseUtils.makeConnection();
            if (conn != null) {
                String sql = "SELECT count(*) as total "
                        + "FROM food "
                        + "WHERE foodQuantity > 0 and status = 1 "
                        + "and categoryId = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, categoryId);
                
                rs = ps.executeQuery();
                while (rs.next()) {
                    total = rs.getInt(RequestParameter.TOTAL);
                }
            }
        } finally {
            DatabaseUtils.closeConnection(conn, ps, rs);
        }
        
        return total;
    }
    
    /**
     * Get food by category id
     * @param categoryId
     * @param off
     * @param len
     * @return list of food
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public List<FoodDTO> getFoodsByCategoryId(int categoryId, int off, int len) 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<FoodDTO> foodLst = new ArrayList<>();
        
        try {
            conn = DatabaseUtils.makeConnection();
            if (conn != null) {
                String sql = "SELECT foodId, foodName, foodImage, description, "
                        + "foodPrice, createAt, status, foodQuantity, updateAt, "
                        + "category.categoryId, categoryName, "
                        + "u1.username as createUser, u2.username as updateUser "
                        + "FROM food  "
                        + "INNER JOIN category USING (categoryId) "
                        + "INNER JOIN user u1 ON food.userCreated = u1.id "
                        + "LEFT JOIN user u2 ON food.userUpdated = u2.id "
                        + "WHERE foodQuantity > 0 and status = 1 "
                        + "and categoryId = ? "
                        + "ORDER BY createAt "
                        + "DESC LIMIT ?, ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, categoryId);
                ps.setInt(2, off);
                ps.setInt(3, len);
                
                rs = ps.executeQuery();
                while (rs.next()) {
                    foodLst.add(mapResultSetToFoodDTO(rs));
                }
            }
        } finally {
            DatabaseUtils.closeConnection(conn, ps, rs);
        }
        
        return foodLst;
    }
    
    /**
     * Get food by name
     * @param keyword
     * @param off
     * @param len
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public List<FoodDTO> getFoodsByName(String keyword, int off, int len) 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<FoodDTO> foodLst = new ArrayList<>();
        
        try {
            conn = DatabaseUtils.makeConnection();
            if (conn != null) {
                String sql = "SELECT foodId, foodName, foodImage, description, "
                        + "foodPrice, createAt, status, foodQuantity, updateAt, "
                        + "category.categoryId, categoryName, "
                        + "u1.username as createUser, u2.username as updateUser "
                        + "FROM food "
                        + "INNER JOIN category USING (categoryId) "
                        + "INNER JOIN user u1 ON food.userCreated = u1.id "
                        + "LEFT JOIN user u2 ON food.userUpdated = u2.id "
                        + "WHERE foodQuantity > 0 and status = 1 "
                        + "and foodName like ? "
                        + "ORDER BY createAt "
                        + "DESC LIMIT ?, ?";
                
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                ps.setInt(2, off);
                ps.setInt(3, len);
                
                rs = ps.executeQuery();
                
                while(rs.next()) {
                    foodLst.add(mapResultSetToFoodDTO(rs));
                }
            }
        } finally {
            DatabaseUtils.closeConnection(conn, ps, rs);
        }
        
        return foodLst;
    }
    
    /**
     * Get total number of active food by name
     * @param keyword
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public int getTotalNumberOfActiveFoodByName(String keyword) 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        int total = 0;
        
        try {
            conn = DatabaseUtils.makeConnection();
            if (conn != null) {
                String sql = "SELECT count(*) as total "
                        + "FROM hanashop.food "
                        + "WHERE foodQuantity > 0 and status = 1 "
                        + "and foodName like ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, "%" + keyword + "%");
                rs = ps.executeQuery();
                
                if (rs.next()) {
                    total = rs.getInt(RequestParameter.TOTAL);
                }
            }
        } finally {
            DatabaseUtils.closeConnection(conn, ps, rs);
        }
        
        return total;
    }
    
    /**
     * Get food by price range
     * @param from
     * @param to
     * @param off
     * @param len
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public List<FoodDTO> getFoodByPriceRange(int from, int to, int off, int len) 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<FoodDTO> foodLst = new ArrayList<>();
        
        if (from > to) {
            return foodLst;
        }
        
        try {
            conn = DatabaseUtils.makeConnection();
            if (conn != null) {
                String sql = "SELECT foodId, foodName, foodImage, description, "
                        + "foodPrice, createAt, status, foodQuantity, updateAt, "
                        + "category.categoryId, categoryName, "
                        + "u1.username as createUser, u2.username as updateUser "
                        + "FROM hanashop.food "
                        + "INNER JOIN category USING (categoryId) "
                        + "INNER JOIN user u1 ON food.userCreated = u1.id "
                        + "LEFT JOIN user u2 ON food.userUpdated = u2.id "
                        + "WHERE foodQuantity > 0 AND status = 1 "
                        + "AND foodPrice BETWEEN ? AND ? "
                        + "ORDER BY createAt "
                        + "DESC LIMIT ?, ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, from);
                ps.setInt(2, to);
                ps.setInt(3, off);
                ps.setInt(4, len);
                
                rs = ps.executeQuery();
                
                while (rs.next()) {
                    foodLst.add(mapResultSetToFoodDTO(rs));
                }
            }
        } finally {
            DatabaseUtils.closeConnection(conn, ps, rs);
        }
        
        return foodLst;
    }
    
    /**
     * Get total number of active food by price range
     * @param from
     * @param to
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public int getTotalNumberOfActiveFoodPriceRange(int from, int to) 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        int total = 0;
        
        try {
            conn = DatabaseUtils.makeConnection();
            if (conn != null) {
                String sql = "SELECT count(*) as total "
                        + "FROM hanashop.food "
                        + "WHERE foodQuantity > 0 AND status = 1 "
                        + "AND foodPrice BETWEEN ? AND ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, from);
                ps.setInt(2, to);
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    total = rs.getInt(RequestParameter.TOTAL);
                }
            }
        } finally {
            DatabaseUtils.closeConnection(conn, ps, rs);
        }
        
        return total;
    }
    
    /**
     * Get all food
     * @param off
     * @param len
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public List<FoodDTO> getAllFood(int off, int len) 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<FoodDTO> foodLst = new ArrayList<>();
        
        try {
            conn = DatabaseUtils.makeConnection();
            
            if (conn != null) {
                String sql = "SELECT foodId, foodName, foodImage, description, "
                        + "foodPrice, createAt, status, foodQuantity, updateAt, "
                        + "category.categoryId, categoryName, "
                        + "u1.username AS createUser, u2.username AS updateUser "
                        + "FROM food INNER JOIN category USING (categoryId) "
                        + "INNER JOIN user u1 ON food.userCreated = u1.id "
                        + "LEFT JOIN user u2 ON food.userUpdated = u2.id "
                        + "ORDER BY createAt DESC LIMIT ?, ?";
                
                ps = conn.prepareStatement(sql);
                ps.setInt(1, off);
                ps.setInt(2, len);
                
                rs = ps.executeQuery();
                
                while (rs.next()) {
                    foodLst.add(mapResultSetToFoodDTO(rs));
                }
            }
        } finally {
            DatabaseUtils.closeConnection(conn, ps, rs);
        }
        
        return foodLst;
    }
    
    /**
     * Get total number of food in database
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public int getTotalNumberOfFood() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        int total = 0;
        
        try {
            conn = DatabaseUtils.makeConnection();
            if (conn != null) {
                String sql = "SELECT count(*) AS total FROM food";
                ps = conn.prepareStatement(sql);
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    total = rs.getInt(RequestParameter.TOTAL);
                }
            }
        } finally {
            DatabaseUtils.closeConnection(conn, ps, rs);
        }
        
        return total;
    }
    
    /**
     * Get food by Id
     * @param foodId
     * @return food specified by Id
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public FoodDTO getFoodById(int foodId) 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        FoodDTO food = null;
        
        try {
            conn = DatabaseUtils.makeConnection();
            if (conn != null) {
                String sql = "SELECT foodId, foodName, foodImage, description, "
                        + "foodPrice, createAt, status, foodQuantity, updateAt, "
                        + "category.categoryId, categoryName, "
                        + "u1.username as createUser, u2.username as updateUser "
                        + "FROM food  "
                        + "INNER JOIN category USING (categoryId) "
                        + "INNER JOIN user u1 ON food.userCreated = u1.id "
                        + "LEFT JOIN user u2 ON food.userUpdated = u2.id "
                        + "WHERE foodId = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, foodId);
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    food = mapResultSetToFoodDTO(rs);
                }
            }
        } finally {
            DatabaseUtils.closeConnection(conn, ps, rs);
        }
        
        return food;
    }
    
    /**
     * Update food
     * @param food
     * @return number of row affected
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public boolean updateFood(FoodDTO food) 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        
        int updatedRow = 0;
        
        try {
            conn = DatabaseUtils.makeConnection();
            
            if (conn != null) {
                String sql = "UPDATE food "
                        + "SET foodName = ?, " // 1
                        + "foodImage = ?, " // 2
                        + "description = ?, " // 3
                        + "foodPrice = ?, " // 4
                        + "categoryId = ?, " // 5
                        + "status = ?, " // 6
                        + "foodQuantity = ?, " // 7
                        + "userUpdated = ? " // 8
                        + "WHERE foodId = ?"; // 9
                
                ps = conn.prepareStatement(sql);
                ps.setString(1, food.getFoodName());
                ps.setString(2, food.getFoodImage());
                ps.setString(3, food.getDescription());
                ps.setInt(4, food.getFoodPrice());
                ps.setInt(5, food.getCategory().getCategoryId());
                ps.setBoolean(6, food.isStatus());
                ps.setInt(7, food.getFoodQuantity());
                ps.setInt(8, food.getUserUpdate().getId());
                ps.setInt(9, food.getFoodId());
                
                updatedRow = ps.executeUpdate();
            }
        } finally {
            DatabaseUtils.closeConnection(conn, ps, null);
        }
        
        return updatedRow > 0;
    }
    
    /**
     * Update food status
     * @param foodId
     * @param status
     * @param userId
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public boolean updateFoodStatus(int foodId, boolean status, int userId) 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        
        int updatedRow = 0;
        
        try {
            conn = DatabaseUtils.makeConnection();
            
            if (conn != null) {
                ps = conn.prepareStatement(UPDATE_FOOD_STATUS);
                ps.setBoolean(1, status);
                ps.setInt(2, userId);
                ps.setInt(3, foodId);
                
                updatedRow = ps.executeUpdate();
            }
            
        } finally {
            DatabaseUtils.closeConnection(conn, ps, null);
        }
        
        return updatedRow > 0;
    }
    
    /**
     * update food quantity
     * @param foodId
     * @param quantity
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public boolean updateFoodQuantity(int foodId, int quantity) 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        
        boolean updated = false;
        
        try {
            conn = DatabaseUtils.makeConnection();
            
            if (conn != null) {
                ps = conn.prepareStatement(UPDATE_FOOD_QUANTITY);
                ps.setInt(1, quantity);
                ps.setInt(2, foodId);
                
                updated = ps.executeUpdate() > 0;
            }
        } finally {
            DatabaseUtils.closeConnection(conn, ps, null);
        }
        
        return updated;
    }
    
    /**
     * Map ResultSet to FoodDTO
     * @param rs
     * @return FoodDTO
     * @throws SQLException 
     */
    private FoodDTO mapResultSetToFoodDTO(ResultSet rs) throws SQLException {
        CategoryDTO category = new CategoryDTO(
                        rs.getInt(CategoryParam.CATEGORY_ID),
                        rs.getString(CategoryParam.CATEGORY_NAME));
        UserDTO userCreate = new UserDTO(rs.getString(FoodParam.USERNAME_CREATE));
        UserDTO userUpdate = new UserDTO(rs.getString(FoodParam.USERNAME_UPDATE));
        int foodId = rs.getInt(FoodParam.FOOD_ID);
        String foodName = rs.getString(FoodParam.FOOD_NAME);
        String foodImage = rs.getString(FoodParam.FOOD_IMAGE);
        String description = rs.getString(FoodParam.DESCRIPTION);
        int foodPrice = rs.getInt(FoodParam.FOOD_PRICE);
        Date createAt = rs.getDate(FoodParam.CREATE_AT);
        Date updateAt = rs.getDate(FoodParam.UPDATE_AT);
        boolean status = rs.getBoolean(FoodParam.STATUS);
        int foodQuantity = rs.getInt(FoodParam.FOOD_QUANTITY);
        
        return new FoodDTO(foodId, foodName, foodImage, description, 
                foodPrice, createAt, updateAt, category, 
                status, foodQuantity, userCreate, userUpdate);
    }
}
