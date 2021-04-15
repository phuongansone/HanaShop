package services;

import constants.CommonAttribute;
import constants.RequestParameter.FoodParam;
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
    private static final String RESOURCES = "resources";
    private static final String FLAG_ON = "1";

    /**
     * Constructor
     */
    public FoodService() {
        this.foodDAO = new FoodDAO();
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
        return foodDAO.getFoodsByCategoryId(categoryId, off, len);
    }
    
    /**
     * get total number of pages necessary for active food
     * @param categoryId
     * @param recordPerPage
     * @return total number of pages
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public int getTotalPageForActiveFoodByCategory(int categoryId, int recordPerPage) 
            throws ClassNotFoundException, SQLException {
        double totalPage = (double)foodDAO.getTotalNumberOfActiveFoodByCategoryId(categoryId)
                / (double)recordPerPage;
        return (int)Math.ceil(totalPage);
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
        return foodDAO.getFoodsByName(keyword, off, len);
    }
    
    /**
     * Get total page for active food by name
     * @param keyword
     * @param recordPerPage
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public int getTotalPageForActiveFoodByName(String keyword, int recordPerPage) 
            throws SQLException, ClassNotFoundException {
        double totalPage = (double)foodDAO.getTotalNumberOfActiveFoodByName(keyword) 
                / (double) recordPerPage;
        return (int)Math.ceil(totalPage);
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
        return foodDAO.getFoodByPriceRange(from, to, off, len);
    }
    
    /**
     * Get total page for active food by price range
     * @param from
     * @param to
     * @param recordPerPage
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public int getTotalPageForActiveFoodByPriceRange(int from, int to, 
            int recordPerPage) throws SQLException, ClassNotFoundException {
        double totalPage = (double)foodDAO.getTotalNumberOfActiveFoodPriceRange(from, to) 
                / (double) recordPerPage;
        return (int)Math.ceil(totalPage);
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
        return foodDAO.getAllFood(off, len);
    }
    
    /**
     * Get total number of page if displaying all food
     * @param recordPerPage
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public int getTotalPageForAllFood(int recordPerPage) 
            throws SQLException, ClassNotFoundException {
        double totalPage = (double)foodDAO.getTotalNumberOfFood() 
                / (double) recordPerPage;
        return (int)Math.ceil(totalPage);
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
        return foodDAO.getFoodById(foodId);
    }
   
    /**
     * add food
     * @param foodDTO
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public boolean addFood(FoodDTO foodDTO) 
            throws ClassNotFoundException, SQLException {
        return foodDAO.addFood(foodDTO);
    }
    
    /**
     * update food
     * @param foodDTO
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException 
     */
    public boolean updateFood(FoodDTO foodDTO) 
            throws SQLException, ClassNotFoundException {
        return foodDAO.updateFood(foodDTO);
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
        return foodDAO.updateFoodStatus(foodId, status, userId);
    }
    
    /**
     * extract foodDTO from request
     * @param request
     * @return
     * @throws IOException
     * @throws ServletException 
     */
    public FoodDTO getFoodDTOFromRequest(HttpServletRequest request) 
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute(CommonAttribute.USER);
        
        String foodName = request.getParameter(FoodParam.FOOD_NAME);
        String foodImg = processUploadedFile(request, FoodParam.FOOD_IMAGE);
        String description = request.getParameter(FoodParam.DESCRIPTION);
        int price = StringUtils.getInteger(request.getParameter(FoodParam.FOOD_PRICE), 0);
        int categoryId = StringUtils.getInteger(request.getParameter(FoodParam.CATEGORY_ID), 0);
        int quantity = StringUtils.getInteger(request.getParameter(FoodParam.FOOD_QUANTITY), 0);
        
        if (request.getParameter(FoodParam.FOOD_ID) != null) {
            // food record is already in database
            boolean isActive = FLAG_ON.equals(request.getParameter(FoodParam.STATUS));
            FoodDTO food = new FoodDTO(foodName, foodImg, description, price, 
                    new CategoryDTO(categoryId), isActive, quantity);
            food.setFoodId(StringUtils.getInteger(request.getParameter(FoodParam.FOOD_ID), 0));
            food.setUserUpdate(user);
            return food;
        }
        
        // otherwise, new food is inserted
        FoodDTO food = new FoodDTO(foodName, foodImg, description, price, 
                new CategoryDTO(categoryId), true, quantity);
        food.setUserCreated(user);
        return food;
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
        // get folder path for resources
        String folderPath = request.getServletContext().getInitParameter(RESOURCES);
        
        // get file name
        Part imagePart = request.getPart(paramName);
        String fileName = Paths.get(imagePart.getSubmittedFileName())
                .getFileName().toString();
        
        if ("".equals(fileName)) {
            // no new file uploaded
            return request.getParameter(paramName);
        }
        
        String savedFileName = FileUtils.generateFileNameWithMilliseconds(fileName);
        
        // get path to save file
        String filePath = FileUtils.getFilePath(folderPath, savedFileName);
        
        // save file
        FileUtils.saveHttpPartToFile(imagePart, filePath);
        
        return savedFileName;
    }
}
