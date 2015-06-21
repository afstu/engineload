<%@ include file="/portal/resources/header.jsp"%>
<%@ include file="/portal/resources/datePicker.jsp"%>

<div class="panel panel-default">
	<div class="panel-heading">
		<h4 class="panel-title">Nieuw Rapport</h4>
	</div>
	
	<div class="panel-body">
	


<div class="container">
    <div class='col-md-5'>
        <div class="form-group">
            <div class='input-group date' id='datetimepicker6'>
                <input type='text' class="form-control" />
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
        </div>
    </div>
    <div class='col-md-5'>
        <div class="form-group">
            <div class='input-group date' id='datetimepicker7'>
                <input type='text' class="form-control" />
                <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
            </div>
        </div>
    </div>
</div>

<form:form method='POST' action='/rapporten/create' modelAttribute="Rapport">
		 <button type="submit" class="btn btn-default">Verzenden</button>
		</form:form>



	
	</div>
	<div class="panel-footer"></div>
</div>


<%@ include file="/portal/resources/footer.jsp"%>