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
    <title>Dashboard - Product</title>
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
                        <li class="breadcrumb-item active">Products</li>
                    </ol>
                    <div class = "mt-5">
                        <div class = "row">
                          <div class="col-12 mx-auto">
                            <div class="d-flex justify-content-between">
                                <h3>Table products</h3>
                                <a href="/admin/product/create" class="btn btn-primary">Create a product</a>
                            </div>
                            <hr>
                            <table class="table table-bordered table-hover">
                              <thead>
                                <tr>
                                  <th scope="col">ID</th>
                                  <th scope="col">Name</th>
                                  <th scope="col">Price</th>
                                  <th scope="col">Factory</th>
                                  <th scope="col">Action</th>
                                </tr>
                              </thead>
                              <tbody>
                                <c:forEach var="product" items="${products}">
                                  <tr>
                                    <th scope="row">${product.id}</th>
                                    <td>${product.name}</td>
                                    <td>${product.price}</td>
                                    <td>${product.factory}</td>
                                    <td>
                                        <a href="/admin/product/${product.id}" class="btn btn-success">View</a>
                                        <a href="/admin/product/update/${product.id}" class="btn btn-warning mx-2">Update</a>
                                        <a class="btn btn-danger" href="/admin/product/delete/${product.id}">Delete</a>
                                    </td>
                                  </tr>
                                </c:forEach>
                              </tbody>
                            </table>
                            <nav aria-label="Page navigation example">
                              <ul class="pagination justify-content-center">
                                <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                  <a class="page-link" href="/admin/product?page=${currentPage-1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                  </a>
                                </li>
                                <c:forEach var="i" begin="1" end="${totalPages}">
                                  <li class="page-item ${i == currentPage ? 'active' : ''}">
                                    <a class="page-link" href="/admin/product?page=${i}">${i}</a>
                                  </li>
                                </c:forEach>
                                <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                                  
                                  <a class="page-link" href="/admin/product?page=${currentPage+1}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                  </a>
                                </li>
                              </ul>
                            </nav>
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