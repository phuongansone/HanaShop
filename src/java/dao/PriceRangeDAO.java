package dao;

import constants.RequestParameter.PriceRangeParam;
import dto.PriceRangeDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DatabaseUtils;

/**
 *
 * @author andtpse62827
 */
public class PriceRangeDAO {
    private static final String GET_ALL_PRICE_RANGES = "SELECT p.id, p.name, p.from, p.to "
                        + "FROM price_range p WHERE p.status = 1";
    
    private static final String GET_RANGE_BY_ID = "SELECT p.id, p.name, p.from, p.to "
                        + "FROM price_range p WHERE p.status = 1 AND p.id = ?";
    
    public List<PriceRangeDTO> getAllActivePriceRanges() 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<PriceRangeDTO> ranges = new ArrayList<>();
        
        try {
            conn = DatabaseUtils.makeConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_ALL_PRICE_RANGES);
                rs = ps.executeQuery();
                
                while(rs.next()) {
                    ranges.add(mapResultSetToPriceRangeDTO(rs));
                }
            }
        } finally {
            DatabaseUtils.closeConnection(conn, ps, rs);
        }
        
        return ranges;
    }
    
    public PriceRangeDTO getPriceRangeById(int id) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        PriceRangeDTO priceRange = null;
        
        try {
            conn = DatabaseUtils.makeConnection();
            
            if (conn != null) {
                ps = conn.prepareStatement(GET_RANGE_BY_ID);
                ps.setInt(1, id);
                
                rs = ps.executeQuery();
                if (rs.next()) {
                    priceRange = mapResultSetToPriceRangeDTO(rs);
                }
            }
        } finally {
            DatabaseUtils.closeConnection(conn, ps, rs);
        }
        
        return priceRange;
    }
    
    private PriceRangeDTO mapResultSetToPriceRangeDTO(ResultSet rs) 
            throws SQLException {
        if (rs == null) {
            return null;
        }
        
        int id = rs.getInt(PriceRangeParam.ID);
        String name = rs.getString(PriceRangeParam.NAME);
        int from = rs.getInt(PriceRangeParam.FROM);
        int to = rs.getInt(PriceRangeParam.TO);
        
        return new PriceRangeDTO(id, name, from, to);
    }
}
