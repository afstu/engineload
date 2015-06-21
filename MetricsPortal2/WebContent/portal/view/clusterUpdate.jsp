<%@ include file="/portal/resources/header.jsp"%>

<div class="panel panel-default">
	<div class="panel-heading">
		<h4 class="panel-title">Update Cluster</h4>
	</div>

	<div class="panel-body">
		<form:form method='POST' action='/clusters/update'
			modelAttribute="Cluster">

			<div class="input-group input-group-sm">
				<span class="input-group-addon" id="sizing-addon3">@</span>
				<form:input type="text" class="form-control"
					placeholder="${Cluster.getClusterNaam()}"
					aria-describedby="sizing-addon3" path="ClusterNaam" />
			</div>
			<div class="input-group input-group-sm">
				<span class="input-group-addon" id="sizing-addon3">@</span>
				<form:input type="text" class="form-control"
					placeholder="${Cluster.getClusterStatus()}"
					aria-describedby="sizing-addon3" path="ClusterStatus" />
			</div>
			<div class="input-group input-group-sm">
				<span class="input-group-addon" id="sizing-addon3">@</span>
				<form:input type="text" class="form-control"
					placeholder="${Cluster.getClusterBeschrijving()}"
					aria-describedby="sizing-addon3" path="ClusterBeschrijving" />
			</div>
			<div class="input-group input-group-sm">
				<span class="input-group-addon" id="sizing-addon3">@</span>
<%-- 				<form:input type="text" class="form-control" --%>
<%-- 					placeholder="${Cluster.getClusterDirector().getDirectorNaam()}" --%>
<%-- 					aria-describedby="sizing-addon3" path="ClusterDirector" /> --%>
						<spring:bind path="ClusterDirector">
  								<input id="cd" name="ClusterDirector" type="text" class="form-control" value="${Cluster.getClusterDirector().getDirectorNaam()}" />
						</spring:bind>
			</div>			


			<button type="submit" class="btn btn-default">Opslaan</button>
		</form:form>

	</div>
	<div class="panel-footer"></div>
</div>


<%@ include file="/portal/resources/footer.jsp"%>