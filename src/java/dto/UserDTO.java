package dto;

import java.io.Serializable;

/**
 * User Data Transfer Object
 * @author andtpse62827
 */
public class UserDTO implements Serializable{
    /** User ID */
    private int id;
    
    /** Username */
    private String username;
    
    /** Password */
    private String password;
    
    /** Email */
    private String email;
    
    /** Phone */
    private String phone;
    
    /** Role ID */
    private int roleId;
    
    /** Address */
    private String address;

    /**
     * No parameter constructor
     */
    public UserDTO() {
    }

    /**
     * Constructor
     * @param id User ID
     * @param username Username
     * @param password Password
     * @param email Email
     * @param phone Phone
     * @param roleId Role ID
     * @param address Address
     */
    public UserDTO(int id, String username, String password, String email, String phone, int roleId, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.roleId = roleId;
        this.address = address;
    }

    /**
     * Constructor
     * @param username Username
     */
    public UserDTO(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
