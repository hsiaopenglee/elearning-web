package online.shixun.demo.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.Page;

import online.shixun.demo.elearning.dto.Teacher;
import online.shixun.demo.elearning.service.TeacherService;

@Controller
@RequestMapping("/team")
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	//返回团队首页
	@GetMapping("/page")
	public String teamPage(Model model) {
		//添加讲师列表
		Page<Teacher> teachers = teacherService.getTeachersByFirstLetter("all", 1, 8);
		model.addAttribute("teachers", teachers);
		return "pages/team";
	}
	
	/**按讲师名字首字母查找教师信息
	 * @param firstLetter 讲师名称拼音首字母
	 * @param model
	 */
	@GetMapping("/page/{firstLetter}/{page}")
	public String teamList(@PathVariable String firstLetter,@PathVariable int page,Model model) {
		//添加讲师列表
		Page<Teacher> teachers = teacherService.getTeachersByFirstLetter(firstLetter, page, 8);
		model.addAttribute("teachers", teachers);
		return "layout/teacher-list";
	}
	
}
