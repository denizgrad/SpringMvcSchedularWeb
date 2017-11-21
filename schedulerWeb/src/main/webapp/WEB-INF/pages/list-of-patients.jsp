<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags"%>

<custom:layout title="List of Patients">

	<h1>List of Studies</h1>
	<p><spring:message code="list.patient.desc"></spring:message> </p>
	<table class="table">
		<thead>
			<tr>
				<th width="10%">id</th>
				<th width="70%">name</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="study" items="${patients}">
				<tr>
					<td>${study.id}</td>
					<td>${study.name}</td>
					<td>
						<a href="${pageContext.request.contextPath}/patient/delete/${patient.id}.html"><spring:message code="delete"></spring:message></a><br />
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty patients}">
				<tr>
					<td colspan="3">
						<spring:message code="no.record.found"></spring:message>
					</td>
				</tr>
			</c:if>			
		</tbody>
	</table>

</custom:layout>

