package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.UserDTO;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import services.UserService;
import constants.CommonAttribute;
import constants.RequestMappingConstants.LoginRequest;
import constants.RequestMappingConstants.SearchItemRequest;
import constants.RequestParameter;
import enums.CRUDStatus;

/**
 * Login Servlet
 * @author andtpse62827
 */
public class LoginServlet extends HttpServlet {

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
        request.getRequestDispatcher(LoginRequest.VIEW).forward(request, response);
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
        
        String username = request.getParameter(RequestParameter.UserParam.USERNAME);
        String password = request.getParameter(RequestParameter.UserParam.PASSWORD);
        HttpSession session = request.getSession();
        String url = LoginRequest.VIEW;
        
        try {
            UserDTO userDTO = null;
            
            if (username != null && password != null) {
                UserService userService = new UserService();
                userDTO = userService.getUser(username, password);
            }
            
            if (userDTO != null) {
                url = SearchItemRequest.SERVLET;
                request.setAttribute(CommonAttribute.LOGIN_STATUS, CRUDStatus.SUCCESS);
                session.setAttribute(CommonAttribute.USER, userDTO);
            } else {
                request.setAttribute(CommonAttribute.LOGIN_STATUS, CRUDStatus.FAIL);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }
        
        request.getRequestDispatcher(url).forward(request, response);
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
