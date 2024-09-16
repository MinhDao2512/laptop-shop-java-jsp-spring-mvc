<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="/resources/taglib.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sản Phẩm - LaptopShop</title>
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
        <h1 class="text-center text-white display-6">Sản phẩm</h1>
        <ol class="breadcrumb justify-content-center mb-0">
            <li class="breadcrumb-item"><a href="/">Trang chủ</a></li>
            <li class="breadcrumb-item active text-white">Sản phẩm</li>
        </ol>
    </div>
    <!-- Single Page Header End -->

    <!-- Fruits Shop Start-->
    <div class="container-fluid fruite py-5">
        <div class="container py-5">
            <ol class="breadcrumb mb-4">
                <li class="breadcrumb-item"><a href="/">Home</a></li>
                <li class="breadcrumb-item active">Danh sách sản phẩm</li>
            </ol>
            <div class="row g-4">
                <div class="col-lg-12">
                    <div class="mt-5 row g-4">
                        <!--Filter Start-->
                        <div class="col-lg-3">
                            <div class="row g-4">
                                <div class="col-12">
                                    <div class="mb-2"><b>Hãng sản xuất</b></div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="checkbox" id="factory-1"
                                            value="APPLE">
                                        <label class="form-check-label" for="factory-1">Apple</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="checkbox" id="factory-2"
                                            value="ASUS">
                                        <label class="form-check-label" for="factory-2">Asus</label>
                                    </div>

                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="checkbox" id="factory-3"
                                            value="LENOVO">
                                        <label class="form-check-label" for="factory-3">Lenovo</label>
                                    </div>

                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="checkbox" id="factory-4"
                                            value="DELL">
                                        <label class="form-check-label" for="factory-4">Dell</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="checkbox" id="factory-5"
                                            value="LG">
                                        <label class="form-check-label" for="factory-5">LG</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="checkbox" id="factory-6"
                                            value="ACER">
                                        <label class="form-check-label" for="factory-6">Acer</label>
                                    </div>

                                </div>
                                <div class="col-12">
                                    <div class="mb-2"><b>Mục đích sử dụng</b></div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="checkbox" id="target-1"
                                            value="GAMING">
                                        <label class="form-check-label" for="target-1">Gaming</label>
                                    </div>

                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="checkbox" id="target-2"
                                            value="SINHVIEN-VANPHONG">
                                        <label class="form-check-label" for="target-2">Sinh viên - văn
                                            phòng</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="checkbox" id="target-3"
                                            value="THIET-KE-DO-HOA">
                                        <label class="form-check-label" for="target-3">Thiết kế đồ
                                            họa</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="checkbox" id="target-4"
                                            value="MONG-NHE">
                                        <label class="form-check-label" for="target-4">Mỏng nhẹ</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="checkbox" id="target-5"
                                            value="DOANH-NHAN">
                                        <label class="form-check-label" for="target-5">Doanh nhân</label>
                                    </div>


                                </div>
                                <div class="col-12">
                                    <div class="mb-2"><b>Mức giá</b></div>

                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="checkbox" id="price-2"
                                            value="duoi-10-trieu">
                                        <label class="form-check-label" for="price-2">Dưới 10 triệu</label>
                                    </div>

                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="checkbox" id="price-3"
                                            value="10-15-trieu">
                                        <label class="form-check-label" for="price-3">Từ 10 - 15
                                            triệu</label>
                                    </div>

                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="checkbox" id="price-4"
                                            value="15-20-trieu">
                                        <label class="form-check-label" for="price-4">Từ 15 - 20
                                            triệu</label>
                                    </div>

                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="checkbox" id="price-5"
                                            value="tren-20-triệu">
                                        <label class="form-check-label" for="price-5">Trên 20 triệu</label>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="mb-2"><b>Sắp xếp</b></div>

                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" id="sort-1"
                                            value="gia-tang-dan" name="radio-sort">
                                        <label class="form-check-label" for="sort-1">Giá tăng dần</label>
                                    </div>

                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" id="sort-2"
                                            value="gia-giam-dan" name="radio-sort">
                                        <label class="form-check-label" for="sort-2">Giá giảm dần</label>
                                    </div>

                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" id="sort-3"
                                            value="gia-nothing" name="radio-sort">
                                        <label class="form-check-label" for="sort-3">Không sắp xếp</label>
                                    </div>

                                </div>
                                <div class="col-12">
                                    <button
                                        class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4">
                                        Lọc Sản Phẩm
                                    </button>
                                </div>
                            </div>
                        </div>
                        <!--Filter End-->

                        <!--Products Start-->
                        <div class="col-lg-9">
                            <div class="row g-4">
                                <c:forEach var="product" items="${products}">
                                    <div class="col-md-6 col-lg-6 col-xl-4">
                                        <div class="rounded position-relative fruite-item">
                                            <div class="fruite-img">
                                                <img src="/images/product/${product.image}" class="img-fluid w-100 rounded-top" alt="">
                                            </div>
                                            <div class="text-white bg-secondary px-3 py-1 rounded position-absolute" style="top: 10px; left: 10px;">${product.factory}</div>
                                            <div class="p-4 border border-secondary border-top-0 rounded-bottom">
                                                <h4 style="font-size: 15px;">
                                                    <a href="/product/${product.id}">${product.name}</a>
                                                </h4>
                                                <p>${product.shortDesc}</p>
                                                <div class="d-flex justify-content-center flex-lg-wrap">
                                                    <p class="text-dark fs-5 fw-bold mb-0">
                                                        <fmt:formatNumber type="number" value="${product.price}"/> đ
                                                    </p>
                                                    <form action="/add-product-to-cart/${product.id}" method="post">
                                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                                                        <button type="submit" class="mt-2 btn border border-secondary rounded-pill px-3 text-primary">
                                                            <i class="fa fa-shopping-bag me-2 text-primary"></i>
                                                            Add to cart
                                                        </button>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                                <div class="col-12">
                                    <div class="pagination d-flex justify-content-center mt-5">
                                        <a href="/products?page=${currentPage-1}" class="rounded">&laquo;</a>
                                        <c:forEach var="i" begin="1" end="${totalPages}">
                                            <a href="/products?page=${i}" class="${i == currentPage ? 'active' : ''} rounded">${i}</a>
                                        </c:forEach>
                                        <a href="/products?page=${currentPage+1}" class="rounded">&raquo;</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--Product End-->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Fruits Shop End-->

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