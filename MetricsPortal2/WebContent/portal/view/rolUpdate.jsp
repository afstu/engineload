<%@ include file="/portal/resources/header.jsp"%>

<div class="panel panel-default">
	<div class="panel-heading">
		<h4 class="panel-title">Update Rol</h4>
	</div>

	<div class="panel-body">
		<form:form method='POST' action='/rollen/update'
			modelAttribute="Rol">

			<div class="input-group input-group-sm disabled">
				<span class="input-group-addon" id="sizing-addon3">@</span>
				<form:input type="text" class="form-control"
					placeholder="${Rol.getId()}"
					aria-describedby="sizing-addon3" path="Id" disabled="true" />
					
					<form:hidden path="Id" value="${Rol.getId()}" />
			</div>

			<div class="input-group input-group-sm">
				<span class="input-group-addon" id="sizing-addon3">@</span>
				<form:input type="text" class="form-control"
					placeholder="${Rol.getRolNaam()}"
					aria-describedby="sizing-addon3" path="RolNaam" />
			</div>
			
			<div class="input-group input-group-sm">
				<span class="input-group-addon" id="sizing-addon3">@</span>
				<form:input type="text" class="form-control"
					placeholder="${Rol.getRolBeschrijving()}"
					aria-describedby="sizing-addon3" path="RolBeschrijving" />
			</div>
			
			<button type="submit" class="btn btn-default">Opslaan</button>
		</form:form>

	</div>
	<div class="panel-footer"></div>
</div>


<%@ include file="/portal/resources/footer.jsp"%>