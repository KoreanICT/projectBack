package kr.co.ictedu.projectBack.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

/**
 * 도서 재고 정보를 저장하는 VO
 * 
 * STOCK 테이블과 연결
 * STOCK : STOCKIMAGE = 1 : N 관계
 */
@Alias("svo")
@Getter
@Setter
public class StockVO {

    // 재고 고유번호 - STOCK PK
    // 등록 전에는 값이 없을 수 있으므로 Integer 사용
    private Integer snum;

    // ISBN
    private String isbn;

    // 도서명
    private String sname;

    // 출판사
    private String spublisher;

    // 저자
    private String author;

    // 재고수량
    private int samount;

    // 판매가격
    private int sprice;

    // 등록일
    private String sdate;

    // 카테고리
    private String scategory;

    // 등록 회원번호 - NULL 허용
    private Integer membernum;


    /*
     * STOCK 테이블의 실제 컬럼은 아님.
     *
     * List.tsx / Detail.tsx에서
     * 대표 이미지 한 장을 편하게 사용하기 위한 값
     */
    private String stockimage;


    /*
     * STOCK 1 : N STOCKIMAGE
     *
     * 하나의 책에 여러 이미지가 등록될 수 있으므로
     * JDK Collection인 List를 사용한다.
     *
     * ArrayList로 초기화하여
     * null 상태를 최대한 방지.
     */
    private List<StockImagesVO> getimglist
            = new ArrayList<>();


    /*
     *
     * JDK의 기본 숫자 연산 사용
     */
    public long getTotalStockPrice() {

        return (long) samount * sprice;
    }


    /*
     * 재고가 존재하는지 확인
     */
    public boolean hasStock() {

        return samount > 0;
    }


    /*
     * 도서명이 비어있는지 확인
     *
     * java.lang.String 사용
     * String은 java.lang이므로 import 필요 없음
     */
    public boolean hasValidName() {

        return sname != null
                && !sname.trim().isEmpty();
    }
}