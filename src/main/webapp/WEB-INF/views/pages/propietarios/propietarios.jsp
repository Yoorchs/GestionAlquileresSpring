<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<body>
	<header>
		<h2>Propietarios</h2>
	</header>

	<div>
		<a href='<c:url value = "propietarios/addPropietario" />' class="btn btn-info">Añadir nuevo
			Propietario</a>
	</div>
	<div class="row">
		<label class="col-xs-3">DNI</label>
		<label class="col-xs-3">Nombre</label>
		<label class="col-xs-3">Apellidos</label>
		<label class="col-xs-3"></label>
	</div>
	<div class="row">
		<c:choose>
			<c:when test="${not empty propietarios}">
				<c:forEach var="propietario" items="${propietarios}">
					<div class="col-xs-3">${propietario.dni}</div>
					<div class="col-xs-3">${propietario.nombre}</div>
					<div class="col-xs-3">${propietario.apellidos}</div>
					<div class="btn-group col-xs-3">
						<a class="col-xs-5 btn btn-warning"
							href='<c:url value="propietarios/deletePropietario/${propietario.codigo}"/>'>Eliminar propietario</a>
						
						<a class="col-xs-5 btn btn-default"
							href='<c:url value="propietarios/${propietario.codigo}"/>'> Editar propietario</a>
							
					<div class="col-xs-2"></div>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<p>No hay registros en BBDD</p>
			</c:otherwise>
		</c:choose>
	</div>
</body>