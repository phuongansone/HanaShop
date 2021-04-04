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
    
    public static class GetSearchResultRequest {
        /** Action parameter */
        public static final String ACTION = "getSearchResult";
        
        /** Processing servlet */
        public static final String SERVLET = "GetSearchResultServlet";
        
        /** View jsp */
        public static final String VIEW = "pages/search-result.jspf";
        
        /**
         * Constructor
         */
        private GetSearchResultRequest() {
        }
    }
    
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
     * Error Page
     */
    public static class ErrorPage {
        
    }
}
