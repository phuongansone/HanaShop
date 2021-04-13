package controllers;

import constants.CommonAttribute;
import dto.UserDTO;
import enums.CRUDStatus;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.FoodService;
import services.UserService;

import static constants.RequestMappingConstants.*;
import dto.CategoryDTO;
import java.util.List;
import javax.servlet.annotation.MultipartConfig;
import services.CategoryService;

/**
 *
 * @author andtpse62827
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5, 
        maxRequestSize = 1024 * 1024 * 5 * 5
)
public class CreateFoodServlet extends HttpServlet {

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
        // Get user info
        HttpSession session = request.getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute(CommonAttribute.USER);

        // Only admin can create new food
        if (!UserService.isAdmin(userDTO)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        
        List<CategoryDTO> categories;
        
        try {
            categories = new CategoryService().getAllCategories();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CreateFoodServlet.class.getName())
                    .log(Level.SEVERE, null, ex);
            
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        
        request.setAttribute(CommonAttribute.CATEGORIES, categories);
        request.getRequestDispatcher(CreateFoodRequest.VIEW).forward(request, response);
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
        // Get user info
        HttpSession session = request.getSession();
        UserDTO userDTO = (UserDTO) session.getAttribute(CommonAttribute.USER);
        
        // Only admin can create new food
        if (!UserService.isAdmin(userDTO)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        
        // add new food
        FoodService foodService = new FoodService();
        
        // get all categories for rendering 
        List<CategoryDTO> categories;
        try {
            foodService.addFood(foodService.getFoodDTOFromRequest(request));
            categories = new CategoryService().getAllCategories();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CreateFoodServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        
        request.setAttribute(CommonAttribute.CATEGORIES, categories);       
        request.setAttribute(CommonAttribute.INSERT_STATUS, CRUDStatus.SUCCESS);
        request.getRequestDispatcher(CreateFoodRequest.VIEW).forward(request, response);
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
    
    
}
