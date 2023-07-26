<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="papaya's page, java project, java web development">
    <meta name="author" content="zawlinshein">
    <title>first java project</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="styles/generan.css">
    <link href="https://fonts.googleapis.com/css2?family=Source+Code+Pro:ital,wght@1,800&display=swap" rel="stylesheet">
</head>
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
    }
    button {
      width: 195px;
      height: 48px;
      margin: auto;
      margin-bottom: 20px;
      cursor: pointer;
      border: none;
      font-size: 1rem;
      border-radius: 5px;
      background-color: rgb(66, 183, 42);
      color: white;
      font-weight: bold;
      padding: 0 16px;
    }
    hr {
      margin-top: 10px;
      margin-bottom: 30px;
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
</style>
<body>
    
    <div class="loginpage">
    <div>
      <p style="font-size: 4rem; font-weight: bolder; letter-spacing: -1px;">facebook</p>
      <p style="font-size: 2rem; word-wrap: break-word;">Facebook helps you connect and share with the people in your life.</p>
    </div>
    <div  class="login-container">
      <form action="home" method="POST" >
        <input type="text" placeholder="Enter user name" name="userName" required>
        <input type="password" placeholder="Password" name="userPassword" required>
        <input type="submit" value="Log in">
      </form>
      <a href="" style="text-decoration: none; text-align: center; margin-bottom: 14px; margin-top: 14px;">Forgotten password?</a>
      <hr style="width: 100%;">
      <button class="registerlinkbtn">Create new account</button>
    </div>
    
  	</div>
    
    <script>  
    
    $(document).ready(function () {
      $(".registerlinkbtn").click(function () {
        window.location.href = "register.jsp";
      })
    })
  
    </script>
</body>
</html>