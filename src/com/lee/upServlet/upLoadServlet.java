package com.lee.upServlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;

public class upLoadServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req,resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		InputStream fileSource  = req.getInputStream();
		//得到服务器的根路径
		String rootPath = req.getRealPath("/");
		System.out.println(rootPath);
		//指定文件存放路径
		String realPath = rootPath + "/" + "upload";
		//定义文件存放的目录，注意 目录也是文件
		File file = new File(realPath);
		//如果目录不存在
		if (!file.isDirectory()) {
		//创建文件上传目录
		file.mkdirs();
		}
		File newFile = new File(realPath + "/" + "tempFile");
		//向newFile文件中写入数据
		//文件存放在Tomcat中项目的根目录下的upload文件夹中
		FileOutputStream outputStream  = new FileOutputStream(newFile);
		byte[] b = new byte[1024];
		int n;
		while((n=fileSource.read(b))!=-1){
		outputStream.write(b,0,n);
		}
		outputStream.close();
		fileSource.close();
		
		
		//获取上传文件的名称
		RandomAccessFile randomFile = new RandomAccessFile(newFile,"r");
		randomFile.readLine();
		String str = randomFile.readLine();
		str=new String(str.getBytes("ISO-8859-1"),"utf-8");
		int beginIndex = str.lastIndexOf("=") + 2;
		int endIndex = str.lastIndexOf("\"");
		String filename = str.substring(beginIndex, endIndex);
//		System.out.println("filename:" + filename);
		
		//重新定位文件内容开始位置
		randomFile.seek(0);
		long startPos=0;
		int i = 1;
		//获取文件开始位置
		while((n=randomFile.readByte())!=-1&&i<=4){
			if(n=='\n'){
				startPos=randomFile.getFilePointer();
				i++;
			}
		}
		startPos=randomFile.getFilePointer()-1;
		//获取文件内容结束位置
		randomFile.seek(randomFile.length());
		long endPos=randomFile.getFilePointer();
		int j=1;
		while(endPos>=0&&j<=2){
			endPos--;
			randomFile.seek(endPos);
			if(randomFile.readByte()=='\n'){
					j++;
			}
		}
		endPos = endPos-1;
		
		//设置保存上传文件的路径
//		String realPath = getServletContext().getRealPath("/") + "images";
//		File fileupload = new File(realPath);
//		if(!fileupload.exists()){
//			fileupload.mkdir();
//		}
		File saveFile = new File(realPath,filename);
		RandomAccessFile randomAccessFile = new RandomAccessFile(saveFile,"rw");
		//从临时文件当中读取文件内容（根据起止位置获取）
		randomFile.seek(startPos);
		while(startPos < endPos){
			randomAccessFile.write(randomFile.readByte());
			startPos = randomFile.getFilePointer();
		}
		//关闭输入输出流、删除临时文件
		randomAccessFile.close();
		randomFile.close();
		newFile.delete();
		
		

		Map<String,Object> dataMap=new HashMap<String,Object>();
		dataMap.put("ret", 0);
		dataMap.put("des", "成功");
		toJson(resp, dataMap);
		
	}
	
	
	
	
	public static void toJson(HttpServletResponse response, Object data) 
	        throws IOException {
	        Gson gson = new Gson();
	        String result = gson.toJson(data);
	        response.setContentType("text/json; charset=utf-8");
	        response.setHeader("Cache-Control", "no-cache"); //取消浏览器缓存
	        PrintWriter out = response.getWriter();
	        out.print(result);
	        out.flush();
	        out.close();
	    }
	
}
