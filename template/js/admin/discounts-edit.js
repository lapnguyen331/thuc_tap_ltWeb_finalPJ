(async function() {
    $.ajax({
        url: '/template/products-2.txt',
        success: function(data) {
            const products = JSON.parse(data).data;
            products.sort(() => 0.5 - Math.random());
            for (let i = 0; i < 4; i++) {
                const html = `
                <div class="product-card">
                    <div class="img-wrap">
                        <img src="${products[i].images[0]}" width='100%' alt="">
                    </div>
                    <div class="info-wrap">
                        <div class="product-title">${products[i].name}</div>
                        <div class="product-price">Giá bán: ${products[i].price}</div>
                        <div class="id-wrap">
                            <div class="product-id">Mã SP: ${products[i].id}</div>
                        </div>
                    </div>
                    <div class="remove-btn">
                        <i class="fa-solid fa-circle-xmark"></i>
                    </div>
                </div>
                `
                $('.products-range').append(html);
            }
        }
    })
})()
const dataTable = new DataTable('#products_filter_table', {
    dom: 'ti',
    paging: false,
    scrollCollapse: true,
    scrollY: '400px',
    width: '100%',
    ajax: '/template/products-2.txt',
    columnDefs: [
        {
            width: 1,
            className: 'dt-nowrap',
            target: 0, 
            className: 'select-checkbox',
            orderable: false,
            data: null,
            render: function() {
                return `<input class="form-check-input" type="checkbox" value=""/>`
            }
        },
        {
            target: 0,
            className: 'dt-center'
        },
        {
            target: 1,
            data: null,
            render: function(data, type) {
                const html = `
                <div class="product-wrap">
                    <div class="img-wrap">
                        <img src="${data.images[0]}" width="100%" alt="">
                    </div>
                    <div class="info-wrap">
                        <div class="product-title">${data.name}</div>
                        <div class="product-price">Giá bán: ${data.price}</div>
                        <div class="id-wrap">
                            <div class="product-id">Mã SP: ${data.id}</div>
                        </div>
                    </div>
                </div>
                `;
                if (type === 'display') return html;
                else
                    return data.name;
            }
        },
        {
            target: 2, 
            defaultContent: '15',
            className: 'dt-center dt-nowrap',
            width: 1
        },
    ],
    select: {
        style: 'single',
        selector: 'td:first-child .form-check-input'
    },
    order: [[1, 'asc']],
});

$('#txt_date').daterangepicker({
    locale: {
        format: 'DD/MM/YYYY',
        applyLabel: "Áp dụng",
        cancelLabel: "Hủy bỏ",
    },
    linkedCalendars: false,
    alwaysShowCalendars: true,
    drop: "auto",
    autoUpdateInput: false,
}, function(start, end, label) {
  console.log('New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')');
});

$('#txt_date').on('apply.daterangepicker', function(ev, picker) {
    $(this).val(picker.startDate.format('DD/MM/YYYY') + ' ~ ' + picker.endDate.format('DD/MM/YYYY'));
})

$('#txt_date').on('cancel.daterangepicker', function(ev, picker) {
    $(this).val('');
});

const basicAutocomplete = document.querySelector('#search-autocomplete');
const data = ['One', 'Two', 'Three', 'Four', 'Five'];
const asyncFilter = async (query) => {
  const url = `https://dummyjson.com/products`;
  const response = await fetch(url);
  const data = await response.json();
  const filter = data.products.filter(({brand}) => brand.indexOf(query) >= 0);
  return filter;
};