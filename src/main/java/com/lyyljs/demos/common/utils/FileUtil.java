package com.lyyljs.demos.common.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class FileUtil {
	/**
	 * 获取文件类型
	 * @param fileName
	 * @return
	 */
	public static String getFileExtName(String fileName) {
        if (fileName!=null ) {
            int i = fileName.lastIndexOf('.');
            if (i>-1) {
                return fileName.substring(i+1).toLowerCase();
            }else {
                return "";
            }
        }else {
            return "";
        }
    }
	
	/**
	 * 上传文件
	 * @param fileBytes
	 * @param filePath
	 * @param fileName
	 * @throws Exception
	 */
	public static void uploadFile(byte[] fileBytes, String filePath, String fileName) throws Exception {	
		File targetFile = new File(filePath);  
        if(!targetFile.exists()){    
            targetFile.mkdirs();    
        }       
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(fileBytes);
        out.flush();
        out.close();
	}
	
	/**
	 * 下载文件
	 * @param filePath
	 * @param fileName
	 * @param response
	 * @throws Exception
	 */
	public static void downloadFile(String filePath, String fileName, HttpServletResponse response) throws Exception{
		File file = new File(filePath, fileName);
		if (file.exists()) {
			response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment;fileName=" +  fileName);
            byte[] buffer = new byte[1024];
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            try{
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            }catch(IOException e){
            	e.printStackTrace();
            }finally {
            	bis.close();
                fis.close();
            }
		}else{
			throw new FileNotFoundException("File Not Found");
		}
	}
}
