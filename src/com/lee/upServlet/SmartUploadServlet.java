package com.lee.upServlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

public class SmartUploadServlet extends HttpServlet {


	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doPost(req,resp);
	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//设置文件上传路径
		String filePath=getServletContext().getRealPath("/")+("images");
		File file=new File(filePath);
		if(!file.exists()){
			file.mkdir();
		}
		
		SmartUpload su = new SmartUpload();
		//初始化对象
		su.initialize(getServletConfig(), req, resp);
		//设置上传文件大小为10M
		su.setMaxFileSize(1024*1024*10);
		//设置所有文件大小
		su.setTotalMaxFileSize(1024*1024*100);
		//设置允许上传文件类型
		su.setAllowedFilesList("txt,jpg,gif");
		String result = "上传成功";
		//设置禁止3上传文件类型
		try {
			su.setDeniedFilesList("rar,jsp,js");
			su.upload();
			int count = su.save(filePath);
			System.out.println("上传成功"+count+"个文件！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if(e.getMessage().indexOf("1015")!=-1){
				result="上传失败：文件类型不正确！";
			}else if(e.getMessage().indexOf("1010")!=-1){
				result="上传失败，文件类型不正确！";
			}else if(e.getMessage().indexOf("1105")!=-1){
				result="上传失败，文件超过允许最大值！";
			}else if(e.getMessage().indexOf("1110")!=-1){
				result="上传失败，上传文件总大小超过允许总量最大值！";
			}
			e.printStackTrace();
		}
		
		
		for(int i =0; i < su.getFiles().getCount(); i++){
			com.jspsmart.upload.File tempFile = su.getFiles().getFile(i);
			System.out.println("---------------------------");
			System.out.println("表单当中name属性值：" + tempFile.getFieldName());
			System.out.println("上传文件名：" + tempFile.getFileName());
			System.out.println("上传文件长度:" + tempFile.getSize());
			System.out.println("上传文件的拓展名：" + tempFile.getFileExt());
			System.out.println("上传文件的全名：" + tempFile.getFilePathName());
			System.out.println("---------------------------");
		}
		
		req.setAttribute("result", result);
		req.getRequestDispatcher("jsp/02.jsp").forward(req, resp);
		
	}


}
