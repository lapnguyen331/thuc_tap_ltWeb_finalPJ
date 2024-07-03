
const translate = {
    "decimal":        "",
    "emptyTable":     "Không có dữ liệu trong table",
    "info":           "Hiển thị từ dòng _START_ đến dòng _END_ trong tổng số _TOTAL_",
    "infoEmpty":      "Showing 0 to 0 of 0 entries",
    "infoFiltered":   "(Lọc ra trong _MAX_ dòng)",
    "infoPostFix":    "",
    "thousands":      ",",
    "lengthMenu":     "Hiển thị tối đa _MENU_ dòng",
    "loadingRecords": "Đang load...",
    "processing":     "",
    "search":         "Tìm kiếm",
    "zeroRecords":    "Không tìm thấy trường nào.",
    "paginate": {
        "first":      "Đầu tiên",
        "last":       "Cuối cùng",
        "next":       "Tiếp theo",
        "previous":   "Trước đó"
    },
    "aria": {
        "sortAscending":  ": activate to sort column ascending",
        "sortDescending": ": activate to sort column descending"
    }
};
const categories = {
    'cao_hong_sam' : 'Cao Hồng Sâm'
}
const data_tables = new DataTable('#table_orders', {
    ajax: `${window.context}/api/user-profile-orderapi`,
    language: translate,
    dom: 'tip',

    // createdRow1: function (row,data,dataIndex){
    //
    // },
    columns: [
        {
            data: 'id'
        },

        {
            data: 'totalprice',
            render: function(data) {
                return new Intl.NumberFormat('en-US').format(data)
            }
        },
        {
            data: 'status',
            render: function(data) {

                var em;
                switch (data) {
                    case 1:
                        em="đã được giao";
                        break;
                    case 2:
                        em="đang giao";
                        break;
                    case 3:
                        em="đang trả về";
                        break;
                    case 0:
                        em="đã bị hủy";
                        break;
                }
                return `<span class="statuscom${data} badge badge-warning">${em}</span>`
            }
        },
        {
            data: 'create'
        },

       
    ],
    createdRow: function( row, data, dataIndex ) {
        $(row).addClass('abc');
        $(row).css("cursor","pointer");

    },
});


//init
(function() {
    $('#table_tabs').on('click', 'li', function() {
        const lis = $(this).siblings().filter('li');
        $(lis).removeClass('active');
        $(this).addClass('active');
        var firstClass = $(this).attr('class').replace(/^(\S*).*/, '$1');
        var all = $(".abc").map(function() {
            this.style.display='none'; //hide element rồi show lại
        }).get();


        switch(firstClass) { //show lại
            case 'order-complete':
                var star = document.querySelector(".statuscom1").closest("tr").removeAttribute("style");
                break;
            case 'order-pending':
                console.log(2);
                document.querySelector(".statuscom2").closest("tr").removeAttribute("style");

                break;
            case 'order-return':
                document.querySelector(".statuscom3").closest("tr").removeAttribute("style");
                break;
            case 'order-cancel':
                console.log(4);
                document.querySelector(".statuscom0").closest("tr").removeAttribute("style");
                break;
            case 'order-all1':
                console.log(0);
                document.querySelector(".statuscom1").closest("tr").removeAttribute("style");
                document.querySelector(".statuscom2").closest("tr").removeAttribute("style");
                document.querySelector(".statuscom3").closest("tr").removeAttribute("style");
                document.querySelector(".statuscom0").closest("tr").removeAttribute("style");
            break;
        }

    })
    
    $('#date_picker').daterangepicker({
        locale: {
            format: 'DD/MM/YYYY'
        },
        linkedCalendars: false,
        alwaysShowCalendars: true,
        drop: "auto",
        autoUpdateInput: false,
    }, function(start, end, label) {
      console.log('New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')');
    });
    
    $('#date_picker').on('apply.daterangepicker', function(ev, picker) {
        $(this).val(picker.startDate.format('DD/MM/YYYY') + ' - ' + picker.endDate.format('DD/MM/YYYY'));
    })
    
    $('#date_picker').on('cancel.daterangepicker', function(ev, picker) {
        $(this).val('');
    });
    
    new mdb.Select($('#status_filter')[0], {
        clearButton: true,
    })
    $('.select-clear-btn').css({display: 'none'});

})();
//search nhiều dòng
// $('#intrestTable1').DataTable( {});
// oTable.column(5).search("caod|bullet",true,false).draw();



// direct order-detail
$('#table_orders').on('click', 'tbody tr', function() {
        var orderid =$(this).children('.sorting_1').text(); //string
    $(this).attr("href",`${window.context}/user-order-detail?${orderid}`);
    const protocol = window.location.protocol;
    const host = window.location.host;
    const pathname = window.location.pathname;
    const search = window.location.search;
    const hash = window.location.hash;
    window.location.replace(protocol+"//"+host+pathname+"-detail"+`?orderdes=${orderid}`);

        // console.log( orderid);
})