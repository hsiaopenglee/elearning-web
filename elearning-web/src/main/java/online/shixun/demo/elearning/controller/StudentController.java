package online.shixun.demo.elearning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import online.shixun.demo.elearning.dto.Student;
import online.shixun.demo.elearning.service.StudentService;

//用户模块Controller
@Controller
@RequestMapping("/user")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	//提交用户注册
	@PostMapping("/register")
	public String registration(Student student) {
		studentService.saveUser(student);
		return "pages/login";
	}
	
	//异步检测用户填写的邮箱是否存在
	@ResponseBody
	@GetMapping("/check/{email}")
	public boolean checkIfEmailExisted(@PathVariable String email) {
		return studentService.isEmailExisted(email);
	}
	
	//用户注册页面
	@GetMapping("/registration")
	public String register() {
		return "pages/register";
	}

}
