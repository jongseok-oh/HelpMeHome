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
			<h2 class="bg-primary text-light text-center">임시</h2>
		</div>
		<div class="row">
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>번호</th>
						<th>공지이름</th>
						<th>공지날짜</th>
						<th>작성자</th>
					</tr>			
				</thead>
				<tbody>
				
				<c:choose>
					<c:when test="${empty noticeList}">	
						<tr> <td colspan="4">등록된 공지사항이 없습니다.</td></tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${requestScope.noticeList}" 
							var="notice" varStatus="status">
							<tr>
								<td>${notice.no}</td>
								<td><a href="${root}/notice/notice_detail.do?no=${notice.no}">${notice.title}</a></td>
								<td>${notice.date}</td>
								<td>${notice.writer}</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			
				</tbody>
			</table>
			
					<tr>
						<td colspan="1">
							<input type="submit" value="등록"/>
						</td>
					</tr>
			
		</div>	
	</div>


            
		<%@ include file="/include/footer.jsp" %>
    </body>
</html>