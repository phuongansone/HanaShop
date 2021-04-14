package dto;

import java.io.Serializable;
import java.sql.Date;

/**
 * Food Data Transfer Object
 * @author andtpse62827
 */
public class FoodDTO implements Serializable{
    /** Food ID */
    private int foodId;
    
    /** Food name */
    private String foodName;
    
    /** Food image */
    private String foodImage;
    
    /** Description */
    private String description;
    
    /** Price */
    private int foodPrice;
    
    /** Create date */
    private Date createAt;
    
    /** Update date */
    private Date updateAt;
    
    /** Category */
    private CategoryDTO category;
    
    /** Status */
    private boolean status;
    
    /** Food Quantity */
    private int foodQuantity;
    
    /** User created */
    private UserDTO userCreated;
    
    /** User updated */
    private UserDTO userUpdate;

    /**
     * Constructor with no param
     */
    public FoodDTO() {
    }

    /**
     * Constructor
     * @param foodId
     * @param foodName
     * @param foodImage
     * @param description
     * @param foodPrice
     * @param createAt
     * @param updateAt
     * @param category
     * @param status
     * @param foodQuantity
     * @param userCreated
     */
    public FoodDTO(int foodId, String foodName, String foodImage, String description, 
            int foodPrice, Date createAt, Date updateAt, CategoryDTO category, 
            boolean status, int foodQuantity, UserDTO userCreated) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodImage = foodImage;
        this.description = description;
        this.foodPrice = foodPrice;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.category = category;
        this.status = status;
        this.foodQuantity = foodQuantity;
        this.userCreated = userCreated;
    }

    /**
     * Constructor
     * @param foodId
     * @param foodName
     * @param foodImage
     * @param description
     * @param foodPrice
     * @param createAt
     * @param updateAt
     * @param category
     * @param status
     * @param foodQuantity
     * @param userCreated
     * @param userUpdated 
     */
    public FoodDTO(int foodId, String foodName, String foodImage, 
            String description, int foodPrice, Date createAt, 
            Date updateAt, CategoryDTO category, boolean status, 
            int foodQuantity, UserDTO userCreated, UserDTO userUpdated) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodImage = foodImage;
        this.description = description;
        this.foodPrice = foodPrice;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.category = category;
        this.status = status;
        this.foodQuantity = foodQuantity;
        this.userCreated = userCreated;
        this.userUpdate = userUpdated;
    }
    
    

    /**
     * Constructor
     * @param foodName
     * @param foodImage
     * @param description
     * @param foodPrice
     * @param category
     * @param status
     * @param foodQuantity
     * @param userCreated 
     */
    public FoodDTO(String foodName, String foodImage, String description, 
            int foodPrice, CategoryDTO category, boolean status, 
            int foodQuantity, UserDTO userCreated) {
        this.foodName = foodName;
        this.foodImage = foodImage;
        this.description = description;
        this.foodPrice = foodPrice;
        this.category = category;
        this.status = status;
        this.foodQuantity = foodQuantity;
        this.userCreated = userCreated;
    }

    /**
     * Constructor with without time and user
     * @param foodName
     * @param foodImage
     * @param description
     * @param foodPrice
     * @param category
     * @param status
     * @param foodQuantity
     */
    public FoodDTO(String foodName, String foodImage, String description, int foodPrice, CategoryDTO category, boolean status, int foodQuantity) {
        this.foodName = foodName;
        this.foodImage = foodImage;
        this.description = description;
        this.foodPrice = foodPrice;
        this.category = category;
        this.status = status;
        this.foodQuantity = foodQuantity;
    }
    
    
    

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(int foodPrice) {
        this.foodPrice = foodPrice;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(int foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    public UserDTO getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(UserDTO userCreated) {
        this.userCreated = userCreated;
    }

    public UserDTO getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(UserDTO userUpdate) {
        this.userUpdate = userUpdate;
    }
}
