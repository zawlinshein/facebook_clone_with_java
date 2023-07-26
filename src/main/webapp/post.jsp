<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:ital@0;1&display=swap" rel="stylesheet">
  <script src="https://kit.fontawesome.com/25e80115fd.js" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="styles/post.css">
  <link rel="stylesheet" href="styles/general.css">
  <link rel="stylesheet" href="styles/nav.css">
<style>
	.likeColor {
		color:blue;
	}
	body {
    	overflow: auto;
  	}
</style>
</head>
<body>	
<c:if test="${session_id == null}">
		<c:redirect url="login.jsp"/>
	</c:if>
	<input type="text" value="${post.getId()}" hidden id="postId">
	
	<div class="post-image-container">

    	<div class="left-div">
      		<a href="javascript:void(0);" onclick="window.history.back();" style="color:white;"><i class="fa-solid fa-xmark"></i></a>
      		<a href="home"><i class="fa-brands fa-facebook"></i> </a>
    	</div>

    	<div class="right-div">
      		<i class="fa-solid fa-magnifying-glass-plus"></i>
      		<i class="fa-solid fa-magnifying-glass-minus"></i>
      		<i class="fa-solid fa-up-right-and-down-left-from-center"></i>
    	</div>
		
		<c:if test="${post.getPostImageName() != '' }">
		<img src="postImages/${post.getPostImageName()}" class="post-image" alt="postImages/${post.getPostImageName()}">
		</c:if>
    	

    </div>
			
	<div class="post-right-container">
	
		<c:choose>
			<c:when test="${check}">				
				<c:set var="count" value="${likeCount}"/>
			</c:when>
			<c:otherwise>
				<c:set var="count" value="0"/>
			</c:otherwise>
		</c:choose>

      <div class="post-nav">

        <!-- nav bar -->
        <div class="right-session">
          <a href="">
            <div class="foreground">
              <div class="right-session-icon">
                <i class="fa-solid fa-ellipsis-vertical"></i>
              </div>
            </div>
          </a>
          <a href="">
            <div class="foreground">
              <div class="right-session-icon">
                <i class="fa-brands fa-facebook-messenger"></i>
              </div>
            </div>
          </a>
          <a href="">
            <div class="foreground">
              <div class="right-session-icon">
                <i class="fa-solid fa-bell"></i>
              </div>
            </div>
          </a>
          <a href="profile?profile_id=${currentUserObject.getId()}">
            <div class="foreground">
              <div class="right-session-icon">  
                <img src="profileImage/${currentUserObject.getImageName()}" alt="${currentUserObject.getImageName()}">
              </div>
            </div>
          </a>
        </div>

      </div>

      <!-- user like comment -->
      <div class="all-user-feedback">

        <!-- user profile -->
        <div class="user-post-desc">
          <div class="posted-user">
            <a>
            <img src="profileImage/${postOwner.getImageName()}" alt="gg">
            </a>
            <div>
              <a href="profile?profile_id=${postOwner.getId()}"><p>${postOwner.getName()}</p></a>
              <p><span class="text-hover-affect">1 h</span> &#x2e; <i class="fa-solid fa-earth-americas"></i></p>
            </div>
            <div><i class="fa-solid fa-ellipsis"></i></div>
          </div>

          <!-- post's descriptoin -->
          <p class="description">${post.getDescription()}</p>
        </div>

        <!-- like comment -->
        <div class="feedback-session">

          <!-- like count and button -->
          <div class="likeUnlike-upper">

            <div class="emotion-icon">
              <i class="fa-regular fa-thumbs-up" style="color: rgb(96, 96, 255);"></i>
              <i class="fa-brands fa-gratipay" style="color: rgb(226, 5, 68);"></i>
              <i class="fa-solid fa-face-laugh-squint" style="color: rgb(227, 227, 0);"></i>
              
              <span style="font-size: 14px; letter-spacing: -1px;" class="count">${count}</span>
              
            </div>

            <a href="">
              <div class="text-hover-affect">
                <p>29</p>
                <span>
                <i class="fa-regular fa-message" style="padding-top: 3px;"></i>
                </span>
              </div>
            </a>

          </div>

          <hr>

          <!-- like unlike btn -->
          <div class="feedback-button">
            <button class="likeUnlikeBtn">
            <c:choose>
				<c:when test="${check}">					
					<i class="fa-regular fa-thumbs-up likeColor like" style="padding-top: 3px;"></i>
					<p class="like likeColor">Like</p>
				</c:when>
				<c:otherwise>				
					<i class="fa-regular fa-thumbs-up like" style="padding-top: 3px;"></i>
					<p class="like">Like</p>
				</c:otherwise>
			</c:choose>
            
              </button>
            <div><i class="fa-regular fa-message"  style="padding-top: 3px;"></i><p>Comment</p></div>
            <div><i class="fa-solid fa-share"  style="padding-top: 3px;"></i><p>Share</p></div>
          </div>

          <hr>

        </div>
        
        <!-- post's comment -->
        <div class="comment-container">        
				
        <c:forEach var="comment" items="${allComments}">
        
          <div class="about-comment">
          
          <c:set var="relevantUser" value="${null}" scope="page" />
          
          	<c:forEach var="user" items="${commentedUsers}">
        		<c:if test="${comment.getUserId() == user.getId()}">
            		<c:set var="relevantUser" value="${user}" scope="page" />
        		</c:if>
   			</c:forEach>
			
			<c:if test="${not empty relevantUser}">
			
			<div>
              <a href="profile?profile_id=${relevantUser.getId()}"><img src="profileImage/${relevantUser.getImageName()}" alt="${relevantUser.getImageName()}"></a>
            </div>

            <div>
              <a href="profile?profile_id=${relevantUser.getId()}" style="color: black; margin-bottom: 5px;"><p>${relevantUser.getName()}</p></a>
				<p>${comment.getComment()}</p>
            </div>
            
			</c:if>
                          
          </div>
        </c:forEach>  
          

        </div>

      </div>

      <div class="comment-box-container">
      
        <img src="profileImage/${currentUserObject.getImageName()}" alt="${currentUserObject.getImageName()}">
        <div class="comment-box">
          <textarea rows="4" id="comments"></textarea>
          <button id="sendButton">send</button>
        </div>
        
      </div>

    </div>
	
	
	<script src="https://js.pusher.com/7.2/pusher.min.js"></script>
	<script>
	
		$(document).ready(function() {
		    Pusher.logToConsole = true;
		    var pusher = new Pusher('555a64f8f69f9b757123', {
		      	cluster: 'ap1'
		    });
		
			var channel = pusher.subscribe('my-channel');
		
			channel.bind('my-event', function(data) {
				
					var object = JSON.parse(data);
					var user = object.user;
					var comment = object.comment;
					
					console.log(user);
					console.log(comment);
					console.log(typeof user);
					console.log(typeof user.id);
					
					$(".comment-container").append(
							'<div class="about-comment">' + 
								"<div>" +
									"<a href=\"profile?profile_id=" + user.id +"\"><img src=\"profileImage/"+user.imageName+"\" alt=\""+user.imageName+"\"></a>" +
								"</div>" + 
								"<div>" +
					              "<a href=\"profile?profile_id="+user.id+"\"style=\"color: black; margin-bottom: 5px;\"><p>"+user.name+"</p></a>" +
					              "<p>"+comment.comment+"</p>" +
					            "</div>" +
							"</div>"
					);
				
			})
			
			$("#sendButton").click(function() {
				
				let id = $("#postId").val();
				let commentText = $("#comments").val();
				let trimtext = $.trim(commentText);
				
				$.ajax({
					url:'livecomment',
					method:"POST",
					data: {
						id: id,
						comment: trimtext
					},
					success:function(response) {
						$("#comments").val("");
					}
				})
				
			})
			
			$(".likeUnlikeBtn").click(function() {
				
				let id = $("#postId").val();
				$(".like").toggleClass("likeColor");
				
				
				
				$.ajax({
					url:"likeunlike",
					method:"POST",
					data: {
						id: id
					},
					success:function() {
						
						let count =	parseInt($(".count").text());
						
						console.log(count);
						
						if($(".like").hasClass("likeColor")) {
							count++;
						} else {
							count--;
						}
						$(".count").text(count);
					}
						
				})
				
			})
			
		})
	
	</script>
</body>
</html>