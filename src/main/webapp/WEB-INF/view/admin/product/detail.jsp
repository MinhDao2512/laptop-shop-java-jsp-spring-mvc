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
    <title>Dashboard - Show Product</title>
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
                    <h1 class="mt-4">Manage Products</h1>
                    <ol class="breadcrumb mb-4">
                        <li class="breadcrumb-item"><a href="/admin">Dashboard </a></li>
                        <li class="breadcrumb-item"><a href="/admin/product">Products</a></li>
                        <li class="breadcrumb-item active">View</li>
                    </ol>
                    <div class = "mt-5">
                        <div class = "row">
                            <div class="col-12 mx-auto">
                                <h3>Product detail with id = ${product.id}</h3>
                                <hr/>
                                <div class="card" style="width: 25rem;">
                                    <c:if test="${not empty product.image}">
                                        <img src="<c:url value='/images/product/${product.image}'/>" style="max-height: 250px; display: block;" alt="image preview"/>
                                    </c:if>
                                    <div class="card-header">Product Information</div>
                                    <ul class="list-group list-group-flush"> 
                                      <li class="list-group-item">ID: ${product.id}</li>
                                      <li class="list-group-item">Name: ${product.name}</li>
                                      <li class="list-group-item">Price: ${product.price}</li>
                                    </ul>
                                </div>
                                <a class="btn btn-success mt-3" href="/admin/product">Back</a> 
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