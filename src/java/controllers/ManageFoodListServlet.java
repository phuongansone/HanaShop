package controllers;

import constants.CommonAttribute;
import constants.RequestMappingConstants.ManageFoodListRequest;
import constants.RequestParameter;
import dao.StatusDAO;
import dto.CategoryDTO;
import dto.FoodDTO;
import dto.StatusDTO;
import dto.UserDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.CategoryService;
import services.FoodService;
import services.UserService;
import utils.StringUtils;

/**
 *
 * @author andtpse62827
 */
public class ManageFoodListServlet extends HttpServlet {
    /** Context parameter: number of record per page */
    private static final String RECORD_PER_PAGE = "recordPerPage";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setDateHeader("Expires", 0); // Proxies.
    }
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        // Get user information from session
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO)session.getAttribute(CommonAttribute.USER);
        
        // Managing food function is only accessible for admin
        if (!UserService.isAdmin(user)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        
        // get targetted page
        int page = getCurrentPage(request);
        int totalPage;
        int[] pages;
        
        // get number of record per page configuration
        int recordPerPage = Integer.parseInt(request.getServletContext()
                .getInitParameter(RECORD_PER_PAGE));
        
        FoodService foodService = new FoodService();
        CategoryService categoryService = new CategoryService();
        StatusDAO statusDAO = new StatusDAO();
        List<FoodDTO> foodLst;
        List<CategoryDTO> categories;
        List<StatusDTO> statuses;
        
        try {
            totalPage = foodService.getTotalPageForAllFood(recordPerPage);
            pages = getPages(totalPage);
            
            int offset = (page - 1) * recordPerPage;
            foodLst = foodService.getAllFood(offset, recordPerPage);
            categories = categoryService.getAllCategories();
            statuses = statusDAO.getAllStatuses();
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ManageFoodListServlet.class.getName())
                    .log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        
        // set attributes for jsp to use for rendering
        request.setAttribute(CommonAttribute.PAGE, page);
        request.setAttribute(CommonAttribute.PAGES, pages);
        request.setAttribute(CommonAttribute.FOODS, foodLst);
        request.setAttribute(CommonAttribute.CATEGORIES, categories);
        request.setAttribute(CommonAttribute.STATUSES, statuses);
        
        request.getRequestDispatcher(ManageFoodListRequest.VIEW)
                .forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    /**
     * Get current requested page
     * @param request
     * @return current requested page, default to 1 if unspecified or invalid
     */
    private int getCurrentPage(HttpServletRequest request) {
        // get targetted page
        return StringUtils.getInteger(
                request.getParameter(RequestParameter.PAGE), 1);
    }
    
    /**
     * get pages array
     * @param totalPage
     * @return 
     */
    private int[] getPages(int totalPage) {
        if (totalPage <= 0) {
            return new int[0];
        }
        
        int[] pages = new int[totalPage];
        
        for (int i = 0; i < totalPage; i++) {
            pages[i] = i + 1;
        }
        return pages;
    }
}
