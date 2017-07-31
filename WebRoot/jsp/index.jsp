
<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>上传下载</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="Access-Control-Allow-Origin" content="*">
	
	<link rel="stylesheet" type="text/css" href="css/common.css">
	
	<script type="text/javascript" src="js/jquery-3.1.0.min.js"></script>
  </head>
  
  <body>
     <img id="previewImg" src="images/preview.jpg" width="80" height="80" />
  	 <!-- <form id="upfile"  enctype="multipart/form-data"> -->
  	 <br>
  	请选择图片：<input id="myfile" name="myfile" type="file" />
  	 
  	<input type="button" value="提交" id="upfile" />
  	<!-- </form> -->
  	<br>
  	下载：<a href="DownLoadFile?filename=test1.txt">test1.txt</a> &nbsp;&nbsp; ${errorResult}
  	
  	<div id="large"></div> 
  	
  	
  	<!-- <form action="">
  		请选择图片：<input id="myFile" name="myFile" type="file" />
  	
  	</form> -->
  	
  	
  	
  	<hr>
  	
    <h2>图片预览</h2>
    <p><img id="largeImg" src="images/img1-lg.jpg" alt="Large Image"/></p>
    <p class="thumbs">
    	<a href="images/img2-lg.jpg" title="Image2"><img src="images/img2-thumb.jpg"></a>
    	<a href="images/img3-lg.jpg" title="Image3"><img src="images/img3-thumb.jpg"></a>
    	<a href="images/img4-lg.jpg" title="Image4"><img src="images/img4-thumb.jpg"></a>
    	<a href="images/img5-lg.jpg" title="Image5"><img src="images/img5-thumb.jpg"></a>
    	<a href="images/img6-lg.jpg" title="Image6"><img src="images/img6-thumb.jpg"></a>
    </p>
  </body>
  
  <script type="text/javascript" src="js/myjs.js"></script>
</html>
