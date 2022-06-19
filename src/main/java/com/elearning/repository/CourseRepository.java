
package com.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.elearning.entities.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
	List<Course> findByCourseid(Integer courseId);

	@Query("select course FROM Course course JOIN UserCourse uc on uc.courseId=course.id WHERE uc.userId = :userId")
	List<Course> findByUserId(long userId);
}
