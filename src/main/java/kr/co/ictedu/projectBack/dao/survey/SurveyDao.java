package kr.co.ictedu.projectBack.dao.survey;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import kr.co.ictedu.projectBack.vo.SurveyQuestionsVO;
import kr.co.ictedu.projectBack.vo.SurveyVO;

@Mapper
public interface SurveyDao {
	
	 void insertSurvey(SurveyVO vo);
	 
	 void insertQuestions(List<SurveyQuestionsVO> list);
	 
	 void insertRatings(Map<String, Object> resMap);
	 
	 void deleteSurvey();
	 
	 SurveyVO selectSurvey();
	 
	 List<SurveyQuestionsVO> selectQuestions(int svnum);
	 
	 Map<String, Object> selectAverages(Map<String, Object> resultMap);
	 
}
