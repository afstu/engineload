<%@ include file="/portal/resources/header.jsp"%>


<div class="panel panel-default">
	<div class="panel-heading">
		<h4 class="panel-title">Portal Gebruikers</h4>
	</div>
	<div class="panel-body">

		<!-- Table -->
		<table class="table">
			<thead>
				<tr>
					<th>CorpKey</th>
					<th>Voornaam</th>
					<th>Achternaam</th>
					<th>Beschrijving</th>
					<th>Rollen</th>
					<th colspan=2>Gebruiker Tools</th>

				</tr>
			</thead>
			<tbody>

				<c:forEach var="gebruiker" items="${gebruikerList}">


					<tr>
						<td>${gebruiker.getCorpKey()}</td>
						<td>${gebruiker.getVoornaam()}</td>
						<td>${gebruiker.getAchternaam()}</td>

						<td>${gebruiker.getGebruikerBeschrijving()}</td>
						<td>
										<c:forEach var="Rol" items="${gebruiker.getGebruikerRollen()}">
							${Rol.getRolNaam()}
						</c:forEach>
						</td>
						<c:choose>

							<c:when test="${gebruiker.getCorpKey()=='admin'}">
								<td><form:form method='GET'
										action='/gebruikers/update'
										modelAttribute="gebruiker">
										<div class="btn-group-xs" role="group" aria-label="...">
											<button type="submit" name="update" class="btn btn-default"
												value="${gebruiker.getCorpKey()}" >Edit</button>
										</div>
									</form:form></td>
															
								<td><form:form method='POST'
										action='/gebruikers/delete/'>
										<div class="btn-group-xs" role="group" aria-label="...">
											<button type="submit" name="delete" class="btn btn-danger"
												value="${gebruiker.getCorpKey()}" disabled >Delete</button>
										</div>
									</form:form></td>
							</c:when>
							<c:otherwise>
								<td><form:form method='GET'
										action='/gebruikers/update'
										modelAttribute="gebruiker">
										<div class="btn-group-xs" role="group" aria-label="...">
											<button type="submit" name="update" class="btn btn-default"
												value="${gebruiker.getCorpKey()}" >Edit</button>
										</div>
									</form:form></td>
								<td><form:form method='POST'
										action='/gebruikers/delete/'>
										<div class="btn-group-xs" role="group" aria-label="...">
											<button type="submit" name="delete" class="btn btn-danger"
												value="${gebruiker.getCorpKey()}">Delete</button>
										</div>
									</form:form></td>
							</c:otherwise>

						</c:choose>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="panel-footer"></div>
</div>




<%@ include file="/portal/resources/footer.jsp"%>