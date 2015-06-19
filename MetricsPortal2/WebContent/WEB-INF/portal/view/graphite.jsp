<%@ include file="/WEB-INF/portal/resources/header.jsp" %>


              
<c:forEach var="gebruiker" items="${gebruikerList}">
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">Portal Gebruikers </h3>
  </div>
  <div class="panel-body">
      <!-- Table -->
  <table class="table">
  <thead><tr><th>ID</th><th>Voornaam</th><th>Achternaam</th><th>CorpKey</th> </tr></thead>
    <tbody><tr><td>${gebruiker.getId()}</td><td>${gebruiker.getVoorNaam()}</td><td>${gebruiker.getAchterNaam()}</td><td>${gebruiker.getCorpKey()}</td> </tr></tbody>
  </table>
  </div>
  <div class="panel-footer"></div>
</div>
</c:forEach>


        
<%@ include file="/WEB-INF/portal/resources/footer.jsp" %>