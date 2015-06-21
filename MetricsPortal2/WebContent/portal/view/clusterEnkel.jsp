<%@ include file="/portal/resources/header.jsp"%>


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
					<th colspan=3>Cluster Tools</th>
					
				</tr>
			</thead>
			<tbody>
					<tr>
						<td>${Cluster.getClusterNaam()}</td>
						<td>${Cluster.getClusterStatus()}</td>
							<td>${Cluster.getClusterDirector().getDirectorNaam()}</td>
						<td>${Cluster.getClusterRollen()}</td>
						<td>${Cluster.getClusterBeschrijving()}</td>
<td>
<div class="input-group input-group-xs">
<div class="btn-group-xs" role="group" aria-label="...">
  			 <a href="/clusters" class="btn btn-success" role="button">OK</a>
		</div>
		</div>
</td>
								<td><form:form method='GET'
										action='/clusters/update'
										modelAttribute="Cluster">
										<div class="btn-group-xs" role="group" aria-label="...">
											<button type="submit" name="update" class="btn btn-default"
												value="${Cluster.getId()}" >Edit</button>
										</div>
									</form:form></td>
								<td><form:form method='POST'
										action='/clusters/delete/' modelAttribute="Cluster">
										<div class="btn-group-xs" role="group" aria-label="...">
											<button type="submit" name="delete" class="btn btn-danger"
												value="${Cluster.getId()}">Delete</button>
										</div>
									</form:form></td>
					</tr>
			</tbody>
		</table>
	</div>
	<div class="panel-footer"></div>
</div>




<%@ include file="/portal/resources/footer.jsp"%>