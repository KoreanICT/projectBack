package kr.co.ictedu.projectBack.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ictedu.projectBack.service.member.MemberService;
import kr.co.ictedu.projectBack.vo.MemberVO;

@RestController
@RequestMapping("/api/member")
public class MemberController {
	@Autowired
	private MemberService memberService;

	@PostMapping("/signup")
	public ResponseEntity<?> memberjoin(@RequestBody MemberVO memberDTO) {
		System.out.println("회원가입 요청 들어옴");

		memberService.create(memberDTO);
		return ResponseEntity.ok("회원가입이 완료되었습니다.");
	}
	@GetMapping("/checkId")
	public ResponseEntity<Integer> checkId(@RequestParam("id") String id) {
	    int count = memberService.checkId(id);
	    return ResponseEntity.ok(count); // 0이면 사용 가능, 1 이상이면 중복
	}//http://192.168.0.19/projectBack/api/member/mypage
	@GetMapping("/mypage")
    public MemberVO getMyInfo(@RequestParam("id") String id) {
        MemberVO vo = memberService.getMemberById(id);
        if (vo != null) {
            vo.setPwd(null);
        }
        return vo;
    }
	@PostMapping("/update")
    public int updateMyInfo(@RequestBody MemberVO vo) {
        return memberService.updateMember(vo);
    }
	@DeleteMapping("/withdraw")
	public String memberWithdraw(@RequestParam("num") int num) {
	    memberService.withdrawMember(num);
	    return "탈퇴 완료";
	}
  }
	
	








