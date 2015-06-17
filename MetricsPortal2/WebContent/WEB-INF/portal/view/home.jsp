<%@ include file="/WEB-INF/portal/resources/header.jsp" %>


 
<div class="container">                 
		<c:forEach var="rol" items="${rolList}">
  			 <div class="starter-template">
	            <h3>${rol.getRolNaam()}</h3>
	            <h4>${rol.getRolBeschrijving()}</h4>
    		</div>
        </c:forEach>             
        </div>
     </div>
    </body>
</html>
