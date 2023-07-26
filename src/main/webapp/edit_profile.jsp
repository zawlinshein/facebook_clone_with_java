<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="styles/editprofile.css">
</head>
<body>
	
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");// Http 1.1 (new version)

	response.setHeader("Pragma", "no-cache");// Http 1.0 (old version)
			
	response.setHeader("Expires", "0");// Proxies
	%>
	<c:if test="${session_id == null || user.getId() != session_id }">
		<c:redirect url="login.jsp"/>
	</c:if>

	
	<div class="edit-form-container">

    <form action="uploadprofile" method="POST" enctype="multipart/form-data">
      <input type="text" value="${user.getId()}" name="id" hidden>
      <input type="text" value="${user.getName()}" placeholder="Enter user name" name="name">
      <input type="text" value="${user.getPassword()}" placeholder="Enter password" name="password">
      <input type="email" value="${user.getEmail()}" placeholder="Enter email" name="email">
      <input type="text" value="${user.getImageName()}" name="oldImage" hidden>

        <div class="image-container">
          <img src="profileImage/${user.getImageName()}" alt="${user.getImageName()}">
        </div>
      
      <label for="image">Profile Image<input type="file" id="image" name="newImage"></label>
      <input type="submit" value="submit">
    </form>

  	</div>
	
	
</body>
</html>