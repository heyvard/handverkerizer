var handverkerliste;

function randomFraArray(array) {
    if (array.length === 0) {
        return 'N/A';
    }
    return array[Math.floor(Math.random() * array.length)]
}

function oppdaterHandverker() {
    var fornavn = randomFraArray(handverkerliste.fornavn);
    var etternavn = randomFraArray(handverkerliste.etternavn);
    var alder = randomIntFromInterval(17, 67);
    var sted = randomFraArray(handverkerliste.steder);
    var yrke = randomFraArray(handverkerliste.yrke);


    $("#fornavn").html(fornavn);
    $("#etternavn").html(etternavn);
    $("#alder").html(alder);
    $("#yrke").html(yrke);
    $("#sted").html(sted);


}

function hentHandverkere() {

    $.get("api/handverker", function (data) {
        handverkerliste = data;
        oppdaterHandverker();
    });
}

function initialiserHovedside() {
    hentHandverkere();


    $('#oppdaterHandverkerKnapp').click(function () {
        oppdaterHandverker();
    });
}

function randomIntFromInterval(min, max) {
    return Math.floor(Math.random() * (max - min + 1) + min);
}