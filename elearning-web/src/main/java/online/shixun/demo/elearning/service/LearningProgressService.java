package online.shixun.demo.elearning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import online.shixun.demo.elearning.dto.LearningProgress;
import online.shixun.demo.elearning.dto.Student;
import online.shixun.demo.elearning.mapper.LearningProgressMapper;

@Service
public class LearningProgressService extends BaseService {
	
	@Autowired
	private LearningProgressMapper learningProgressMapper;
	
	//获取学习进度
	public List<LearningProgress> getUserLearningProgeresses(){
		Student student = getCurrentUser();
		List<LearningProgress> progresses = learningProgressMapper.getUserLearningProgresses(student.getId());
		return progresses;
	}
	
	//获取用户学习课程的数量
	public int getUserLearnedCourseCount() {
		Student student = getCurrentUser();
		return learningProgressMapper.getUserLearnedCourseCount(student.getId());
	}

}
