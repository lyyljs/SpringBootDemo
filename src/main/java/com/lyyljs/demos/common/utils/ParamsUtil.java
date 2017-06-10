package com.lyyljs.demos.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParamsUtil {

	/**
	 * 实数正则
	 */
	public static final String NumberRegPattern = "^-{0,}[0-9]{1,}(\\.{0,})[0-9]{0,}$";
	
	/**
	 * 非负整数正则
	 */
	public static final String NonnegativeIntegerRegPattern = "^(0|[1-9][0-9]*)$";
	
	/**
	 * 非负实数正则
	 */
	public static final String NonnegativeNumberRegPattern = "^[0-9]+(.[0-9]{0,})?$";
	
	/**
	 * 判断是否为实数
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str){
		return patternMatch(NumberRegPattern, str);
	}
	
	public static boolean isNonnegativeInteger(String str){
		return patternMatch(NonnegativeIntegerRegPattern, str);
	}
	
	public static boolean patternMatch(String regPattern, String str){
		Pattern pattern = Pattern.compile(regPattern);
		Matcher matcher = pattern.matcher(str);
		return matcher.find();
	}
	
	public static void main(String[] args){
		System.out.println(isNonnegativeInteger("-11"));
	}
}
