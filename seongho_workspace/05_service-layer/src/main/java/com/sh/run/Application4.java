package com.sh.run;

import com.sh.model.dto.CategoryDTO;
import com.sh.model.dto.MenuDTO;
import com.sh.model.service.MenuService;

public class Application4 {
    public static void main(String[] args) {
        //신규 카테고리와 메뉴 동시에 추가하기( 둘다 잘 돼야 성공)
        String categoryName = "기타";
        Integer refCategoryCode = null;

        String menuName = "개구리뒷다리살";
        int menuPrice = 7000;
        int categoryCode= 4;
        String orderableStatus = "Y";


        CategoryDTO category = new CategoryDTO();
        category.setCategoryName(categoryName);
        category.setRefCategoryCode(refCategoryCode);

        MenuDTO menu = new MenuDTO();
        menu.setMenuName(menuName);
        menu.setMenuPrice(menuPrice);
        menu.setCategoryCode(categoryCode);
        menu.setOrderableStatus(orderableStatus);

        int result = new MenuService().registCategortAndMenu(category,menu);

    }
}
