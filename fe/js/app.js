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
                    '<li id="'+ value.id +'">'+
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
                        '<li id="'+ responce.id +'">'+
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
                console.log($(this).parent().parent().attr("id"));
                var _url = "api/todos/" + $(this).parent().parent().attr("id");
                var completed = "1";
                console.log(_url);
                $.ajax({
                    url: _url ,
                    type: 'PUT',
                    data: completed,
                    success: function(){
                        console.log("성공 c1");
                    }
                })
            }else{
                $(this).parent().parent().removeClass("completed");
                var _url = "api/todos/" + $(this).parent().parent().attr("id");
                $.ajax({
                    url: _url,
                    type: 'PUT',
                    data: "0",
                    success: function(){
                        console.log("성공 c0");
                    }
                })
            }
        })
    )
    $(document).on('click','.destroy',(function(){
            $(this).parent().parent().remove();
        })
    )


})(window);


