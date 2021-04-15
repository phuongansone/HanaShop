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
        
        /** Update at */
        public static final String UPDATE_AT = "updateAt";
        
        /** Id of the last user updated */
        public static final String USER_UPDATED = "userUpdated";
        
        /** Id of the user created */
        public static final String USER_CREATED = "userCreated";
        
        /** Username of the last user updated */
        public static final String USERNAME_UPDATE = "updateUser";
        
        /** Username of the user that create the record */
        public static final String USERNAME_CREATE = "createUser";
        
        /** Search result parameter */
        public static final String SEARCH_RESULT = "searchResult";
        
        /** Keyword to search for food */
        public static final String KEYWORD = "keyword";
        
        /**
         * private no argument constructor to avoid users from initializing
         */
        private FoodParam() {
            
        }
    }
    
    /**
     * Common parameter for Category
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
    
    /**
     * Common parameter for price range
     */
    public static class PriceRangeParam {
        
        public static final String ID = "id";
        
        public static final String NAME = "name";
        
        public static final String FROM = "from";
        
        public static final String TO = "to";
        
        public static final String RANGE_ID = "rangeId";
        
        /**
         * private no argument constructor to avoid users from initializing
         */
        private PriceRangeParam() {
            
        }
    }
    
    public static class StatusParam {
        public static final String CODE = "code";
        public static final String NAME = "name";
        
        private StatusParam() {
            
        }
    }
    
    public static class OrderParam {
        public static final String ORDER_ID = "orderId";
        public static final String CREATE_ORDER_AT = "createOrderAt";
        public static final String USER_ID = "userId";
        public static final String TOTAL_PRICE = "totalPrice";
        public static final String PHONE = "phone";
        public static final String EMAIL = "email";
        public static final String ADDRESS = "address";
        public static final String USERNAME = "username";
        public static final String PAYMENT_METHOD = "paymentMethod";
        public static final String PAYMENT_METHOD_NAME = "paymentMethodName";
        public static final String DATE = "date";
        public static final String KEYWORD = "keyword";
        
        private OrderParam() {
            
        }
    }
    
    public static class CartParam {
        public static final String QUANTITY = "quantity";
        public static final String ORDER_ID = "orderId";
        
        private CartParam() {
            
        }
    }
}
