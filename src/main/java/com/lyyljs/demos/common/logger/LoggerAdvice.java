package com.lyyljs.demos.common.logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.lyyljs.demos.common.utils.ContextUtil;
import com.lyyljs.demos.common.utils.TypeUtil;
import com.lyyljs.demos.domain.result.Response;
import com.lyyljs.demos.domain.result.ResponseData;
import com.lyyljs.demos.domain.result.ResponseMsg;
import com.lyyljs.demos.exception.AnnotationException;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Component
@Aspect
public class LoggerAdvice {
	private Logger logger = Logger.getLogger(this.getClass());

	@Before("execution(* com.lyyljs..*(..)) && @annotation(apiOperation)")
	public void addBeforeLogger(JoinPoint joinPoint, ApiOperation apiOperation) {
		logger.info("执行 " + apiOperation.value() + " 开始");
		logger.info(joinPoint.getSignature().toString());
		logger.info(parseParames(joinPoint.getArgs()));
	}
	
	@AfterReturning("within(com.lyyljs..*) && @annotation(apiOperation)")
	public void addAfterReturningLogger(JoinPoint joinPoint, ApiOperation apiOperation) {
		logger.info("执行 " + apiOperation.value() + " 结束");
	}
	
	@AfterThrowing(pointcut = "within(com.lyyljs..*) && @annotation(apiOperation)", throwing = "ex")
	public void addAfterThrowingLogger(JoinPoint joinPoint, ApiOperation apiOperation, Exception ex) {
		logger.error("执行 " + apiOperation.value() + " 异常", ex);
	}
	
	private String parseParames(Object[] parames) {
		if (null == parames || parames.length <= 0) {
			return "";
		}
		StringBuffer param = new StringBuffer("传入参数[{}] ");
		for (Object obj : parames) {
			param.append(ToStringBuilder.reflectionToString(obj)).append("  ");
		}
		return param.toString();
	}
}
