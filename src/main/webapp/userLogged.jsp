<%@ page import="ExamplePackage.UserBean"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Successful</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            background-color: #f5f5f5;
        }
        .welcome-card {
            background-color: white;
            padding: 30px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            text-align: center;
            max-width: 500px;
        }
        h1 {
            color: #4CAF50;
        }
    </style>
</head>
<body>
    <div class="welcome-card">
        <% 
        UserBean currentUser = (UserBean) session.getAttribute("currentSessionUser");
        if (currentUser != null) {
        %>
            <h1>Welcome, <%= currentUser.getFirstName() + " " + currentUser.getLastName() %>!</h1>
            <p>You have successfully logged into the system.</p>
        <% 
        } else {
            response.sendRedirect("LoginPage.jsp");
        }
        %>
    </div>
</body>
</html>