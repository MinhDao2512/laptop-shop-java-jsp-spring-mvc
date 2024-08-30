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
    <title>Dashboard - Update User</title>
    <link href="/css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
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
                        <li class="breadcrumb-item"><a href="/admin/user">Users</a></li>
                        <li class="breadcrumb-item active">Update</li>
                    </ol>
                    <div class="mt-5">
                        <div class="row">
                            <div class="col-md-6 col-12 mx-auto">
                                <h3>Update a user</h3>
                                <hr />
                                <form:form class="row" method="post" action="/admin/user/update" modelAttribute="newUser" enctype="multipart/form-data">
                                    <div class="mb-3 col-12 col-md-6" style="display: none;">
                                        <label class="form-label">Id:</label>
                                        <form:input type="text" class="form-control" path="id"/>
                                    </div>
                                    <div class="mb-3 col-12 col-md-6">
                                        <label class="form-label">Email:</label>
                                        <form:input type="email" class="form-control" path="email" disabled="true"/>
                                    </div>
                                    <div class="mb-3 col-12 col-md-6">
                                        <label class="form-label">Phone number:</label>
                                        <form:input type="text" class="form-control" path="phone"/>
                                    </div>
                                    <div class="mb-3 col-12 col-md-6">
                                        <label class="form-label">Full Name:</label>
                                        <form:input type="text" class="form-control" path="fullName"/>
                                    </div>
                                    <div class="mb-3 col-12 col-md-6">
                                        <label class="form-label">Address:</label>
                                        <form:input type="text" class="form-control" path="address"/>
                                    </div>                                    
                                    <div class="mb-3 col-12 col-md-6">
                                        <label class="form-label">Role:</label>
                                        <form:select class="form-select" path="role.name">
                                            <form:option value="ADMIN">ADMIN</form:option>
                                            <form:option value="USER">USER</form:option>
                                        </form:select>
                                    </div>
                                    <div class="mb-3 col-12 col-md-6">
                                        <label for="avatarFile" class="form-label">Avatar:</label>
                                        <input class="form-control" type="file" id="avatarFile" accept=".png, .jpg, .jpeg" name="avatarFile">
                                    </div>
                                    <div class="mb-3 col-12">
                                        <c:choose>
                                            <c:when test = "${newUser.avatar != null}">
                                                <img src="<c:url value='/images/avatar/${newUser.avatar}'/>" 
                                                    style="max-height: 250px; display: block"
                                                    alt="avatar preview" 
                                                    id="avatarPreview"/>
                                            </c:when>
                                            <c:otherwise>
                                                <img  style="max-height: 250px; display: none"
                                                    alt="avatar preview" 
                                                    id="avatarPreview"/>
                                            </c:otherwise>
                                        </c:choose>   
                                    </div>
                                    <div class="d-flex justify-content-between">
                                        <a class="btn btn-success" href="/admin/user">Cancel</a>
                                        <button type="submit" class="btn btn-warning">Update</button>
                                    </div>
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
    <script>
        $(document).ready(() => {
            const avatarFile = $("#avatarFile");
            avatarFile.change(
                function(e){
                    const imfURL = URL.createObjectURL(e.target.files[0]);
                    $("#avatarPreview").attr("src", imfURL);
                    $("#avatarPreview").css({"display": "block"});
                }
            );
        });
    </script>
</body>

</html>