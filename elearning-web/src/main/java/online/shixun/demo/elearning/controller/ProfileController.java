package online.shixun.demo.elearning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import online.shixun.demo.elearning.dto.Comment;
import online.shixun.demo.elearning.dto.LearningProgress;
import online.shixun.demo.elearning.dto.Student;
import online.shixun.demo.elearning.service.CommentService;
import online.shixun.demo.elearning.service.LearningProgressService;
import online.shixun.demo.elearning.service.StorageService;
import online.shixun.demo.elearning.service.StudentService;

//个人中心
@Controller
@RequestMapping("/profile")
public class ProfileController {
	
	@Autowired 
	private CommentService commentService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private StorageService storageService;
	@Autowired
	private LearningProgressService learningProgressService;
	
	//个人中心主页
	@GetMapping("/home")
	public String display(Model model) {
		//增加学习记录
		List<LearningProgress> learningProgresses = learningProgressService.getUserLearningProgeresses();
		model.addAttribute("learningProgresses", learningProgresses);
		
		//增加用户学习课程的总数
		int userLearnedCourseCount = learningProgressService.getUserLearnedCourseCount();
		model.addAttribute("userLearnedCourseCount", userLearnedCourseCount);
		
		//增加用户发表评论的总数
		int userPostedCommentsCount = commentService.getUserComments().size();
		model.addAttribute("userPostedCommentsCount", userPostedCommentsCount);
		
		//增加学生信息
		Student student  = studentService.getUpdateCurrentStudent();
		student.getImage();
		model.addAttribute("student", student);
		
		return "pages/profile";
	}
	
	//异步获取用户的学习进度
	@GetMapping("/hostory")
	@PreAuthorize("isAuthenticated()")
	public String learningHistory(Model model) {
		//增加学习记录
		List<LearningProgress> learningProgresses = learningProgressService.getUserLearningProgeresses();
		model.addAttribute("learningProgresses", learningProgresses);
		return "layout/profile::learningHistory";
	}
	
	//异步获取用户发表的评论记录
	@GetMapping("/comment")
	@PreAuthorize("isAuthenticated()")
	public String userComment(Model model) {
		//添加用户发表过的评论列表
		List<Comment> comments = commentService.getUserComments();
		model.addAttribute("comments", comments);
		return "layout/profile-content::comment";
	}
	
	//删除评论，级联删除回复被删除的子评论
	@ResponseBody
	@GetMapping("/comment/delete/{commentId}")
	public boolean deleteComment(@PathVariable long commentId) {
		int result = commentService.deleteCommentById(commentId);
		return result > 0;
	}
	
	//更新我的评论
	@GetMapping("/edit")
	@PreAuthorize("isAuthenticated()")
	public String myProfile(Model model) {
		Student student = studentService.getUpdateCurrentStudent();
		model.addAttribute("student", student);
		return "layout/profile-content::editProfile";
	}
	
	//保存学生个人信息
	@ResponseBody
	@PostMapping("/save")
	@PreAuthorize("isAuthenticated()")
	public boolean saveProfile(Student student,@RequestParam("avatar") MultipartFile avatar) {
		//确保用户上传了文件，且带有正确的图片后缀
		if(avatar.getOriginalFilename().matches(".*\\.(gif|jpg|jpeg|tiff|png)$")) {
			storageService.storeStudentImage(avatar, student);
		}
		return studentService.saveStudentProfile(student);
	}
	
	
}
