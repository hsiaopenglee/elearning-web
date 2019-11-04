package online.shixun.demo.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import online.shixun.demo.elearning.dto.CourseChapter;
import online.shixun.demo.elearning.service.CourseChapterService;

/**
* 课程章节学习Controller
*/
@Controller
@RequestMapping("/course")
public class CourseChapterController {
private final CourseChapterService courseChapterService;

@Autowired
public CourseChapterController(CourseChapterService courseChapterService) {
    this.courseChapterService = courseChapterService;
}

/**
 * 课程章节学习页面
 *
 * @param courseChapterId
 * @param model
 * @return
 */
@GetMapping("/study/{courseChapterId}")
public String page(@PathVariable long courseChapterId, Model model) {
    // 填加课程章节
    CourseChapter courseChapter = courseChapterService.getCourseChapterById(courseChapterId);
    model.addAttribute("chapter", courseChapter);

    // 更新学习进度
    courseChapterService.updateUserCourseLearningProgress(courseChapter.getCourseId(),courseChapter.getId());
    return "pages/courses-study";
}
}
