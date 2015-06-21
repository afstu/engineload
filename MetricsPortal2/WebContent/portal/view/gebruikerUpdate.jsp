<%@ include file="/portal/resources/header.jsp"%>

<div class="panel panel-default">
	<div class="panel-heading">
		<h4 class="panel-title">Update Gebruiker</h4>
	</div>

	<div class="panel-body">
		<form:form method='POST' action='/gebruikers/update'
			modelAttribute="gebruiker">


			<div class="input-group input-group-sm">
				<span class="input-group-addon" id="sizing-addon3">@</span>
				<form:input type="text" class="form-control"
					placeholder="${gebruiker.getCorpKey()}"
					aria-describedby="sizing-addon3" path="CorpKey" />
			</div>
			<div class="input-group input-group-sm">
				<span class="input-group-addon" id="sizing-addon3">@</span>
				<form:input type="text" class="form-control"
					placeholder="${gebruiker.getVoornaam()}"
					aria-describedby="sizing-addon3" path="Voornaam" />
			</div>
			<div class="input-group input-group-sm">
				<span class="input-group-addon" id="sizing-addon3">@</span>
				<form:input type="text" class="form-control"
					placeholder="${gebruiker.getAchternaam()}"
					aria-describedby="sizing-addon3" path="Achternaam" />
			</div>
			<div class="input-group input-group-sm">
				<span class="input-group-addon" id="sizing-addon3">@</span>
				<form:input type="password" class="form-control"
					placeholder="${gebruiker.getWachtWoord()}"
					aria-describedby="sizing-addon3" path="WachtWoord" />
			</div>
			<div class="input-group input-group-sm">
				<span class="input-group-addon" id="sizing-addon3">@</span>
									<spring:bind path="GebruikerRollen">
  								<input id="gr" name="GebruikerRollen" type="text" class="form-control" value="${gebruiker.getGebruikerRollenString()}" disabled/>
						</spring:bind>
						</div>
			<div class="input-group input-group-sm">
				<span class="input-group-addon" id="sizing-addon3">@</span>
				<form:input type="text" class="form-control"
					placeholder="${gebruiker.getGebruikerBeschrijving()}"
					aria-describedby="sizing-addon3" path="GebruikerBeschrijving" />
			</div>
			
			

			<button type="submit" class="btn btn-default">Opslaan</button>
		</form:form>

	</div>
	<div class="panel-footer"></div>
</div>


<%@ include file="/portal/resources/footer.jsp"%>