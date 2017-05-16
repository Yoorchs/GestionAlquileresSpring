<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Aplicacion de Gestion de alquileres</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Mis propios estilos, deben ir tras los de bootstrap -->
<spring:url var="misEstilos" value="/resources/css/styles.css" />
<link rel="stylesheet" href="${misEstilos}">


<script src="https://code.jquery.com/jquery-3.1.1.min.js"
	integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
	crossorigin="anonymous"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<![endif]-->

<meta name="viewport" content="width=device-width, initial-scale=1">

<h1 class="row">
	<span class="col-xs-12 text-center text-uppercase"><spring:message
			code="aplicacion.name" /></span>
	<spring:url var="scriptW" value="/resources/js/weather.js" />
	<script type="text/javascript" src="${scriptW}"></script>
</h1>


<nav class="navbar navbar-default" id="nav">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Pincha para visualizar</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<c:url value='/'/>">Home</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="nav nav-tabs" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href='<c:url value="/inquilinos"/>'><spring:message
							code="menu.inquilinos" /></a></li>
				<li><a href='<c:url value="/pisos"/>'><spring:message
							code="menu.pisos" /></a></li>
				<li><a href='<c:url value="/propietarios"/>'><spring:message
							code="menu.propietarios" /></a></li>
				<li><a href='<c:url value="/swagger-ui.html"/>'><spring:message
							code="menu.servicios" /></a></li>
				<li><a href="weather.html"></a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
		        <li class="dropdown">
						<button class="btn btn-default btn-lg dropdown-toggle" data-toggle="dropdown" >Idiomas<span class="caret"></span></button>
						<ul class="dropdown-menu">
							<li>
								<a class="" href="?locale=es"><spring:message code="idioma.castellano" text="castellano"/></a>
							</li>
							<li>
								<a class="" href="?locale=en"><spring:message code="idioma.ingles" text="ingles"/></a>
							</li>
						</ul>
				</li>
			</ul>
			<!-- 
			<div id="weather">
			<section id="today">
				<div id="location"></div>
				<div id="country"></div>
			</section>
			</div>
			 -->
		<!-- 
		<div class="btn-group">
			<button class="btn btn-default btn-lg dropdown-toggle"
				data-toggle="dropdown">
				<spring:message code="boton.idioma" text="Castellano " />
				<span class="caret" ></span>
			</button>
			<ul class="dropdown-menu">
				<li><a class="btn btn-default" href="?locale=es"> <spring:message
							code="idioma.castellano" text="Castellano " /></a></li>
				<li><a class="btn btn-default" href="?locale=en"> <spring:message
							code="idioma.ingles" text="Ingles " /></a></li>
			</ul>
		</div>
			
			
			
		</div>

		
		--->
	</div>
</nav>