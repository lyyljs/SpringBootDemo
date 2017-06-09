package com.lyyljs.demos.common.utils;

public class TypeUtil {
	
	public static final String TYPE_INT = "int";
	public static final String TYPE_INTEGER = "integer";
	public static final String TYPE_DOUBLE = "double";
	public static final String TYPE_BOOLEAN = "boolean";
	public static final String TYPE_STRING = "string";
	public static final String TYPE_LONG = "long";
	public static final String TYPE_CHAR = "char";
	public static final String TYPE_FLOAT = "float";
	
	public static String getType(Object o) {  
        return o.getClass().getSimpleName();  
    }  
      
    public static String getType(int i) {  
        return TYPE_INT;  
    }  
      
    public static String getType(long l) {  
        return TYPE_LONG;  
    }  
      
    public static String getType(boolean b) {  
        return TYPE_BOOLEAN;  
    }  
      
    public static String getType(char b) { 
        return TYPE_CHAR;  
    }  
      
    public static String getType(float f) {  
        return TYPE_FLOAT;  
    }  
      
    public static String getType(double d) {  
        return TYPE_DOUBLE;  
    }  
      
    public static String getType(String s) {  
        return TYPE_STRING;  
    }
    
    public static boolean isNumberType(String type){
    	if (type.equalsIgnoreCase(TYPE_INT) || type.equalsIgnoreCase(TYPE_DOUBLE) 
    			|| type.equalsIgnoreCase(TYPE_FLOAT) || type.equalsIgnoreCase(TYPE_LONG)
    			|| type.equalsIgnoreCase(TYPE_INTEGER))
    		return true;
    	return false;
    }
}
