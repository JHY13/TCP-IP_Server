
<%@page import="com.jspprj.web.entities.Image"%>
<%@page import="com.jspprj.web.dao.ImageDao"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<body>
	<h1>sss
		<a href="">게시판수정</a>
	</h1>
	<ul>
		<li><a href="../index.jsp">home</a>
		<li>
		<li><a href="notice">공지사항</a>
		<li>
	</ul>
	<form action="image-edit" method="post">
		<fieldset>
			<legend>
				<table border=1>
					<tbody>
						<tr>
							<td>배송사</td>
							<td><input type="text" name="title" value="${n.title}" /></td>
							<td>운송장번호</td>
							<td><input type="text" name="delivery_number" value="${n.delivery_number}" /></td>
						</tr>
						<tr>
							<td>배송정보 등록일</td>
							<td colspan="3">${n.regdate}</td>

						</tr>
						<tr>
							<td>상품영</td>
							<td colspan="3">${n.item}</td>
							
						</tr>
						<tr>
							<td>받는 이</td>
							<td>${n.writer}</td>
							<td>운송장번호</td>
							<td>${n.delivery_number}</td>

						</tr>
						<tr>
							<td>배송지</td>
							<td colspan="3"><input type="text" name="address" value="${n.address}" /></td>
						</tr>
						
						<tr>
							<td>QR코드</td>
							<td colspan="3"><textarea name="content" rows="20" cols="60">${n.content}</textarea></td>
						</tr>
					</tbody>
				</table>
				<div>
					<input type="hidden" name="code" value="${n.code}">	
					<input type ="submit" value="저장">
					<a href="image-detail?code=${n.code}">취소</a>
				</div>
			</legend>
		</fieldset>
	</form>
</body>
</html>