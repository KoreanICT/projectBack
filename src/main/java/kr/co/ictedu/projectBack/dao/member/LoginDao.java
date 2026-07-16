package kr.co.ictedu.projectBack.dao.member;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.co.ictedu.projectBack.vo.MemberVO;


@Mapper
public interface LoginDao {
	@Select("SELECT NAME, COUNT(*) cnt FROM MEMBER WHERE \r\n "
			+ "id=#{id} and password=#{pwd} group by name")
	Map<String, Object> loginCheck(MemberVO vo);

}


