package com.elearning.api.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.elearning.service.GrammarExerciseService;
import com.elearning.service.GrammarQuestionService;

@RestController
@RequestMapping("api/client/grammar-exercise")
public class GrammarExerciseAPIClient {

	@Autowired
	private GrammarExerciseService grammarExerciseService;

	@Autowired
	private GrammarQuestionService grammarQuestionService;

	@GetMapping("/id={grammarExerciseId}")
	public ResponseEntity<Object> getListgrammarQuestionBygrammarExerciseId(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "4") int pagesize, @PathVariable long grammarExerciseId) {
		return ResponseEntity.ok(grammarQuestionService.findByGrammarExerciseId(page, pagesize, grammarExerciseId));
	}

	@GetMapping("/getall")
	public ResponseEntity<Object> findAllByPartAndLevel(@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "") String part, @RequestParam(defaultValue = "") String level) {
		return ResponseEntity.ok(grammarExerciseService.findAllListGrammarExerciseByLevelAdmin(page, 2, level));
	}

}
