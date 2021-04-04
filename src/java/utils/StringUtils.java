package utils;

import java.text.SimpleDateFormat;

/**
 * String Utilities
 * @author andtpse62827
 */
public class StringUtils {
    /**
     * Get String
     * @param value
     * @param defaultValue
     * @return value
     */
    public static String getString(String value, String defaultValue) {
        return (value != null && value.length() > 0) ? value : defaultValue;
    }
    
    /**
     * Get Integer
     * @param value
     * @param defaultValue
     * @return integer value
     */
    public static int getInteger(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NullPointerException | NumberFormatException e) {
            return defaultValue;
        }
    }
    
    /**
     * Get Double
     * @param value
     * @param defaultValue
     * @return double value
     */
    public static double getDouble(String value, double defaultValue) {
        try {
            return Double.parseDouble(value);
        } catch (NullPointerException | NumberFormatException e) {
            return defaultValue;
        }
    }
    
    /**
     * Get date from string
     * @param date
     * @return date value
     */
    public String getDateFromString(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }
}
