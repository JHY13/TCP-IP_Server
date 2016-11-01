
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:if test="${empty param.p}">
			<c:set var="page" value="1" />
		</c:if>
		<c:if test="${not empty param.p}">
			<c:set var="page" value="${param.p}" />
		</c:if>

		<c:set var="start" value="${page-(page-1)%5}" />
		<c:set var="end" value="${fn:substringBefore((count%10==0?(count/10):(count/10)+1),'.')}" />

<div>
			<a href="notice?p=${(start==1)?1:start-1}&t=${param.t}&q=${param.q}">이전</a>
		</div>
		<ul>
			<c:forEach var="i" begin="0" end="4">
				<c:if test="${start+i<=end }">
					<c:if test="${page ==start+i }">
						<li><a href="notice?p=${start+i}&t=${param.t}&q=${param.q}"
							class="strong">${start+i}</a></li>
					</c:if>
					<c:if test="${page !=start+i }">
						<li><a href="notice?p=${start+i}&t=${param.t}&q=${param.q}">${start+i}</a></li>
					</c:if>
				</c:if>
			</c:forEach>
		</ul>
		<div>
			<a href="notice?p=${start+5}">다음</a>
		</div>