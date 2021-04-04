package dto;

import java.io.Serializable;

/**
 * Category Data Transfer Object
 * @author andtpse62827
 */
public class CategoryDTO implements Serializable{
    /** Category ID */
    private int categoryId;
    
    /** Category name */
    private String categoryName;

    /**
     * Constructor with no parameter
     */
    public CategoryDTO() {
    }

    /**
     * Constructor
     * @param categoryId Category ID
     * @param categoryName Category name
     */
    public CategoryDTO(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    /**
     * Constructor
     * @param categoryId Category ID
     */
    public CategoryDTO(int categoryId) {
        this(categoryId, null);
    }
    
    /**
     * Constructor
     * @param categoryName Category name
     */
    public CategoryDTO(String categoryName) {
        this(0, categoryName);
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
}
