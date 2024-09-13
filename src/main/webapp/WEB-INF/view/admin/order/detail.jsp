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
    <title>Dashboard - Order ${id}</title>
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
                    <h1 class="mt-4">Manage Orders</h1>
                    <ol class="breadcrumb mb-4">
                        <li class="breadcrumb-item"><a href="/admin">Dashboard </a></li>
                        <li class="breadcrumb-item"><a href="/admin/order">Orders </a></li>
                        <li class="breadcrumb-item active">View detail</li>
                    </ol>
                    <div class = "mt-5">
                        <div class = "row">
                            <div class="col-12 mx-auto">
                                <h3>Order detail with id = ${id}</h3>
                                <hr/>
                                <table class="table table-bordered table-hover">
                                    <thead>
                                        <tr>
                                        <th scope="col">Products</th>
                                        <th scope="col">Name</th>
                                        <th scope="col">Price</th> 
                                        <th scope="col">Quantity</th>
                                        <th scope="col">Total Price</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="orderDetail" items="${orderDetails}">
                                            <tr>
                                                <th scope="row">
                                                    <div class="d-flex align-items-center">
                                                        <img src="/images/product/${orderDetail.product.image}" class="img-fluid me-5 rounded-circle" style="width: 80px; height: 80px;" alt="">
                                                    </div>
                                                </th>
                                                <td>
                                                    <p class="mb-0 mt-4">
                                                        <a href="/product/${orderDetail.product.id}" target="_blank">
                                                            ${orderDetail.product.name}
                                                        </a>
                                                    </p>
                                                </td>
                                                <td>
                                                    <p class="mb-0 mt-4">
                                                        <fmt:formatNumber type="number" value="${orderDetail.product.price}"/> đ
                                                    </p>
                                                </td>
                                                <td>
                                                    <div class="input-group quantity mt-4" style="width: 100px;">
                                                        <input type="text" class="form-control form-control-sm text-center border-0" value="${orderDetail.quantity}">
                                                    </div>
                                                </td>   
                                                <td>
                                                    <p class="mb-0 mt-4"></p>
                                                        <fmt:formatNumber type="number" value="${orderDetail.price}"/> đ
                                                    </p>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                <a class="btn btn-success mt-3" href="/admin/order">Back</a>
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