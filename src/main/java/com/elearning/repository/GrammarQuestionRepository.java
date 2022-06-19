package com.elearning.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.entities.GrammarQuestion;

public interface GrammarQuestionRepository extends JpaRepository<GrammarQuestion, Long> {

	Page<GrammarQuestion> findById(long id, Pageable pageable);

	Page<GrammarQuestion> findByGrammarExerciseId(long GrammarExerciseId, Pageable pageable);

	List<GrammarQuestion> findById(long id);
}
