$(function () {
  $("#userDataTable")
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
        [5, 10, 15, 20, -1],
        [5, 10, 25, 50, "Tất cả"],
      ],
      pageLength: 5,
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
    .appendTo("#userDataTableWrapper .col-md-6:eq(0)");
});
