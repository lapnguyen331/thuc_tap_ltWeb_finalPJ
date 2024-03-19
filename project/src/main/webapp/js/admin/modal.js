$('.product-list-body',).on('click', '.view-btn', function() {
    const id = this.dataset.productId;
    loadModal(id);
    product_details_modal.show();
})
const product_details_modal = new mdb.Modal($('#admin-product-modal'),{});
const blog_change_confirm_modal = new mdb.Modal($('#confirm-modal'), {});

let admin_mdal_tabs = $('#admin-product-modal .tab')
$(admin_mdal_tabs).on('click', function() {
    $(admin_mdal_tabs).removeClass('active');
    $(this).addClass('active');
    let tabs = $('.tab-content');
    const targetTab = $(this.dataset.modalTab);
    $(tabs).removeClass('show')
    $(targetTab).addClass('show');
})

$('#confirm-modal .modal-footer').on('click', '.btn-confirm-cancel, .btn-confirm-changed', function() {
    if ($(this).hasClass('btn-confirm-cancel')) {
        setTimeout(function() {
            editor.getContext().tool.save.removeAttribute('disabled');
        }, 10)
    }
    else if ($(this).hasClass('btn-confirm-changed')) {
        console.log('saved handled!')
    }
})

const editor = SUNEDITOR.create((document.getElementById('sample')),{
    font : [
        'Segoe UI',
        'Arial',
        'tahoma',
        'Verdana',
    ],
    defaultStyle: "font-size:15px; font-family: 'Segoe UI'",
    buttonList: [
        ['undo', 'redo'],
        ['font', 'fontSize', 'formatBlock'],
        ['paragraphStyle', 'blockquote'],
        ['bold', 'underline', 'italic', 'strike', 'subscript', 'superscript'],
        ['fontColor', 'hiliteColor', 'textStyle', 'removeFormat'],
        ['preview', 'fullScreen'],
        ['outdent', 'indent'],
        ['align', 'horizontalRule', 'list', 'lineHeight'],
        ['table', 'link', 'image', 'showBlocks', 'save'],
    ],
    callBackSave : function (contents, isChanged) {
        if (isChanged) blog_change_confirm_modal.show();
    },
    previewTemplate: `
    <section id="details-product">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="tabs">
                        <div class="tablinks tablinks-is-active" data-tab-target="#tab-product-content">Mô tả sản phẩm</div>
                        <div class="tablinks"> Địa chỉ mua hàng</div>
                        <div class="tablinks"> Đánh giá sản phẩm</div>
                    </div>
                </div>
                <div class="col-md-12">
                    <div class="tab-content-wrapper">
                        <section class="tab-content tab-is-active" id="tab-product-content">
                            {{contents}}
                        </section>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <script>
        document.querySelector('body').classList.remove('sun-editor-editable');
    </script>
    `,
});

let preStoredImage = [
    {id: 1, src: 'https://hongsamkimy.vn/wp-content/uploads/2023/07/Untitled-design-2023-07-13T093015.683-jpg.webp'},
    {id: 2, src: 'https://hongsamkimy.vn/wp-content/uploads/2023/07/cao-hong-sam-hong-seon-gold-3-768x768.webp'},
    {id: 3, src: 'https://hongsamkimy.vn/wp-content/uploads/2023/07/cao-hong-sam-hong-seon-gold-2-768x768.webp'},
    {id: 4, src: 'https://hongsamkimy.vn/wp-content/uploads/2023/07/cao-hong-sam-hong-seon-gold-5-768x768.webp'},
    {id: 5, src: 'https://hongsamkimy.vn/wp-content/uploads/2023/07/cao-hong-sam-hong-seon-gold-4-768x768.webp'}
]

$('#input_price_product').on('change', function() {
    console.log($(this).val())
    let value = ($(this).val().replaceAll(',',''));
    if (!value.match(/^[0-9]+$/)) value = 0;
    $(this).val(new Intl.NumberFormat('en-US').format(value));
})

const asyncAutocomplete = document.querySelector('#async-brands');
const asyncFilter = async (query) => {
  const url = `https://dummyjson.com/products`;
  const response = await fetch(url);
  const data = await response.json();
  const filter = data.products.filter(({brand}) => brand.indexOf(query) >= 0);
  return filter;
};

new mdb.Autocomplete(asyncAutocomplete, {
  filter: asyncFilter,
  displayValue: (value) => value.brand
});

const category = [
    {name: 'Nước hồng sâm', value: 'nuoc_hong_sam'},
    {name: 'Cao hồng sâm', value: 'cao_hong_sam'},
    {name: 'Sâm khô', value: 'sam_kho'},
    {name: 'Sâm mật ong', value: 'sam_mat_ong'},
    {name: 'Hắc sâm', value: 'hac_sam'},
    {name: 'Nhân sâm tươi', value: 'nhan_sam_tuoi'},
    {name: 'Rượu sâm linh chi', value: 'ruou_sam_linh_chi'},
    {name: 'Trà hồng sâm', value: 'tra_hong_sam'},
    {name: 'Viên hồng sâm', value: 'vien_hong_sam'},
]

const producers = [
    {name: 'Nhà cung cấp 1', value: 'NCC1'},
    {name: 'Nhà cung cấp 2', value: 'NCC2'},
    {name: 'Nhà cung cấp 3', value: 'NCC3'},
    {name: 'Nhà cung cấp 4', value: 'NCC4'},
    {name: 'Nhà cung cấp 5', value: 'NCC5'}
]

const products = {
    "new": {
        id: '',
        name: '',
        price: '',
        category: '',
        specific: '',
        weight: 0,
        brand: '',
        remains: 0,
        min_age: 0,
        producer: '',
        desc: '',
        thumbnail: undefined,
        gallery: undefined,
        blog: '',
    },
    
    "CHS013912": { 
        id: 'CHS013912',
        name: 'Cao hồng sâm Hong Seon Gold hộp 2 lọ 250g',
        price: '980000',
        category: 'cao_hong_sam',
        specific: 'Hộp 2 lọ 250g',
        weight: 600,
        brand: 'Hong Seon',
        remains: 15,
        min_age: 13,
        producer: 'NCC2',
        desc: 'Cao hồng sâm 6 năm tuổi kết hợp bới một số loại thảo dược quý như linh chi, bạch chỉ,' +
        'xuyên khung,… làm gia tăng những thành phần đặc hiệu và công năng của sản phẩm giúp cho việc hỗ trợ' 
        + 'và tăng cường bồi bổ cơ thể, cải thiện sức khỏe đạt hiệu quả tốt nhất…',
        thumbnail: [{id: 1, src: 'https://hongsamkimy.vn/wp-content/uploads/2023/07/Untitled-design-2023-07-13T093015.683-jpg.webp'}],
        gallery: [
            {id: 1, src: 'https://hongsamkimy.vn/wp-content/uploads/2023/07/Untitled-design-2023-07-13T093015.683-jpg.webp'},
            {id: 2, src: 'https://hongsamkimy.vn/wp-content/uploads/2023/07/cao-hong-sam-hong-seon-gold-3-768x768.webp'},
            {id: 3, src: 'https://hongsamkimy.vn/wp-content/uploads/2023/07/cao-hong-sam-hong-seon-gold-2-768x768.webp'},
            {id: 4, src: 'https://hongsamkimy.vn/wp-content/uploads/2023/07/cao-hong-sam-hong-seon-gold-5-768x768.webp'},
            {id: 5, src: 'https://hongsamkimy.vn/wp-content/uploads/2023/07/cao-hong-sam-hong-seon-gold-4-768x768.webp'}
        ],
        blog: `
        <h4>Cao hồng sâm Hong Seon Gold hộp 2 lọ 250g</h4>
        <img src="/template/image/cao-hong-sam-hong-seon-gold-hop-2-lo-250g-1.webp" alt="">
        <h4>Thành phần:</h4>
        <p>15% Hồng sâm cô đặc 6 năm tuổi (Ginsenosides Rg1, Rb1 và Rg3 tổng 4mg/g, chất rắn 70% trở lên), 70% hỗn hợp thực vật cô đặc (táo tàu 9%, rễ bạch chỉ 9%, rễ hoa cúc 9%, ngải cứu, Gujeolcho, Rễ Jihwang (Sukjihwang), lá non Artemisia, rễ hoa cúc, thân rễ sapju, rễ mẫu đơn, rễ xương cựa, hạch nấm bokryeong, nấm linh chi, cam thảo, chất rắn 60% trở lên), isomaltooligosaccharide.</p>
        <h4>Công dụng:</h4>
        <ul>
            <li>Bồi bổ cơ thể, tăng sức lao động trí óc và chân tay, chống mệt mỏi.</li>
            <li>Chống lão hóa, cải thiện chức năng não bộ ở người lớn tuổi, tăng khả năng tập trung trí tuệ, tăng trí nhớ</li>
            <li>Tăng cường khả năng miễn dịch, giúp cơ thể tăng khả năng thích nghi phòng vệ đối với những kích thích có hại</li>
            <li>Điều hòa huyết áp, giúp hồi phục huyết áp ở cơ thể.</li>
            <li>Giảm tác hại của chất phóng xạ, cải thiện và phòng ngừa sự phát triển của các tế bào ung thư.</li>
            <li>Ngăn ngừa nguy cơ phát sinh cholesterol cao, từ đó hạn chế sự hình thành các mảng xơ vữa động mạch</li>
            <li>Thành phần chính Saponin có trong cao sâm có tác dụng loại bỏ chất Alloxan và chất Streptozotocin là nguyên nhân làm tăng đường huyết do đó cao sâm cũng hỗ trợ trong việc giảm đường huyết, tiểu đường</li>
            <li>Saponin trong hồng sâm làm tăng hoạt động enzym liên quan tới sự thoái hóa ethanol và acetaldehyd, do đó đối với người hay uống rượu, để giảm gan tránh được độc tính</li>
        </ul>
        <h4>Đối tượng sử dụng: </h4>
        <ul>
            <li>Người suy kiệt cơ thể hậu điều trị cần tăng cường đề kháng và bồi bổ sức khỏe.</li>
            <li>Người làm việc trí óc hoặc học tập cường độ cao.</li>
            <li>Người bị ung thư, mắc các bệnh về rối loạn chuyển hóa đường trong nước tiểu, mỡ, tim, huyết áp…</li>
            <li>Người già cần bồi bổ sức khỏe, tăng cường trí nhớ.</li>
            <li>Phụ nữ hơn 30 tuổi mong muốn duy trì một cơ thể khỏe mạnh và một làn da tươi trẻ.</li>
        </ul>
        <img src="/template/image/cao-hong-sam-hong-seon-gold-hop-2-lo-250g.webp" alt="">
        <h4>Cách dùng:</h4>
        <ul>
            <li><b>Phòng bệnh:</b> Mỗi ngày uống 2 lần, mỗi lần uống lấy 1 thìa cao nhỏ (có trong hộp)= 5mg, pha với nước ấm rồi uống trước bữa ăn 15-20 phút.</li>
            <li><b>Chữa bệnh:</b> Mỗi ngày uống 3 lần. Mỗi lần uống lấy 2 thìa cao nhỏ, pha với nước ấm, uống trước bữa ăn 15-20 phút.</li>
        </ul>
        <p><b>Quy cách đóng gói:</b> Hộp 2 lọ 250g</p>
        <p><b>Hạn sử dụng:</b> 2 năm kể từ ngày sản xuất</p>
        <p><b>Lưu ý:</b> Thực phẩm không là thuốc, không thay thế thuốc chữa bệnh</p>
        <p><b>Nhà sản xuất:</b> KOREA GINSENG CENTER</p>
        <p><b>Điện thoại:</b> 0934 138 985</p>
        `,
    }
}

function loadModal(productID) {
    const  target = products[productID],
        form = $('#mdal-tab-setting .form-product-setting'),
        input_id_product = $("#input_id_product"),
        input_name_product = $("#input_name_product"),
        input_price_product = $('#input_price_product'),
        select_category_product = $('#select_category_product'),
        select_producers_product = $('#select_producers_product'),
        input_specific_product = $('#input_specific_product'),
        input_weight_product = $('#input_weight_product'),
        input_brand_product = $('#input_brand_product'),
        input_remains_product = $('#input_remains_product'),
        input_age_product = $('#input_age_product'),
        thumbnail = $('#images_thumbnail_product'),
        gallery = $('#images_gallery_product'),
        txtArea_desc_product = $('#txtArea_desc_product');

    $(input_id_product).val(target.id)
    $(input_name_product).val(target.name);
    $(input_price_product).val(new Intl.NumberFormat('en-US').format(target.price));
    $(input_specific_product).val(target.specific);
    $(input_weight_product).val(target.weight);
    $(input_brand_product).val(target.brand);
    $(input_remains_product).val(target.remains);
    $(input_age_product).val(target.min_age);
    //reset inner HTML
    $(select_category_product).html('');
    $(select_producers_product).html('');
    $(thumbnail).html('');
    $(gallery).html('');
    $(txtArea_desc_product).html('');

    category.forEach(cate => {
        const optionHTML = `<option value="${cate.value}" ${cate.value === target.category ? 'selected' : ''}>${cate.name}</option>`;
        $(select_category_product).append(optionHTML);
    })
    producers.forEach(pr => {
        const optionHTML = `<option value="${pr.value}" ${pr.value === target.producer ? 'selected' : ''}>${pr.name}</option>`;
        $(select_producers_product).append(optionHTML);
    })
    $(thumbnail).imageUploader({
        preloaded: target.thumbnail,
        imagesInputName: 'photos',
        preloadInputName: 'old-photos',
        maxFiles: 1
    });
    $(gallery).imageUploader({
        preloaded: target.gallery,
        imagesInputName: 'photos',
        preloadInputName: 'old-photos',
        maxFiles: 6
    });
    $(txtArea_desc_product).append(target.desc);
    editor.setContents(target.blog);

    if (productID === 'new') {
        $(input_id_product).attr('readonly', false)
        $(form)[0].reset();
    }
    else {
        $(input_id_product).attr('readonly', true)
    }
}