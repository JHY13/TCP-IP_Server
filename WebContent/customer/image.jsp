<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>이미지...테이블...</title>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<td>번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>작성일</td>
				<td>조회수</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="n" items="${list}">
				<tr>
					<td>${n.code}</td>
					<td><a href="notice-detail?code=${n.code}">${n.title}</a></td>
					<td>${n.writer}</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${n.regdate }" />
					</td>
					<td><img src="" /></td>
					<%-- <td>${n.regDate}</td> --%>
					<td>${n.hit}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>
</html>