package com.elearning.api.client;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.elearning.entities.NguoiDung;

import com.elearning.entities.UserCourse;
import com.elearning.service.CourseService;
import com.elearning.service.NguoiDungService;
import com.elearning.service.UserCourseService;

@Controller

@RequestMapping("api/user/client")
@SessionAttributes("loggedInUser")
public class UserApiClient {

	@Autowired
	private NguoiDungService nguoiDungService;
	@Autowired
	private UserCourseService userCourseService;

	@Autowired
	private CourseService courseService;

	public NguoiDung getSessionUser(HttpServletRequest request) {
		NguoiDung nguoiDung = (NguoiDung) request.getSession().getAttribute("loggedInUser");
		return nguoiDung;
	}

	@ModelAttribute("loggedInUser")
	public NguoiDung loggedInUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		NguoiDung nguoiDung = new NguoiDung();
		if (auth.getClass().isAssignableFrom(OAuth2AuthenticationToken.class)) {
			String principal = auth.getPrincipal().toString();
			String[] part = principal.split(",");
			String name = part[2].split("=")[1];
			nguoiDung.setHoTen(name);
			nguoiDung.setLoginOauth2(true);
			return nguoiDung;
		} else {
			return nguoiDungService.findByEmail(auth.getName());
		}
	}

	@GetMapping("/getcourse")
	public ResponseEntity<Object> getcourse(HttpServletRequest request) {
		long longUserId = -1;
		try {
			NguoiDung user = getSessionUser(request);
			longUserId = user.getId();

		} catch (Exception e) {

		}
		return ResponseEntity.ok(userCourseService.findByUserId(longUserId));
	}

	@GetMapping("/course/getlist")
	public ResponseEntity<Object> getListcourse(HttpServletRequest request) {
		long longUserId = -1;
		try {
			NguoiDung user = getSessionUser(request);
			longUserId = user.getId();

		} catch (Exception e) {

		}
		System.out.println(longUserId);
		return ResponseEntity.ok(courseService.findByUserId(longUserId));
	}

	@PostMapping(value = "/course/add")
	public ResponseEntity<?> add(@RequestBody UserCourse UserCourse, HttpServletRequest request) {
		try {

			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = "";
			if (principal instanceof UserDetails) {
				username = ((UserDetails) principal).getUsername();
			} else {
				username = principal.toString();
			}
			NguoiDung user = nguoiDungService.findByEmail(username);
			UserCourse.setUserId(user.getId());
			userCourseService.save(UserCourse);

			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

}
