package controllers;

import constants.CommonAttribute;
import constants.RequestMappingConstants.LoginRequest;
import constants.RequestMappingConstants.SaveOrderRequest;
import constants.RequestMappingConstants.SearchItemRequest;
import constants.RequestMappingConstants.ViewCartRequest;
import dto.CartItem;
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
import services.OrderService;
import static services.OrderService.checkFoodOutOfStock;
import services.UserService;

/**
 *
 * @author andtpse62827
 */
public class SaveOrderServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute(CommonAttribute.USER);
        
        if (user == null) {
            response.sendRedirect(LoginRequest.SERVLET);
            return;
        }
        
        if (UserService.isAdmin(user)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        
        List<CartItem> cart = (List<CartItem>) session.getAttribute(CommonAttribute.CART);
        boolean outOfStock;
        
        try {
            outOfStock = checkFoodOutOfStock(cart);
            session.setAttribute(CommonAttribute.CART, cart);
            
            if (outOfStock) {
                response.sendRedirect(ViewCartRequest.SERVLET);
                return;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SaveOrderServlet.class.getName())
                    .log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        
        request.getRequestDispatcher(SaveOrderRequest.VIEW_GET).forward(request, response);
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
        
        if (user == null) {
            response.sendRedirect(LoginRequest.SERVLET);
            return;
        }
        
        if (UserService.isAdmin(user)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        
        List<CartItem> cart = (List<CartItem>) session.getAttribute(CommonAttribute.CART);
        
        boolean outOfStock;
        OrderService orderService = new OrderService();
        try {
            outOfStock = OrderService.checkFoodOutOfStock(cart);
            session.setAttribute(CommonAttribute.CART, cart);
            
            if (outOfStock) {
                // TODO: if food is out of stock, display warning message
                response.sendRedirect(ViewCartRequest.SERVLET);
                return;
            } else {
                // if ok, save order to database
                orderService.saveOrder(request);
                session.removeAttribute(CommonAttribute.CART);
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SaveOrderServlet.class.getName())
                    .log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        
        request.getRequestDispatcher(SearchItemRequest.SERVLET).forward(request, response);
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
