package online.shixun.demo.elearning.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import online.shixun.demo.elearning.dto.Teacher;
import online.shixun.demo.elearning.mapper.TeacherMapper;

@Service
public class TeacherService extends BaseService {
	@Autowired
	private TeacherMapper teacherMapper;
	
	/**
	 * 按姓名首字母获取讲师列表
	 * @param firstLetter
	 */
	public Page<Teacher> getTeachersByFirstLetter(String firstLetter,int pageNo,int pageSize){
		PageHelper.startPage(pageNo,pageSize);
		return teacherMapper.getTeachersByFirstLetter(firstLetter);
	}

}
