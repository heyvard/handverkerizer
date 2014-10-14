



function initialiserPriss() {
    $('#genererPriss').click(function () {
            $.blockUI();
            $.get("api/priss", function (data) {
                $("#prissTekst").html(data);
                $.unblockUI();
            });
        }
    );
}
