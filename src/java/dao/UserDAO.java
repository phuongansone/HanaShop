package dao;

import dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DatabaseUtils;

/**
 * User Data Access Object
 * @author andtpse62827
 */
public class UserDAO {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    private static final String GET_USER = "select u.id, u.roleId, u.username, "
                        + "u.email, u.phone, "
                        + "u.address "
                        + "from user u inner join role r "
                        + "using (roleId) "
                        + "where u.username = ? and password = ?";
    
    /**
     * Get user based on username and password
     * @param username username
     * @param password password
     * @return UserDTO
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public UserDTO getUser(String username, String password) 
            throws ClassNotFoundException, SQLException {
        UserDTO userDTO = null;
        
        try {
            conn = DatabaseUtils.makeConnection();
            if (conn != null) {
                ps = conn.prepareStatement(GET_USER);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();
                
                if (rs.next()) {
                    userDTO = mapResultSetToUserDTO(rs);
                }
            }
        
        } finally {
            DatabaseUtils.closeConnection(conn, ps, rs);
        }
        
        return userDTO;
    }
    
    private UserDTO mapResultSetToUserDTO(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int roleId = rs.getInt("roleId");
        String name = rs.getString("username");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        String address = rs.getString("address");

        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setRoleId(roleId);
        userDTO.setUsername(name);
        userDTO.setEmail(email);
        userDTO.setPhone(phone);
        userDTO.setAddress(address);

        return userDTO;
    }
}
