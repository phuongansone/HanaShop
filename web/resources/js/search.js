// jQuery selector
var SELECT_CATEGORY = '#category';
var TXB_KEYWORD = '#keyword';
var CHECKED_RADIO_RANGE = 'input[id^="priceRange_"]:checked';
var RADIO_RANGE = 'input[id^="priceRange_"]';
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
        data : condition,
        dataType : 'html',
        success : function(html) {
            $('#searchResult').html(html);
            window.scrollTo(0, 0);
        }
    };
    
    settings.data.action = 'getSearchResult';
    
    $.ajax(settings);
}

// reset others filter when one filter is selected
$(TXB_KEYWORD).on('click', function() {
    $(SELECT_CATEGORY).val('0');
    $(CHECKED_RADIO_RANGE).prop('checked', false);
});

$(SELECT_CATEGORY).on('click', function() {
    $(TXB_KEYWORD).val('');
    $(CHECKED_RADIO_RANGE).prop('checked', false);
});

$(RADIO_RANGE).on('click', function() {
    $(TXB_KEYWORD).val('');
    $(SELECT_CATEGORY).val('0');
})

// Click button search to search by categoryId
$(BTN_SEARCH).on('click', function() {
    searchItems(UTILS.getCondition());
});

$(document).ready(function() {
    searchItems({});
});

