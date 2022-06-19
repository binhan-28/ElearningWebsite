package com.elearning.service;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.elearning.entities.NguoiDung;
import com.elearning.entities.TestLogs;
import com.elearning.helper.ApiRes;
import com.elearning.repository.TestLogRepository;

@Service
public class TestLogService {

	@Autowired
	private NguoiDungService nguoiDungService;
	@Autowired
	private TestLogRepository testLogRepository;

	public ApiRes<Object> findAllListTestLogsByUserId(int page, int size) {
		ApiRes<Object> apiRes = new ApiRes<Object>();
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = "";
			if (principal instanceof UserDetails) {
				username = ((UserDetails) principal).getUsername();
			} else {
				username = principal.toString();
			}
			NguoiDung user = nguoiDungService.findByEmail(username);
	
			Page<TestLogs> testlogs = testLogRepository.findByUserId(user.getId(), PageRequest.of(page - 1, size));
			apiRes.setObject(testlogs);
		} catch (Exception e) {
			apiRes.setError(true);
			apiRes.setErrorReason(e.getMessage());
			e.printStackTrace();
		}
		return apiRes;
	}

	public ApiRes<Object> saveTestLogs(TestLogs testLogs) {
		ApiRes<Object> apiRes = new ApiRes<Object>();
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = "";
			if (principal instanceof UserDetails) {
				username = ((UserDetails) principal).getUsername();
			} else {
				username = principal.toString();
			}
			NguoiDung user = nguoiDungService.findByEmail(username);
			testLogs.setUserId(user.getId());
			TestLogs currentTestLogs = testLogRepository.save(testLogs);
			apiRes.setObject(true);
		} catch (Exception e) {
			apiRes.setError(true);
			apiRes.setErrorReason(e.getMessage());
		}

		return apiRes;
	}
}
