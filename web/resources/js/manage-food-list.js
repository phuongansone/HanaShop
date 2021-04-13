/* global UTILS */

var PAGE_LINK_CLASS = '.page-link';
var FOOD_IMAGE_INPUT_CLASS = '.foodImgInput';
var FOOD_FORM_CLASS = '.food-form';
var DELETE_FOOD_FORM = '.delete-form';
var DELETE_FOOD_BTN = '.btn-delete';

var CONFIRM_DEL_MSG = 'Sản phẩm sẽ bị xóa. Bạn có muốn tiếp tục không?';

// Bind event to img input class
$(FOOD_IMAGE_INPUT_CLASS).on('change', UTILS.onChangeFoodImg);

//$(DELETE_FOOD_BTN).on('click', function(event) {
//    event.preventDefault();
//    if (!confirm(CONFIRM_DEL_MSG)) {
//        return;
//    }
//    $(this).click();
//});

// Click page button for pagination
$(PAGE_LINK_CLASS).on('click', function(event) {
   var page = event.target.value;
   var condition = {page: page};

   condition = UTILS.getCondition(condition);

   getFoodList(condition);
});

