package com.util;

/**
 *
 * @author lucas
 */
public class Format {

    private Format(){}
    
    public static Long getLong(String value){
        try {
            if (value != null && !value.isEmpty()) {
                return new Long(value.replaceAll("[\\D]", "").trim());
            }
        } catch (NumberFormatException e) {
            return null;
        }
        return null;
    }
    public static Integer getInteger(String value){
        try {
            if (value != null && !value.isEmpty()) {
                return new Integer(value.replaceAll("[\\D]", "").trim());
            }
        } catch (NumberFormatException e) {
            return null;
        }
        return null;
    }
}
