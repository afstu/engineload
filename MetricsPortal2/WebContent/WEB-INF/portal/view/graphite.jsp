<%@ include file="/WEB-INF/portal/resources/header.jsp"%>

<div class="panel panel-default">
	<div class="panel-heading">
		<h3 class="panel-title">Portal Graphite</h3>
	</div>
	<div class="panel-body">
		<!-- Table -->
		<table class="table">
			<thead>
			<tr>
				<th>Graphite URL</th>
				<th>Graphite Tools</th>
				</tr>
			</thead>
			
				<c:forEach var="graphite" items="${graphiteList}">
			<tr>
				<td>${graphite.getGraphiteUrl()}</td>

				<td><form:form method='GET'
						action='/MetricsPortal2/graphite/update' modelAttribute="graphite">
						<div class="btn-group-xs" role="group" aria-label="...">
							<button type="submit" name="update" class="btn btn-danger"
								value="${graphite.getId()}">Edit</button>
						</div>
					</form:form></td>
			</tr>
			</c:forEach>
		</table>
	</div>
	<div class="panel-footer"></div>
</div>


<%@ include file="/WEB-INF/portal/resources/footer.jsp"%>