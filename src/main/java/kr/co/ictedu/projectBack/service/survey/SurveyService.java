package kr.co.ictedu.projectBack.service.survey;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.ictedu.projectBack.dao.survey.SurveyDao;
import kr.co.ictedu.projectBack.vo.SurveyQuestionsVO;
import kr.co.ictedu.projectBack.vo.SurveyVO;

@Service
public class SurveyService {
	
	@Autowired
	private SurveyDao surveyDao;
	
	/**
	 * @param SurveyVO, 관리자가 추가할 고유한 평가지
	 * @param SurveyQuestionsVO, 관리자가 추가할 고유한 평가지의 평가 항목
	 * @detail SurveyVO를 받아 우선적으로 평가지를 DB에 저장한 뒤 SurveyQuestionsVO를 받아 평가지에 들어갈 평가 항목을 DB에 저장합니다.
	 * */
	@Transactional
	public void insertSurvey(SurveyVO svo) {
		surveyDao.insertSurvey(svo);
		List<SurveyQuestionsVO> questionList = new ArrayList<>();
		int i = 1;
		for(SurveyQuestionsVO q : svo.getQuestions()) {
			SurveyQuestionsVO questionVO = new SurveyQuestionsVO();
			questionVO.setQuestions_id(i);
			questionVO.setQuestions_text(q.getQuestions_text());
			questionList.add(questionVO);
			i++;
		}
		surveyDao.insertQuestions(questionList);
	}
	
	public Map selectContents() {
		SurveyVO svo = surveyDao.selectSurvey();
		List<SurveyQuestionsVO> qlist = surveyDao.selectQuestions(svo.getSvnum());
		
		return null;
	}
}

