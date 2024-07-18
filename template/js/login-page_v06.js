$(document).ready(function() {
    $('#login-form, #login-form btn').on('submit', (e) =>  {
        e.preventDefault();
        let formData = new FormData($('#login-form')[0]);
        $.ajax({
            method: 'POST',
            data: {
                username: formData.get('username'),
                password: formData.get('password'),
                loginAction: 'login'
            },
            url: `${window.context}/login`,
            beforeSend: () => showLoading(),
            success: (data) => loginHandle(data),
        })
    });

    const loginHandle = async function({status, msg}) {
        await hideLoading();
        const html = `<div class="text-${status >= 400 ? 'danger' : 'success'} fw-light">${msg}</div>`
        $('#login-status').append(html);
        if (status == 200) {
            await new Promise((resolve) => setTimeout(() => resolve(), 1000));
            window.location.reload()
        }
    }

    const showLoading = function() {
        const loadingHTML = `
            <div class="spinner-border text-success loading-icon" role="status">
                <span class="visually-hidden">Loading...</span>
            </div>
        `;
        $('#login-form button').find('.loading-icon').remove();
        $('#login-form button').append(loadingHTML);
        $('#login-form button').attr('disabled', true);
        $('#login-status').empty()
    }

    const hideLoading = async function() {
        await new Promise((resolve) => setTimeout(() => resolve(), 1000));
        $('#login-form button').find('.loading-icon').remove();
        $('#login-form button').attr('disabled', false);
    }
})