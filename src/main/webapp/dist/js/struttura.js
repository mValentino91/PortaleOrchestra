
/**
 * Change Tile content without circular effect, the transition stop on the first and last elements.
 * @Param:
 *	- tile inner container 
 *	- direction: accepted value "next" or "prev"
 * @Return:
 *	- 0 if position of next element is internal (not last neither first)
 *	- 1 if the next element is the last of the list
 *	- -1 if the next element is the fist of the list
 * 	- null when is called on the fist o last element
 */
$.extend({
    changeTile: function (tile, direction) {

        var position = 0;

        //acrive tile
        var active = tile.find('.tile_inner').find('.act');

        if (direction == 'next') {

            //if active is not the last element
            if (!active.is(':last-child')) {

                //get next element
                var next = active.next('.tile_content');

                //if next is the last element set return value to 1
                if (next.is(':last-child')) {
                    position = 1;
                }

                //disable current active
                active.removeClass("act").addClass("dis");
                //enable next
                next.removeClass("dis").addClass("act");

            }
            else {
                position = 2;
            }

        }

        if (direction == 'prev') {

            //if active is not the first element
            if (!active.is(':first-child')) {

                //get prev element
                var next = active.prev('.tile_content');

                //if prev is the first element set return value to -1
                if (next.is(':first-child')) {
                    position = -1;
                }

                //disable current active
                active.removeClass("act").addClass("dis");
                //enable next
                next.removeClass("dis").addClass("act");

            }
            else {
                position = -2;
            }
        }

        return position;

    }
});





/**
 * Change Tile content with circular effect (after fist element will be loaded the first and vice-versa)
 * @Param:
 *	- tile inner container 
 *	- direction: accepted value "next" or "prev"
 */
$.extend({
    changeTileCircular: function (tile, direction) {

        //acrive tile
        var active = $('.tile_inner').find('.act');

        if (direction == 'next') {

            //if active is the last element
            if (active.is(':last-child')) {
                //get first element
                var next = $('.tile_inner').find('.tile_content').first();

            }
            else {
                //get next element
                var next = active.next('.tile_content');

            }

        }

        if (direction == 'prev') {
            //if active is the first element
            if (active.is(':first-child')) {
                //get last element
                var next = $('.tile_inner').find('.tile_content').last();

            }
            else {
                //get prev element
                var next = active.prev('.tile_content');

            }
        }


        //disable current active
        active.removeClass("act").addClass("dis");
        //enable next
        next.removeClass("dis").addClass("act");


    }
});




$.extend({
    changeClick: function (direction) {
        var pos = null;
        $('.tile').each(function (index) {
            pos = $.changeTile($(this), direction);
        })
        return pos;
    }
});


$.extend({
    enableTileAnimation: function () {
        $('.tile_animated').mouseenter(function() {
            var img_act = $(this).find('.tile_icon_act');
            var img_dis = $(this).find('.tile_icon_dis');
            img_act.removeClass('tile_icon_act').addClass('tile_icon_dis');
            img_dis.removeClass('tile_icon_dis').addClass('tile_icon_act');
        });

        $('.tile_animated').mouseleave(function() {
            var img_act = $(this).find('.tile_icon_act');
            var img_dis = $(this).find('.tile_icon_dis');
            img_act.removeClass('tile_icon_act').addClass('tile_icon_dis');
            img_dis.removeClass('tile_icon_dis').addClass('tile_icon_act');
        });	
    }
});

$.extend({
    enableTileButtons: function () {
        $('#button_p').click(function() {
            var pos = $.changeClick('prev');
            //enable button n
            $('#button_n').removeClass('disabled');
            
            //if the tiles are the lasts, disable button p
            if(pos==-2){
                $(this).addClass('disabled');
            }
            
            console.log(pos);
        });
        $('#button_n').click(function() {
            var pos = $.changeClick('next');
            //enable button p
            $('#button_p').removeClass('disabled');
            
            //if the tiles are the lasts, disable button n
            if(pos==2){
                $(this).addClass('disabled');
            }
            
            console.log(pos);
        }); 
        
    }
});           



$.extend({
    enableTop10Slider: function () {

        var owl = $("#top10-slider");

          owl.owlCarousel({
                navigation: false,
                  items : 3, //10 items above 1000px browser width
                  itemsDesktop : [1000,1], //1 items between 1000px and 901px
                  itemsDesktopSmall : [940,1], // betweem 900px and 601px
                  itemsTablet: [600,1], //1 items between 600 and 0
                  itemsMobile : false // itemsMobile disabled - inherit from itemsTablet option
          });

          // Custom Navigation Events
          $("#poi-next").click(function(){
            owl.trigger('owl.next');
          });
          $("#poi-prev").click(function(){
            owl.trigger('owl.prev');
          });


          $("#top10-row").mouseenter(function(){
              $(".top_arrow_box").css("opacity","0.3");
          });

          $("#top10-row").mouseleave(function(){
              $(".top_arrow_box").css("opacity","0");
          });     
        
    }
});       