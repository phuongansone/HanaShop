/* global URL */

// jQuery selector
var SELECT_CATEGORY = '#category';
var TXB_KEYWORD = '#keyword';
var CHECKED_RADIO_RANGE = 'input[id^="priceRange_"]:checked';
var RADIO_RANGE = 'input[id^="priceRange_"]';
var BTN_PAGE = '#btnPage';
var BTN_SEARCH = '#btnSearch';
var PAGE_LINK_CLASS = '.page-link';
var FOOD_IMG_CLASS = '.food-img';
var IMG_TAG = 'img';

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
        } else if (UTILS.strIsNotNullOrUndefinedOrBlank($(CHECKED_RADIO_RANGE).val())) {
            condition.rangeId = $(CHECKED_RADIO_RANGE).val();
        }
      
      return condition;
    },
    onChangeFoodImg: function(event) {
        var $imgInput = $(event.target);
        var $imgTag = $imgInput.parents(FOOD_IMG_CLASS).find(IMG_TAG);
        $imgTag.attr('src', URL.createObjectURL(event.target.files[0]));
    }
};