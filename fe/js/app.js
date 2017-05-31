(function (window) {
	'use strict';
    var todolist = $(".todo-list");

	// Your starting point. Enjoy the ride!
    //road todolist
    $.ajax({
                url: "/api/todos",
                type: 'GET',
                success: function(responce){
                    $.each(responce, function(index, value) {
                        todolist.prepend(
                        '<li>'+
                        '<div class="view">' +
                        '<input class="toggle" type="checkbox">'+
                        '<label>'+value.todo+'</label>'+
                        '<button class="destroy"></button>'+
                        '</div>'+
                        '<input class="edit" value="...">'+
                        '</li>'
                        );
                    });
            }
    })

    //create new todo
    $(".new-todo").on( "keydown", function( event ) {
        var todo = $(this).val();
        if(event.which == 13 && todo != ""){
            $.ajax({
                url: "/api/todos",
                type: 'POST',
                data: todo,
                success: function(responce){
                    console.log(responce.todo);
                    todolist.prepend(
                        '<li>'+
                        '<div class="view">' +
                        '<input class="toggle" type="checkbox">'+
                        '<label>'+responce.todo+'</label>'+
                        '<button class="destroy"></button>'+
                        '</div>'+
                        '<input class="edit" value="...">'+
                        '</li>'
                        );
                    $(".new-todo").val('');
                }

            })
        }
    })

    //completed todo
    $(document).on('click','.toggle',(function(){
            if ($(this).prop("checked")) {
                $(this).parent().parent().addClass("completed");
            }else{
                $(this).parent().parent().removeClass("completed");
            }
        })
    )


})(window);


