package kr.co.ictedu.projectBack.vo;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;


@Alias("sivo")
@Getter
@Setter
public class StockImagesVO {

    // 저장된 이미지 파일명
    private String stockimage;

    // STOCK.snum을 참조하는 FK
    private Integer stocksnum;


    /*
     * 이미지 파일명이 정상적으로 존재하는지 확인
     *
     * JDK String 기능 사용
     */
    public boolean hasImage() {

        return stockimage != null
                && !stockimage.trim().isEmpty();
    }
}