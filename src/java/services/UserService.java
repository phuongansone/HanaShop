package services;

import dao.UserDAO;
import dto.UserDTO;
import enums.Role;
import java.sql.SQLException;

/**
 * Service class for User entity
 * @author andtpse62827
 */
public class UserService {
    
    /** User Data Access Object */
    private final UserDAO userDAO;

    /** Constructors */
    public UserService() {
        userDAO = new UserDAO();
    }
    
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
        return userDAO.getUser(username, password);
    }
    
    /**
     * Check if user is admin
     * @param userDTO
     * @return 
     */
    public static boolean isAdmin(UserDTO userDTO) {
        if (userDTO != null) {
            return userDTO.getRoleId() == Role.ADMIN.getValue();
        }
        return Boolean.FALSE;
    }
}
