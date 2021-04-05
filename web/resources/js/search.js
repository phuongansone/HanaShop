// jQuery selector
var SELECT_CATEGORY = '#category';
var BTN_PAGE = '#btnPage';
var BTN_SEARCH = '#btnSearch';
var PAGE_LINK_CLASS = ".page-link";

/**
 * Search for items by condition (if specified)
 * @param {Object} condition
 */
function searchItems(condition) {
    var settings = {
        method : 'GET',
        url: 'MainServlet',
        data : {
            action : 'getSearchResult'
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

// Click button search to search by categoryId
$(BTN_SEARCH).on('click', function() {
    if ($(SELECT_CATEGORY).val() === '0') {
        searchItems();
        return;
    }
    
    searchItems({categoryId: $(SELECT_CATEGORY).val()});
});

$(document).ready(function() {
    searchItems();
});

