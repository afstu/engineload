<%@ include file="/portal/resources/header.jsp"%>

<div class="panel panel-default">
	<div class="panel-heading">
		<h4 class="panel-title">Nieuwe Rol</h4>
	</div>
	
	<div class="panel-body">
<form:form method='POST' action='/rollen/create' modelAttribute="Rol">
					<div class="input-group input-group-sm">
				<span class="input-group-addon" id="sizing-addon3">@</span>
									<spring:bind path="Rol">
  								<input id="rn" name="RolNaam" type="text" class="form-control" />
						</spring:bind>
						</div>
									<div class="input-group input-group-sm">
				<span class="input-group-addon" id="sizing-addon3">@</span>
									<spring:bind path="Rol">
  								<input id="rb" name="RolBeschrijving" type="text" class="form-control"/>
						</spring:bind>
						</div>
						
						
		
<!-- 		<div class="input-group input-group-sm"> -->
<!-- 		  <span class="input-group-addon" id="sizing-addon3">@</span> -->
<%--   			<form:input type="text" class="form-control" placeholder="Rolnaam" aria-describedby="sizing-addon3" path="RolNaam"/> --%>
<!-- 		</div> -->
<!-- 				<div class="input-group input-group-sm"> -->
<!-- 		  <span class="input-group-addon" id="sizing-addon3">@</span> -->
<%--   			<form:input type="text" class="form-control" placeholder="Beschrijving" aria-describedby="sizing-addon3" path="RolBeschrijving"/> --%>
<!-- 		</div> -->
	
		 <button type="submit" class="btn btn-default">Opslaan</button>
		</form:form>
	
	</div>
	<div class="panel-footer"></div>
</div>


