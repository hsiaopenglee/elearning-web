package online.shixun.demo.elearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.Page;

import online.shixun.demo.elearning.dto.Course;
import online.shixun.demo.elearning.dto.CourseCategory;
import online.shixun.demo.elearning.dto.Teacher;
import online.shixun.demo.elearning.service.CourseService;

@Controller
@RequestMapping("/course")
public class CourseController {
	 private final CourseService courseService;
	    @Autowired
	    public CourseController(CourseService courseService) {
	        this.courseService = courseService;
	    }
	/**
	 * 课程列表页面
	 * @param categoryId 课程列表类别，<0：所有类别
	 */
	@GetMapping("/page/{categoryId}")
	public String coursePage(@PathVariable long categoryId,Model model) {
		//第一次获取第一页
		Page<Course> courses  = courseService.getCourseByPage(1, 6, categoryId);
		model.addAttribute("coursePage", courses);
		//添加课程类别列表
		List<CourseCategory> courseCategories = courseService.getCourseCountOfCategories();
		model.addAttribute("courseCategories", courseCategories);
		//添加当前选择的分类ID，作为前端分类下拉框的初始值
		model.addAttribute("courseCategory", categoryId);
		return "pages/courses-grid";
	}
	
	/**
	 * 按页获取课程列表，使用模板响应前端ajax请求
	 * @param pageNo 页数
	 * @param categoryId 课程列表类别 ，<0：所有类别
	 * @param model
	 */
	@GetMapping("/page/{categoryId}/{pageNo}")
	public String courseList(@PathVariable int pageNo,@PathVariable long categoryId,Model model) {
		Page<Course> courses  = courseService.getCourseByPage(pageNo, 6, categoryId);
		model.addAttribute("coursePage",courses);
		return "pages/course-list";
	}
	
	//课程详情
	@GetMapping("/detail/{courseId}")
	public String courseDetail(@PathVariable long courseId,Model model) {
		//添加课程信息
		Course course = courseService.getCourseById(courseId);
		model.addAttribute("course", course);
		
		//添加获取课程分页列表
		List<CourseCategory> categoryList = courseService.getCourseCountOfCategories();
		model.addAttribute("categoryList", categoryList);
		
		//添加获取相关讲师列表
		List<Teacher> teacherList = courseService.getTeacherByCourseCategory(course.getCategoryId());
		model.addAttribute("teacherList", teacherList);
		
		//添加获取相关课程列表
		List<Course> relatedCourseList = courseService.getRelatedCourses(course.getId(), course.getCategoryId());
		model.addAttribute("relatedCourseList", relatedCourseList);
		return "pages/courses-single";
	}
	

}
