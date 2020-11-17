$(function () {

    var getData = function () {
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

    var updateInfo = function () {
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
        var $form = $('.to-be-saved');
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
            $('.save').submit(function (event) {
                $(this).closest('form').removeClass('save');
                $(this).closest('form').addClass('to-be-saved');
                event.preventDefault();
                saveBier();
                $(this).closest('tr').remove();
            });
        });
    });

});