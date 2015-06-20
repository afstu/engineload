<%@ include file="/WEB-INF/portal/resources/header.jsp"%>

<div class="panel panel-default">
	<div class="panel-heading">
		<h4 class="panel-title">Nieuwe Cluster</h4>
	</div>
	
	<div class="panel-body">
<form:form method='POST' action='/MetricsPortal2/clusters/create' modelAttribute="Cluster">
	
		<div class="input-group input-group-sm">
		  <span class="input-group-addon" id="sizing-addon3">@</span>
  			<form:input type="text" class="form-control" placeholder="Clusternaam" aria-describedby="sizing-addon3" path="ClusterNaam"/>
		</div>
			<div class="input-group input-group-sm">
		  <span class="input-group-addon" id="sizing-addon3">@</span>
  			<form:input type="text" class="form-control" placeholder="Clusterstatus (D|T|A|P)" aria-describedby="sizing-addon3" path="ClusterStatus"/>
		</div>
				<div class="input-group input-group-sm">
		  <span class="input-group-addon" id="sizing-addon3">@</span>
  			<form:input type="text" class="form-control" placeholder="Clusterdirector" aria-describedby="sizing-addon3" path="ClusterDirector"/>
		</div>
				<div class="input-group input-group-sm">
		  <span class="input-group-addon" id="sizing-addon3">@</span>
  			<form:input type="text" class="form-control" placeholder="Beschrijving" aria-describedby="sizing-addon3" path="ClusterBeschrijving"/>
		</div>
	
		 <button type="submit" class="btn btn-default">Opslaan</button>
		</form:form>
	
	</div>
	<div class="panel-footer"></div>
</div>


<%@ include file="/WEB-INF/portal/resources/footer.jsp"%>