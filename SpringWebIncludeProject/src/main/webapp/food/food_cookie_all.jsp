<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <div class="row">
     <c:if test="${size==0 }">
       <h3 class="text-center">방문한 레시픽 없습니다</h3>
     </c:if>
     <c:if test="${size>0 }">
       <c:forEach var="vo" items="${fList }">
         <div class="col-md-3">
		    <div class="thumbnail">
		      <a href="../food/detail.do?fno=${vo.fno }">
		        <img src="http://menupan.com${vo.poster }" title="${vo.address}" style="width:230px;height: 150px">
		        <div class="caption">
		          <p>${vo.name }</p>
		        </div>
		      </a>
		    </div>
		  </div>
       </c:forEach>
     </c:if>
   </div>
   <div style="height: 10px"></div>
   <div class="row">
     <div class="text-right">
       <a href="../food/food_cookie_delete.do" class="btn btn-sm btn-danger">삭제</a>
     </div>
   </div>
</body>
</html>