
package com.elearning.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// import com.elearning.entities.QGrammarExercise;
import com.elearning.entities.GrammarQuestion;
import com.elearning.helper.ApiRes;
import com.elearning.repository.GrammarQuestionRepository;

@Service
public class GrammarQuestionService {

	@Autowired
	private GrammarQuestionRepository GrammarQuestionRepo;

	public ApiRes<Object> findByGrammarExerciseId(int page, int size, long GrammarExerciseId) {
		ApiRes<Object> apiRes = new ApiRes<Object>();
		try {

			Page<GrammarQuestion> pageGrammarQuestion = GrammarQuestionRepo.findByGrammarExerciseId(GrammarExerciseId,
					PageRequest.of(page - 1, size));
			apiRes.setObject(pageGrammarQuestion);
		} catch (Exception e) {
			apiRes.setError(true);
			apiRes.setErrorReason(e.getMessage());
		}
		return apiRes;
	}

	public List<GrammarQuestion> findCauHoiById(long id) {
		return GrammarQuestionRepo.findById(id);
	}

	@Transactional
	public GrammarQuestion saveGrammarQuestion(GrammarQuestion GrammarQuestion, HttpServletRequest request)
			throws IOException {
		return GrammarQuestionRepo.save(GrammarQuestion);
	}

	public void deleteGrammarQuestion(long id) {
		GrammarQuestionRepo.deleteById(id);
	}

}