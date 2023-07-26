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
  <link rel="stylesheet" href="styles/nav.css">
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

    <form action="create_post" method="post" enctype='multipart/form-data'>
      <label for="description">Description
        <textarea name="description" id="description"placeholder="About your post"></textarea>
      </label>
      
      <label for="image">Add post photo<input type="file" id="image" name="post_photo"></label>
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