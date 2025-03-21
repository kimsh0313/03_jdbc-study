package com.sh.run;

import com.sh.model.dao.MenuDAO;
import com.sh.model.dto.CategoryDTO;
import com.sh.model.dto.MenuDTO;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

import static com.sh.common.JDBCTemplate.getConnection;

public class Application2 {
    public static void main(String[] args) {
        /*
        메뉴 등록
        마지막 메뉴 번호 조회
        카테고리 전체 정보 조회
        메뉴 등록
         */
        Connection conn = getConnection();

        MenuDAO dao = new MenuDAO();
        //마지막 메뉴 번호 조회
        int lastMenuCode = dao.selectLastMenuCode(conn);

        //카테고리 전체 정보 조회
        List<CategoryDTO> list = dao.selectCategoryList(conn);
        for (CategoryDTO cate : list){
            System.out.println(cate);
        }

        MenuDTO menu = new MenuDTO();
        menu.setMenuCode(lastMenuCode + 1);

        Scanner sc = new Scanner(System.in);
        System.out.println("등록 메뉴 : " );
        menu.setMenuName(sc.nextLine());
        System.out.println("가격 : ");
        menu.setMenuPrice(sc.nextInt());
        System.out.println("등록할 메뉴 카테고리 번호 : ");
        menu.setCategoryCode(sc.nextInt());
        sc.nextLine();
        System.out.println("주문 여부 y/n: ");
        menu.setOrderableStatus(sc.nextLine());
        //3 메뉴등록
        int result = dao.insertMenu(conn, menu   );

        if (result >0){
            System.out.println("O");
        }else {
            System.out.println("X");
        }
    }
}
