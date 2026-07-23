package kr.co.ictedu.projectBack.dao.member;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.co.ictedu.projectBack.vo.MemberVO;

//kr.co.ictedu.projectBack.dao.member.MemberDao
@Mapper
public interface MemberDao {
	void insertMember(MemberVO vo);
	
	int countByEmail(String email);
	int checkEmail(String email);
	int countById(String id);
	int countByNick(String nick);
	MemberVO getMemberById(String id);
    int updateMember(MemberVO vo);
	int deleteMember(int mnum);
}

