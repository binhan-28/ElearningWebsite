package com.elearning.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import com.elearning.entities.TestLogs;

@Repository
public interface TestLogRepository extends JpaRepository<TestLogs, Integer> {

	Page<TestLogs> findByUserId(long userId, Pageable pageable);
//	CommentGrammar addComment(CommentGrammar c);
}
