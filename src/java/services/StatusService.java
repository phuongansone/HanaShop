package services;

import dao.StatusDAO;
import dto.StatusDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author andtpse62827
 */
public class StatusService {
    private final StatusDAO statusDAO;

    public StatusService() {
        statusDAO = new StatusDAO();
    }
    
    public List<StatusDTO> getAllStatuses() 
            throws SQLException, ClassNotFoundException {
        return statusDAO.getAllStatuses();
    }
}
