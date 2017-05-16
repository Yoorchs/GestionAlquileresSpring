<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<body>
	<header>
		<h2>Detalle de Pisos</h2>
	</header>
	<form:form action="save" method="post" commandName="piso" class="container-fluid">
		<c:if test="${!empty piso}">
			<form:hidden path="codigo" />
		</c:if>
		<div class="row">
			<form:label class="col-xs-3" cssClass="" path="direccion">Direccion: </form:label>
			<form:input class="col-xs-3" placeholder="Introduzca su Direccion" path="direccion" cssErrorClass="" cssClass="" />
			<form:errors class="col-xs-3" path="direccion" cssClass="text-danger" />
		</div>
		<div class="row">
			<form:label class="col-xs-3" path="superficie">Superficie: </form:label>
			<form:input class="col-xs-3" placeholder="Introduzca la Superficie" path="superficie" cssErrorClass="" cssClass="" />
			<form:errors class="col-xs-3" path="superficie" cssClass="text-danger"></form:errors>
		</div>
		<div class="row">
			<form:label class="col-xs-3" path="precionoche">Cuota de Alquiler: </form:label>
			<form:input class="col-xs-3" placeholder="Introduzca la cuota de alquiler" path="precionoche" cssErrorClass="" cssClass="" />
			<form:errors class="col-xs-3" path="precionoche" cssClass="text-danger"></form:errors>
		</div>
		<div class="row">
			<form:label class="col-xs-3" path="referenciacatastral">Referencia Catastral: </form:label>
			<form:input class="col-xs-3" placeholder="Introduzca la referencia catastral" path="referenciacatastral" cssErrorClass="text-danger" cssClass="" />
			<form:errors class="col-xs-3" path="referenciacatastral" cssClass="text-danger"></form:errors>
		</div>
		<c:set var="men" value="Editar"></c:set>
		<c:set var="disable" value=""></c:set>
		<c:if test="${piso.codigo == 0}">
			<c:set var="men" value="Crear"></c:set>
			<c:set var="disable" value="hidden"></c:set>
		</c:if>
		<input type="submit" class="btn btn-default" value="${men}">
		<a class="btn btn-info" href="<c:url value='/pisos'/>"> Volver</a>
	</form:form>
</body>