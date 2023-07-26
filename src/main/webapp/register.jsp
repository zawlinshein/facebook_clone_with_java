<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
  <style>
    body {
      height: 100vh;
      padding: 0;
      margin: 0;
      font-family:'Times New Roman', Times, serif;
      display: flex;
      justify-content: center;
      align-items: center;
    }
    p {
      margin-top: 0;
      margin-bottom: 0;
    }
    .login-container {
      width: 400px;
      display: flex;
      flex-direction: column;
      border-radius: 5px;
      box-shadow: 0 2px 5px rgba(0, 0, 0, .1), 0 -8px 20px rgba(0, 0, 0, .1);
      padding: 18px;
    }
    .login-container > form {
      display: flex;
      flex-direction: column;
      width: 100%;
      row-gap: 20px;
      margin-bottom: 10px;
    }
    input {
      padding: 15px;
      border-radius: 5px;
      border: 1px solid rgb(16, 16, 16);
    }
    [type="submit"] {
      background-color: blue;
      color: white;
      font-size: 1.35rem;
      padding: 10px 15px;
      cursor: pointer;
    }
    button {
      width: 195px;
      height: 48px;
      margin: auto;
      cursor: pointer;
      border: none;
      font-size: 1rem;
      border-radius: 5px;
      background-color: rgb(66, 183, 42);
      color: white;
      font-weight: bold;
      padding: 0 16px;

    }
    .loginpage {
      display: flex;
      padding: 0 100px;
    }
    .loginpage > div:first-of-type {
      display: flex;
      flex-direction: column;
      justify-content: center;
      column-gap: 0px;
      width: 550px;
    }
    .loginpage > div:first-of-type > p:first-of-type {
      color: blue;
    }
    [type="file"] {
      display: none;
    }
    label {
      border: 1px solid black;
      padding: 15px;
      border-radius: 5px;
      cursor: pointer;
      color: rgba(red, green, blue, .2);
    }
  </style>
</head>
<body>
  <div class="loginpage">
    <div>
      <p style="font-size: 4rem; font-weight: bolder; letter-spacing: -1px;">Sign up</p>
    </div>
    <div  class="login-container">
    
      <form action="register" method="POST" enctype="multipart/form-data">
        <input type="text" placeholder="Enter user name" required name="userName">
        <input type="password" placeholder="Password" required name="userPassword">
        <input type="password" placeholder="Confirm password" required name="confirmpw">
        <input type="email" placeholder="Enter email" required name="email">
        <label for="profilepitcher">Profile pitcher<input type="file" id="profilepitcher" name="profileImage"></label>
        <input type="submit" value="Register" >
      </form>
      
      <a href="login.jsp" style="text-decoration: none; text-align: center; margin-bottom: 14px; margin-top: 14px;">login?</a>
      
    </div>
    
  </div>

</body>
</html>