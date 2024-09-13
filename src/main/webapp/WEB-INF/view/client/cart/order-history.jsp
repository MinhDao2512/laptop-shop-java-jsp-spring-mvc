<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file = "/resources/taglib.jsp" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Giỏ Hàng - LaptopShop</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap" rel="stylesheet"> 

    <!-- Icon Font Stylesheet -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="/client/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
    <link href="/client/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">


    <!-- Customized Bootstrap Stylesheet -->
    <link href="/client/css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="/client/css/style.css" rel="stylesheet">
</head>
<body>
    <!--Header Start-->
    <jsp:include page="../layout/header.jsp"/>
    <!--Header End-->

    <!-- Single Page Header start -->
    <div class="container-fluid page-header py-5">
        <h1 class="text-center text-white display-6">Giỏ hàng</h1>
        <ol class="breadcrumb justify-content-center mb-0">
            <li class="breadcrumb-item"><a href="/">Trang chủ</a></li>
            <li class="breadcrumb-item active text-white">Giỏ hàng</li>
        </ol>
    </div>
    <!-- Single Page Header End -->

    <!-- Cart Page Start -->
    <div class="container-fluid py-5">
        <div class="container py-5">
            <div class="table-responsive">
                <c:if test="${not empty mapData}">
                    <table class="table">
                        <thead>
                            <tr>
                            <th scope="col">Products</th>
                            <th scope="col">Name</th>
                            <th scope="col">Price</th>
                            <th scope="col">Quantity</th>
                            <th scope="col">Total</th>
                            <th scope="col">Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="entry" items="${mapData}">
                                <c:set var="order" value="${entry.key}"/>
                                <c:set var="orderDetails" value="${entry.value}"/>
                                <tr style="background-color: #e6e6ff; font-weight: bold;">
                                    <td colspan="2">
                                        <p class="mb-0">
                                            Order Id = ${order.id}
                                        </p>
                                    </td>
                                    <td colspan="2">
                                        <p class="mb-0">
                                            <fmt:formatNumber type="number" value="${order.totalPrice}"/> đ
                                        </p>
                                    </td>
                                    <td>
                                        <p class="mb-0"></p>
                                    </td>
                                    <c:choose>
                                        <c:when test="${order.status == 'COMPLETE'}">
                                            <td style="color: green;">
                                                <p class="mb-0">
                                                    ${order.status}
                                                </p>
                                            </td>
                                        </c:when>
                                        <c:when test="${order.status == 'CANCEL'}">
                                            <td style="color: red;">
                                                <p class="mb-0">
                                                    ${order.status}
                                                </p>
                                            </td>
                                        </c:when>
                                        <c:when test="${order.status == 'PENDING'}">
                                            <td style="color: orange;">
                                                <p class="mb-0">
                                                    ${order.status}
                                                </p>
                                            </td>
                                        </c:when>
                                        <c:when test="${order.status == 'SHIPPING'}">
                                            <td style="color: gold;">
                                                <p class="mb-0">
                                                    ${order.status}
                                                </p>
                                            </td>
                                        </c:when>
                                    </c:choose>
                                </tr>
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
                                            <p class="mb-0 mt-4">
                                                ${orderDetail.quantity}
                                            </p>
                                        </td>
                                        <td>
                                            <p class="mb-0 mt-4">
                                                <fmt:formatNumber type="number" value="${orderDetail.price}"/> đ
                                            </p>
                                        </td>
                                        <td>
                                            <p class="mb-0 mt-4"></p>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <c:if test="${empty mapData}">
                    <h5 class="mt-5 d-flex justify-content-center">Chưa có đơn hàng nào</h5>
                </c:if>
            </div>
        </div>
    </div>
    <!-- Cart Page End -->

    <!--Footer Start-->
    <jsp:include page="../layout/footer.jsp"/>
    <!--Footer End-->

    <!-- JavaScript Libraries -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="/client/lib/easing/easing.min.js"></script>
    <script src="/client/lib/waypoints/waypoints.min.js"></script>
    <script src="/client/lib/lightbox/js/lightbox.min.js"></script>
    <script src="/client/lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Template Javascript -->
    <script src="/client/js/main.js"></script>
</body>
</html>