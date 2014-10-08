



function initialiserPriss() {
    $('#genererPriss').click(function () {
            $.get("api/priss", function (data) {
                $("#prissTekst").html(data);
            });

        }
    );
}
