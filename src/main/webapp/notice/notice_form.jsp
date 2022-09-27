<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <%@ include file="/include/head.jsp" %>
    <body class="d-flex flex-column">
        <main class="flex-shrink-0">
            <%@ include file="/include/nav.jsp" %>


	<div class="container">
		<div class="row mt-3">
			<h2 class="bg-primary text-light text-center">부서 목록</h2>
		</div>
		<div class="row">
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>번호</th>
						<th>부서번호</th>
						<th>부서이름</th>
						<th>부서지역</th>
					</tr>			
				</thead>
				<tbody>
				
				<c:choose>
					<c:when test="${empty deptList}">	
						<tr> <td colspan="4">등록된 부서정보가 없습니다.</td></tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${requestScope.deptList}" 
							var="dept" varStatus="status">
							<tr>
								<td>${status.count}</td>
								<td>${dept.deptNo}</td>
								<td><a href="${root}/dept/detail.do?deptno=${dept.deptNo}">${dept.dname}</a></td>
								<td>${dept.loc}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			
				</tbody>
			</table>
		</div>	
	</div>


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
    </body>
</html>