<link rel="stylesheet" type="text/css" href="..\portal/scripts/css/bootstrap-datetimepicker.min.css"/>
<script src="..\portal/scripts/js/Moment.js"></script>
<script src="..\portal/scripts/js/bootstrap-datetimepicker.min.js"></script>

<script type="text/javascript">
    $(function () {
        $('#datetimepicker6').datetimepicker({ locale: 'en-uk' });
        $('#datetimepicker7').datetimepicker({ locale: 'en-uk' });
        $("#datetimepicker6").on("dp.change", function (e) {
            $('#datetimepicker7').data("DateTimePicker").minDate(e.date);
            
        });
        $("#datetimepicker7").on("dp.change", function (e) {
            $('#datetimepicker6').data("DateTimePicker").maxDate(e.date);
            $('#datetimepicker7').data("DateTimePicker").maxDate(e.date);
        });
    });
</script>
	