package com.sh.run;

import com.sh.model.dao.MenuDAO;
import com.sh.model.dto.CategoryDTO;
import com.sh.model.dto.MenuDTO;

import java.awt.*;
import java.sql.Connection;

import static com.sh.common.JDBCTemplate.getConnection;

public class Application3 {
    public static void main(String[] args) {
        //신규 카테고리와 메뉴 동시에 추가하기( 둘다 잘 돼야 성공)
        String categoryName = "기타";
        Integer refCategoryCode = null;

        String menuName = "개구리뒷다리살";
        int menuPrice = 7000;
        int categoryCode= 0;
        String orderableStatus = "Y";

        CategoryDTO category = new CategoryDTO();
        category.setCategoryName(categoryName);
        category.setRefCategoryCode(refCategoryCode);

        MenuDTO menu = new MenuDTO();
        menu.setMenuName(menuName);
        menu.setMenuPrice(menuPrice);
        menu.setCategoryCode(categoryCode);
        menu.setOrderableStatus(orderableStatus);

        MenuDAO dao = new MenuDAO();
        Connection conn = getConnection();

        int result1= dao.insertCategory(conn,category);

        int result2= dao.inserMenu(conn,menu);

        if (result1 > 0 && result2>0){
            commit(conn);
        }else {
            rollback(conn);
        }

    }
}
