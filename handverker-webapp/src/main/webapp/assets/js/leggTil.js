function initialiserLeggTil() {
    $('a#leggTilKnapp').click(function () {

            var type = $('select#velgType').find(':selected').val();
            var ord = $('input#leggTilInput').val();


            if (ord.length < 3) {
                return;
            }
            $.blockUI();

            $.ajax('api/handverker/' + type, {
                data: JSON.stringify(ord),
                contentType: 'application/json',
                type: 'POST',
                success: function () {
                    $('input#leggTilInput').val('');
                    $.unblockUI();
                },
                error: function () {

                    $('input#leggTilInput').val('Auda, det gikk feil');
                    $.unblockUI();
                }
            });

        }
    );
}
