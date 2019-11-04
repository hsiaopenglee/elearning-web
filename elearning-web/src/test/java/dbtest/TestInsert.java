package dbtest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import online.shixun.demo.elearning.StartWebApplication;
import online.shixun.demo.elearning.dto.Course;
import online.shixun.demo.elearning.mapper.CourseMapper;
import online.shixun.demo.elearning.util.IdUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StartWebApplication.class)
public class TestInsert {
	@Autowired
	CourseMapper courseMapper;
	@Test
	public void initCourseData() {
		IdUtil idUtil =  new IdUtil();
		Course course = new Course();
		long courseId = idUtil.nextId();
		course.setId(courseId);
		course.setBrief("测试课程");
		course.setTitle("测试课程标题");
		courseMapper.insert(course);
		
		Course courseFromDb = courseMapper.selectByPrimaryKey(courseId);
 		assertEquals(courseFromDb.getTitle(), "测试课程标题");
	}

}
