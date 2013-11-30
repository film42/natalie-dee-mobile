$(window).ready(function() {

  var updating = false;

  var appendComics = function() {

    $.get("/random", function(data) {

      newComics = $(data).children();

      $('.images ul').append(newComics);
      $('.loading').addClass('hidden');

      // Allow more updates!
      updating = false;

    });

  }

  $(window).scroll(function() {

    var scrollDist = $(document).height() - $(window).scrollTop();

    if(scrollDist < 1000 && updating == false) {

      // Ensure not massive surge
      updating = true;
      $('.loading').removeClass('hidden');

      // Loading comics
      appendComics();
    }

  });

});
