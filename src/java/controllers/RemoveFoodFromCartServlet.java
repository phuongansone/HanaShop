package controllers;

import constants.CommonAttribute;
import constants.RequestMappingConstants;
import constants.RequestMappingConstants.LoginRequest;
import constants.RequestMappingConstants.ViewCartRequest;
import constants.RequestParameter;
import constants.RequestParameter.FoodParam;
import dto.CartItem;
import dto.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.UserService;
import utils.StringUtils;

/**
 *
 * @author andtpse62827
 */
public class RemoveFoodFromCartServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute(CommonAttribute.USER);
        String URL = "MainServlet?action=viewCart";
        
        if (user == null) {
            response.sendRedirect(LoginRequest.SERVLET);
            return;
        }
        
        if (UserService.isAdmin(user)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        
        List<CartItem> cart = (List<CartItem>) session.getAttribute(CommonAttribute.CART);
        int foodId = StringUtils.getInteger(request.getParameter(FoodParam.FOOD_ID), 1);
        int index = searchFoodInCart(cart, foodId);
        
        if (index != -1) {
            cart.remove(index);
        }
        
        session.setAttribute(CommonAttribute.CART, cart);
        
        response.sendRedirect(URL);
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
    
    private int searchFoodInCart(List<CartItem> cart, int foodId) {
        if (cart != null) {
            for (int i = 0; i < cart.size(); i++) {
                if (cart.get(i).getFood().getFoodId() == foodId) {
                    return i;
                }
            }
        }

        return -1;
    }

}
