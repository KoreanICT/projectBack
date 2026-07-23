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
	 * @param SurveyVO
	 * @detail 
	 */
	@PostMapping("/addSurvey")
	public ResponseEntity<String> insertSurvey(@RequestBody SurveyVO svo) {
		service.addSurvey(svo);
		return ResponseEntity.ok("success");
	}
	
	/**
	 * @param vo
	 * @detail
	 */
	@PostMapping("/addResult")
	public ResponseEntity<String> addResult(@RequestBody SurveyVO svo) {
		service.addResult(svo);
		return ResponseEntity.ok("success");
	}
	
	/**
	 * @return
	 * @detail
	 */
	@GetMapping("/selectSurvey")
	public Map<String, Object> getSurvey() {
		Map<String, Object> surveyDataMap = service.selectSurvey();
		return surveyDataMap;
	}
	
	/**
	 * @return
	 * @detail
	 */
	@GetMapping("/getAvg")
	public Map<String, Object> getAverage() {
		Map<String, Object> avgMap = service.getAverage();
		return avgMap;
	}
}
