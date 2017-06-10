package com.lyyljs.demos.common.logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.lyyljs.demos.common.utils.ParamsUtil;
import com.lyyljs.demos.common.utils.TypeUtil;
import com.lyyljs.demos.domain.result.ResponseData;
import com.lyyljs.demos.domain.result.ResponseMsg;
import com.lyyljs.demos.exception.AnnotationException;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@Component
@Aspect
public class ParamsCheckAdvice {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Around("execution(* com.lyyljs..*(..)) && @annotation(apiImplicitParams) ")
	public Object paramsCheck(ProceedingJoinPoint pJoinPoint, ApiImplicitParams apiImplicitParams) throws Throwable{
		logger.info("Params Check Start");
		Object[] parames = pJoinPoint.getArgs();
		int index = 0;
		int length = apiImplicitParams.value().length;
		
		for (int i = 0; i < parames.length; i++) {
			Object obj = parames[i];
			if (index < length){
				ApiImplicitParam apiImplicitParam = apiImplicitParams.value()[index];
				
				if (obj == null){
					// Add Default Value With Annotation
					if (StringUtils.isNotBlank(apiImplicitParam.defaultValue())){
						parames[i] = generateDefaultValue(apiImplicitParam);
					}else{
						// Parameter Required
						if (apiImplicitParam.required()){
							return new ResponseData(ResponseMsg.ParameterMissing, apiImplicitParam.name());
						}
					}
				}else{
					
					// do with allowableValues
					if (!checkAllowableValues(obj, apiImplicitParam.allowableValues())){
						return new ResponseData(ResponseMsg.ParameterError, apiImplicitParam.name());
					}
					
					// do with regular expressions
					if (!checkRegPassPattern(obj, apiImplicitParam.regPassPattern()) 
							|| !checkRegFilterPattern(obj, apiImplicitParam.regFilterPattern())){
						return new ResponseData(ResponseMsg.ParameterError, apiImplicitParam.name());
					}
					
				}
			}
			index += 1;
		}
		logger.info("Params Check End");
		Object returnValue = pJoinPoint.proceed(parames);
		
		return returnValue;
	}
	
	private boolean checkRegFilterPattern(Object obj, String[] regs){
		if (regs.length == 0){
			return true;
		}
		String str = obj.toString();
		for (String reg : regs){
			if (ParamsUtil.patternMatch(reg, str)){
				return false;
			}
		}
		return true;
	}
	
	private boolean checkRegPassPattern(Object obj, String[] regs){
		if (regs.length == 0){
			return true;
		}
		String str = obj.toString();
		for (String reg : regs){
			if (!ParamsUtil.patternMatch(reg, str)){
				System.out.println("unpass find");
				return false;
			}
		}
		return true;
	}
	
	/**
	 * check AllowableValues
	 * @param obj
	 * @param allowValues
	 * @return
	 * @throws AnnotationException
	 */
	private boolean checkAllowableValues(Object obj, String allowValues) throws AnnotationException{
		if (StringUtils.isBlank(allowValues)){
			return true;
		}
		String numberRegPattern = "-{0,}[0-9]{1,}(\\.{0,})[0-9]{0,}";
		Pattern numberReg = Pattern.compile(numberRegPattern);
		String allowableValues[] = allowValues.split(",");
		
		if (TypeUtil.isNumberType(obj) && 
				allowValues.contains("range")){ //do with range
			if (allowableValues.length != 2)
				throw new AnnotationException("Annotation Range Error");
			
			for (int j = 0; j < 2; j++){
				Matcher matcher = numberReg.matcher(allowableValues[j]);
			    if (matcher.find()) {
			    	int k = 0;
			    	for(k = 0; k < matcher.groupCount(); k++){
			    		if (matcher.group(k) != null) break;
			    	}
			    	Double number = Double.parseDouble(matcher.group(k));
			    	Double objValue = Double.parseDouble(obj.toString());
			    	if (j == 0){
			    		if (objValue < number || 
				    			(allowableValues[j].contains("(") && objValue == number)){// be careful with accuracy
			    			return false;
				    	}
			    	}else{
			    		if (objValue  > number || 
				    			(allowableValues[j].contains(")") && objValue == number)){// be careful with accuracy
			    			return false;
				    	}
			    	}
			    }
			}
			return true;
		}// do with range end
		
		boolean eqFlag = false;
		
		for (int j = 0; j < allowableValues.length; j++){
			allowableValues[j] = StringUtils.strip(allowableValues[j]);
			if (TypeUtil.getType(obj).equals(TypeUtil.TYPE_BOOLEAN)){
				if ((allowableValues[j].equals("true") && (Boolean)obj)
						|| (allowableValues[j].equals("false") && !(Boolean)obj)){
					eqFlag = true;
					break;
				}
			}
			if (TypeUtil.isNumberType(obj)){
				if (Double.parseDouble(obj.toString()) ==  Double.parseDouble(allowableValues[j])){
					eqFlag = true;
					break;
				}
			}
			if (allowableValues[j].equals(obj)){
				eqFlag = true;
				break;
			}
		}
		return eqFlag;
	}
	
	private Object generateDefaultValue(ApiImplicitParam apiImplicitParam){
		if (apiImplicitParam.dataType().equalsIgnoreCase(TypeUtil.TYPE_INT)){
			return Integer.parseInt(apiImplicitParam.defaultValue());
		}else if (apiImplicitParam.dataType().equalsIgnoreCase(TypeUtil.TYPE_LONG)){
			return Long.parseLong(apiImplicitParam.defaultValue());
		}else if (apiImplicitParam.dataType().equalsIgnoreCase(TypeUtil.TYPE_DOUBLE)){
			return Double.parseDouble(apiImplicitParam.defaultValue());
		}else if (apiImplicitParam.dataType().equalsIgnoreCase(TypeUtil.TYPE_BOOLEAN)){
			return Boolean.parseBoolean(apiImplicitParam.defaultValue());
		}else if (apiImplicitParam.dataType().equalsIgnoreCase(TypeUtil.TYPE_STRING)){
			return apiImplicitParam.defaultValue();
		}
		return apiImplicitParam.defaultValue();
	}
}
