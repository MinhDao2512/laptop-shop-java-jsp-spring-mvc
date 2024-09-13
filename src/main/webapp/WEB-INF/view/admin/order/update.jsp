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
    <title>Dashboard - Update Order</title>
    <link href="/admin/css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    <!--Import JQuery-->
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
                    <h1 class="mt-4">Manage Orders</h1>
                    <ol class="breadcrumb mb-4">
                        <li class="breadcrumb-item"><a href="/admin">Dashboard </a></li>
                        <li class="breadcrumb-item"><a href="/admin/order">Orders</a></li>
                        <li class="breadcrumb-item active">Update</li>
                    </ol>
                    <div class="mt-5">
                        <div class="row">
                            <div class="col-md-6 col-12 mx-auto">
                                <h3>Update Order</h3>
                                <hr />
                                <form:form class="row" method="post" action="/admin/order/update" modelAttribute="order">
                                    <div class="mb-3 col-12 col-md-6" style="display: none;">
                                        <label class="form-label">Id:</label>
                                        <form:input type="text" class="form-control" path="id"/>
                                    </div>
                                    <div class="mb-3 col-12 col-md-6">
                                        <label class="form-label">User:</label>
                                        <input type="email" class="form-control" value="${userDesc}" readonly="true" 
                                            style="background-color: #e9ecef; opacity: 1; cursor: not-allowed;"/>
                                    </div>
                                    <div class="mb-3 col-12 col-md-6">
                                        <label class="form-label">Status:</label>
                                        <form:select class="form-select" path="status">
                                            <form:option value="PENDING">PENDING</form:option>
                                            <form:option value="SHIPPING">SHIPPING</form:option>
                                            <form:option value="COMPLETE">COMPLETE</form:option>
                                            <form:option value="CANCEL">CANCEL</form:option>
                                        </form:select>
                                    </div>
                                    <div class="d-flex justify-content-between">
                                        <a class="btn btn-success" href="/admin/order">Back</a>
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
    <script src="/admin/js/scripts.js"></script>
</body>
</html>