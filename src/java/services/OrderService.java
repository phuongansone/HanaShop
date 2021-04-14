package services;

import constants.CommonAttribute;
import constants.RequestParameter.OrderParam;
import dao.FoodDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import dto.CartItem;
import dto.OrderDTO;
import dto.UserDTO;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import utils.StringUtils;

/**
 *
 * @author andtpse62827
 */
public class OrderService {
    private final OrderDAO orderDAO;
    private final OrderDetailDAO orderDetailDAO;
    private final FoodDAO foodDAO;
    
    private static final int PAYMENT_UPON_DELIVERY = 1;

    public OrderService() {
        orderDAO = new OrderDAO();
        orderDetailDAO = new OrderDetailDAO();
        foodDAO = new FoodDAO();
    }
    
    public boolean saveOrder(HttpServletRequest request) 
            throws SQLException, ClassNotFoundException {
        HttpSession session = request.getSession();
        List<CartItem> cart = (List<CartItem>) session.getAttribute(CommonAttribute.CART);
        
        boolean saved = false;
        
        OrderDTO order = mapRequestToOrderDTO(request);
        int orderId = orderDAO.insertOrder(order);
        
        for (CartItem item : cart) {
            orderDetailDAO.insertOrderDetail(item, orderId);
            
            int foodId = item.getFood().getFoodId();
            int boughtQuantity = item.getQuantity();
            int remain = item.getFood().getFoodQuantity() - boughtQuantity;
            saved = foodDAO.updateFoodQuantity(foodId, remain);
        }
        
        return saved;
    }
    
    /**
     * map request object to order dto
     * @param request
     * @return 
     */
    private OrderDTO mapRequestToOrderDTO(HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute(CommonAttribute.USER);
        List<CartItem> cart = (List<CartItem>) session.getAttribute(CommonAttribute.CART);
        
        int userId = user.getId();
        int totalPrice = calculateTotalPrice(cart);
        String phone = request.getParameter(OrderParam.PHONE);
        String email = request.getParameter(OrderParam.EMAIL);
        String address = request.getParameter(OrderParam.ADDRESS);
        String username = request.getParameter(OrderParam.USERNAME);
        int paymentMethod = StringUtils
                .getInteger(
                        request.getParameter(OrderParam.PAYMENT_METHOD), 
                        PAYMENT_UPON_DELIVERY);
        
        return new OrderDTO(phone, email, address, userId, username, 
                paymentMethod, totalPrice);
    }
    
    public static int calculateTotalPrice(List<CartItem> cart) {
        int total = 0;
        
        if (cart != null) {
            for (CartItem item : cart) {
                total += item.getQuantity() * item.getFood().getFoodPrice();
            }            
        }
      
        return total;
    }
}
