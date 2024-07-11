'use strict';
(function() {
    $('.product-img[data-public-id] img').each(async (_, element) => {
        const cl = cloudinary.Cloudinary.new({cloud_name: 'dqki124o5'})
        const public_id = $(element).parent('.product-img').data('public-id')
        const imgURL = cl.url(public_id, {secure: true})
        $(element).prop('src', imgURL);
        $(element).on('load', () => $(element).removeAttr('style'))
    })
})()