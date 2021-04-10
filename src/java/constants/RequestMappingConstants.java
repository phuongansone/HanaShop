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
    
    /**
     * Error Page
     */
    public static class ErrorPage {
        
    }
}
