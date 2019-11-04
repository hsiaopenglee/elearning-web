package online.shixun.demo.elearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import online.shixun.demo.elearning.dto.Course;
import online.shixun.demo.elearning.dto.CourseCategory;
import online.shixun.demo.elearning.dto.Teacher;
import online.shixun.demo.elearning.mapper.CourseCategoryMapper;
import online.shixun.demo.elearning.mapper.CourseMapper;
import online.shixun.demo.elearning.mapper.TeacherMapper;

/**
 * 课程Service
 */
@Service
public class CourseService {
	
	@Autowired
	private TeacherMapper teacherMapper;
 final private CourseMapper courseMapper;
 final private CourseCategoryMapper courseCategoryMapper;
 @Autowired
 public CourseService(CourseMapper courseMapper,CourseCategoryMapper courseCategoryMapper) {
  this.courseMapper = courseMapper;
  this.courseCategoryMapper = courseCategoryMapper;
 }
 
 //分页获取课程
 public Page<Course> getCourseByPage(int pageNo, int pageSize,long categoryId) {
	 PageHelper.startPage(pageNo,pageSize);
	 return courseMapper.getCourses(-1, categoryId);	
}
 
 //获取课程类别名称以及该类型课程的数量
 public List<CourseCategory> getCourseCountOfCategories(){
	 return courseCategoryMapper.getCourseCategories();
 }
 
 /**
  * 获取首页展示的课程
  *
  * @return
  */
 public List<Course> getHomePageCourses() {
  return courseMapper.getHomePageCourses();
 }
 
 //根据Id获取课程
 public Course getCourseById(long id) {
	return courseMapper.getCourseById(id);
}
 
 //获取课程类型获取课程
 public List<Course> getRelatedCourses(long courseId,long categoryId) {
	return courseMapper.getRelatedCourse(courseId, categoryId);
}
 
 //获取与特定课程类型关联的讲师
 public List<Teacher> getTeacherByCourseCategory(long courseCategoryId){
	 return teacherMapper.getTeachersByCourseCategory(courseCategoryId);
 }
}