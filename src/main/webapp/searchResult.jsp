<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:ital@0;1&display=swap" rel="stylesheet">
<link rel="stylesheet" href="styles/general.css">
<link rel="stylesheet" href="styles/posts.css">
<link rel="stylesheet" href="styles/search_result.css">
<link rel="stylesheet" href="styles/posts.css">
<link rel="stylesheet" href="styles/nav.css">
<script src="https://kit.fontawesome.com/25e80115fd.js" crossorigin="anonymous"></script>
<style>
	.likeColor {
		color: blue;
	}
	body {
      display: flex;
      flex-direction: column!important;
    }
    h3 {
      margin-top: 10px;
      margin-bottom: 0;
    }
</style>
</head>
<body>

	<c:if test="${session_id == null}">
		<c:redirect url="login.jsp"/>
	</c:if>

	<jsp:include page="nav.jsp"/>
	
	<div class="people" style="margin-top: 75px;">
    	<h3>People</h3>
    	<c:forEach var="user" items="${userResult}">
    	<div class="people-result">
      		<a href="profile?profile_id=${user.getId}"><img src="profileImage/${user.getImageName()}" alt="${user.getImageName()}"></a>
      		<a href="profile?profile_id=${user.getId}"><p><span>${user.getName()}</span></p></a>
     	 	<button>Add Friend</button>
    	</div>
 		</c:forEach>
  	</div>
  	
  	<div class="posts-container">
	
	<c:forEach var="post" items="${postResult}">
	<div class="post" id="${post.getId()}" style="margin-top: 20px;">
			<c:choose>
				<c:when test="${checkLike[post.getId()]}">
					<c:set var="count" value="${likeCount[post.getId()]}"/>
				</c:when>
				<c:otherwise>
					<c:set var="count" value="0"/>
				</c:otherwise>
			</c:choose>						            	
      
        <div class="post-info">
          <div class="user-info">
          				
				<a href="profile?profile_id=${post.getUserId()}"><img src="profileImage/${post.getUserImage()}" alt="${post.getUserImage()}"></a>
            <div class="username-time">
              	<a href="profile?profile_id=${post.getUserId()}" class="text-hover-affect">${post.getUserName()}</a>
			  	<p><span class="text-hover-affect">1 h</span> &#x2e; <i class="fa-solid fa-earth-americas"></i></p>
            </div>
              
            <!-- update or delete on session id -->
            
            <div class="update-delete">
            <c:if test="${session_id == post.getUserId()}">
            	<a href="edit_post?id=${post.getId()}" title="edit your post"><i class="fa-solid fa-ellipsis"></i></a>
            	<button class="deletebtn" value="${post.getId()}" title="delete">X</button>
            </c:if>      
            </div>
                        
            <!-- here is post image and description -->
          </div>
          <div class="about-post">
            <p>
            ${post.getDescription()}
            </p>
            <a href="post?id=${post.getId()}" class="text-hover-affect">See more</a>
          </div>
        </div>
        
        <a href="post?id=${post.getId()}">
          <img src="postImages/${post.getPostImageName()}" alt="">
        </a>
        
        <div class="user-feedback">
          <div class="like-comment-count">
            <div>
			
              <i class="fa-regular fa-thumbs-up" style="color: rgb(96, 96, 255);"></i>
              <i class="fa-brands fa-gratipay" style="color: rgb(226, 5, 68);"></i>
              <i class="fa-solid fa-face-laugh-squint" style="color: rgb(227, 227, 0);"></i>
     				
			<span style="font-size: 14px; letter-spacing: -1px;" id="like${post.getId()}">${count}</span>
             
            </div>
            
            <a href="post.html?id=1">
            	<div class="text-hover-affect">
              		<span>
              			<i class="fa-regular fa-message" style="padding-top: 3px;"></i>
              		</span>
            	</div>
          	</a>
          	
          </div>
          <hr>
          <div class="feedback-button">
          
           
          
            <button class="likeUnlikeBtn" id="${post.getId()}">   
            
            <c:choose>
				<c:when test="${checkLike[post.getId()]}">
					<i class="fa-regular fa-thumbs-up likeColor like${post.getId()}" style="padding-top: 3px;"></i>
					<p class="like${post.getId()} likeColor">Like</p>
				</c:when>
				<c:otherwise>
					<i class="fa-regular fa-thumbs-up like${post.getId()}" style="padding-top: 3px;"></i>
					<p class="like${post.getId()}">Like</p>
				</c:otherwise>
			</c:choose>
            
            </button>
            
            <a href="post?id=${post.getId()}">
              <div>
                <i class="fa-regular fa-message"  style="padding-top: 3px;"></i>
                Comment
              </div>
            </a>
            <div><i class="fa-solid fa-share"  style="padding-top: 3px;"></i><p>Share</p></div>
          </div>
        </div>
        
      </div>
	
	</c:forEach>
  	  
  	</div>
	
	
	
	<script src="js/likeBtnClick.js"></script>
	<script src="js/search.js"></script>
</body>
</html>