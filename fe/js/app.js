(function(window) {
    'use strict';
    var todolist = $(".todo-list");

    // Your starting point. Enjoy the ride!

    // count non-completed todos
    var setCountNotComplted = function() {
        $.ajax({
            url: "api/todos/count",
            type: 'GET',
            success: function(responce) {
                console.log("성공 count");
            }
        })
    }

    //road todolist
    $.ajax({
        url: "/api/todos",
        type: 'GET',
        success: function(responce) {
            $.each(responce, function(index, value) {
                var tags;
                if (value.completed == 1) {
                    tags = '<li class="completed"' + value.id + '">' +
                        '<div class="view">' +
                        '<input class="toggle" type="checkbox" checked>';
                } else {
                    tags = '<li id="' + value.id + '">' +
                        '<div class="view">' +
                        '<input class="toggle" type="checkbox">';
                }
                todolist.prepend(
                    tags +
                    '<label>' + value.todo + '</label>' +
                    '<button class="destroy"></button>' +
                    '</div>' +
                    '<input class="edit" value="...">' +
                    '</li>'
                );
            });
        }
    })
    setCountNotComplted();


    //create new todo
    $(".new-todo").on("keydown", function(event) {
        var todo = $(this).val();
        if (event.which == 13 && todo != "") {
            var jtodo = { todo: todo }
            $.ajax({
                url: "/api/todos",
                type: 'POST',
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(jtodo),
                dataType: "json",
                success: function(responce) {
                    console.log(responce.todo);
                    todolist.prepend(
                        '<li id="' + responce.id + '">' +
                        '<div class="view">' +
                        '<input class="toggle" type="checkbox">' +
                        '<label>' + responce.todo + '</label>' +
                        '<button class="destroy"></button>' +
                        '</div>' +
                        '<input class="edit" value="...">' +
                        '</li>'
                    );
                    $(".new-todo").val('');
                    setCountNotComplted();
                }

            })
        }
    })

    //completed todo
    $(document).on('click', '.toggle', (function() {
        if ($(this).prop("checked")) {
            $(this).parent().parent().addClass("completed");
            console.log($(this).parent().parent().attr("id"));
            var _url = "api/todos/" + $(this).parent().parent().attr("id");
            var comp = { completed: 1 };
            console.log(_url);
            $.ajax({
                url: _url,
                type: 'PUT',
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(comp),
                dataType: "json",
                success: function() {
                    console.log("성공 c1");
                    setCountNotComplted();
                }
            })
        } else {
            $(this).parent().parent().removeClass("completed");
            var _url = "api/todos/" + $(this).parent().parent().attr("id");
            var comp = { completed: 0 };
            $.ajax({
                url: _url,
                type: 'PUT',
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(comp),
                dataType: "json",
                success: function() {
                    console.log("성공 c0");
                    setCountNotComplted();
                }
            })
        }
    }))

    //destroy todo
    $(document).on('click', '.destroy', (function() {
        $(this).parent().parent().remove();
        var _url = "api/todos/" + $(this).parent().parent().attr("id");
        //ajax로 db에서 삭제
        $.ajax({
            url: _url,
            type: 'DELETE',
            success: function() {
                console.log("성공 d");
                setCountNotComplted();
            }
        })
    }))

    //filtering
    $(".filters").children().children().click(function() {
        if (!$(this).hasClass("selected")) {
            $(".filters").children().children(".selected").removeClass("selected");
            $(this).addClass("selected");
            var thisdom = $(this);
            $.ajax({
            url: "/api/todos",
            type: 'GET',
            success: function(responce) {
                todolist.children().remove();
                $.each(responce, function(index, value) {
                    if (thisdom.text() == "All") {
                        console.log("all");
                        var tags;
                        if (value.completed == 1) {
                            tags = '<li class="completed"' + value.id + '">' +
                                '<div class="view">' +
                                '<input class="toggle" type="checkbox" checked>';
                        } else {
                            tags = '<li id="' + value.id + '">' +
                                '<div class="view">' +
                                '<input class="toggle" type="checkbox">';
                        }
                        todolist.prepend(
                            tags +
                            '<label>' + value.todo + '</label>' +
                            '<button class="destroy"></button>' +
                            '</div>' +
                            '<input class="edit" value="...">' +
                            '</li>'
                        );
                    }
                    else if (thisdom.text() == "Active") {
                        console.log("active");
                        if (value.completed == 0) {
                            var tags = '<li id="' + value.id + '">' +
                                '<div class="view">' +
                                '<input class="toggle" type="checkbox">';
                            todolist.prepend(
                                tags +
                                '<label>' + value.todo + '</label>' +
                                '<button class="destroy"></button>' +
                                '</div>' +
                                '<input class="edit" value="...">' +
                                '</li>'
                            );
                        }
                    } else if (thisdom.text() == "Completed") {
                        console.log("completed");
                        if (value.completed == 1) {
                            var tags = '<li class="completed"' + value.id + '">' +
                                '<div class="view">' +
                                '<input class="toggle" type="checkbox" checked>';
                            todolist.prepend(
                                tags +
                                '<label>' + value.todo + '</label>' +
                                '<button class="destroy"></button>' +
                                '</div>' +
                                '<input class="edit" value="...">' +
                                '</li>'
                            );
                        }
                    }
                });
            }
        })

        }
    })


})(window);
