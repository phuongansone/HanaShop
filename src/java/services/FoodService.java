package services;

import constants.CommonAttribute;
import static constants.RequestParameter.*;
import dao.FoodDAO;
import dto.CategoryDTO;
import dto.FoodDTO;
import dto.UserDTO;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import utils.FileUtils;
import utils.StringUtils;

/**
 * Food Service
 * @author andtpse62827
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5, 
        maxRequestSize = 1024 * 1024 * 5 * 5
)
public class FoodService {
    /** Food Data Access Object */
    private final FoodDAO foodDAO;
    private static final String IMAGES_DIR = "images";
    private static final String RESOURCES = "resources";

    /**
     * Constructor
     */
    public FoodService() {
        this.foodDAO = new FoodDAO();
    }
    
    /**
     * Add new food based on the information received from HttpServletRequest
     * @param request request from client
     * @return insert result
     * @throws ClassNotFoundException
     * @throws SQLException 
     * @throws java.io.IOException 
     * @throws javax.servlet.ServletException 
     */
    public boolean addFood(HttpServletRequest request) 
            throws ClassNotFoundException, SQLException, 
            IOException, ServletException {
        // Get user info
        HttpSession session = request.getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute(CommonAttribute.USER);

        // Get food info
        String foodName = request.getParameter(FoodParam.FOOD_NAME);
        String description = request.getParameter(FoodParam.DESCRIPTION);
        int foodPrice = StringUtils.getInteger(request.getParameter(FoodParam.FOOD_PRICE), 0);
        int categoryId = StringUtils.getInteger(request.getParameter(FoodParam.CATEGORY_ID), 0);
        int foodQuantity = StringUtils.getInteger(request.getParameter(FoodParam.FOOD_QUANTITY), 0);
        String foodImage = processUploadedFile(request, FoodParam.FOOD_IMAGE);
        
        FoodDTO foodDTO = new FoodDTO(foodName, foodImage, description, foodPrice, 
                new CategoryDTO(categoryId), true, foodQuantity, userDTO);
        
        return foodDAO.addFood(foodDTO);
    }
    
    /**
     * Process uploaded file
     * @param request
     * @param paramName
     * @return
     * @throws IOException
     * @throws ServletException 
     */
    private String processUploadedFile(HttpServletRequest request, String paramName) 
            throws IOException, ServletException {
        // get app path
        String appPath = FileUtils.getAppPath(request);
        // concate to get resource path
        appPath = appPath + request.getServletContext().getInitParameter(RESOURCES);
        
        Part imagePart = request.getPart(paramName);
        String fileName = FileUtils.generateFileNameWithMilliseconds(
                Paths.get(imagePart.getSubmittedFileName())
                .getFileName().toString());
        String filePath = FileUtils.getFilePath(appPath, IMAGES_DIR, fileName);
        
        FileUtils.saveHttpPartToFile(imagePart, filePath);
        
        return fileName;
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
        return foodDAO.getAllAvailableFood(off, len);
    }
    
    /**
     * get total number of pages necessary for active food
     * @param recordPerPage
     * @return total number of pages
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public int getTotalPageForActiveFood(int recordPerPage) 
            throws ClassNotFoundException, SQLException {
        double totalPage = (double)foodDAO.getTotalNumberOfActiveFood() / (double)recordPerPage;
        return (int)Math.ceil(totalPage);
    }
}
