<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./static/layui/css/layui.css">
</head>
<body>
<script type="text/javascript" src="./static/js/jquery-1.11.1.js" charset="utf-8"></script>
<script type="text/javascript" src="./static/layui/layui.js" charset="utf-8"></script>

   <form action="./scholastic/uploadImage" method="post" enctype="multipart/form-data" >
      <img src="" width="300" heigth="300"  id="picimg">
      <input type="file" name="uploadfile" onchange="showPic(this);">
      <button type="submit" class="layui-btn">上传图片</button>
   </form>
   </br>
   </br>
   </br>
   </br>
   <form action="./scholastic/downImage" method="post" enctype="multipart/form-data" >
      <img src="" width="300" heigth="300"  id="picimg">
      <input type="file" name="downfile" onchange="showPic(this);">
      <button type="submit" class="layui-btn">下载图片</button>
   </form>
   
   
   
   
<script type="text/javascript">

function showPic(obj) { 
    var newPreview = document.getElementById('picimg');
    
    if (obj) { 
        //ie浏览器兼容 
        if (window.navigator.userAgent.indexOf("MSIE") >= 1) { 
            obj.select(); 
            newPreview.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);"; 
            var path = document.selection.createRange().text;
            var flag = judgeImgSuffix(path);
            if(flag){
                newPreview.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = document.selection.createRange().text;
            }else{
            	 console.log("要求图片格式为png,jpg,jpeg");
            }
            return; 
        } 
        //firefox浏览器兼容 
        else if (window.navigator.userAgent.indexOf("Firefox") >= 1) { 
            if (obj.files) { 
                newPreview.src = window.URL.createObjectURL(obj.files.item(0)); 
                return; 
            } 
            newPreview.src = obj.value; 
                    return; 
                } 
                newPreview.src = obj.value; 
                return; 
            } 
  }

        function judgeImgSuffix(path){
            var index = path.lastIndexOf('.');
            var suffix = "";
            if(index > 0){
                suffix = path.substring(index+1);
            }
            if("png"==suffix || "jpg"==suffix || "jpeg"==suffix || "PNG"==suffix || "JPG"==suffix || "JPEG"==suffix){
                return true;
            }else{
            	console.log("图片格式不正确");
                return false;
            }
        }


</script>







</body>
</html>