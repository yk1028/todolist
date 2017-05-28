(function (window) {
	'use strict';
    var list = $(".todo-list");

	// Your starting point. Enjoy the ride!

    //create new todo
    $(".new-todo").on( "keydown", function( event ) {
        var context = $(this).val();
        if(event.which == 13 && context != ""){
            $.ajax({
                url: "/api/todos",
                type: 'GET',
                data: {"context" : context},
                success: function(responce){

                }

            })
        }
    });

})(window);

