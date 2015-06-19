<%@ include file="/WEB-INF/portal/resources/header.jsp" %>


              
<c:forEach var="gebruiker" items="${clusterList}">
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">Portal Clusters </h3>
  </div>
  <div class="panel-body">
      <!-- Table -->
  <table class="table">
  <thead><tr><th>ID</th><th>Clusternaam</th><th>Clusterstatus</th><th>Clusterdirector</th> </tr></thead>
    <tbody><tr><td>${gebruiker.getId()}</td><td>${gebruiker.getVoorNaam()}</td><td>${gebruiker.getAchterNaam()}</td><td>${gebruiker.getCorpKey()}</td> </tr></tbody>
  </table>
  </div>
  <div class="panel-footer"></div>
</div>
</c:forEach>


        
<%@ include file="/WEB-INF/portal/resources/footer.jsp" %>