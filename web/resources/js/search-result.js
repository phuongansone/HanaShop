var PAGE_LINK_CLASS = '.page-link';
// Click page button for pagination
$(PAGE_LINK_CLASS).on("click", function(event) {
    var page = event.target.value;
    var condition = {page: page};

    condition = UTILS.getCondition(condition);

    searchItems(condition);
});