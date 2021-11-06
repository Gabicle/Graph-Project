$(document).ready(function () {
    $("#newSelect").click(function () {

        $.ajax({
            url: "/getStops",
            type: 'GET',
            dataType : 'json',
            success: function (results){
                let resultblock;
                $("#stopSelect1").empty();
                $.each(results , function( key, value ) {
                    resultblock =  '<option value=' +
                        value.id +
                        ' data-stopId="' +
                        value.stop_id +
                        '">' +
                        value.stop_name +
                        '</option>'

                    $("#stopSelect1").append(resultblock);

                });

            },
            error: function (e) {
                console.log(e);
            }

        })
    })

    $("#newSelect").click(function () {
        $.ajax({
            url: "/getStops",
            type: 'GET',
            dataType : 'json',
            success: function (results){
                let resultblock;
                $("#stopSelect2").empty();
                $.each(results , function( key, value ) {
                    resultblock =  '<option value=' +
                        value.id +
                        ' data-stopId="' +
                        value.stop_id +
                        '">' +
                        value.stop_name +
                        '</option>'

                    $("#stopSelect2").append(resultblock);

                });

            },
            error: function (e) {
                console.log(e);
            }

        })
    })

    $("#newSelect2").click(function () {
        console.log("clicked")
        $.ajax({
            url: "/getStops",
            type: 'GET',
            dataType : 'json',
            success: function (results){
                let resultblock;
                $("#stopSelect3").empty();
                $.each(results , function( key, value ) {
                    resultblock =  '<option value=' +
                        value.id +
                        ' data-stopId="' +
                        value.stop_id +
                        '">' +
                        value.stop_name +
                        '</option>'

                    $("#stopSelect3").append(resultblock);

                });

            },
            error: function (e) {
                console.log(e);
            }

        })
    })


    //GET BFS
    $("#bfsForm").submit(function (e) {
        e.preventDefault();
        let first = $("#stopSelect1 option:selected").val();
        let second = $("#stopSelect2 option:selected").val();
        console.log(first + " "+ second);
        $.ajax({
            url: "/runBFS",
            data: {first: first, second:second},
            success: function (data){
                getSpecificStops(data)
                console.log("success");
            }
        });
    });

    //Get BFS Traversal
    $("#bfsTravForm").submit(function (e) {
        e.preventDefault();
        let first = $("#stopSelect3 option:selected").val();
        console.log(first);
        $.ajax({
            url: "/runbfstrasversal",
            data: {first: first},
            success: function (data){
                console.log(data);
                getSpecificStops(data)
                console.log("success");
            },
            error: function (e) {
                console.log(e);
            }
        });
    });

    //GET Dij
    $("#dijForm").submit(function (e) {
        e.preventDefault();
        let first = $("#stopSelect1 option:selected").val();
        let second = $("#stopSelect2 option:selected").val();
        console.log(first + " "+ second);
        $.ajax({
            url: "/runDij",
            data: {first: first, second:second},
            success: function (data){
                getSpecificStops(data)
                console.log("success");
            }
        });
    });

    //GET Dij
    $("#dijTravForm").submit(function (e) {
        e.preventDefault();
        let first = $("#stopSelect3 option:selected").val();

        console.log(first);
        $.ajax({
            url: "/rundijkstraversal",
            data: {first: first},
            success: function (data){
                getSpecificStops(data)
                console.log("success");
            }
        });
    });

    function getSpecificStops(arr){
        $.ajax({
            url: "/getStops",
            type: 'GET',
            dataType : 'json',
            success: function (results){
                let resultblock;
                $("#stopSelect2").empty();
                for (var i =0; i< arr.length; i++){
                    $.each(results , function( key, value ) {
                        if (arr[i] ==  value.id){
                            resultblock =  '<div>' +
                                value.id + " " +
                                value.stop_id + " " +
                                value.stop_name +
                                '</div>'
                        }


                    });
                    $("#quickOne").append(resultblock);

                }


            },
            error: function (e) {
                console.log(e);
            }

        })
    }

})