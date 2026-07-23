package kr.co.ictedu.projectBack.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.co.ictedu.projectBack.service.OrderService;
import kr.co.ictedu.projectBack.vo.OrderFormVO;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Value("${spring.servlet.multipart.location}")
	private String filePath;

	@PostMapping("/orderForm")
	public ResponseEntity<?> handleOrderSubmit(
	    @RequestParam("order") Map<String, Object> orderJson,   // JSON 문자열로 넘어온 order
	    @RequestParam("signature") MultipartFile file   		// signature로 넘어온 파일
	) {
	    
		if(!file.isEmpty()) {
			String originalFilename = file.getOriginalFilename();
			File f = new File(filePath+"/signature/",originalFilename);
			try {
				file.transferTo(f);//업로드 완료
				OrderFormVO vo = new OrderFormVO();//이미지 객체 생성
				vo.setOfile(file); //이미지들의 이름을 vo저장
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
