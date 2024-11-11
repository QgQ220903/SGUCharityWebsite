$(function () {
  $("#categoryDataTable")
    .DataTable({
      paging: true,
      lengthChange: true,
      searching: true,
      ordering: true,
      info: true,
      autoWidth: false,
      responsive: true,
      buttons: ["excel", "pdf", "print", "colvis"],
    })
    .buttons()
    .container()
    .appendTo("#categoryDataTableWrapper .col-md-6:eq(0)");
});
