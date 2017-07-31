/**
 * 
 */

$(function(){
 $(".thumbs a").click(function(){
	var largeImg = $(this).attr("href");
	var largeAlt=$(this).attr("title");
	$("#largeImg").attr({
		src:largeImg,
		alt:largeAlt
	})
	return false;
 });
 
 
 
 $("#upfile").click(function(){
	 /*var formData = new FormData($( "#myfile" )[0]);*/
	 
	 var formData = new FormData();
     formData.append("myfile", document.getElementById("myfile").files[0]);
//     console.log(formData),
	 $.ajax({
		 url:"UploadFile",
		 type:"post",
		 data:formData,
		 
		 /**
          *必须false才会自动加上正确的Content-Type
          */
         contentType: false,
          /**
          * 必须false才会避开jQuery对 formdata 的默认处理
          * XMLHttpRequest会对 formdata 进行正确的处理
          */
         processData: false,
		 success:function(res){
			 console.log(res)
		 },
		 
	 })
	 
	 
	 
 });
 
 
})