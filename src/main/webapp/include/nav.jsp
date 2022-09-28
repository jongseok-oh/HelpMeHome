<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>

	<c:if test="${not empty msg}">
		alert("${msg}");  
	</c:if>

</script>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <div class="container px-5">
                    <a class="navbar-brand" style="font-size: 200%;" href="${root}/index.do">HELP ME HOME</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        	<li class="nav-item"><a class="nav-link" href="${root}/notice/notice.do">Notice</a></li>
                            <li class="nav-item"><a class="nav-link" href="${root}/index.do">Home</a></li>
                            <li class="nav-item"><a class="nav-link" href="${root}/index.do">About</a></li>
                            <c:choose>
							<c:when test="${empty userId}">	
                            <li class="nav-item"><a class="nav-link" href="${root}/user/signup_form.do">Sign Up</a></li>
                            <li class="nav-item dropdown">
                                <!-- 수정 dropdown-->
                                <div class="dropdown">
                                    <button type="button" class="btn btn-dark dropdown-toggle" id="signin" data-bs-toggle="dropdown" aria-expanded="false" data-bs-auto-close="outside">
                                        Sign in
                                    </button>
                                    <form class="dropdown-menu p-4" method="post" action="${root}/user/login.do">
                                    <div class="mb-3">
                                        <label for="userId" class="form-label">ID</label>
                                        <input type="text" class="form-control" name="userId" id="userId" placeholder="user id">
                                    </div>
                                    <div class="mb-3">
                                        <label for="passWord" class="form-label">Password</label>
                                        <input type="text" class="form-control" name="passWord" id="passWord"  placeholder="password">
                                    </div>
                                    <div class="mb-3">
                                        <div class="form-check">
                                        <input type="checkbox" class="form-check-input" id="dropdownCheck2">
                                    <label class="form-check-label" for="dropdownCheck2">
                                            ID 저장
                                        </label>
                                        </div>
                                        </div>
                                        <button type="submit" class="btn btn-dark">Sign in</button>
                                    </form>
                                    </div>
                            </li>
                            </c:when>
                            <c:otherwise>
                             <li class="nav-item"><a class="nav-link" href="${root}/area/gwansimForm.do">관심지역</a></li>
                           		 <li class="nav-item"><a class="nav-link" href="${root}/user/fix_form.do">수정</a></li>
                           		 <li class="nav-item"><a class="nav-link" href="${root}/user/logout.do">로그아웃</a></li>	
                           	</c:otherwise>
							</c:choose> 
                            
                            <!-- 수정 dropdown-->
                        </ul>
                    </div>
                </div>
            </nav>