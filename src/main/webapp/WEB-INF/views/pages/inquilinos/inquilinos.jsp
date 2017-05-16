<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<div>
		<a href='<c:url value = "inquilinos/addInquilino" />' class="btn btn-info">Añadir nuevo
			inquilino</a>
	</div>
	<div class="row">
		<label class="col-xs-3">DNI</label>
		<label class="col-xs-3">Nombre</label>
		<label class="col-xs-3">Apellidos</label>
		<label class="col-xs-3"></label>
	</div>
	<div class="row">
		<c:choose>
			<c:when test="${not empty inquilinos}">
				<c:forEach var="inquilino" items="${inquilinos}">
					<div class="col-xs-3">${inquilino.dni}</div>
					<div class="col-xs-3">${inquilino.nombre}</div>
					<div class="col-xs-3">${inquilino.apellidos}</div>
					<div class="btn-group col-xs-3">
						<a class="col-xs-5 btn btn-warning"
							href='<c:url value="inquilinos/deleteInquilino/${inquilino.codigo}"/>'>Eliminar inquilino</a>
						<a class="col-xs-5 btn btn-default"
							href='<c:url value="inquilinos/${inquilino.codigo}"/>' > Editar inquilino</a>
						<div class="col-xs-2"></div>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<p>No hay registros en BBDD</p>
			</c:otherwise>
		</c:choose>
	</div>
