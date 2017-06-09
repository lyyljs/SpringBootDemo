package com.lyyljs.demos.exception;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lyyljs.demos.common.Const;
import com.lyyljs.demos.domain.result.ResponseData;
import com.lyyljs.demos.domain.result.ResponseMsg;

@ControllerAdvice
public class GlobalExceptionHandle {
	private Logger logger = Logger.getLogger(this.getClass());

//	@ExceptionHandler(value = Exception.class)
//    public ModelAndView defaultErrorHandler(Exception e, HttpServletRequest request) throws Exception {
//        logger.info("请求地址：" + request.getRequestURL());
//        ModelAndView mav = new ModelAndView(Const.DEFAULT_ERROR_VIEW);
//        //mav.addObject("exception", e);
//        logger.error("异常信息：",e);
//        return mav;
//    }
	
	@ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseData jsonErrorHandler(HttpServletRequest req, Exception e) throws Exception {
		logger.info("请求地址：" + req.getRequestURL());
		logger.error("异常信息：",e);
		return new ResponseData(ResponseMsg.FAILED, e);
    }
}
