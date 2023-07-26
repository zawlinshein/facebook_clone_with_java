<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%
	//if(session.getAttribute("session_id") == null) {
		//response.sendRedirect("login.jsp");
	//}
	//response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");// Http 1.1 (new version)

	//response.setHeader("Pragma", "no-cache");// Http 1.0 (old version)
			
	//response.setHeader("Expires", "0");// Proxies
%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="facebook-clone, training">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Roboto+Mono:ital@0;1&display=swap" rel="stylesheet">
<script src="https://kit.fontawesome.com/25e80115fd.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="styles/nav.css">
<link rel="stylesheet" href="styles/left-panel.css">
<link rel="stylesheet" href="styles/middle-panel.css">
<link rel="stylesheet" href="styles/general.css">
<link rel="stylesheet" href="styles/right-panel.css">
<link rel="stylesheet" href="styles/posts.css">
<title>Facekbook</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<style type="text/css">

	.likeColor {
		color: blue;
	}
	body {
      padding-top: 56px;
    }
    
</style>

</head>
<body>
	<c:if test="${session_id == null}">
		<c:redirect url="login.jsp"/>
	</c:if>
	<jsp:include page="nav.jsp"/>	
	
	<!-- online users -->
		
	<!-- all user posts -->
		
<section class="left-panel">

    <div class="upper-panel">
        <a href="profile?profile_id=${session_id}">
          <div class="upper-panel-container">
            <img src="profileImage/${currentUserObject.getImageName()}" alt="">
            <p>
             	${currentUserObject.getName()}
            </p>
          </div>
        </a>
        <a href="">
          <div class="upper-panel-container">
            <div class="friends-icon"></div>
            <p>
              Find Friends
            </p>
          </div>
        </a>
        <a href="">
          <div class="upper-panel-container">
            <div class="save-icon"></div>
            <p>
              Saved
            </p>
          </div>
        </a><a href="">
          <div class="upper-panel-container">
            <div class="watch-icon"></div>
            <p>
              Watch
            </p>
          </div>
        </a><a href="">
          <div class="upper-panel-container">
            <div class="memories-icon"></div>
            <p>
              Memories
            </p>
          </div>
        </a>
        <a href="">
          <div class="upper-panel-container">
            <div class="feed-icon"></div>
            <p>
              Feeds(most recent)
            </p>
          </div>
        </a><a href="">
          <div class="upper-panel-container">
            <div class="event-icon"></div>
            <p>
              Events
            </p>
          </div>
        </a><a href="">
          <div class="upper-panel-container">
            <div class="group-icon"></div>
            <p>
              Groups
            </p>
          </div>
        </a><a href="">
          <div class="upper-panel-container">
            <div class="ad-icon"></div>
            <p>
              Ads Manager
            </p>
          </div>
        </a><a href="">
          <div class="upper-panel-container">
            <div class="crisis-icon"></div>
            <p>
              Crisis Response
            </p>
          </div>
        </a><a href="">
          <div class="upper-panel-container">
            <div class="fundraiser-icon"></div>
            <p>
              Fundraiser
            </p>
          </div>
        </a>
        <a href="">
          <div class="upper-panel-container">
            <div class="seemore">
              <i class="fa-solid fa-chevron-down"></i>
            </div>
            <p>
              See More
            </p>
          </div>
        </a>
      </div>

      <div class="copyright">
        Privacy  · Terms  · Advertising  · Ad choices   · Cookies  · More · Meta © 2023
      </div>
    </section>
    <div class="left-dummy">
    </div>

  <!-- main panle here create posts here and put posts here  -->
  <main>

  <div class="create">

    <a href="creat_post.jsp">
      <div class="create-story">
        <div class="plus-icon">
          <i class="fa-solid fa-plus"></i>
        </div>
        <div class="create-text">
          <p>Create Story</p>
          <p>Share a photo or write something.</p>
        </div>
      </div>
    </a>

  </div>

  <div class="background-panel">

  
    <section class="posts">

      <!-- create posts -->
      <div class="on-my-mind">
        <div class="firstdiv">
          <a href="profile?profile_id=${session_id}"><img src="profileImage/${currentUserObject.getImageName()}" alt="profile"></a>
          <a href="create_post.jsp">
          <p>
            What's on your mind, Papaya?
          </p>
          </a>
        </div>
        <hr>
        <div class="seconddiv">
          <div>
            <img src="icons/liveicon.png" alt="">
            <p>Live video</p>
          </div>
          <div>
            <img src="icons/photo.png" alt="">
            <p>Photo/video</p>
          </div>
          <div>
            <img src="icons/smile.png" alt="">
            <p>Feeling/activity</p>
          </div>
        </div>
      </div>

      <!-- not important -->
      <div class="createroom">
        <div class="createroom-container">
          <div class="video-icon"></div>
          <p>Create Room</p>
        </div>
      </div>

      <!-- loop here show posts here -->
      
      <c:forEach var="post" items="${allPosts}">
      <div class="post">
      
      	<c:choose>
			<c:when test="${likedCountForPosts.containsKey(post.getId())}">
				<c:set var="likeCount" value="${likedCountForPosts[post.getId()]}"/>
			</c:when>
			<c:otherwise>
				<c:set var="likeCount" value="0"/>
			</c:otherwise>
		</c:choose>
      
        <div class="post-info">
          <div class="user-info">
          
          <c:forEach var="user" items="${allUserArrayList}">
			<c:if test="${user.getId() == post.getUserId()}">
				
				<a href="profile?profile_id=${user.getId()}"><img src="profileImage/${user.getImageName()}" alt="${user.getImageName()}"></a>
            <div class="username-time">
              	<a href="profile?profile_id=${user.getId()}" class="text-hover-affect">${user.getName()}</a>
			  	<p><span class="text-hover-affect">1 h</span> &#x2e; <i class="fa-solid fa-earth-americas"></i></p>
            </div>
			</c:if>
		</c:forEach>
              
            <!-- update or delete on session id -->
            
            <div class="update-delete">
            <c:if test="${session_id == post.getUserId()}">
            	<a href="edit_post?id=${post.getId()}" title="edit your post"><i class="fa-solid fa-ellipsis"></i></a>
            </c:if>      
              <a href="" title="hide"><i class="fa-solid fa-xmark"></i></a>
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
     				
			<span style="font-size: 14px; letter-spacing: -1px;" id="like${post.getId()}">${likeCount}</span>
             
            </div>
            
            <a href="post?id=${post.getId()}">
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
				<c:when test="${checkLiked[post.getId()]}">
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

    </section>
  </div>
  </main>

  <div class="right-panel-dummy"></div>

  <section class="right-panel">

      <!-- online users here -->
      <div class="online-user">

        <div class="option">
        <p>Contacts</p>
          <div class="option-button">
            <div><i class="fa-solid fa-video"></i></div>
            <div><i class="fa-solid fa-magnifying-glass"></i></div>
            <div><i class="fa-solid fa-ellipsis"></i></div> 
          </div>
        </div>

        <!-- here looop and show users -->
        
        <c:forEach var="user" items="${allUserArrayList}">
			<c:if test="${user.getId() != session_id }">
			
				<a href="profile?profile_id=${user.getId()}">
          			<div class="online-users">
            			<img src="profileImage/${user.getImageName()}" alt="">
            			<p>${user.getName()}</p>
          			</div>
          		</a>
          		
			</c:if>
		</c:forEach>

      </div>

      <hr>

      <div class="create-group">
        <p>Group Conversation</p>
     
          <div class="create-group-button">
            <div>
              <i class="fa-solid fa-plus"></i>
            </div>
            <p>Create New Group</p>
          </div>
      </div>

  </section>

<script src="js/search.js"></script>
<script src="js/likeBtnClick.js"></script>
</body>
</html>