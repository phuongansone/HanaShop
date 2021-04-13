/* global UTILS */

var PAGE_LINK_CLASS = '.page-link';
var FOOD_IMAGE_INPUT_CLASS = '.foodImgInput';
var FOOD_FORM_CLASS = '.food-form';

// Bind event to img input class
$(FOOD_IMAGE_INPUT_CLASS).on('change', UTILS.onChangeFoodImg);

// Click page button for pagination
$(PAGE_LINK_CLASS).on('click', function(event) {
   var page = event.target.value;
   var condition = {page: page};

   condition = UTILS.getCondition(condition);

   getFoodList(condition);
});

