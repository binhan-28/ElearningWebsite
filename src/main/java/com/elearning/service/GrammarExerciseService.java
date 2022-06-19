
package com.elearning.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elearning.entities.QGrammarExercise;
import com.elearning.entities.GrammarExercise;
import com.elearning.entities.GrammarQuestion;
import com.elearning.helper.ApiRes;
import com.elearning.repository.GrammarExerciseRepository;
import com.elearning.util.ExcelUtilDoc;
import com.querydsl.core.BooleanBuilder;

@Service
public class GrammarExerciseService {

	@Autowired
	private GrammarExerciseRepository GrammarExerciseRepo;

	@Autowired
	private GrammarQuestionService GrammarQuestionService;

	@Autowired
	private ExcelUtilDoc excelUtilDoc;

	public ApiRes<Object> findAllListGrammarExerciseByPartAndLevel(int page, int size, int Level) {
		ApiRes<Object> apiRes = new ApiRes<Object>();
		try {
			Page<GrammarExercise> lstGrammarExercisePage = GrammarExerciseRepo.findByLevel(Level,
					PageRequest.of(page - 1, size));
			apiRes.setObject(lstGrammarExercisePage);
		} catch (Exception e) {
			apiRes.setError(true);
			apiRes.setErrorReason(e.getMessage());
		}
		return apiRes;
	};

	public ApiRes<Object> findAllListGrammarExerciseByLevelAdmin(int page, int size, String Level) {
		ApiRes<Object> apiRes = new ApiRes<Object>();
		try {
			BooleanBuilder builder = new BooleanBuilder();

			if (!Level.equals("")) {
				builder.and(QGrammarExercise.grammarExercise.level.eq(Integer.parseInt(Level)));
			}
			Page<GrammarExercise> lstGrammarExercisePage = GrammarExerciseRepo.findAll(builder,
					PageRequest.of(page - 1, size));

			apiRes.setObject(lstGrammarExercisePage);
		} catch (Exception e) {
			apiRes.setError(true);
			apiRes.setErrorReason(e.getMessage());
		}
		return apiRes;
	}

	public Optional<GrammarExercise> findGrammarExerciseById(long id) {
		return GrammarExerciseRepo.findById(id);
	}

	@Transactional
	public GrammarExercise save(GrammarExercise GrammarExercise, HttpServletRequest request) {
		List<GrammarQuestion> listQuestion = new ArrayList<>();
		GrammarExercise currentBaiDoc = GrammarExerciseRepo.save(GrammarExercise);
		try {
			for (GrammarQuestion objQuestion : excelUtilDoc
					.getListGrammarQuestionFromFileExcel(GrammarExercise.getFileExcel().getInputStream())) {
				objQuestion.setGrammarExercise(currentBaiDoc);
				listQuestion.add(GrammarQuestionService.saveGrammarQuestion(objQuestion, request));
			} //
			currentBaiDoc.setGrammarQuestions(listQuestion);
			return currentBaiDoc;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void deleteGrammarExercise(long id) {
		GrammarExerciseRepo.deleteById(id);
	}

}
