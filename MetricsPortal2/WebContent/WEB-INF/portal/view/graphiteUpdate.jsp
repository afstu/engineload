<%@ include file="/WEB-INF/portal/resources/header.jsp"%>

<div class="panel panel-default">
	<div class="panel-heading">
		<h4 class="panel-title">Update Graphite</h4>
	</div>

	<div class="panel-body">
		<form:form method='POST' action='/MetricsPortal2/graphite/update'
			modelAttribute="Graphite">
				<div class="input-group input-group-sm">
				<span class="input-group-addon" id="sizing-addon3">@</span>
				<form:input type="text" class="form-control"
					placeholder="${Graphite.getGraphiteUrl()}"
					aria-describedby="sizing-addon3" path="GraphiteUrl" />
					
										<form:hidden path="Id" value="${Graphite.getId()}" />
			</div>
			<button type="submit" class="btn btn-default">Opslaan</button>
		</form:form>

	</div>
	<div class="panel-footer"></div>
</div>


<%@ include file="/WEB-INF/portal/resources/footer.jsp"%>