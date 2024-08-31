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
    <link href="/admin/css/styles.css" rel="stylesheet" />
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
                  <h1 class="mt-4">Manage Users</h1>
                  <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item"><a href="/admin">Dashboard </a></li>
                    <li class="breadcrumb-item active">Users</li>
                  </ol>
                  <div class = "mt-5">
                    <div class = "row">
                      <div class="col-12 mx-auto">
                        <div class="d-flex justify-content-between">
                            <h3>Table users</h3>
                            <a href="/admin/user/create" class="btn btn-primary">Create a user</a>
                        </div>
                        <hr>
                        <table class="table table-bordered table-hover">
                          <thead>
                            <tr>
                              <th scope="col">ID</th>
                              <th scope="col">Email</th>
                              <th scope="col">Full Name</th>
                              <th scope="col">Role Name</th>
                              <th scope="col">Action</th>
                            </tr>
                          </thead>
                          <tbody>
                            <c:forEach var="user" items="${users}">
                              <tr>
                                <th scope="row">${user.id}</th>
                                <td>${user.email}</td>
                                <td>${user.fullName}</td>
                                <td>${user.role.name}</td>
                                <td>
                                    <a href="/admin/user/${user.id}" class="btn btn-success">View</a>
                                    <a href="/admin/user/update/${user.id}" class="btn btn-warning mx-2">Update</a>
                                    <a class="btn btn-danger" href="/admin/user/delete/${user.id}">Delete</a>
                                </td>
                              </tr>
                            </c:forEach>
                          </tbody>
                        </table>
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
    <script src="/admin/js/scripts.js"></script>
</body>

</html>