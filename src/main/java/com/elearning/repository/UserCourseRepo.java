package com.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.elearning.entities.UserCourse;

@Repository
public interface UserCourseRepo extends JpaRepository<UserCourse, Integer> {

	List<UserCourse> findByUserId(long userId);

//	CommentGrammar addComment(CommentGrammar c);
}
