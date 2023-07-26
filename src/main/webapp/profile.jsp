<%
if(session.getAttribute("session_id") == null) {
	response.sendRedirect("login.jsp");
}
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>facebook</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="styles/general.css">
<script src="https://kit.fontawesome.com/25e80115fd.js" crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:ital@0;1&display=swap" rel="stylesheet">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link rel="stylesheet" href="styles/profile.css">
<link rel="stylesheet" href="styles/posts.css">
<link rel="stylesheet" href="styles/nav.css">
<style>
	.likeColor {
		color: blue;
	}
</style>
</head>
<body>
	<jsp:include page="nav.jsp"/>
		
	<div class="profile-body">

    <div class="background-image">
      <div>
        <i class="fa-solid fa-camera-retro"></i>
        <h4>Add Cover Photo</h4>
      </div>
      
    </div>

    <!-- user info put here -->
    <div class="profile-user-info">

      <div>
        <div class="annoying-profile">

          <!-- profile image here -->
          <img src="profileImage/${currentUser.getImageName()}" alt="">
        </div>
        <div class="camera-icon">
          <i class="fa-solid fa-camera-retro"></i>
        </div>
      </div>

      <div>

        <!-- user name here -->
        <h1>${currentUser.getName()}</h1>
      </div>

      <div>
        
        <!-- change here -->
        <c:if test="${session_id == currentUser.getId()}">
        <div><a href="create_post.jsp">+ Add to story</a></div>
        <div><a href="edit_profile?id=${session_id}"><i class="fa-solid fa-pen"></i><p>Edit profile</p></a></div>
        <div><a href="logout?id=${session_id}" style="color: black;">LogOut</a></div>
        </c:if>
        
      </div>
    </div>
	
	<div class="post-container">
	
		<c:forEach var="post" items="${userPosts}">
		<div class="post" id="${post.getId()}">
		
			<c:choose>
				<c:when test="${likeCount.containsKey(post.getId())}">
					<c:set var="count" value="${likeCount[post.getId()]}"></c:set>
				</c:when>
				<c:otherwise>
					<c:set var="count" value="0"></c:set>
				</c:otherwise>
			</c:choose>						            	
      
        <div class="post-info">
          <div class="user-info">
          				
				<a href="profile?profile_id=${currentUser.getId()}"><img src="profileImage/${currentUser.getImageName()}" alt="${currentUser.getImageName()}"></a>
            <div class="username-time">
              	<a href="profile?profile_id=${currentUser.getId()}" class="text-hover-affect">${currentUser.getName()}</a>
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
	
	</div>
	
	
	<script>
		$(document).ready(function() {
			$(".deletebtn").click(function() {
				let deletebtn = $(this);
				let postId = deletebtn.val();
				console.log("eee : " + postId + "")
				console.log(postId);
				$.ajax({
					url:"deletePost",
					method:"POST",
					data: {
						id:postId
					},
					success:(function() {
						$("#" + postId + "").hide();
					})
				})
			})
		})
	</script>
	<script src="js/likeBtnClick.js"></script>
	<script src="js/search.js"></script>
</body>
</html>