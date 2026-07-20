package kr.co.ictedu.projectBack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.ictedu.projectBack.common.PagingService;
import kr.co.ictedu.projectBack.service.InquiryService;
import kr.co.ictedu.projectBack.vo.InquiryVO;

@RestController
@RequestMapping("/api/inquiry")
public class InquiryController {
	
	@Autowired
	private InquiryService inquiryService;
	
	@Autowired
	private PagingService paginService;
	
	@Value("${spring.servlet.multipart.location")
	private String filePath;
	
	@GetMapping("/getPath")
	public String getPathTest() {
		System.out.println("Path:" + filePath);
		return filePath;
	}
	
}
