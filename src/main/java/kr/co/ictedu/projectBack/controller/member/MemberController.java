package kr.co.ictedu.projectBack.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ictedu.projectBack.service.member.MemberService;
import kr.co.ictedu.projectBack.vo.MemberVO;

@RestController
@RequestMapping("/api/members")
public class MemberController {
	@Autowired
	private MemberService memberService;
	


}


