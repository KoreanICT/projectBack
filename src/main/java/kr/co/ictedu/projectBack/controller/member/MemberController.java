package kr.co.ictedu.projectBack.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/emailCheck")
	public int emailCheck(@RequestParam("email") String email) {
	    return memberService.checkEmail(email);
	}
}




