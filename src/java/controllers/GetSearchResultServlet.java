/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static constants.RequestParameter.*;
import static constants.RequestMappingConstants.*;
import constants.CommonAttribute;
import dto.FoodDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.FoodService;

/**
 *
 * @author PC
 */
public class GetSearchResultServlet extends HttpServlet {
    /** Context parameter's no of record per page */
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
        String categoryId = request.getParameter(CategoryParam.CATEGORY_ID);
        
        // get targetted page
        int page = getCurrentPage(request);
        int totalPage;
        int[] pages;
        
        int recordPerPage = Integer.parseInt(request.getServletContext()
                .getInitParameter(RECORD_PER_PAGE));
        
        FoodService foodService = new FoodService();
        List<FoodDTO> foodLst;
        try {
            if (categoryId == null) {
                // If the user do not search by category
                totalPage = foodService.getTotalPageForActiveFood(recordPerPage);
                pages = getPages(totalPage);
                
                int offset = (page - 1) * recordPerPage;
                foodLst = foodService.getAllAvailableFood(offset, recordPerPage);
            } else {
                // If the user search by category
                totalPage = foodService.getTotalPageForActiveFoodByCategory(
                                Integer.parseInt(categoryId), 
                                recordPerPage);
                
                pages = getPages(totalPage);
                
                int offset = (page - 1) * recordPerPage;
                foodLst = foodService.getFoodsByCategoryId(
                        Integer.parseInt(categoryId),
                        offset, recordPerPage);
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(GetSearchResultServlet.class.getName())
                    .log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        
        // set attributes for jsp to use for rendering
        request.setAttribute(CommonAttribute.PAGE, page);
        request.setAttribute(CommonAttribute.PAGES, pages);
        request.setAttribute(CommonAttribute.TOTAL, totalPage);
        request.setAttribute(CommonAttribute.FOODS, foodLst);
        
        // forward to jsp for rendering
        request.getRequestDispatcher(GetSearchResultRequest.VIEW)
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
        int page = 1;
        
        if (request.getParameter(PAGE) != null) {
            try {
                page = Integer.parseInt(request.getParameter(PAGE));
            } catch (NumberFormatException ex) {
                Logger.getLogger(GetSearchResultServlet.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
        }
        
        return page;
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
