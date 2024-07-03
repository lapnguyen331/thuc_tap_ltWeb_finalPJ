const numberField = $('.number-field');
$(numberField).find('.number-btn').on('click', function(e) {
    if($(numberField).find('input')[0].dataset.amount == 0){
        if(this.classList.contains('btn-right')){
            $(numberField).find('input')[0].dataset.amount++
            console.log("hehe2")

        }
        let value = $(numberField).find('input')[0].dataset.amount
        $(numberField).find('input').val(value);
        console.log("hehe")
        return;
    }
    else {
        if(this.classList.contains('btn-left')){
            $(numberField).find('input')[0].dataset.amount--
            console.log("hehe1")

        }
        if(this.classList.contains('btn-right')){
            $(numberField).find('input')[0].dataset.amount++
            console.log("hehe2")

        }
    }
    let value = $(numberField).find('input')[0].dataset.amount
    $(numberField).find('input').val(value);


    // (this.classList.contains('btn-left')) && $(numberField).find('input')[0].dataset.amount--;
    // (this.classList.contains('btn-right')) && $(numberField).find('input')[0].dataset.amount++;
    // let value = $(numberField).find('input')[0].dataset.amount
    // $(numberField).find('input').val(value);
    // if($(numberField).find('input')[0].dataset.amount < 0){
    //     (this.classList.contains('btn-left')) && $(numberField).find('input')[0].dataset.amount++;
    // }

})

const tabsNav = $('.tabs');
$(tabsNav).find('.tablinks').on('click', function() {

    const target = this.dataset.tabTarget;

    $(target).parent().find('.tab-content').removeClass('tab-is-active')
    $(target).addClass('tab-is-active');

    $(this).parent().find('.tablinks').removeClass('tablinks-is-active')
    $(this).addClass('tablinks-is-active');
})

$('.rating-field input[type="radio"]').on('click', (e) => {
    $(e.target).prop('checked', true);
})