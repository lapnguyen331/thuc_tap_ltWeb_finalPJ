'use strict';
$('#input-images').imageUploader();
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