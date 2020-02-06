$(document).on("click", "a.confirm", function(e) {
    e.preventDefault();
    if (confirm('Are you sure to delete message?')) {
        location.href = $(this).attr('href');
    }
});