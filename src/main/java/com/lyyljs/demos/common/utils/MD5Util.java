package com.lyyljs.demos.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	
	public static String encrypt(String str) {
	    String newstr = null;
	    try{
	      MessageDigest md5 = MessageDigest.getInstance("MD5");
	      newstr = toHexString(md5.digest(str.getBytes("utf-8")));
	    }catch (NoSuchAlgorithmException e){
	      e.printStackTrace();
	    }catch (UnsupportedEncodingException e){
	      e.printStackTrace();
	    }
	    return newstr;
	}
	
	public static String toHexString(byte[] b) {
	    StringBuffer hexString = new StringBuffer();
	    for (int i = 0; i < b.length; i++){
	      String plainText = Integer.toHexString(0xFF & b[i]);
	      if (plainText.length() < 2) {
	        plainText = "0" + plainText;
	      }
	      hexString.append(plainText);
	    }
	    return hexString.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(encrypt("root"));
	}
}
