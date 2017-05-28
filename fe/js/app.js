(function (window) {
	'use strict';
    var list = $(".todo-list");

	// Your starting point. Enjoy the ride!

    //create new todo
    $(".new-todo").on( "keydown", function( event ) {
        var todo = $(this).val();
        if(event.which == 13 && todo != ""){
            $.ajax({
                url: "/api/todos",
                type: 'POST',
                data: {"Todo" : todo},
                success: function(responce){
                    console.log("please");
                }

            })
        }
    });

})(window);

