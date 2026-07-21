package kr.co.ictedu.projectBack.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.ictedu.projectBack.vo.ProductVO;

@Mapper
public interface ProductDao {

	void add(ProductVO pvo);
	
	List<ProductVO> list(Map<String, Object> map);	
}
