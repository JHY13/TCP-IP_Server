<%@page import="com.jspprj.web.dao.mybatis.MyBatisImageDao"%>
<%@page import="com.jspprj.web.entities.Image"%>
<%@page import="java.util.Scanner"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	/* List<NoticeModel> list = new MybatisNoticeDao().getList();
	pageContext.setAttribute("list", list);
	 */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지사항 관리페이지</title>
<!-- <link href="./css/style.css" type="text/css" rel="stylesheet" />
<link href="../css/manager_.css" type="text/css" rel="stylesheet" /> -->

</head>
<body>

	<div class = "container">
<!-- 
	<div class="head">
		<header>
		<a href="manager"><img class="setting" src="../images/setting.png" /></a>
		<a href="m_notice"><img class="notice" src="../images/notice.png" /></a> 
		<a href="m_plus"><img class="plus" src="../images/plus.png" /></a>
	
		</header>
	</div>
 -->
	<div class="body"></div>
		<h1></h1>

		<!-- <div class="body-aside">
			<aside>
			<h1></h1>
			<nav>
			<ul>
				<li><a href="m_notice">공지사항 관리</a></li>
				<li><a href="m_notice_review">리뷰 게시판 관리</a></li>
				<li><a href="m_notice_qna">1:1문의 게시판 관리</a></li>
			</ul>
			</nav> 	
			</aside>
		</div> -->

		<div class="body-main">
			<main>
			<h1>
				<a href="">게시판상세</a>
			</h1>

			<table border=1>
		<tbody>

			<tr>
				<td>배송사</td>
				<td>${n.title}</td>
				<td>운송장번호</td>
				<td>${n.delivery_number}</td>
			</tr>
			<tr>
				<td>배송정보 등록일</td>
				<td colspan="3">${n.regdate}</td>

			</tr>
			<tr>
				<td>상품명</td>
				<td colspan="3">${n.item}</td>

			</tr>
			<tr>
				<td>받는 이</td>
				<td>${n.name}</td>
				<td>연락처</td>
				<td>${n.phone}</td>
			</tr>
			<tr>
				<td>배송지</td>
				<td colspan="3">${n.address}</td>

			</tr>
			<tr>
				<td>QR코드</td>
				<td colspan="3">${n.content}</td>

			</tr>

		</tbody>
	</table>
	<div>
		<input type="hidden" name="code" value="${n.code}">
		<ul>
			<li><a href="image">목록</a></li>
			<li><a href="image-edit?code=${n.code}">수정</a></li>
			<li><a href="image-del?code=${n.code}">삭제</a></li>
		</ul>
	</div>
			</main>

		</div>	
	

	</div>
</body>
</html>

