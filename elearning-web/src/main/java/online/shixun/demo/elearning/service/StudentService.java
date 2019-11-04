package online.shixun.demo.elearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import online.shixun.demo.elearning.dto.Student;
import online.shixun.demo.elearning.mapper.StudentMapper;

/*
 * 用户Service
 */
@Service
public class StudentService extends BaseService{
	@Autowired
	private StudentMapper studentMapper;
	
	//通过id获取学生记录
		public Student getUpdateCurrentStudent() {
			return studentMapper.selectByPrimaryKey(getCurrentUser().getId());
		}
	
	/**
	 * 用户保存信息
	 * @param student 要保存的用户实例
	 */
	public void saveUser(Student student) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String hashedPassword = encoder.encode(student.getPassword());
		//对密码进行加密，因为Spring Security会使加密后的密码进行验证
		student.setPassword(hashedPassword);
		student.setId(nextId());
		student.setName(nextId() + "");
		log.debug("received registration message:" + student.getPassword());
		studentMapper.insert(student);		
	}
	
	//注册用户名是否被占用
	public boolean isEmailExisted(String email) {
		return studentMapper.ifEmailExisted(email);
	}
	
	//保存用户资料
	public boolean saveStudentProfile(Student student) {
		return studentMapper.updateByPrimaryKey(student)>0;
	}
	

}
