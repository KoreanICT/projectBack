package kr.co.ictedu.projectBack.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ictedu.projectBack.dao.member.MemberDao;
import kr.co.ictedu.projectBack.vo.MemberVO;

@Service
public class MemberService {
	@Autowired
	private MemberDao memberDao;
	
	public void create(MemberVO vo) {
		memberDao.insertMember(vo);	
	}
	
	public int checkEmailDup(String email) {
		return memberDao.countByEmail(email);
	}
	public int checkEmail(String email) {
		return memberDao.checkEmail(email);
	}
}