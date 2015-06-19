<%@ include file="/WEB-INF/portal/resources/header.jsp"%>


<div class="panel panel-default">
	<div class="panel-heading">
		<h4 class="panel-title">Portal Gebruikers</h4>
	</div>
	<div class="panel-body">


		<c:forEach var="gebruiker" items="${gebruikerList}">
			<!-- Table -->
			<table class="table">
				<thead>
					<tr>
						<!-- 						<th>ID</th> -->
						<th>Voornaam</th>
						<th>Achternaam</th>
						<th>CorpKey</th>
						<th>Beschrijving</th>
						<th>Tools</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<%-- 						<td>${gebruiker.getId()}</td> --%>
						<td>${gebruiker.getVoornaam()}</td>
						<td>${gebruiker.getAchternaam()}</td>
						<td>${gebruiker.getCorpKey()}</td>
						<td>${gebruiker.getGebruikerBeschrijving()}</td>
						<td><c:choose>
								<c:when test="${gebruiker.getId()=='0'}">
									<form:form method='GET'
										action='/MetricsPortal2/gebruikers/update'
										modelAttribute="gebruiker">
										<div class="span2">
											<div class="btn-group-xs" role="group" aria-label="...">
												<button type="submit" name="update" class="btn btn-default"
													value=${gebruiker.getId()} >Edit</button>
											</div>
										</div>
										</form:form>
								</c:when>
								<c:otherwise>
									<form:form method='GET'
										action='/MetricsPortal2/gebruikers/update'
										modelAttribute="gebruiker">
										<div class="span2">
											<div class="btn-group-xs" role="group" aria-label="...">
												<button type="submit" name="update" class="btn btn-default"
													value=${gebruiker.getId()} >Edit</button>
											</div>
										</div>
									</form:form>
									<form:form method='POST'
										action='/MetricsPortal2/gebruikers/delete/'>
										<div class="span2">
											<div class="btn-group-xs" role="group" aria-label="...">
												<button type="submit" name="delete" class="btn btn-danger"
													value=${gebruiker.getId()}>Delete</button>
											</div>
										</div>
									</form:form>
								</c:otherwise>
							</c:choose></td>
					</tr>
				</tbody>
			</table>

		</c:forEach>
	</div>
	<div class="panel-footer"></div>
</div>




<%@ include file="/WEB-INF/portal/resources/footer.jsp"%>