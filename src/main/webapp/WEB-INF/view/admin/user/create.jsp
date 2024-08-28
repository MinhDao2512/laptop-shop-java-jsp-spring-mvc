<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file = "/resources/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="Tôi Làm Dev - Dự án LaptopShop" />
    <meta name="author" content="Tôi Làm Dev" />
    <title>Dashboard - User</title>
    <link href="/css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>

<body class="sb-nav-fixed">
    <!--Start Header-->
    <jsp:include page="../layout/header.jsp"/>
    <!--End Header-->

    <div id="layoutSidenav">
        <div id="layoutSidenav_nav">
            <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                <!--Start Sidebar-->
                <jsp:include page="../layout/sidebar.jsp"/>
                <!--End Sidebar-->
            </nav>
        </div>
        <div id="layoutSidenav_content">
            <!--Start Content-->
            <main>
                <div class="container-fluid px-4">
                    <div class="container mt-5">
                        <div class="row">
                            <div class="col-md-6 col-12 mx-auto">
                                <h3>Create a user</h3>
                                <hr />
                                <form:form method="post" action="/admin/user/create" modelAttribute="newUser">
                                    <div class="mb-3">
                                        <label class="form-label">Email:</label>
                                        <form:input type="email" class="form-control" path="email"/>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Password:</label>
                                        <form:input type="password" class="form-control" path="password"/>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Phone number:</label>
                                        <form:input type="text" class="form-control" path="phone"/>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Full Name:</label>
                                        <form:input type="text" class="form-control" path="fullName"/>
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Address:</label>
                                        <form:input type="text" class="form-control" path="address"/>
                                    </div>
                    
                                    <button type="submit" class="btn btn-primary">Create</button>
                                </form:form>
                            </div>
                        </div>
                    </div>
                </div>
            </main> 
            <!--End Content-->

            <!--Footer-->
            <jsp:include page="../layout/footer.jsp"/>
            <!--End Footer-->
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
    <script src="/js/scripts.js"></script>
</body>

</html>