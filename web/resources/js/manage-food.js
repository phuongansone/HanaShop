// JQuery select
var FOOD_FORM_CLASS = '.food-form';
var PAGE_LINK_CLASS = '.page-link';
var FOOD_LIST_DIV = '#foodList';

function bindingEvent() {
    // Bind event to img input class
    $(FOOD_IMAGE_INPUT_CLASS).on('change', UTILS.onChangeFoodImg);
}

function getFoodList(condition) {
    var settings = {
        method: 'GET',
        url: 'MainServlet',
        data: condition,
        dataType: 'html',
        success: function(html) {
            $(FOOD_LIST_DIV).html(html);
            bindingEvent();
            
            window.scroll(0, 0);
        }
    };
    
    settings.data.action = 'manageFoodList';
    
    $.ajax(settings);
}

$(document).ready(function() {
    getFoodList({});
});