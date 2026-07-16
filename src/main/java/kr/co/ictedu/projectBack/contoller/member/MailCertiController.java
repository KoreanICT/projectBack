package kr.co.ictedu.projectBack.contoller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ictedu.projectBack.dao.member.CertiNumRedisDao;
import kr.co.ictedu.projectBack.service.member.EmailSender;
import kr.co.ictedu.projectBack.vo.EmailCheckVO;
import kr.co.ictedu.projectBack.vo.EmailCountCheckVO;


@RestController
@RequestMapping("/api/auth")
public class MailCertiController {

	@Autowired
	private EmailSender emailSender;
	@Autowired
	private CertiNumRedisDao certiNumRedisDao;
	@PostMapping("/emailCheck")
	public int sendEmail(@RequestBody EmailCheckVO email) {
		System.out.println("요청 처리됨" + email.getEmail());
		int checkEmail = emailSender.duplicateEmail(email.getEmail());
		if (checkEmail == 0) {
			
			emailSender.sendEmail(email.getEmail());
			return 0;
		} else {
			return 1;
		}
	}
	@PostMapping("/emailCheck/certi")
	public ResponseEntity<EmailCountCheckVO> verifyCertificationNumber(@RequestBody EmailCheckVO dto) {
	    boolean hasKey = certiNumRedisDao.hasKey(dto.getEmail());
	    int attempts = certiNumRedisDao.getAttempt(dto.getEmail());
	    if (!hasKey) { 
	        return ResponseEntity.ok(new EmailCountCheckVO(false, "expired")); 
	    } else if (attempts >= 3) {
	        return ResponseEntity.ok(new EmailCountCheckVO(false, "exceeded")); 
	    } else if (certiNumRedisDao.getCertiRedisNum(dto.getEmail()).equals(dto.getCode())) {
	    	certiNumRedisDao.delCertiRedisNum(dto.getEmail());
	        return ResponseEntity.ok(new EmailCountCheckVO(true, "ok")); 
	    } else {
	    	certiNumRedisDao.incrAttempt(dto.getEmail());
	        return ResponseEntity.ok(new EmailCountCheckVO(false, "wrong"));
	    }
	}
}