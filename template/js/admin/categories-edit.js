'use strict';
$('#input-images, #image_blog').imageUploader();
$('#txt_date').daterangepicker({
    "singleDatePicker": true,
}, function(start, end, label) {
  console.log('New date range selected: ' + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD') + ' (predefined range: ' + label + ')');
});

const editor = SUNEDITOR.create((document.getElementById('blog_desc')), {
    width: '100%',
    height: 250,
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
        ['outdent', 'indent', 'fullScreen'],
        ['align', 'horizontalRule', 'list', 'lineHeight'],
        ['table', 'link', 'image', 'showBlocks', 'save'],
    ],
    callBackSave : function (contents, isChanged) {
        if (isChanged) blog_change_confirm_modal.show();
    },
});

const async_data_blog = async (value) => {
    const url = "/template/blog.txt";
    const response = await fetch(url);
    let {data} = await response.json();
    return data.filter(blog => {
        return blog.title.toLowerCase().startsWith(value.toLowerCase());
    });
}

new mdb.Autocomplete($('#box_blog_filter')[0], {
    filter: async_data_blog,
    displayValue: (blog) => blog.id,
    itemContent: (blog) => {
      const html = `
        <div class="blog-selector-wrap">
            <div class="img-wrap">
                <img src="${blog.img}" alt="">
            </div>
            <div class="blog-info">
                <div class="fw-semibold blog-title">
                    ${blog.title}
                </div>
                <div class="fw-normal blog-id">
                    Mã: ${blog.id}
                </div>
            </div>
        </div>
      `
      return html;
    },
});