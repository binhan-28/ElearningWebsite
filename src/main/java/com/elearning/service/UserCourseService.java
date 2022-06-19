package com.elearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elearning.entities.UserCourse;
import com.elearning.helper.ApiRes;
import com.elearning.repository.UserCourseRepo;

@Service
public class UserCourseService {
	@Autowired
	UserCourseRepo userCourseRepo;

	public ApiRes<Object> findByUserId(long UserId) {
		ApiRes<Object> apiRes = new ApiRes<Object>();
		try {
			List<UserCourse> lstUserCourses = userCourseRepo.findByUserId(UserId);
			apiRes.setObject(lstUserCourses);
		} catch (Exception ex) {
			apiRes.setError(true);
			apiRes.setErrorReason(ex.getMessage());
		}
		return apiRes;
	}

	public ApiRes<Object> save(UserCourse userCourse) {
		ApiRes<Object> apiRes = new ApiRes<Object>();
		try {
			userCourseRepo.save(userCourse);
			apiRes.setObject(true);
		} catch (Exception ex) {
			apiRes.setError(true);
			apiRes.setErrorReason(ex.getMessage());
		}
		return apiRes;
	}

}
