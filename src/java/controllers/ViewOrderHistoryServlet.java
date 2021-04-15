package controllers;

import constants.CommonAttribute;
import constants.RequestMappingConstants;
import constants.RequestMappingConstants.ViewOrderHistoryRequest;
import constants.RequestParameter;
import constants.RequestParameter.OrderParam;
import dto.OrderDTO;
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
import services.UserService;

/**
 *
 * @author andtpse62827
 */
public class ViewOrderHistoryServlet extends HttpServlet {

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
        System.out.println("I'M HERE");
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
        
        // redirect to login if user does not log in
        if (user == null) {
            response.sendRedirect(RequestMappingConstants.LoginRequest.SERVLET);
            return;
        }
        
        // Admin cannot use this function
        if (UserService.isAdmin(user)) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        
        String date = request.getParameter(OrderParam.DATE);
        String keyword = request.getParameter(OrderParam.KEYWORD);
        OrderService orderService = new OrderService();
        List<OrderDTO> orders;
        try {
            if (keyword != null && !keyword.isBlank()) {
               orders = orderService.getOrdersByName(user.getId(), keyword);
            } else if (date != null && !date.isBlank()) {
                orders = orderService.getOrdersByDate(user.getId(), date);
            } else {
                orders = orderService.getAllOrders(user.getId());
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ViewOrderHistoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        
        request.setAttribute(CommonAttribute.ORDERS_HISTORY, orders);
        request.getRequestDispatcher(ViewOrderHistoryRequest.VIEW).forward(request, response);
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
