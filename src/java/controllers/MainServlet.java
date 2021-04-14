package controllers;

import constants.RequestMappingConstants.AddToCartRequest;
import constants.RequestMappingConstants.CreateFoodRequest;
import constants.RequestMappingConstants.DeleteFoodRequest;
import constants.RequestMappingConstants.EditFoodRequest;
import constants.RequestMappingConstants.FoodDetailRequest;
import constants.RequestMappingConstants.GetSearchResultRequest;
import constants.RequestMappingConstants.LoginRequest;
import constants.RequestMappingConstants.LogoutRequest;
import constants.RequestMappingConstants.ManageFoodListRequest;
import constants.RequestMappingConstants.ManageFoodRequest;
import constants.RequestMappingConstants.RemoveFoodFromCartRequest;
import constants.RequestMappingConstants.SaveOrderRequest;
import constants.RequestMappingConstants.SearchItemRequest;
import constants.RequestMappingConstants.UpdateCartRequest;
import constants.RequestMappingConstants.ViewCartRequest;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Objects;
import javax.servlet.annotation.MultipartConfig;

/**
 * Main Servlet for dispatching request
 * @author andtpse62827
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5, 
        maxRequestSize = 1024 * 1024 * 5 * 5
)
public class MainServlet extends HttpServlet {
    /** Action parameter */
    private static final String ACTION_PARAM = "action";
    
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
        
        request.setCharacterEncoding("UTF-8");
        String reqAction = request.getParameter(ACTION_PARAM);
        String url = SearchItemRequest.SERVLET;
        
        if (Objects.nonNull(reqAction)) {
            switch(reqAction) {
                case LoginRequest.ACTION:
                    url = LoginRequest.SERVLET;
                    break;
                case LogoutRequest.ACTION:
                    url = LogoutRequest.SERVLET;
                    break;
                case CreateFoodRequest.ACTION:
                    url = CreateFoodRequest.SERVLET;
                    break;
                case GetSearchResultRequest.ACTION:
                    url = GetSearchResultRequest.SERVLET;
                    break;
                case ManageFoodRequest.ACTION:
                    url = ManageFoodRequest.SERVLET;
                    break;
                case ManageFoodListRequest.ACTION:
                    url = ManageFoodListRequest.SERVLET;
                    break;
                case FoodDetailRequest.ACTION:
                    url = FoodDetailRequest.SERVLET;
                    break;
                case EditFoodRequest.ACTION:
                    url = EditFoodRequest.SERVLET;
                    break;
                case DeleteFoodRequest.ACTION:
                    url = DeleteFoodRequest.SERVLET;
                    break;
                case AddToCartRequest.ACTION:
                    url = AddToCartRequest.SERVLET;
                    break;
                case ViewCartRequest.ACTION:
                    url = ViewCartRequest.SERVLET;
                    break;
                case RemoveFoodFromCartRequest.ACTION:
                    url = RemoveFoodFromCartRequest.SERVLET;
                    break;
                case UpdateCartRequest.ACTION:
                    url = UpdateCartRequest.SERVLET;
                    break;
                case SaveOrderRequest.ACTION:
                    url = SaveOrderRequest.SERVLET;
                    break;
                    
            }
        }
        
        request.getRequestDispatcher(url).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
    }// </editor-fold>

}
