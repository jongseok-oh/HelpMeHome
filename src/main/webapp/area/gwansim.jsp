<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <%@ include file="/include/head.jsp" %>
    <body class="d-flex flex-column">
        <main class="flex-shrink-0">
            <%@ include file="/include/nav.jsp" %>
	
	<div class="bg-light rounded-3 py-2 px-2 px-md-2 mb-2 w-100">
    <div class="text-center mb-5">
      <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-envelope"></i></div>
        <h1 class="fw-bolder">관심 지역 등록</h1>
        <p class="lead fw-normal text-muted mb-0"></p>
      </div>
      <div class="row gx-5 justify-content-center">
        <div class="col-lg-8 col-xl-6">
          <div style="display: flex; flex-direction: row">
          <select
            class="form-select sido-select"
            id="apart-sido"
            aria-label="Default select example"
          >
            <option selected disabled>시/도</option>
          </select>
          <select class="form-select" id="apart-gugun" aria-label="Default select example">
            <option selected disabled>시/구/군</option>
          </select>
          <select class="form-select" id="apart-dong" aria-label="Default select example">
            <option selected disabled>동</option>
          </select>
            <input class="btn btn-primary" style="margin:0px 5px; width:400px" type='button' value='등록'/>
      </div>
      <div style="height : 1000px">hi</div>
    </div>
  </div>
<%@ include file="/include/footer.jsp" %>
    </body>
</html>