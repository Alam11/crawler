$(document).ready(function(){

    var hash = window.location.hash;
    (!hash) ? loadContent("art/crisis.html") : loadContent(hash.substring(1));

    $('.navigationButton').click(function(e) {
        e.preventDefault();
        href = $(this).attr('href');
        loadContent.call(this, href );
        window.location.hash = href;
        return false;
    });
});

function loadContent(href) {
    $.ajax({
        url: href,
        dataType: "html",
        type: "GET",
        success: function (data) {
            $('#content').html(data);
        }
    });
}