<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<body>
	<header>
		<h2>Detalles de Inquilino</h2>
	</header>
	<form:form action="save" method="post" commandName="inquilino">
		<c:if test="${!empty inquilino}">
			<form:hidden path="codigo" />
		</c:if>
		<div class="row">
			<form:label cssClass="" class="col-xs-3" path="nombre">Nombre: </form:label>
			<form:input class="col-xs-3" placeholder="Introduzca su Nombre" path="nombre"
				cssErrorClass="text-danger" cssClass="" />
			<form:errors class="col-xs-3" path="nombre" cssClass="text-danger" />
		</div>
		<div class="row">
			<form:label class="col-xs-3" path="apellidos">Apellidos: </form:label>
			<form:input class="col-xs-3" placeholder="Introduzca su Apellido" path="apellidos"
				cssErrorClass="" cssClass="" />
			<form:errors class="col-xs-3" path="apellidos" cssClass="text-danger"></form:errors>
		</div>
		<div class="row">
			<form:label class="col-xs-3" path="dni">DNI: </form:label>
			<form:input class="col-xs-3" path="dni" cssErrorClass="" cssClass="" />
			<form:errors class="col-xs-3" path="dni" cssClass="text-danger"></form:errors>
		</div>
		<div class="row">
			<form:label class="col-xs-3" path="email">Email: </form:label>
			<form:input class="col-xs-3" path="email" cssErrorClass="" cssClass="" />
			<form:errors class="col-xs-3" path="email" cssClass="text-danger"></form:errors>
		</div>
		<div class="row">
			<form:label class="col-xs-3" path="telefono">Telefono: </form:label>
			<form:input class="col-xs-3" path="telefono" cssErrorClass="" cssClass="" />
			<form:errors class="col-xs-3" path="telefono" cssClass="text-danger"></form:errors>
		</div>
		<c:set var="men" value="Crear"></c:set>
		<c:if test="${inquilino.codigo > 0}">
			<c:set var="men" value="Editar"></c:set>
		</c:if>
		<input type="submit" value="${men}">
	</form:form>
</body>
