// jQuery selector
var SELECT_CATEGORY = '#category';
var TXB_KEYWORD = '#keyword';
var RADIO_RANGE = 'input[id^="priceRange_"]:checked';
var BTN_PAGE = '#btnPage';
var BTN_SEARCH = '#btnSearch';
var PAGE_LINK_CLASS = ".page-link";

// utilities
var UTILS = {
    strIsNotNullOrUndefinedOrBlank: function(str) {
        return str !== undefined && str !== null && str.trim().length > 0;
    },
    getCondition: function(condition) {
        if (condition === undefined || condition === null) {
            condition = {};
        }
      
        if (UTILS.strIsNotNullOrUndefinedOrBlank($(TXB_KEYWORD).val())) {
            condition.keyword = $(TXB_KEYWORD).val();
        } else if (UTILS.strIsNotNullOrUndefinedOrBlank($(SELECT_CATEGORY).val())
                && $(SELECT_CATEGORY).val() !== '0') {
            condition.categoryId = $(SELECT_CATEGORY).val();
        } else if (UTILS.strIsNotNullOrUndefinedOrBlank($(RADIO_RANGE).val())) {
            condition.rangeId = $(RADIO_RANGE).val();
        }
      
      return condition;
    }
};

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

// Click button search to search by categoryId
$(BTN_SEARCH).on('click', function() {
    searchItems(UTILS.getCondition());
});

$(document).ready(function() {
    searchItems({});
});

