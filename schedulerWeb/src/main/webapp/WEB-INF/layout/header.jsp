<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>

<spring:message code="add" var="add"></spring:message>
<spring:message code="list" var="list"></spring:message>
<c:set value="${pageContext.response.locale}" var="locale" /> 

<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">S-Cape Assigment</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a href="${contextPath}/"><spring:message code="home"></spring:message></a></li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
						<spring:message code="patients"></spring:message> <span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="${contextPath}/patient/add">${add}</a></li>
						<li><a href="${contextPath}/patient/list">${list}</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
						<spring:message code="studies"></spring:message> <span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="${contextPath}/study/add">${add}</a></li>
						<li><a href="${contextPath}/study/list">${list}</a></li>
					</ul>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
						Language <span class="caret"></span>
					</a>
					<ul class="dropdown-menu" id="lang-dropdown">
						<li><a class="item" data="en_US" href="${requestScope['javax.servlet.forward.request_uri']}?lang=en_US">English US</a></li>
				        <li><a class="item" data="en_GB" href="${requestScope['javax.servlet.forward.request_uri']}?lang=en_GB">English GB</a></li>
					</ul>
				</li>			
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
	<script>
		$('#lang-dropdown>li>a[data=' + '${locale}' + ']').addClass('active-dropdown');
		$('#lang-dropdown>li>a[data=' + '${locale}' + ']').css('font-weight', 'bold');
	</script>
</nav>