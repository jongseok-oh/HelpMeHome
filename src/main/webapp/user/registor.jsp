<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<!DOCTYPE html>
<html lang="en">
    <%@ include file="/include/head.jsp" %>
    <body class="d-flex flex-column">
        <main class="flex-shrink-0">
        <%@ include file="/include/nav.jsp" %>
            <!-- Page content-->
            <section class="py-5">
                <div class="container px-5">
                    <!-- Contact form-->
                    <div class="bg-light rounded-3 py-5 px-4 px-md-5 mb-5">
                        <div class="text-center mb-5">
                            <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-envelope"></i></div>
                            <h1 class="fw-bolder">회원 등록</h1>
                            <p class="lead fw-normal text-muted mb-0"></p>
                        </div>
                        <div class="row gx-5 justify-content-center">
                            <div class="col-lg-8 col-xl-6">
                                <form id="contactForm" data-sb-form-api-token="API_TOKEN" method="post" action="${root}/user/register.do">
                                    
                                    <div class="form-floating mb-3">
                                        <input class="form-control" name="userId" id="userId" type="text" placeholder="Enter your name..." data-sb-validations="required" />
                                        <label for="name">Username</label>
                                        
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" name="passWord" id="passWord" type="text" placeholder="name@example.com" data-sb-validations="required,email" />
                                        <label for="password">Password</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" name="name" id="name"  type="text" placeholder="name@example.com" data-sb-validations="required,email" />
                                        <label for="name">이름</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" name="phoneNumber" id="phoneNumber" type="text" placeholder="name@example.com" data-sb-validations="required,email" />
                                        <label for="email">전화번호</label>
                                    </div>
                                    <div class="d-grid"><button class="btn btn-primary btn-lg" id="submitButton" type="submit">Sign in</button></div>
                                </form>
                            </div>
                        </div>
                    </div>
                    
            </section>
        </main>
        <%@ include file="/include/footer.jsp" %>
    </body>
</html>