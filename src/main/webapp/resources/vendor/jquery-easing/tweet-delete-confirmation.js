$(document).on("click", "a.confirm", function(e) {
    e.preventDefault();
    if (confirm('Are you sure to delete tweet?')) {
        location.href = $(this).attr('href');
    }
});