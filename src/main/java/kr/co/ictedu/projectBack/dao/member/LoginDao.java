package kr.co.ictedu.projectBack.dao.member;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.co.ictedu.projectBack.vo.MemberVO;


@Mapper
public interface LoginDao {
	@Select("SELECT MNUM, NAME, COUNT(*) cnt FROM MEMBER WHERE \r\n "
			+ "email=#{email} and pwd=#{pwd} group by MNUM, name")
	Map<String, Object> loginCheck(MemberVO vo);
	
}


