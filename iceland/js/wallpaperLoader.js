$(document).ready(function(){

    var links = [
        "img/wallpapers/Untitled3.jpg",
        "img/wallpapers/nature.jpg",
        "img/wallpapers/lake.jpg",
        "img/wallpapers/lake2.jpg",
        "img/wallpapers/blue.jpg",
        "img/wallpapers/city1.jpg",
        "img/wallpapers/city2.jpg",
        "img/wallpapers/volc1.jpg",
        "img/wallpapers/volc2.jpg",
        "img/wallpapers/storm.jpg",
        "img/wallpapers/mist.jpg",
    ]

    function loadMoreContent(index)
    {
        $('#content').append('<img src='+links[index]+'>');
    };

    index = 0;

    $(window).scroll(function() {

        if( window.location.hash == "#art/Wallpapers.html"){
            const HOW_FAR_FROM_BOTT_NEW_PIC_LOAD = 10;
            if (( $(document).height() - $(window).height() -  $(window).scrollTop() < HOW_FAR_FROM_BOTT_NEW_PIC_LOAD  ) &&
                (index < links.length)) {
                loadMoreContent(index);
                index++;
            }
        }
    });

});
