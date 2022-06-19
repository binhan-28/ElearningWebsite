package com.elearning.api.client;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.elearning.entities.TestLogs;
import com.elearning.request.BaseReq;
import com.elearning.service.TestLogService;

@Controller

@RequestMapping("api/testlogs")
public class TestLogsApi {
	@Autowired
	private TestLogService testLogService;

	@RequestMapping(value = "/getbyuser", method = RequestMethod.POST, consumes = "application/json", produces = "application/json; charset=utf-8")
	public ResponseEntity<Object> getlog(@RequestParam(defaultValue = "1") int page) {
		return ResponseEntity.ok(testLogService.findAllListTestLogsByUserId(page, 10));
	}

	@PostMapping(value = "/add")
	public ResponseEntity<?> add(@RequestBody TestLogs testLogs, HttpServletRequest request) {
		return ResponseEntity.ok(testLogService.saveTestLogs(testLogs));

	}

}
