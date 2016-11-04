<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>이미지...테이블...</title>
</head>
<body>
<h1>게시판</h1>
	<table border="1">
		<thead>
			<tr>
				<td>번호</td>
				<td>배송사</td>
				<td>상품명</td>
				<td>운송장번호</td>
				<td>배송정보 등록일</td>
				<td>QR코드생성</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="n" items="${list}">
				<tr>
					<td>${n.code}</td>
					<td><a href="image-detail?code=${n.code}">${n.title}</a></td>
					<td>${n.item}</td>
					<td>${n.delivery_number}</td>
					<td><fmt:formatDate pattern="yyyy-MM-dd" value="${n.regdate }" />
					</td>
					<td><input type="submit" name="qr" value="생성"></td>
					
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<c:set var="start" value="${page-(page-1)%5}" /> <c:set var="end"
				value="${fn:substringBefore((count%10==0?count/10:count/10+1),'.')}" />

			<!-- 먼저 param.p의 값을 page라는 값에 담아 제어구조문으로 기본값을 정의후  밑에서 page라는 값을 쓴다 -->
			<c:if test="${empty param.p}">
				<c:set var="page" value="1" />
			</c:if> <c:if test="${!empty param.p}">
				<c:set var="page" value="${param.p}" />
			</c:if>
			<div>${page}/${end}pages</div>
			<div>
				<a href="image-reg">글쓰기</a>
			</div>
			<div>



				<div>
					<a href="image?p=${(page==1)?1:start-1}">이전</a>
				</div>
				<ul>
					<c:forEach var="i" begin="0" end="4">
						<c:if test="${start+i <= end}">
							<c:if test="${page==start+i}">
								<li><a href="image?p=${start+i}&t=${param.t}&q=${param.q}"
									class="strong">${start+i}</a></li>
							</c:if>
							<c:if test="${page!=start+i}">
								<li><a href="image?p=${start+i}&t=${param.t}&q=${param.q}">${start+i}</a></li>
							</c:if>
						</c:if>
					</c:forEach>
				</ul>
				<div>
					<a href="image?p=${start+5}&t=${param.t}&q=${param.q}">다음</a>
				</div>
			</div>

</body>
</html>