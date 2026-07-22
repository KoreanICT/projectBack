package kr.co.ictedu.projectBack.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.ictedu.projectBack.vo.OrderFormVO;

@Mapper
public interface OrderDao {

	void add(OrderFormVO ofvo);
	
	List<Map<String, Object>> list(Map<String, Object> map);
	
	OrderFormVO detail(int ofnum);
	
	void delete(int ofnum);
}
