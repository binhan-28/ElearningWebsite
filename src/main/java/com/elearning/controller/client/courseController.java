package com.elearning.controller.client;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.elearning.entities.Lesson;
import com.elearning.entities.NguoiDung;
import com.elearning.entities.UserCourse;
import com.elearning.repository.UserCourseRepo;
import com.elearning.entities.Course;
import com.elearning.service.CourseService;
import com.elearning.service.LessonService;
import com.elearning.service.NguoiDungService;

@Controller

@RequestMapping("/course")
public class courseController {
	@Autowired
	private LessonService lessonService;
	@Autowired
	private CourseService courseService;
	@Autowired
	UserCourseRepo userCourseRepo;
	@Autowired
	NguoiDungService nguoiDungService;

	public NguoiDung getSessionUser(HttpServletRequest request) {
		NguoiDung nguoiDung = (NguoiDung) request.getSession().getAttribute("loggedInUser");
		return nguoiDung;
	}

	@GetMapping("")
	public String course(Model model) {

		List<Course> listkKhoaHocs = courseService.getAllCourse();
		model.addAttribute("listData", listkKhoaHocs);
		return "client/list-course";

	}

	@GetMapping("/detail")
	public String DetalVocab(HttpServletRequest request, @RequestParam int courseId, Model model) {

		long longUserId = -1;
		try {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String username = "";
			if (principal instanceof UserDetails) {
				username = ((UserDetails) principal).getUsername();
			} else {
				username = principal.toString();
			}
			NguoiDung user = nguoiDungService.findByEmail(username);
			longUserId = user.getId();

		} catch (Exception e) {

		}
		List<UserCourse> lstUserCourses = userCourseRepo.findByUserId(longUserId);
		if (lstUserCourses != null && lstUserCourses.size() > 0)
			model.addAttribute("isRegisted", true);
		else
			model.addAttribute("isRegisted", false);
		List<Lesson> listLesson = lessonService.getCourseLesson(courseId);
		Course course = courseService.getCourse(courseId).get(0);
		model.addAttribute("course", course);
		model.addAttribute("listLesson", listLesson);
		model.addAttribute("CourseId", course.getCourseId());
		return "client/coursedetail";

	}

	@GetMapping("/learning")
	public String lesson(@RequestParam int courseId, @RequestParam int start, Model model) {
		List<Lesson> listLesson = lessonService.getCourseLesson(courseId);
		Course course = courseService.getCourse(courseId).get(0);
		model.addAttribute("course", course);
		model.addAttribute("listLesson", listLesson);
		model.addAttribute("CourseId", course.getCourseId());
		model.addAttribute("currentLessonId", start);
		return "client/learning";

	}
}
