package constants;

/**
 * Constants for request
 * @author andtpse62827
 */
public class RequestMappingConstants {
    /**
     * Login request
     */
    public static class LoginRequest {
        /** Action parameter */
        public static final String ACTION = "login";
        
        /** Processing servlet */
        public static final String SERVLET = "LoginServlet";
        
        /** View jsp */
        public static final String VIEW = "pages/authentication/login.jsp";
        
        /**
         * Constructor
         */
        private LoginRequest() {
        }
    }
    
    /**
     * Log out request
     */
    public static class LogoutRequest {
        /** Action parameter */
        public static final String ACTION = "logout";
        
        /** Processing servlet */
        public static final String SERVLET = "LogoutServlet";
        
        /**
         * Constructor
         */
        private LogoutRequest() {
        }
    }
    
    /**
     * Search Item request
     */
    public static class SearchItemRequest {
        /** Action parameter */
        public static final String ACTION = "searchitem";
        
        /** Processing servlet */
        public static final String SERVLET = "SearchItemServlet";
        
        /** View jsp */
        public static final String VIEW = "pages/search.jsp";
        
        /**
         * Constructor
         */
        private SearchItemRequest() {
        }
    }
    
    /**
     * Get search result
     */
    public static class GetSearchResultRequest {
        /** Action parameter */
        public static final String ACTION = "getSearchResult";
        
        /** Processing servlet */
        public static final String SERVLET = "GetSearchResultServlet";
        
        /** View jsp */
        public static final String VIEW = "pages/search-result.jsp";
        
        /**
         * Constructor
         */
        private GetSearchResultRequest() {
        }
    }
    
    /**
     * Create new food request
     */
    public static class CreateFoodRequest {
        /** Action parameter */
        public static final String ACTION = "createFood";
        
        /** Processing servlet */
        public static final String SERVLET = "CreateFoodServlet";
        
        /** View jsp */
        public static final String VIEW = "pages/admin/create-food.jsp";
        
        /**
         * Constructor
         */
        private CreateFoodRequest() {
        }     
    }
    
    /**
     * Get food list for manage food page request
     */
    public static class ManageFoodListRequest {
        /** Action parameter */
        public static final String ACTION = "manageFoodList";
        
        /** Processing servlet */
        public static final String SERVLET = "ManageFoodListServlet";
        
        /** View jsp */
        public static final String VIEW = "pages/admin/manage-food-list.jsp";
        
        /**
         * Constructor
         */
        private ManageFoodListRequest() {
        }
    }
    
    public static class ManageFoodRequest {
        /** Action parameter */
        public static final String ACTION = "manageFood";
        
        /** Processing servlet */
        public static final String SERVLET = "ManageFoodServlet";
        
        /** View jsp */
        public static final String VIEW = "pages/admin/manage-food.jsp";
        
        /**
         * Constructor
         */
        private ManageFoodRequest() {
        }
    }
    
    /**
     * Food detail request
     */
    public static class FoodDetailRequest {
        /** Action parameter */
        public static final String ACTION = "foodDetail";
        
        /** Processing servlet */
        public static final String SERVLET = "FoodDetailServlet";
        
        /** View jsp for admin */
        public static final String VIEW_ADMIN = "pages/admin/edit-food.jsp";
        
        /** View jsp for normal user */
        public static final String VIEW_USER = "pages/view-food.jsp";
        
        /**
         * Constructor
         */
        private FoodDetailRequest() {
        }
    }
    
    /**
     * Edit food request
     */
    public static final class EditFoodRequest {
        /** Action parameter */
        public static final String ACTION = "editFood";
        
        /** Processing servlet */
        public static final String SERVLET = "EditFoodServlet";
        
        /** View jsp for admin */
        public static final String VIEW = "pages/admin/edit-food.jsp";
        
        /**
         * Constructor
         */
        private EditFoodRequest() {
        }
    }
    
    public static final class DeleteFoodRequest {
        /** Action parameter */
        public static final String ACTION = "deleteFood";
        
        /** Processing servlet */
        public static final String SERVLET = "DeleteFoodServlet";
        
        /**
         * Constructor
         */
        private DeleteFoodRequest() {
        }
    }
    
    public static class AddToCartRequest {
        /** Action parameter */
        public static final String ACTION = "addToCart";
        
        /** Processing servlet */
        public static final String SERVLET = "AddToCartServlet";
        
        /**
         * Constructor
         */
        private AddToCartRequest() {
        }
    }
    
    public static class ViewCartRequest {
        /** Action parameter */
        public static final String ACTION = "viewCart";
        
        /** Processing servlet */
        public static final String SERVLET = "ViewCartServlet";
        
        /** View jsp for admin */
        public static final String VIEW = "pages/view-cart.jsp";
        
        /**
         * Constructor
         */
        private ViewCartRequest() {
        }
    }
    
    public static class RemoveFoodFromCartRequest {
        /** Action parameter */
        public static final String ACTION = "removeFoodFromCart";
        
        /** Processing servlet */
        public static final String SERVLET = "RemoveFoodFromCartServlet";
        
        /**
         * Constructor
         */
        private RemoveFoodFromCartRequest() {
        }
    }
    
    public static class UpdateCartRequest {
        /** Action parameter */
        public static final String ACTION = "updateCart";
        
        /** Processing servlet */
        public static final String SERVLET = "UpdateCartServlet";
        
        /**
         * Constructor
         */
        private UpdateCartRequest() {
        }
    }
    
    /**
     * Error Page
     */
    public static class ErrorPage {
        
    }
}
