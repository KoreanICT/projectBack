package kr.co.ictedu.projectBack.controller.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ictedu.projectBack.service.survey.SurveyService;
import kr.co.ictedu.projectBack.vo.SurveyVO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/survey")
public class SurveyController {

	@Autowired
	private SurveyService service;
	
	/**
	 * 
	 * @param vo
	 * @return
	 * @detail 
	 */
	@PostMapping("/insertSurvey")
	public ResponseEntity<String> insertSurvey(@RequestBody SurveyVO vo) {
		service.insertSurvey(vo);
		return ResponseEntity.ok("success");
	}
}
