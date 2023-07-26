<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
  <nav>
    <!-- left -->

    <div class="left-session">
      <a href="home"><i class="fa-brands fa-facebook"></i></a>
      <div class="search-input-icon">
        <div class="search-icon">
          <i class="fa-solid fa-magnifying-glass"></i>
        </div>
        <input type="text" placeholder="Search Facebook" id="search-input" autocomplete="off">
      </div>
    </div>

    <!-- middle -->

    <div class="middle-session">
      <a href=""><i class="fa-solid fa-house"></i></a></i>
      <a href=""><i class="fa-solid fa-user-group"></i></a></i>
      <a href=""><i class="fa-solid fa-tv"></i></a></i>
      <a href=""><i class="fa-solid fa-people-group"></i></a></i>
      <a href=""><i class="fa-solid fa-gamepad"></i></a></i>
    </div>

    <!-- right -->

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
      <a href="profile?profile_id=${session_id}">
        <div class="foreground">
          <div class="right-session-icon">  
            <img src="profileImage/${currentUserObject.getImageName()}" alt="${currentUserObject.getImageName()}">
          </div>
        </div>
      </a>
    </div>

  </nav>

</body>	
</html>