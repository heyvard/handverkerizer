//$('#hjem a[href=#hjem]')

$(document).ready(function () {
    var $wrapper = $('body');

    var wrapperTmpl = Handlebars.getTemplate('wrapper');
    $wrapper.empty().html(wrapperTmpl());


    $wrapper.on("pagechange", function (event, info) {
        var side = info.toPage[0].id;

        $('#' + side + ' a[href=#' + side + ']').addClass('ui-btn-active ui-state-persist');
    });
    window.location.hash = "hjem";

    $wrapper.trigger('create');

    initialiserPriss();
});