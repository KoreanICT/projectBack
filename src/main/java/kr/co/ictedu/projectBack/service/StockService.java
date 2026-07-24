package kr.co.ictedu.projectBack.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.ictedu.projectBack.dao.StockDao;
import kr.co.ictedu.projectBack.vo.StockImagesVO;
import kr.co.ictedu.projectBack.vo.StockVO;


@Service
public class StockService {


    @Autowired
    private StockDao stockDao;


     @Transactional
    public void insertStock(
            StockVO svo,
            List<StockImagesVO> imageList
    ) {


        // JDK String 기본 기능 사용
      
        validateStock(svo);


        svo.setIsbn(
                svo.getIsbn().trim()
        );

        svo.setSname(
                svo.getSname().trim()
        );

        svo.setSpublisher(
                svo.getSpublisher().trim()
        );

        svo.setAuthor(
                svo.getAuthor().trim()
        );

        svo.setScategory(
                svo.getScategory().trim()
        );


    
        svo.setSamount(
                Math.max(
                        svo.getSamount(),
                        0
                )
        );

        svo.setSprice(
                Math.max(
                        svo.getSprice(),
                        0
                )
        );


        
        stockDao.insertStock(
                svo
        );


        
      
        List<StockImagesVO> saveImages =
                new ArrayList<>();


        if (
            imageList != null &&
            !imageList.isEmpty()
        ) {


            for (
                StockImagesVO image
                : imageList
            ) {


                if (
                    image.getStockimage() == null ||
                    image.getStockimage()
                         .trim()
                         .isEmpty()
                ) {

                    continue;

                }


                StockImagesVO imageVO =
                        new StockImagesVO();


                imageVO.setStockimage(
                        image.getStockimage().trim()
                );


                // 부모 STOCK의 PK를
                // STOCKIMAGE FK에 넣는다.
                imageVO.setStocksnum(
                        svo.getSnum()
                );


                saveImages.add(
                        imageVO
                );

            }

        }


        if (!saveImages.isEmpty()) {

            stockDao.insertImages(
                    saveImages
            );

        }


        svo.setGetimglist(
                saveImages
        );

    }


    
    public List<StockVO> stockList(
            Map<String, String> map
    ) {


        List<StockVO> list =
                stockDao.stockList(
                        map
                );


        if (list == null) {

            return new ArrayList<>();

        }


        return list;

    }


    public int totalCount(
            Map<String, String> map
    ) {

        return stockDao.totalCount(
                map
        );

    }


    public StockVO stockDetail(
            int snum
    ) {


        if (snum <= 0) {

            throw new IllegalArgumentException(
                    "잘못된 재고번호입니다."
            );

        }


        StockVO svo =
                stockDao.stockDetail(
                        snum
                );


        if (svo == null) {

            throw new IllegalArgumentException(
                    "도서 정보가 없습니다."
            );

        }


        return svo;

    }


    
    @Transactional
    public void updateStock(
            StockVO svo,
            List<StockImagesVO> imageList
    ) {


        validateStock(
                svo
        );


        if (
            svo.getSnum() == null ||
            svo.getSnum() <= 0
        ) {

            throw new IllegalArgumentException(
                    "재고번호가 없습니다."
            );

        }


        
        stockDao.updateStock(
                svo
        );


  
        // 새 이미지가 들어온 경우만 교체
       
        if (
            imageList != null &&
            !imageList.isEmpty()
        ) {


            // 기존 이미지 삭제
            stockDao.deleteStockImages(
                    svo.getSnum()
            );


            List<StockImagesVO> saveImages =
                    new ArrayList<>();


            for (
                StockImagesVO image
                : imageList
            ) {


                if (
                    image.getStockimage() == null ||
                    image.getStockimage()
                         .trim()
                         .isEmpty()
                ) {

                    continue;

                }


                StockImagesVO imageVO =
                        new StockImagesVO();


                imageVO.setStockimage(
                        image.getStockimage().trim()
                );


                imageVO.setStocksnum(
                        svo.getSnum()
                );


                saveImages.add(
                        imageVO
                );

            }


            if (!saveImages.isEmpty()) {

                stockDao.insertImages(
                        saveImages
                );

            }

        }

    }


    
    @Transactional
    public void deleteStock(
            int snum
    ) {


        if (snum <= 0) {

            throw new IllegalArgumentException(
                    "잘못된 재고번호입니다."
            );

        }


        stockDao.deleteStock(
                snum
        );

    }


    
    private void validateStock(
            StockVO svo
    ) {


        if (svo == null) {

            throw new IllegalArgumentException(
                    "도서정보가 없습니다."
            );

        }


        // ISBN 숫자 13자리
        if (
            svo.getIsbn() == null ||
            !svo.getIsbn()
                 .trim()
                 .matches("[0-9]{13}")
        ) {

            throw new IllegalArgumentException(
                    "ISBN은 숫자 13자리여야 합니다."
            );

        }


        // 책 제목
        if (
            svo.getSname() == null ||
            svo.getSname()
                 .trim()
                 .isEmpty()
        ) {

            throw new IllegalArgumentException(
                    "책 제목은 필수입니다."
            );

        }


        // 출판사
        if (
            svo.getSpublisher() == null ||
            svo.getSpublisher()
                 .trim()
                 .isEmpty()
        ) {

            throw new IllegalArgumentException(
                    "출판사는 필수입니다."
            );

        }


        // 저자
        if (
            svo.getAuthor() == null ||
            svo.getAuthor()
                 .trim()
                 .isEmpty()
        ) {

            throw new IllegalArgumentException(
                    "저자는 필수입니다."
            );

        }


        // 카테고리
        if (
            svo.getScategory() == null ||
            svo.getScategory()
                 .trim()
                 .isEmpty()
        ) {

            throw new IllegalArgumentException(
                    "카테고리는 필수입니다."
            );

        }


        if (svo.getSamount() < 0) {

            throw new IllegalArgumentException(
                    "재고수량은 0 이상이어야 합니다."
            );

        }


        if (svo.getSprice() < 0) {

            throw new IllegalArgumentException(
                    "가격은 0 이상이어야 합니다."
            );

        }

    }


  
    public int plusAmount(
            int amount
    ) {

        return amount + 1;

    }


    // 재고 -1
    public int minusAmount(
            int amount
    ) {

        return Math.max(
                amount - 1,
                0
        );

    }


    // 가격 +1000
    public int plusPrice(
            int price
    ) {

        return price + 1000;

    }


    // 가격 -1000
    public int minusPrice(
            int price
    ) {

        return Math.max(
                price - 1000,
                0
        );

    }


    // 재고 총 금액
    public long totalStockPrice(
            int amount,
            int price
    ) {

        return (long) amount * price;

    }

}