<link rel="stylesheet" type="text/css" href="/MetricsPortal2/scripts/css/bootstrap-datetimepicker.min.css"/>
<script src="/MetricsPortal2/scripts/js/Moment.js"></script>
<script src="/MetricsPortal2/scripts/js/bootstrap-datetimepicker.min.js"></script>

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
	