<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<div>
		<a class="btn btn-info" href='<c:url value = "pisos/addPiso" />'>Registrar nuevo alojamiento (solo propietarios registrados)</a>
	</div>
	<div class="row">
		<span class="col-xs-2">Direccion</span> 
		<span class="col-xs-2"> Superficie</span> 
		<span class="col-xs-2"> Cuota Mensual</span>
		<span class="col-xs-2"> Referencia Catastral</span> 
		<span class="col-xs-2"> Disponible</span>
		<span class="col-xs-2"></span> 
	</div>
	<div>
		<c:choose>
			<c:when test="${not empty listadopisos}">
				<c:forEach var="piso" items="${listadopisos}">
					<div class="row">
						<span class="col-xs-2">${piso.direccion}</span>
						<span class="col-xs-2">${piso.superficie}</span> 
						<span class="col-xs-2">${piso.precionoche}</span>
						<span class="col-xs-2">${piso.referenciacatastral}</span> 
						<c:if test="${piso.alquilado == false}">
							<a class="btn col-xs-2 btn-info" href="<c:url value = '/pisos/${piso.codigo}'/>">Detalle de  piso</a>
						</c:if>
						<span class="col-xs-2"> </span>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<div>No hay registros en BBDD</div>
			</c:otherwise>
		</c:choose>
	</div>
