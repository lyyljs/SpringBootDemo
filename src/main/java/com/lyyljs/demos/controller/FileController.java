package com.lyyljs.demos.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lyyljs.demos.common.utils.FileUtil;
import com.lyyljs.demos.domain.result.Response;
import com.lyyljs.demos.domain.result.ResponseMsg;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/file")
public class FileController {
	private static final Logger logger = Logger.getLogger(FileController.class);
	
	@Value("${file.upload.path}")
	private String fileUploadPath;
	
	@Value("${file.download.path}")
	private String fileDownloadPath;
	
	@ApiOperation(value="文件上传")
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public Response upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new Response(ResponseMsg.ParameterMissing);
        }
        logger.info("upload path:" + fileUploadPath);
        String fileName = file.getOriginalFilename();
        logger.info("upload file name：" + fileName);
        String suffixName = FileUtil.getFileExtName(fileName);
        logger.info("upload suffix name：" + suffixName);
        fileName = UUID.randomUUID().toString() + "." + suffixName;
        try {
			FileUtil.uploadFile(file.getBytes(), fileUploadPath, fileName);
			return new Response(ResponseMsg.SUCCESS);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
        return new Response(ResponseMsg.FAILED);
    }
    
	@ApiOperation(value="文件下载")
    @RequestMapping(value = "/download", method = RequestMethod.GET)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "fileName", value = "文件名称", 
				paramType = "query", required = true, dataType = "string",
				regFilterPattern = {"\\.{2,}", "\\/{1,}", "^.{20,}$"})
	})
    public Response downloadFile(String fileName, HttpServletResponse response){
        if (StringUtils.isNotBlank(fileName)) {
        	try{
        		FileUtil.downloadFile(fileDownloadPath, fileName, response);
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        }
        return null;
    }
}
