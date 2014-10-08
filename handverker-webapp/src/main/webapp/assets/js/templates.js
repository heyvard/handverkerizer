Handlebars.getTemplate = function(name) {
    if (Handlebars.templates === undefined || Handlebars.templates[name] === undefined) {
        $.ajax({
            url : 'assets/templates/' + name + '.hbs',
            success : function(data) {
                if (Handlebars.templates === undefined) {
                    Handlebars.templates = {};
                }
                Handlebars.templates[name] = Handlebars.compile(data);
            },
            async : false
        });
    }
    return Handlebars.templates[name];
};

function registrerPartial(navn) {
    var partial = Handlebars.getTemplate(navn);
    Handlebars.registerPartial(navn, partial)
}


registrerPartial('footer');
registrerPartial('header');
registrerPartial('hjem');
registrerPartial('visAlle');
registrerPartial('priss');
registrerPartial('leggTil');