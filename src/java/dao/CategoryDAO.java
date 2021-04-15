package dao;

import constants.RequestParameter.CategoryParam;
import dto.CategoryDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DatabaseUtils;

/**
 * Category Data Access Object
 * @author andtpse62827
 */
public class CategoryDAO {
    private static final String GET_ALL_CATEGORIES = "SELECT categoryId, categoryName FROM category";
    /**
     * Get all categories
     * @return categories
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public List<CategoryDTO> getAllCategories() 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<CategoryDTO> categories = new ArrayList<>();
        
        try {
            conn = DatabaseUtils.makeConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_ALL_CATEGORIES);
                rs = ps.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt(CategoryParam.CATEGORY_ID);
                    String name = rs.getString(CategoryParam.CATEGORY_NAME);
                    categories.add(new CategoryDTO(id, name));
                }
            }
            
        } finally {
            DatabaseUtils.closeConnection(conn, ps, rs);
        }
        
        return categories;
    }
}
