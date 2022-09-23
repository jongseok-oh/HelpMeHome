<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                            <h1 class="fw-bolder">회원 정보 확인</h1>
                            <p class="lead fw-normal text-muted mb-0"></p>
                        </div>
                        <div class="row gx-5 justify-content-center">
                            <div class="col-lg-8 col-xl-6">
                                <form id="contactForm" data-sb-form-api-token="API_TOKEN">
                                    
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="email" type="email" placeholder="name@example.com" data-sb-validations="required,email" />
                                        <label for="password">Username</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="email" type="email" placeholder="name@example.com" data-sb-validations="required,email" />
                                        <label for="password">Password</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="email" type="email" placeholder="name@example.com" data-sb-validations="required,email" />
                                        <label for="name">이름</label>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="email" type="email" placeholder="name@example.com" data-sb-validations="required,email" />
                                        <label for="email">전화번호</label>
                                    </div>
                                   
                                    <div class="
                                    my-3
                                    row
                                    justify-content-around 
                                    col
                                    ">
                                    <button type="button" class="btn btn-lg btn-outline-primary output col-5">
                                            확인
                                    </button>
                                    <button type="button" class="btn btn-lg btn-primary output col-5">
                                            수정
                                    </button>
</div>
                                    <!--
                                    <div class="form-floating mb-3">
                                        <div class="d-grid"><button class="btn btn-primary btn-lg disabled" id="submitButton" type="submit">확인</button></div>
                                    </div>
                                    <div class="form-floating mb-3">
                                        <div class="d-grid"><button class="btn btn-primary btn-lg disabled" id="submitButton" type="submit">수정</button></div>
                                    </div>
                                    --> 
                                    <button type="button" class="btn btn-lg btn-outline-dark">삭제하기</button>
                                </form>
                            </div>
                        </div>
                        <!-- 시작-->
                    </div>
                    
            </section>
        </main>
        <!-- Footer-->
        <footer class="bg-dark py-4 mt-auto">
            <div class="container px-5">
                <div class="row align-items-center justify-content-between flex-column flex-sm-row">
                    <div class="col-auto"><div class="small m-0 text-white">(SSAFY) 서울시 강남구 테헤란로 멀티스퀘어</div></div>
                    <div class="col-auto">
                        <a class="link-light small" href="#!">Privacy</a>
                        <span class="text-white mx-1">&middot;</span>
                        <a class="link-light small" href="#!">Terms</a>
                        <span class="text-white mx-1">&middot;</span>
                        <a class="link-light small" href="#!">Contact</a>
                    </div>
                </div>
            </div>
		<%@ include file="/include/footer.jsp" %>
    