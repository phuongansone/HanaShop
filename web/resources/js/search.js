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
        }
    };
    
    if (condition !== undefined) {
        settings.data.categoryId = condition.categoryId;
    }
    
    $.ajax(settings);
}

$(document).ready(function() {
    searchItems();
});

