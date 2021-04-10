package services;

import dao.PriceRangeDAO;
import dto.PriceRangeDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author andtpse62827
 */
public class PriceRangeService {
    private final PriceRangeDAO priceRangeDAO;

    public PriceRangeService() {
        this.priceRangeDAO = new PriceRangeDAO();
    }
    
    public List<PriceRangeDTO> getAllActivePriceRanges() 
            throws SQLException, ClassNotFoundException {
        return priceRangeDAO.getAllActivePriceRanges();
    }
    
    public PriceRangeDTO getPriceRangeById(int id) 
            throws SQLException, ClassNotFoundException {
        return priceRangeDAO.getPriceRangeById(id);
    }
}
