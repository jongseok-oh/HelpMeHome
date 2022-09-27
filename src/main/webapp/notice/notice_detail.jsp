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
			<h2 class="bg-primary text-light text-center">${notice.title}</h2>
		</div>
		<form method="post" action="${root}/notice/modify.do" class="row">
			<table class="table">
				<tbody>
					<tr>
						<th><label for="no">공지번호</label></th>
						<td><input type="text" name="no" id="no" 
									value="${notice.no}" readonly="readonly"/></td>
					</tr>
					<tr>
						<th><label for="title">공지사항</label></th>
						<td><input type="text" name="title" id="title" 
							value="${notice.title}"/></td>
					</tr>
					<tr>
						<th><label for="date">일자</label></th>
						<td><input type="text" name="date" id="date" 
								value="${notice.date}" /></td>
					</tr>
					<tr>
						<th><label for="writer">작성자</label></th>
						<td><input type="text" name="writer" id="writer" 
								value="${notice.writer}" /></td>
					</tr>
				</tbody>
				<tbody>
					<tr>
						<td><input type="text" name="info" id="info" 
								value="${notice.info}" /></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="2">
							<input type="submit" value="수정"/>
							<input type="submit" value="삭제" 
								formaction="${root}/notice/remove.do" formmethod="get"/>
							<input type="submit" value="취소" formaction="${root}/index.do" formmethod="get"/>
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>


            
		<%@ include file="/include/footer.jsp" %>
    </body>
</html>