package dto;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author andtpse62827
 */
public class OrderDTO implements Serializable {
    private int orderId;
    private String phone;
    private String email;
    private String address;
    private int userId;
    private String username;
    private int paymentMethod;
    private int totalPrice;
    private Date createOrderAt;

    public OrderDTO(int orderId, String phone, String email, String address, int userId, String username, int paymentMethod, int totalPrice, Date createOrderAt) {
        this.orderId = orderId;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.userId = userId;
        this.username = username;
        this.paymentMethod = paymentMethod;
        this.totalPrice = totalPrice;
        this.createOrderAt = createOrderAt;
    }

    public OrderDTO() {
    }

    public OrderDTO(String phone, String email, String address, int userId, 
            String username, int paymentMethod, int totalPrice) {
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.userId = userId;
        this.username = username;
        this.paymentMethod = paymentMethod;
        this.totalPrice = totalPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreateOrderAt() {
        return createOrderAt;
    }

    public void setCreateOrderAt(Date createOrderAt) {
        this.createOrderAt = createOrderAt;
    }

    
    
}
