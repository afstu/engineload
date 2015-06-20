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
					<th>Cluster Naam</th>
					<th>Cluster Status</th>
					<th>Cluster Director</th>
					<th>Cluster Beschrijving</th>
					<th>Cluster Rollen</th>
					<th colspan=2>Cluster Tools</th>

				</tr>
			</thead>
			<tbody>

				<c:forEach var="Cluster" items="${clusterList}">

					<tr>
						<td>${Cluster.getClusterNaam()}</td>
						<td>${Cluster.getClusterStatus()}</td>
						<td>${Cluster.getClusterDirectorNaam()}</td>
						<td>${Cluster.getClusterBeschrijving()}</td>
						<td>${Cluster.getClusterRollenString()}</td>

						<td><form:form method='GET'
								action='/MetricsPortal2/clusters/update'>
								<div class="btn-group-xs" role="group" aria-label="...">
									<button type="submit" name="update" class="btn btn-default"
										value="${Cluster.getClusterId()}">Edit</button>
								</div>
							</form:form></td>
						<td><form:form method='POST'
								action='/MetricsPortal2/clusters/delete/'>
								<div class="btn-group-xs" role="group" aria-label="...">
									<button type="submit" name="delete" class="btn btn-danger"
										value="${Cluster.getClusterId()}">Delete</button>
								</div>
							</form:form></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="panel-footer"></div>
</div>




<%@ include file="/WEB-INF/portal/resources/footer.jsp"%>