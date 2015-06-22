<%@ include file="/portal/resources/header.jsp"%>

<div class="panel panel-default">
	<div class="panel-heading">
		<h4 class="panel-title">Nieuwe Gebruiker</h4>
	</div>
	
	<div class="panel-body">
<form:form method='POST' action='/gebruikers/create' modelAttribute="Gebruiker">
	
		<div class="input-group input-group-sm">
		  <span class="input-group-addon" id="sizing-addon3">@</span>
  			<form:input type="text" class="form-control" placeholder="Corporate Key" aria-describedby="sizing-addon3" path="CorpKey"/>
		</div>
			<div class="input-group input-group-sm">
		  <span class="input-group-addon" id="sizing-addon3">@</span>
  			<form:input type="text" class="form-control" placeholder="Voornaam" aria-describedby="sizing-addon3" path="Voornaam"/>
		</div>
				<div class="input-group input-group-sm">
		  <span class="input-group-addon" id="sizing-addon3">@</span>
  			<form:input type="text" class="form-control" placeholder="Achternaam" aria-describedby="sizing-addon3" path="Achternaam"/>
		</div>
				<div class="input-group input-group-sm">
		  <span class="input-group-addon" id="sizing-addon3">@</span>
  			<form:input type="password" class="form-control" placeholder="P@55w0rD" aria-describedby="sizing-addon3" path="WachtWoord"/>
		</div>

				<div class="input-group input-group-sm">
		  <span class="input-group-addon" id="sizing-addon3">@</span>
  			<form:input type="text" class="form-control" placeholder="Beschrijving" aria-describedby="sizing-addon3" path="GebruikerBeschrijving"/>
		</div>
	
<%-- 	<c:forEach var="Rol" items="${RolList}"> --%>
<!-- 					<div class="input-group input-group-sm"> -->
<!-- 		  <span class="input-group-addon" id="sizing-addon3"><input type="checkbox" aria-label="..."></span> -->
<%--   			<form:input class="form-control" aria-describedby="sizing-addon3" name="RolList" id="RolList" value="${Rol.getId()}" path="${RolList[i].rl}" readonly="true"/> --%>
<!-- 		</div>		 -->
<%-- 						</c:forEach> --%>
	
					
	
		 <button type="submit" class="btn btn-default">Opslaan</button>
		</form:form>
	
	</div>
	<div class="panel-footer"></div>
</div>


<%@ include file="/portal/resources/footer.jsp"%>