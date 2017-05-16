<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<body>
	<section>
		<c:url var="/loginURL" value="login.html"/>
		<form action="${loginURL}" method="post">
			<div class="input-group input-sm">
				<label>Usuario</label>
				<input type="text" id="userId" name="userId" required="required">
			</div>
			<div class="input-group input-sm">
				<label>Password</label>
				<input type="password" id="password" name="password" required="required">
			</div>
			<div class="center-block">
				<input type="submit" value="Log In" class="btn btn-block btn-primary">
			</div>
		</form>
	</section>
</body>