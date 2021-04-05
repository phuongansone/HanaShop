<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
        
        <style>
            .food-items {
                display: flex;
                justify-content: space-around;
                flex-wrap: wrap;
            }
            
            .food-item {
                margin: 1rem;
            }
        </style>
    </head>
    <body>
        <div class="container food-items">
            <c:forEach items="${requestScope.FOODS}" var="food">
                <div class="card food-item" style="width: 18rem;">
                  <img class="card-img-top" src="resources/images/${food.foodImage}" alt="Food image">
                  <div class="card-body">
                    <h5 class="card-title">${food.foodName}</h5>
                    <p class="card-text">${food.description}</p>
                  </div>
                  <ul class="list-group list-group-flush">
                      <li class="list-group-item"><b>${food.foodPrice}</b></li>
                  </ul>
                </div> 
            </c:forEach>
        </div>
        <nav aria-label="Page navigation">
              <ul class="pagination">
                <c:forEach items="${requestScope.PAGES}" var="pageNo">
                    <li class="page-item ${pageNo == requestScope.PAGE ? 'active' : ''}">
                        <button class="page-link" 
                                id="btnPage" value="${pageNo}" name="page">
                            ${pageNo}
                        </button>
                    </li>
                </c:forEach>
              </ul>
        </nav>
    </body>
    <script>
        // jQuery selector
        var PAGE_LINK_CLASS = ".page-link";
        
        // Click page button for pagination
        $(PAGE_LINK_CLASS).on("click", function(event) {
            var page = event.target.value;
            var condition = {page: page};

            if ($(SELECT_CATEGORY).val() !== null
                    && $(SELECT_CATEGORY).val() !== undefined
                    && $(SELECT_CATEGORY).val() !== '0') {
                condition.categoryId = $(SELECT_CATEGORY).val();
            }

            searchItems(condition);
        });
    </script>
</html>
