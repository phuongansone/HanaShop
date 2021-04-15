package dao;

import constants.RequestParameter.StatusParam;
import dto.StatusDTO;
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
public class StatusDAO {
    private static final String GET_ALL_STATUSES = "SELECT code, name FROM status";
    
    public List<StatusDTO> getAllStatuses() 
            throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        List<StatusDTO> statuses = new ArrayList<>();
        
        try {
            conn = DatabaseUtils.makeConnection();
            if (conn != null) {
                String sql = GET_ALL_STATUSES;
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                
                while (rs.next()) {
                    int code = rs.getInt(StatusParam.CODE);
                    String name = rs.getString(StatusParam.NAME);
                    
                    statuses.add(new StatusDTO(code, name));
                }
            }
            
        } finally {
            DatabaseUtils.closeConnection(conn, ps, rs);
        }
        
        return statuses;
    }
}
