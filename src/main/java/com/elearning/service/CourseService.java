
package com.elearning.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.elearning.entities.Course;
import com.elearning.helper.ApiRes;

public interface CourseService {

	void save(Course course);

	List<Course> getCourse(int id);

	Page<Course> getCourse(int page, int limit);

	List<Course> getAllCourse();

	void delete(int id);

	ApiRes<Object> findByUserId(long UserId);

	/* List<KhoaHoc> searchKhoaHoc(String search); */

}
