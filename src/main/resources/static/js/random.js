$(function () {

    var getData = function() {
        $.ajax({
            type: "GET",
            url: "/api/getSearchResult",
            data: {},
            success: function (data) {
                $(".striped").slideUp(1000, function () {
                });

                console.log('recieved data = ' + data)
                $("#savedItems").text(data);
            }
        });
    }

    var updateInfo = function() {
        $.ajax({
            type: "GET",
            url: "/api/getSearchResult",
            data: {},
            success: function (data) {
                console.log('recieved data = ' + data)
                $("#savedItems").text(data);
            }
        });
    }

    function saveBier() {
        var $form = $('#save');
        console.log($form);
        $.ajax({
            type: "POST",
            url: "/save",
            data:
                $form.serialize(),
            success: function () {
                getData();
            }
        });
    }

    jQuery(function ($) {
        $(document).ready(function ($) {
            updateInfo();
            $("#save").submit(function (event) {
                event.preventDefault();
                saveBier();
            });
        });
    });

});