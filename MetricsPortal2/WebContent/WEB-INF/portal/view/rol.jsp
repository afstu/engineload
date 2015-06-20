<%@ include file="/WEB-INF/portal/resources/header.jsp"%>


<div class="panel panel-default">
	<div class="panel-heading">
		<h4 class="panel-title">Portal Clusters</h4>
	</div>
	<div class="panel-body">

		<!-- Table -->
		<table class="table">
			<thead>
				<tr>
					<th>Rol Naam</th>
					<th>Rol Beschrijving</th>
					<th colspan=2>Rol Tools</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="Rol" items="${rolList}">

					<tr>
						<td>${Rol.getRolNaam()}</td>
						<td>${Rol.getRolBeschrijving()}</td>
						<c:choose>
							<c:when test="${Rol.getId()=='0'}">
								<td><form:form method='GET'
										action='/MetricsPortal2/rollen/update'
										modelAttribute="gebruiker">
										<div class="btn-group-xs" role="group" aria-label="...">
											<button type="submit" name="update" class="btn btn-default"
												value="${Rol.getId()}">Edit</button>
										</div>
									</form:form>
								<td><form:form method='POST'
										action='/MetricsPortal2/rollen/delete/'>
										<div class="btn-group-xs" role="group" aria-label="...">
											<button type="submit" name="delete" class="btn btn-danger"
												value="${Rol.getId()}" disabled>Delete</button>
										</div>
									</form:form></td>
							</c:when>
							<c:otherwise>
								<td><form:form method='GET'
										action='/MetricsPortal2/rollen/update'
										modelAttribute="gebruiker">
										<div class="btn-group-xs" role="group" aria-label="...">
											<button type="submit" name="update" class="btn btn-default"
												value="${Rol.getId()}">Edit</button>
										</div>
									</form:form></td>
								<td><form:form method='POST'
										action='/MetricsPortal2/rollen/delete/'>
										<div class="btn-group-xs" role="group" aria-label="...">
											<button type="submit" name="delete" class="btn btn-danger"
												value="${Rol.getId()}">Delete</button>
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




<%@ include file="/WEB-INF/portal/resources/footer.jsp"%>