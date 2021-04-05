/**
 * Search for items by condition (if specified)
 * @param {Object} condition
 */
function searchItems(condition) {
    var settings = {
        method : 'GET',
        url: 'MainServlet',
        data : {
            action : "getSearchResult"
        },
        dataType : 'html',
        success : function(html) {
            $('#searchResult').html(html);
            window.scrollTo(0, 0);
        }
    };
    
    if (condition !== undefined && condition !== null) {
        if (condition.categoryId !== undefined && condition.categoryId !== null) {
            settings.data.categoryId = condition.categoryId;
        }
        if (condition.page !== undefined && condition.page !== null) {
            settings.data.page = condition.page;
        }
    }
    
    $.ajax(settings);
}

$(document).ready(function() {
    searchItems();
});

