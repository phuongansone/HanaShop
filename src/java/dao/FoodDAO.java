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
                        + "foodPrice, createAt, status, foodQuantity, "
                        + "categoryName, username "
                        + "FROM food  "
                        + "INNER JOIN category USING (categoryId) "
                        + "INNER JOIN user ON food.userCreated = user.id "
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
     * Map ResultSet to FoodDTO
     * @param rs
     * @return FoodDTO
     * @throws SQLException 
     */
    private FoodDTO mapResultSetToFoodDTO(ResultSet rs) throws SQLException {
        CategoryDTO category = new CategoryDTO(
                        rs.getString(CategoryParam.CATEGORY_NAME));
        UserDTO user = new UserDTO(rs.getString(UserParam.USERNAME));
        int foodId = rs.getInt(FoodParam.FOOD_ID);
        String foodName = rs.getString(FoodParam.FOOD_NAME);
        String foodImage = rs.getString(FoodParam.FOOD_IMAGE);
        String description = rs.getString(FoodParam.DESCRIPTION);
        int foodPrice = rs.getInt(FoodParam.FOOD_PRICE);
        Date createAt = rs.getDate(FoodParam.CREATE_AT);
        boolean status = rs.getBoolean(FoodParam.STATUS);
        int foodQuantity = rs.getInt(FoodParam.FOOD_QUANTITY);
        
        return new FoodDTO(foodId, foodName, foodImage, description, 
                foodPrice, createAt, null, category, 
                status, foodQuantity, user);
    }
}
