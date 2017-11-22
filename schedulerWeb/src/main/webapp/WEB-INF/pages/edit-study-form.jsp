<!-- handles edit and new organization -->

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	language="java"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags"%>

<custom:layout title="${pageTitle}">

	<h1>${pageTitle}</h1>
	<p>${description}</p>

	<form:form method="POST" commandName="study"
		action="${contextPath}/study/edit/${study.id}">
		<div class="form-group">
			<label for="name"><spring:message code="name"></spring:message></label>
			<form:input class="form-control" type="text" path="name" id="name" />
			<form:errors path="name" cssClass="help-inline" />
		</div>
		<div class="form-group">
			<label for="description"><spring:message code="description"></spring:message></label>
			<form:input class="form-control" type="text" path="description"
				id="description" />
			<form:errors path="description" cssClass="help-inline" />
		</div>
		<div class="form-group">
			<label for="startTime"><spring:message code="startTime"></spring:message></label>
			<form:input class="form-control" type="datetime-local"
				path="startTime" id="startTime" />
			<form:errors path="startTime" cssClass="help-inline" />
		</div>

		<div class="form-group">
			<label for="endTime"><spring:message code="endTime"></spring:message></label>
			<form:input class="form-control" type="datetime-local" path="endTime"
				id="endTime" />
			<form:errors path="endTime" cssClass="help-inline" />
		</div>

		<div class="form-group">
			<label for="patient"><spring:message code="patient"></spring:message></label>
			<form:select id="patient" cssClass="form-control" path="patientId"
				items="${patientList}" itemLabel="name" itemValue="id" />
			<form:errors path="patientId" cssClass="help-inline" />
		</div>
		<div class="form-group">
			<label for="room"><spring:message code="room"></spring:message></label>
			<form:select id="patient" cssClass="form-control" path="roomId"
				items="${roomList}" itemLabel="name" itemValue="id" />
			<form:errors path="roomId" cssClass="help-inline" />
		</div>
		<div class="form-group">
			<label for="doctors"><spring:message code="doctors"></spring:message></label>
			<form:select id="doctors" cssClass="form-control" path="doctorIds"
				items="${doctorList}" itemLabel="name" itemValue="id" />
			<form:errors path="doctorIds" cssClass="help-inline" />
		</div>
		<div class="form-group">
			<label for="status"><spring:message code="status"></spring:message></label> 
			<form:select cssClass="form-control" path="status" items="${statusList}" itemValue="shortName" itemLabel="fullName" />
			<form:errors path="status" cssClass="help-inline" />
		</div>	
		<button type="submit" class="btn btn-primary">Submit</button>
	</form:form>
</custom:layout>