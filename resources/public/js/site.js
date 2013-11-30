$(window).ready(function() {

  var MAX_SCROLL = 5;
  var CURRENT_SCROLL_LEVEL = 0;

  var updating = false;

  var appendComics = function() {

    $.get("/random", function(data) {

      newComics = $(data).children();

      $('.images ul').append(newComics);
      $('.loading').addClass('hidden');

      // Allow more updates!
      if(CURRENT_SCROLL_LEVEL < MAX_SCROLL)
        updating = false;

    });

  }

  $(window).scroll(function() {

    var scrollDist = $(document).height() - $(window).scrollTop();

    if(scrollDist < 1000 && updating == false) {

      // Ensure not massive surge
      updating = true;
      CURRENT_SCROLL_LEVEL++;
      $('.loading').removeClass('hidden');

      // Loading comics
      appendComics();
    }

  });

});
