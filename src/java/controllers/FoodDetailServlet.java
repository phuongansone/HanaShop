package controllers;

import constants.CommonAttribute;
import constants.RequestMappingConstants.EditFoodRequest;
import constants.RequestMappingConstants.FoodDetailRequest;
import dto.UserDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.UserService;

import constants.RequestParameter.*;
import dto.FoodDTO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.MultipartConfig;
import services.FoodService;

/**
 * Servlet to process requests related to a food's detail
 * @author andtpse62827
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5
)
public class FoodDetailServlet extends HttpServlet {

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
        
        // Get logged in user
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute(CommonAttribute.USER);
        
        // Get food specified by foodID
        FoodDTO food;
        
        try {
            int foodId = Integer.parseInt(request.getParameter(FoodParam.FOOD_ID));
            food = new FoodService().getFoodById(foodId);
        } catch (SQLException | ClassNotFoundException | NumberFormatException ex) {
            Logger.getLogger(FoodDetailServlet.class.getName())
                    .log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        
        // If there is no food with the requested id
        if (food == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        
        
        // Determine url
        String url = UserService.isAdmin(user) ? EditFoodRequest.SERVLET 
                : FoodDetailRequest.VIEW_USER;
        
        request.setAttribute(CommonAttribute.FOOD, food);
        request.getRequestDispatcher(url).forward(request, response);
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

}
