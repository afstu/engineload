<%@ include file="/WEB-INF/portal/resources/header.jsp"%>

<div class="panel panel-default">
	<div class="panel-heading">
		<h4 class="panel-title">Nieuwe Rol</h4>
	</div>
	
	<div class="panel-body">
<form:form method='POST' action='/MetricsPortal2/rollen/create' modelAttribute="Rol">
	
		<div class="input-group input-group-sm">
		  <span class="input-group-addon" id="sizing-addon3">@</span>
  			<form:input type="text" class="form-control" placeholder="Rolnaam" aria-describedby="sizing-addon3" path="RolNaam"/>
		</div>
				<div class="input-group input-group-sm">
		  <span class="input-group-addon" id="sizing-addon3">@</span>
  			<form:input type="text" class="form-control" placeholder="Beschrijving" aria-describedby="sizing-addon3" path="RolBeschrijving"/>
		</div>
	
		 <button type="submit" class="btn btn-default">Opslaan</button>
		</form:form>
	
	</div>
	<div class="panel-footer"></div>
</div>


<%@ include file="/WEB-INF/portal/resources/footer.jsp"%>