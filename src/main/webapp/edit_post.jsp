<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");// Http 1.1 (new version)

	response.setHeader("Pragma", "no-cache");// Http 1.0 (old version)
			
	response.setHeader("Expires", "0");// Proxies
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:ital@0;1&display=swap" rel="stylesheet">
  <script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="styles/editprofile.css">
  <link rel="stylesheet" href="styles/general.css">
<style>
    textarea {
      resize: none;
      overflow: auto;
      max-height: 200px;
    }

    textarea::-webkit-scrollbar {
      display: none;
    }
  </style>
</head>
<body>
	<c:if test="${session_id == null}">
		<c:redirect url="login.jsp"/>
	</c:if>
	<div class="edit-form-container">

   <form method="POST" enctype="multipart/form-data" action="upload">
   
   		<input value="${post.getId()}" type="text" name="id" hidden>
   		<input value="${post.getUserId()}" type="text" name="userId" hidden>
   		<input value="${post.getPostImageName()}" type="text" name="oldPostImage" hidden>
   		
      <label for="description">Description
        <textarea name="description" id="description">${post.getDescription()}</textarea>
      </label>
        
        	<c:if test="${post.getPostImageName() != '' }">
        	<div class="image-container">
        		<img src="postImages/${post.getPostImageName()}" alt="${post.getPostImageName()}">
        	</div>
        	</c:if>
       
      
      <label for="image">Change post image<input type="file" id="image" name="newPostImage"></label>
      <input type="submit" value="submit">
    </form>
    
  </div>
  
  <script>
    $(document).ready(function() {
      $("textarea").on('input', function() {
      this.style.height = 'auto';
      this.style.height = (this.scrollHeight) + 'px';
      })
    })
  </script>
	
</body>
</html>