<%@include file = "/resources/taglib.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Delete User ${id}</title>
    <!-- Latest compiled and minified CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <!-- <link href="/css/demo.css" rel="stylesheet"> -->
</head>
<body>
    <div class = "container mt-5">
        <div class = "row">
            <div class="col-12 mx-auto">
                <h3>Delete the user with id = ${id}</h3>
                <hr>
                <div class="alert alert-danger" role="alert">
                    Are you sure to delete this user ?
                </div>
                <form:form action="/admin/user/delete" method="post" modelAttribute="currentUser">
                    <div class="mb-3" style="display: none;">
                        <label class="form-label">Id:</label>
                        <form:input type="text" class="form-control" path="id"/>
                    </div>
                    <button type="submit" class="btn btn-danger">Confirm</button>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>