package online.shixun.demo.elearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import online.shixun.demo.elearning.dto.BlogWithBLOBs;
import online.shixun.demo.elearning.dto.Course;
import online.shixun.demo.elearning.dto.Slide;
import online.shixun.demo.elearning.service.BlogService;
import online.shixun.demo.elearning.service.CourseService;
import online.shixun.demo.elearning.service.SlideService;

@Controller
public class HomeController {
	private final CourseService courseService;
	private final SlideService slideService;
	private final BlogService blogService;

	@Autowired
	public HomeController(CourseService courseService, SlideService slideService, BlogService blogService) {
		this.courseService = courseService;
		this.slideService = slideService;
		this.blogService = blogService;
	}

	// 首页
	@GetMapping("/")
	public String home(Model model) {
		// 填加首页课程
		List<Course> courseList = courseService.getHomePageCourses();
		model.addAttribute("courseList", courseList);
		// 填加首页轮播图
		List<Slide> slides = slideService.getHomePageSlides();
		model.addAttribute("slides", slides);

		// 填加首页博客
		List<BlogWithBLOBs> blogs = blogService.getHomeBlogs();
		model.addAttribute("blogs", blogs);
		return "index";

	}

}