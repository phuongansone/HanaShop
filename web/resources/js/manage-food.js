// JQuery select
var FOOD_FORM_CLASS = '.food-form';
var PAGE_LINK_CLASS = '.page-link';
var FOOD_LIST_DIV = '#foodList';

function getFoodList(condition) {
    var settings = {
        method: 'GET',
        url: 'MainServlet',
        data: condition,
        dataType: 'html',
        success: function(html) {
            $(FOOD_LIST_DIV).html(html);
        }
    };
    
    settings.data.action = 'manageFoodList';
    
    $.ajax(settings);
}

$(document).ready(function() {
    getFoodList({});
});