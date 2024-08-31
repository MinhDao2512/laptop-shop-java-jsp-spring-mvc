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
    <title>Dashboard - Create User</title>
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
                    <h1 class="mt-4">Manage Products</h1>
                    <ol class="breadcrumb mb-4">
                        <li class="breadcrumb-item"><a href="/admin">Dashboard </a></li>
                        <li class="breadcrumb-item"><a href="/admin/product">Products</a></li>
                        <li class="breadcrumb-item active">Create</li>
                    </ol>
                    <div class="mt-5">
                        <div class="row">
                            <div class="col-md-6 col-12 mx-auto">
                                <h3>Create a product</h3>
                                <hr />
                                <form:form class="row" method="post" action="/admin/product/create" modelAttribute="newProduct" 
                                    enctype="multipart/form-data">
                                    <div class="mb-3 col-12 col-md-6">
                                        <label class="form-label">Name:</label>
                                        <form:input type="text" class="form-control" path="name"/>
                                    </div>
                                    <div class="mb-3 col-12 col-md-6">
                                        <label class="form-label">Price:</label>
                                        <form:input type="number" class="form-control" path="price"/>
                                    </div>
                                    <div class="mb-3 col-12">
                                        <label class="form-label">Detail description:</label>
                                        <form:textarea type="document" class="form-control" path="detailDesc"/>
                                    </div>
                                    <div class="mb-3 col-12 col-md-6">
                                        <label class="form-label">Short description:</label>
                                        <form:input type="text" class="form-control" path="shortDesc"/>
                                    </div>
                                    <div class="mb-3 col-12 col-md-6">
                                        <label class="form-label">Quantity:</label>
                                        <form:input type="number" class="form-control" path="quantity"/>
                                    </div>
                                    <div class="mb-3 col-12 col-md-6">
                                        <label class="form-label">Factory:</label>
                                        <form:select class="form-select" path="factory">
                                            <form:option value="APPLE">Apple (MacBook)</form:option>
                                            <form:option value="ASUS">Asus</form:option>
                                            <form:option value="LENOVO">Lenovo</form:option>
                                            <form:option value="DELL">Dell</form:option>
                                            <form:option value="LG">LG</form:option>
                                            <form:option value="ACER">Acer</form:option>
                                        </form:select>
                                    </div>
                                    <div class="mb-3 col-12 col-md-6">
                                        <label class="form-label">Target:</label>
                                        <form:select class="form-select" path="target">
                                            <form:option value="GAMMING">Gamming</form:option>
                                            <form:option value="SINHVIEN-VANPHONG">Sinh viên - Văn phòng</form:option>
                                            <form:option value="THIET-KE-DO-HOA">Thiết kế đồ họa</form:option>
                                            <form:option value="MONG-NHE">Mỏng nhẹ</form:option>
                                            <form:option value="DOANG-NHAN">Doanh nhân</form:option>
                                        </form:select>
                                    </div>
                                    <div class="mb-3 col-12 col-md-6">
                                        <label for="imageFile" class="form-label">Image:</label>
                                        <input class="form-control" type="file" id="imageFile" accept=".png, .jpg, .jpeg" name="imageFile">
                                    </div>
                                    <div class="mb-3 col-12">
                                        <img style="max-height: 250px; display: none;" alt="image preview" id="imagePreview"/>
                                    </div>
                                    <div class="d-flex justify-content-between">
                                        <a class="btn btn-success" href="/admin/product">Back</a>
                                        <button type="submit" class="btn btn-primary">Create</button>
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
    <!--JQuery Show Avatar Preview-->
    <script>
        $(document).ready(() => {
            const avatarFile = $("#imageFile");
            avatarFile.change(
                function(e){
                    const imfURL = URL.createObjectURL(e.target.files[0]);
                    $("#imagePreview").attr("src", imfURL); 
                    $("#imagePreview").css({"display": "block"});
                }
            );
        });
    </script>
</body>
</html>