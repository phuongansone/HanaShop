/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import constants.CommonAttribute;
import constants.RequestMappingConstants.*;
import static constants.RequestParameter.*;
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
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.CategoryService;
import services.FoodService;
import services.StatusService;
import services.UserService;

/**
 *
 * @author andtpse62827
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5, 
        maxRequestSize = 1024 * 1024 * 5 * 5
)
public class EditFoodServlet extends HttpServlet {

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
        
        // get user
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute(CommonAttribute.USER);
        
        // Edit food functionality is accessible only for admin
        if (!UserService.isAdmin(user)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        
        FoodDTO foodDTO;
        List<CategoryDTO> categories;
        List<StatusDTO> statuses;
        
        try {
            int foodId = Integer.parseInt(request.getParameter(FoodParam.FOOD_ID));
            foodDTO = new FoodService().getFoodById(foodId);
            categories = new CategoryService().getAllCategories();
            statuses = new StatusService().getAllStatuses();
            
        } catch (SQLException | ClassNotFoundException | NumberFormatException ex) {
            Logger.getLogger(EditFoodServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        
        if (foodDTO == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        
        request.setAttribute(CommonAttribute.FOOD, foodDTO);
        request.setAttribute(CommonAttribute.CATEGORIES, categories);
        request.setAttribute(CommonAttribute.STATUSES, statuses);
        
        request.getRequestDispatcher(EditFoodRequest.VIEW)
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
        
        // get currently logged in user
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute(CommonAttribute.USER);
        
        // Edit food functionality is accessible only for admin
        if (!UserService.isAdmin(user)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        
        FoodService foodService = new FoodService();
        FoodDTO foodDTO = foodService.getFoodDTOFromRequest(request);
        List<CategoryDTO> categories;
        List<StatusDTO> statuses;
        try {
            // do update
            foodService.updateFood(foodDTO);
            
            // get data for re-rendering
            categories = new CategoryService().getAllCategories();
            statuses = new StatusService().getAllStatuses();
            foodDTO = foodService.getFoodById(foodDTO.getFoodId());
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(EditFoodServlet.class.getName())
                    .log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        
        
        request.setAttribute(CommonAttribute.FOOD, foodDTO);
        request.setAttribute(CommonAttribute.CATEGORIES, categories);
        request.setAttribute(CommonAttribute.STATUSES, statuses);
        
        request.getRequestDispatcher(EditFoodRequest.VIEW).forward(request, response);
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
