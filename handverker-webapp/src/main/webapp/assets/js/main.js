//$('#hjem a[href=#hjem]')


function styleNavKnapp(side) {
    $('#' + side + ' a[href=#' + side + ']').addClass('ui-btn-active ui-state-persist');

}


$(document).ready(function () {
    var $wrapper = $('body');

    var wrapperTmpl = Handlebars.getTemplate('wrapper');
    $wrapper.empty().html(wrapperTmpl());


    $('div[data-role="navbar"] a').click(function (event) {

        var side = event.currentTarget.href.split('#')[1];
        styleNavKnapp(side)

    });

    window.location.hash = "hjem";
    styleNavKnapp('hjem')
    $wrapper.trigger('create');
    initialiserLeggTil();
    initialiserPriss();
    initialiserHovedside();

});