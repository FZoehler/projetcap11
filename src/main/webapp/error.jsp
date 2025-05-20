<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error - Banco Digital</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-6 offset-md-3 text-center">
                <h1 class="text-danger">Oops!</h1>
                <h2>Something went wrong</h2>
                <p>We're sorry, but there was an error processing your request.</p>
                <a href="${pageContext.request.contextPath}/" class="btn btn-primary">Return to Home</a>
            </div>
        </div>
    </div>
</body>
</html>