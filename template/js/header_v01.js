$('.log-out-btn').on('click', (e) => {
    e.preventDefault();
    $.ajax({
        method: 'POST',
        url: `${window.context}/login`,
        data: {
            loginAction: 'logout'
        },
        success: () => {window.location.reload()}
    })
})