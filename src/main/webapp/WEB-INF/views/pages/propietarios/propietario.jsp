<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<body>
	<header>
		<h2>Detalles de Propietario</h2>
	</header>
	<form:form action="save" method="post" commandName="propietario" class="container-fluid">
		<c:if test="${!empty propietario}">
			<form:hidden path="codigo" />
		</c:if>
		<div class="row">
			<form:label cssClass="" class="col-xs-3" path="nombre">Nombre: </form:label>
			<form:input class="col-xs-3" placeholder= "${propietario.nombre}" path="nombre"
				cssErrorClass="" cssClass="" />
			<form:errors class="col-xs-3" path="nombre" cssClass="text-danger" />
		</div>
		<div class="row">
			<form:label class="col-xs-3" path="apellidos">Apellidos: </form:label>
			<form:input class="col-xs-3" placeholder="${propietario.apellidos}" path="apellidos"
				cssErrorClass="" cssClass="" />
			<form:errors class="col-xs-3" path="apellidos" cssClass="text-danger"></form:errors>
		</div>
		<div class="row">
			<form:label class="col-xs-3" path="dni">DNI: </form:label>
			<form:input class="col-xs-3" path="dni" placeholder="${propietario.dni}" cssErrorClass="" cssClass="" />
			<form:errors class="col-xs-3" path="dni" cssClass="text-danger"></form:errors>
		</div>
		<div class="row">
			<form:label class="col-xs-3" path="email">Email: </form:label>
			<form:input class="col-xs-3" path="email" placeholder="${propietario.email}" cssErrorClass="" cssClass="" />
			<form:errors class="col-xs-3" path="email" cssClass="text-danger"></form:errors>
		</div>
		<div class="row">
			<form:label class="col-xs-3" path="telefono">Telefono: </form:label>
			<form:input class="col-xs-3" path="telefono" placeholder="${propietario.telefono}" cssErrorClass="" cssClass="" />
			<form:errors class="col-xs-3" path="telefono" cssClass="text-danger"></form:errors>
		</div>
		
		<div class="row">
			<form:label class="col-xs-3" path="nss">NIF: </form:label>
			<form:input class="col-xs-3" path="nss" placeholder="${propietario.nss}" cssErrorClass="" cssClass="" />
			<form:errors class="col-xs-3" path="nss" cssClass="text-danger"></form:errors>
		</div>
		
		<c:set var="men" value="Crear"></c:set>
		<c:if test="${propietario.codigo > 0}">
			<c:set var="men" value="Editar"></c:set>
		</c:if>
		<input type="submit" value="${men}">
	
	<div><h2>Listado de pisos del propietario</h2></div>
		
		<div class="row">
			<span class="col-xs-2">Direccion</span> 
			<span class="col-xs-2"> Superficie</span> 
			<span class="col-xs-2"> Cuota Mensual</span>
			<span class="col-xs-2"> Referencia Catastral</span> 
			<span class="col-xs-2"></span> 
		</div>

		<div>
			<c:choose>
				<c:when test="${not empty propietario.pisos}">
					<c:forEach var="piso" items="${propietario.pisos}">
						<div class="row">
							<span class="col-xs-2">${piso.direccion}</span>
							<span class="col-xs-2">${piso.superficie}</span> 
							<span class="col-xs-2">${piso.precionoche}</span>
							<span class="col-xs-2">${piso.referenciacatastral}</span> 
							<span class="col-xs-2"> </span>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<div>No hay registros en BBDD</div>
				</c:otherwise>
			</c:choose>
		</div>
	</form:form>
</body>