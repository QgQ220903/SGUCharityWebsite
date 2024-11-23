$(function () {
  $("#blogDataTable")
    .DataTable({
      paging: true,
      lengthChange: true,
      searching: true,
      ordering: true,
      info: true,
      autoWidth: false,
      responsive: true,
      buttons: ["excel", "pdf", "print", "colvis"],
      lengthMenu: [
        [4, 8, 12, 16, -1],
        [4, 8, 12, 16, "Tất cả"],
      ],
      pageLength: 4,
      language: {
        buttons: {
          excel: "Xuất Excel",
          pdf: "Xuất PDF",
          print: "In",
          colvis: "Chọn cột",
        },
        info: "Hiển thị _START_ đến _END_ trong tổng số _TOTAL_ người dùng",
        infoEmpty: "Không có dữ liệu",
        search: "Tìm kiếm:",
        paginate: {
          first: "Đầu",
          previous: "Trước",
          next: "Sau",
          last: "Cuối",
        },
        lengthMenu: "Hiển thị _MENU_ bản ghi",
      },
    })
    .buttons()
    .container()
    .appendTo("#blogDataTableWrapper .col-md-6:eq(0)");
});
