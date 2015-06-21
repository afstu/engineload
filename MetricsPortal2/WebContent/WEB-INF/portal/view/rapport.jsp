<%@ include file="/WEB-INF/portal/resources/header.jsp" %>


<div class="panel panel-default">
	<div class="panel-heading">
		<h4 class="panel-title">Rapport List</h4>
	</div>
	
	<div class="panel-body">
	
	<c:forEach var="rapport" items="${rapportList}>
	    <img src=${rapport.getGraphiteUrl()}/render?target=aliasByMetric(SE.HPC.${rapport.getClusterNaam()}.${rapport.getClusterStatus()}.${rapport.getClusterDirector()}.U)&drawNullAsZero=true&title=${rapport.getClusterDirector()}&from=-1hourss&height=230&width=650&fontSize=12&areaMode=all&areaAlpha=0.2&yStepLeft=10&bgcolor=EFEFEF30/>
        <img src=${rapport.getGraphiteUrl()}/render?target=aliasByMetric(SE.HPC.${rapport.getClusterNaam()}.${rapport.getClusterStatus()}.${rapport.getClusterDirector()}.S)&drawNullAsZero=true&title=${rapport.getClusterDirector()}&from=-1hourss&height=230&width=650&fontSize=12&areaMode=all&areaAlpha=0.2&yStepLeft=10&bgcolor=EFEFEF30/>
        <img src=${rapport.getGraphiteUrl()}/render?target=aliasByMetric(SE.HPC.${rapport.getClusterNaam()}.${rapport.getClusterStatus()}.${rapport.getClusterDirector()}.E)&drawNullAsZero=true&title=${rapport.getClusterDirector()}&from=-1hourss&height=230&width=650&fontSize=12&areaMode=all&areaAlpha=0.2&yStepLeft=10&bgcolor=EFEFEF30/>
        <img src=${rapport.getGraphiteUrl()}/render?target=aliasByMetric(SE.HPC.${rapport.getClusterNaam()}.${rapport.getClusterStatus()}.${rapport.getClusterDirector()}.C)&drawNullAsZero=true&title=${rapport.getClusterDirector()}&from=-1hourss&height=230&width=650&fontSize=12&areaMode=all&areaAlpha=0.2&yStepLeft=10&bgcolor=EFEFEF30/>
	
	</c:forEach>
		</div>
	<div class="panel-footer"></div>
</div>


        
<%@ include file="/WEB-INF/portal/resources/footer.jsp" %>