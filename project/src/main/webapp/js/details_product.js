'use strict';
(function() {
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('id');

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

    const productDTOState = {
        __aInternal: undefined,
        aListener: function(prev, cur) {},
        set product(val) {
            this.aListener(this.__aInternal, val);
            this.__aInternal = val;
        },
        get product() {
            return this.__aInternal;
        },
        onChangeHandler: function(listener) {
            this.aListener = listener;
        }
    }
    productDTOState.onChangeHandler((prev, dto) => {
        $('#product-name h4').removeClass('placeholder').text(dto.name)
        $('#product-description').html(`<span>${dto.description}</span>`)
        $("#product-specification span").removeClass('placeholder').text(dto.specification)
        $('#product-brand span').removeClass('placeholder').text(dto.brand)
        $('#product-id span').removeClass('placeholder').text(dto.id)
        $('#product-price span').removeClass('placeholder').text(`${dto.priceFormat}đ`)
        $('#product-discount-name span').removeClass('placeholder').text(dto.discount.name)
        $('#product-discount-price h4').removeClass('placeholder').text(dto.discountPriceFormat)
        document.getElementById('blog-content').dataset.blogPublicId = dto.blogPath;
        let carousel = `
            <div class="carousel-wrapper">
                <!-- Flickity HTML init -->
                <div class="carousel carousel-main" data-flickity='{"pageDots": false, "contain": true, "fullscreen": true, "wrapAround": true}'>
                </div>
                <div class="carousel carousel-nav"
                     data-flickity='{ "asNavFor": ".carousel-main", "contain": true, "pageDots": false, "contain": true}'>
                </div>
            </div>
        `;
        $('#gallery').parent().html(carousel)
        dto.gallery.forEach(public_id => {
            $('.carousel-main').append(`
                <div class="carousel-cell" data-public-id="${public_id}">
                    <img src="${window.contextPath}/inventory/images/loading-gif.gif" alt="">
                </div>
            `)
            $('.carousel-nav').append(`
                <div class="carousel-cell" data-public-id="${public_id}">
                    <img src="${window.contextPath}/inventory/images/loading-gif.gif" alt="">
                </div>
            `)
        })
        const element = document.querySelector('.carousel-main');
        new Flickity(element, {
            contain: true,
            pageDots: false,
            fullscreen: true,
            wrapAround: true,
        });
        const element2 = document.querySelector('.carousel-nav')
        new Flickity(element2, {
            asNavFor: ".carousel-main",
            contain: true,
            pageDots: false,
        });
    })
    $.ajax({
        url: `${window.contextPath}/api/v1/products/getDetails?id=${id}`,
    }).then(data => {
        return new Promise(resolve => setTimeout(() => resolve(data), 2000))
    }).then((data) => {
        productDTOState.product = data
        return new Promise(resolve => setTimeout(() => resolve(data), 1000))
    }).then(() => {
        $('*[data-public-id] img').each(async (_, element) => {
            const cl = cloudinary.Cloudinary.new({cloud_name: 'dqki124o5'})
            const public_id = $(element).parent().data('public-id')
            const imgURL = cl.url(public_id, {secure: true})
            $(element).prop('src', imgURL);
        })
        return new Promise(resolve => setTimeout(() => resolve(), 1000))
    }).then(() => {
        const cl = cloudinary.Cloudinary.new({cloud_name: 'dqki124o5'})
        const public_id = $('*[data-blog-public-id]').data('blog-public-id')
        const blogPath = cl.url(public_id, {secure: true, resource_type: 'raw'})
        return $.ajax({url: blogPath})
    }).then(response => {
        $('#tab-product-content').html(`${response}`)
    })
})()