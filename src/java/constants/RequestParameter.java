package constants;

/**
 * Common Request Parameter
 * @author andtpse62827
 */
public class RequestParameter {
    /** Total */
    public static final String TOTAL = "total";
    
    /** Page */
    public static final String PAGE = "page";
    
    /**
     * Common parameters for User entity
     */
    public static class UserParam {
        
        /** Username */
        public static final String USERNAME = "username";
        
        /** Password */
        public static final String PASSWORD = "password";
        
        /**
         * private no argument constructor to avoid users from initializing
         */
        private UserParam() {
            
        }
    }
    
    /**
     * Common parameters for Food entity
     */
    public static class FoodParam {
        /** Food Id */
        public static final String FOOD_ID = "foodId";
        
        /** Food name */
        public static final String FOOD_NAME = "foodName";
        
        /** Path to the location of food's image */
        public static final String FOOD_IMAGE = "foodImage";
        
        /** Food description */
        public static final String DESCRIPTION = "description";
        
        /** Food price */
        public static final String FOOD_PRICE = "foodPrice";
        
        /** Category ID */
        public static final String CATEGORY_ID = "categoryId";
        
        /** Status */
        public static final String STATUS = "status";
        
        /** Food quantity */
        public static final String FOOD_QUANTITY = "foodQuantity";
        
        /** Create at */
        public static final String CREATE_AT = "createAt";
        
        /** User created */
        public static final String USER_CREATED = "userCreated";
        
        /** Search result parameter */
        public static final String SEARCH_RESULT = "searchResult";
        
        /**
         * private no argument constructor to avoid users from initializing
         */
        private FoodParam() {
            
        }
    }
    
    /**
     * Common parameter for Category Parameter
     */
    public static class CategoryParam {
        /** Category Id */
        public static final String CATEGORY_ID = "categoryId";
        
        /** Category name */
        public static final String CATEGORY_NAME = "categoryName";
        
        /**
         * private no argument constructor to avoid users from initializing
         */
        private CategoryParam() {
            
        }
    }
}
