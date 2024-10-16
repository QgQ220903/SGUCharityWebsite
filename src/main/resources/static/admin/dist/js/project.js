$(function () {
  // Summernote
  $("#summernote").summernote();

  $('#projectDataTable').DataTable({
    "paging": true,
    "lengthChange": true,
    "searching": true,
    "ordering": true,
    "info": true,
    "autoWidth": false,
    "responsive": true,
    "buttons": ["copy", "csv", "excel", "pdf", "print", "colvis"]
  }).buttons().container().appendTo('#projectDataTableWrapper .col-md-6:eq(0)');

  //Date picker
  $("#startDate").datetimepicker({
    format: "L",
  });
  //Date picker
  $("#endDate").datetimepicker({
    format: "L",
  });

  //Date and time picker
  $("#reservationdatetime").datetimepicker({ icons: { time: "far fa-clock" } });

  //Date range picker
  $("#reservation").daterangepicker();
  //Date range picker with time picker
  $("#reservationtime").daterangepicker({
    timePicker: true,
    timePickerIncrement: 30,
    locale: {
      format: "MM/DD/YYYY hh:mm A",
    },
  });
  //Date range as a button
  $("#daterange-btn").daterangepicker(
    {
      ranges: {
        Today: [moment(), moment()],
        Yesterday: [moment().subtract(1, "days"), moment().subtract(1, "days")],
        "Last 7 Days": [moment().subtract(6, "days"), moment()],
        "Last 30 Days": [moment().subtract(29, "days"), moment()],
        "This Month": [moment().startOf("month"), moment().endOf("month")],
        "Last Month": [
          moment().subtract(1, "month").startOf("month"),
          moment().subtract(1, "month").endOf("month"),
        ],
      },
      startDate: moment().subtract(29, "days"),
      endDate: moment(),
    },
    function (start, end) {
      $("#reportrange span").html(
        start.format("MMMM D, YYYY") + " - " + end.format("MMMM D, YYYY")
      );
    }
  );
});
