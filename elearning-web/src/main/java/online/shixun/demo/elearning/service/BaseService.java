package online.shixun.demo.elearning.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import online.shixun.demo.elearning.dto.Student;
import online.shixun.demo.elearning.util.IdUtil;

/**
 * 父类
 *
 */
@Service
@Transactional
public class BaseService {
	//初始化日志
	protected final Logger log = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 获取当前登录用户
	 */
	public Student getCurrentUser() {
		Student student = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return student;
		
	}
	
	/**
	 * uiji生成不重复的ID
	 */
	long nextId() {
		IdUtil idUtil = new IdUtil();
		return idUtil.nextId();
	}
	
	

}
