<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin:0px auto;
	width:800px;
}
</style>
</head>
<body>
	<div class="container">
		<h3 class="text-center">답변형 게시판</h3>
		<div class="row">
			<table class="table">
				<tr>
					<td>
						<a href="../board/insert.do" class="btn btn-sm btn-primary">새글</a>
					</td>
				</tr>
			</table>
			<table class="table table-hover">
				<tr>
					<th width=10% class="text-center">번호</th>
					<th width=45% class="text-center">제목</th>	
					<th width=15% class="text-center">이름</th>
					<th width=20% class="text-center">작성일</th>
					<th width=10% class="text-center">조회수</th>
				</tr>
				<c:set var="count" value="${count }"/>
				<c:forEach var="vo" items="${list }">
					<tr>
						<td width=10% class="text-center">${count }</td>
						<td width=45%>
						<c:if test="${vo.group_tab>0 }">
							<c:forEach var="i" begin="1" end="${vo.group_tab }">
								&nbsp;&nbsp;
							</c:forEach>
							<img src="../board/images/re_icon.png">
						</c:if>
						<c:if test="${vo.subject!=type }">
							<a href="../board/detail.do?no=${vo.no }">${vo.subject }</a>
						</c:if>
						<c:if test="${vo.subject==type }">
							<span style="color:gray">${vo.subject }</span>
						</c:if>
								&nbsp;
							<c:if test="${today==vo.dbday }">
								<sup><img src="../board/images/new.gif"></sup>
							</c:if>
						</td>
						<td width=15% class="text-center">${vo.name }</td>
						<td width=20% class="text-center">${vo.dbday }</td>
						<td width=10% class="text-center">${vo.hit }</td>
					</tr>
					<c:set var="count" value="${count-1 }"/>
				</c:forEach>
			</table>
			<table class="table">
				<tr>
					<td class="text-center">
						<a href="#" class="btn btn-sm btn-danger">이전</a>
							${curpage } page / ${totalpage } pages
						<a href="#" class="btn btn-sm btn-danger">다음</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>