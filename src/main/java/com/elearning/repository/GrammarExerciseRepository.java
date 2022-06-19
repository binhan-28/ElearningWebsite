package com.elearning.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.elearning.entities.GrammarExercise;

public interface GrammarExerciseRepository
		extends JpaRepository<GrammarExercise, Long>, QuerydslPredicateExecutor<GrammarExercise> {
	Page<GrammarExercise> findByLevel(int level, Pageable pageable);

}
