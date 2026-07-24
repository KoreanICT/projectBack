package kr.co.ictedu.projectBack.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.co.ictedu.projectBack.vo.StockImagesVO;
import kr.co.ictedu.projectBack.vo.StockVO;

@Mapper
public interface StockDao {

    void insertStock(StockVO svo);

    void insertImages(List<StockImagesVO> imageList);

    List<StockVO> stockList(Map<String, String> map);

    int totalCount(Map<String, String> map);

    StockVO stockDetail(@Param("snum") int snum);

    void updateStock(StockVO svo);

    void deleteStockImages(@Param("snum") int snum);

    void deleteStock(@Param("snum") int snum);
}