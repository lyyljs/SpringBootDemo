package com.lyyljs.demos.common.logger;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAdvice {
	private Logger logger = Logger.getLogger(this.getClass());

	@Before("execution(* com.lyyljs..*(..)) && @annotation(methodDescription)")
	public void addBeforeLogger(JoinPoint joinPoint, MethodDescription methodDescription) {
		logger.info("执行 " + methodDescription.description() + " 开始");
		logger.info(joinPoint.getSignature().toString());
		logger.info(parseParames(joinPoint.getArgs()));
	}
	
	@AfterReturning("within(com.lyyljs..*) && @annotation(methodDescription)")
	public void addAfterReturningLogger(JoinPoint joinPoint, MethodDescription methodDescription) {
		logger.info("执行 " + methodDescription.description() + " 结束");
	}
	
	@AfterThrowing(pointcut = "within(com.lyyljs..*) && @annotation(methodDescription)", throwing = "ex")
	public void addAfterThrowingLogger(JoinPoint joinPoint, MethodDescription methodDescription, Exception ex) {
		logger.error("执行 " + methodDescription.description() + " 异常", ex);
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
