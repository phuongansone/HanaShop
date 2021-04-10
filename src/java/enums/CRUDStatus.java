package enums;

/**
 * Status of CRUD Operation
 * @author andtpse62827
 */
public enum CRUDStatus {
    SUCCESS,
    FAIL;
    
    public String getValue() {
        return this.toString();
    }
}
