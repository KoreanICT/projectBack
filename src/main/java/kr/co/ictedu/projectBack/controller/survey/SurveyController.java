package kr.co.ictedu.projectBack.controller.survey;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ictedu.projectBack.service.survey.SurveyService;
import kr.co.ictedu.projectBack.vo.SurveyVO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("/selectSurvey")
	public Map<String, Object> getMethodName(@RequestParam String param) {
		Map<String, Object> surveyDataMap = service.selectSurvey();
		return surveyDataMap;
	}
	
	@PostMapping("path")
	public String postMethodName(@RequestBody String entity) {
		//TODO: process POST request
		
		return entity;
	}
	
	@GetMapping("path")
	public String getMethoName(@RequestParam String param) {
		return new String();
	}
	
	
}
