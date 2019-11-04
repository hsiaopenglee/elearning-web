package dbtest;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.annotation.Rollback;
import online.shixun.demo.elearning.StartWebApplication;
import online.shixun.demo.elearning.dto.BlogWithBLOBs;
import online.shixun.demo.elearning.dto.Course;
import online.shixun.demo.elearning.dto.CourseChapter;
import online.shixun.demo.elearning.dto.Slide;
import online.shixun.demo.elearning.dto.Student;
import online.shixun.demo.elearning.dto.Teacher;
import online.shixun.demo.elearning.mapper.BlogMapper;
import online.shixun.demo.elearning.mapper.CourseChapterMapper;
import online.shixun.demo.elearning.mapper.CourseMapper;
import online.shixun.demo.elearning.mapper.SlideMapper;
import online.shixun.demo.elearning.mapper.StudentMapper;
import online.shixun.demo.elearning.mapper.TeacherMapper;
import online.shixun.demo.elearning.util.DictionaryConstant;
import online.shixun.demo.elearning.util.IdUtil;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest(classes = StartWebApplication.class)
@RunWith(SpringRunner.class)
public class DataInit {
	
	@Autowired 
	SlideMapper slideMapper;
	@Autowired 
	TeacherMapper teacherMapper;
	@Autowired 
	CourseMapper courseMapper;
	@Autowired 
	CourseChapterMapper courseChapterMapper;
	@Autowired 
	BlogMapper blogMapper;
	@Autowired 
	IdUtil idUtil;
	@Autowired
	StudentMapper studentMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Test
	public void initStudent() {
	    Student student = new Student();
	    student.setId(idUtil.nextId());
	    student.setName("alice");
	    student.setEmail("demo@shixun.online");
	    student.setPassword(passwordEncoder.encode("123456"));
	    studentMapper.insert(student);
	}
	
	@Test
	public void initSlideData() {
		Slide slide = new Slide();
		Long id = idUtil.nextId();
		slide.setId(id);
		slide.setImageSuffix(".jpg");
		slide.setTitle("What’s new in Java 10");
		slide.setBrief("Better Docker support and technologies from several projects will provide a pipeline of new capabilities in Java 10 and later versions");
		slide.setCreateTime(new Date());
		slide.setUpdateTime(new Date());
		slide.setTypeCode(DictionaryConstant.SLID_TYPE_BLOG);
		slideMapper.insert(slide);
		
		slide.setId(idUtil.nextId());
		slide.setTitle("30天快速入门Java Web开发");
		slide.setBrief("零基础学习Java，用使用30天时间，将自己蜕变成一个初步具备Java web开发技能的程序员");
		slide.setCreateTime(new Date());
		slide.setUpdateTime(new Date());
		slide.setTypeCode(DictionaryConstant.SLID_TYPE_COURSE);
		slideMapper.insert(slide);
		
		slide.setId(idUtil.nextId());
		slide.setTitle("JAVA9模块化");
		slide.setBrief("为了提高可靠的配置性和强大的封装性，我们将模块化看作是java程序组件的一个基本的新特性，这样它对开发者和可支持的工具更加友好。一个模块是一个被命名的，代码和数据的自描述的集合。");
		slide.setCreateTime(new Date());
		slide.setUpdateTime(new Date());
		slide.setTypeCode(DictionaryConstant.SLID_TYPE_BLOG);
		slideMapper.insert(slide);
	}
	
	@Test
	@Rollback(false)//事务不会回滚，数据会写入到数据库中
	public void initCourseData() {
		Teacher teacher = new Teacher();
		Long teacherId1 = idUtil.nextId();
		teacher.setId(teacherId1);
		teacher.setName("谢俊");
		teacher.setFirstLetter("x");
		teacher.setImageSuffix(".jpg");
		teacher.setSex("男");
		teacherMapper.insert(teacher);
		
		long teacherId2 = idUtil.nextId();
		teacher.setId(teacherId2);
		teacher.setName("王敏");
		teacher.setFirstLetter("w");
		teacher.setImageSuffix(".jpg");
		teacher.setSex("女");
		teacherMapper.insert(teacher);
		
		Course course = new Course();
		course.setClassSize(100);
		long courseId1 = idUtil.nextId();
		course.setId(courseId1);
		course.setTitle("变量定义");
		course.setCategoryId(1L);
		course.setLecturerId(teacherId1);
		course.setBrief("Java要求在使用变量前显式定义变量并声明变量值的类型，所以Java被称为是一个强类型的语言，有些语言则没有这些要求。");
		course.setImageSuffix(".jpg");
		courseMapper.insert(course);
		
		CourseChapter courseChapter = new CourseChapter();
		courseChapter.setBrief("在Java语言中，所有的变量在使用前必须声明。声明变量的基本格式如下：type identifier [ = value][, identifier [= value] ...]");
		courseChapter.setId(idUtil.nextId());
		courseChapter.setTitle("Java变量定义");
		courseChapter.setCourseId(courseId1);
		courseChapterMapper.insert(courseChapter);
		
		
		long courseId2 = idUtil.nextId();
		course.setId(courseId2);
		course.setTitle("Java数据类型");
		course.setLecturerId(teacherId2);
		course.setCategoryId(2L);
		course.setBrief("J所有变量和表达式的类型再编译时就已经完全确定。由于是静态类型，导致Java语言也是强类型（Strong typed）的。强类型意味着每个变量都具有一种类型，"
					+ "每个表达式具有一种类型，并且每种类型都是严格定义的，类型限制了变量可以赋予哪些值，表达式最终产生什么值");
		courseMapper.insert(course);
			
		courseChapter.setBrief("Java包含8种基本变量类型，byte,short,int,long,float,double,char,boolean");
		courseChapter.setId(idUtil.nextId());
		courseChapter.setTitle("Java变量类型");
		courseChapter.setCourseId(courseId2);
		courseChapterMapper.insert(courseChapter);
			
			
		long courseId3 = idUtil.nextId();
		course.setId(courseId3);
		course.setTitle("Java循环语句");
		course.setLecturerId(teacherId2);
		course.setCategoryId(2L);
		course.setBrief("是根据循环条件反复执行相同的语句，直到循环条件不成立。顺序结构的程序语句只能被执行一次，如果想要同样的操作执行多次，就需要使用循环结构语句。");
		courseMapper.insert(course);
			
		courseChapter.setBrief("Java的循环包含while,for,do-Wile循环");
		courseChapter.setId(idUtil.nextId());
		courseChapter.setTitle("Java变量类型");
		courseChapter.setCourseId(courseId3);
		courseChapterMapper.insert(courseChapter);
	}
	
	@Test
	public void initBlogData() {
		BlogWithBLOBs blog = new BlogWithBLOBs();
		blog.setImageSuffix(".jpg");
		blog.setTitle("What’s new in Java 10");
		blog.setBrief("Better Docker support and technologies from several projects will provide a pipeline of new capabilities in Java 10 and later versions");
		blog.setId(idUtil.nextId());
		blog.setContent("content of blog");
		blog.setCategory(2L);
		blogMapper.insert(blog);
			
			
		blog.setTitle("JAVA9模块化");
		blog.setBrief("为了提高可靠的配置性和强大的封装性，我们将模块化看作是java程序组件的一个基本的新特性，这样它对开发者和可支持的工具更加友好。一个模块是一个被命名的，代码和数据的自描述的集合。");
		blog.setId(idUtil.nextId());
		blog.setContent("content of blog");
		blog.setCategory(2L);
		blogMapper.insert(blog);
			
		blog.setTitle("浅谈程序员职业规划");
		blog.setBrief("在中国有很多人都认为IT行业是吃青春饭的，如果过了30岁就很难有机会再发展下去！其实现实并不是这样子的，在下从事.NET及JAVA方面的开发的也有8年的时间了，在这里在下想凭借自己的亲身经历，与大家一起探讨一下。");
		blog.setId(idUtil.nextId());
		blog.setContent("content of blog");
		blog.setCategory(3L);
		blogMapper.insert(blog);
	}
	
	@Test
	@Repeat(50)
	public void initCourseData1() {
		Course course = new Course();
		course.setId(idUtil.nextId());
		course.setTitle("变量定义");
		course.setBrief("Java要求在使用变量前显式定义变量并声明变量值的类型，所以Java被称为是一个强类型的语言，有些语言则没有这些要求。");
		course.setImageSuffix(".jpg");
		course.setCurrentEnrollNo(80);
		course.setUpdateTime(new Date());
		course.setReleaseDate(new Date());
		course.setClassSize(100);
		course.setDuration(10d);
		course.setCategoryId((long)ThreadLocalRandom.current().nextInt(1, 3 + 1));
		courseMapper.insert(course);
		
		course.setId(idUtil.nextId());
		course.setTitle("Java数据类型");
		course.setBrief("J所有变量和表达式的类型再编译时就已经完全确定。由于是静态类型，导致Java语言也是强类型（Strong typed）的。强类型意味着每个变量都具有一种类型，"
				+ "每个表达式具有一种类型，并且每种类型都是严格定义的，类型限制了变量可以赋予哪些值，表达式最终产生什么值");
		course.setCategoryId((long)ThreadLocalRandom.current().nextInt(1, 3 + 1));
		courseMapper.insert(course);
		
		course.setId(idUtil.nextId());
		course.setTitle("Java循环语句");
		course.setBrief("是根据循环条件反复执行相同的语句，直到循环条件不成立。顺序结构的程序语句只能被执行一次，如果想要同样的操作执行多次，就需要使用循环结构语句。");
		course.setCategoryId((long)ThreadLocalRandom.current().nextInt(1, 3 + 1));
		courseMapper.insert(course);
	}
	
	@Test
	 @Rollback(false)
	 public void supplementCourseInfo() {
	  long[] tIds = new long[] {idUtil.nextId(),idUtil.nextId(),idUtil.nextId()};
	  Teacher teacher = new Teacher();
	  teacher.setImageSuffix(".jpg");
	  
	  teacher.setName("王敏");
	  teacher.setFirstLetter("w");
	  teacher.setId(tIds[0]);
	  teacher.setTitle("中级讲师");
	  teacher.setSex("女");
	  teacher.setEmail("wang_min@shixun.online");
	  teacherMapper.insert(teacher);
	  
	  teacher.setName("张力");
	  teacher.setFirstLetter("z");
	  teacher.setId(tIds[1]);
	  teacher.setTitle("高级讲师");
	  teacher.setSex("女");
	  teacher.setEmail("zhang_li@shixun.online");
	  teacherMapper.insert(teacher);
	  
	  teacher.setName("游明");
	  teacher.setFirstLetter("y");
	  teacher.setId(tIds[2]);
	  teacher.setTitle("高级讲师");
	  teacher.setSex("男");
	  teacher.setEmail("you_ming@shixun.online");
	  teacherMapper.insert(teacher);
	  
	  // 获取所有讲师
	  List<Course> courses = courseMapper.getCourses(-1, -1);
	  courses.stream().forEach(course->{
	   long randomLectureId = tIds[ThreadLocalRandom.current().nextInt(0,3)];
	   course.setLecturerId(randomLectureId);
	   courseMapper.updateByPrimaryKey(course);
	   CourseChapter courseChapter = new CourseChapter();
	   courseChapter.setCourseId(course.getId());
	   courseChapter.setId(idUtil.nextId());
	   courseChapter.setTitle("环境准备");
	   courseChapter.setChapterIndex(1);
	   courseChapter.setDuration(new BigDecimal(0.45));
	   courseChapterMapper.insert(courseChapter);
	   
	   courseChapter.setId(idUtil.nextId());
	   courseChapter.setTitle("代码练习");
	   courseChapter.setChapterIndex(2);
	   courseChapter.setDuration(new BigDecimal(2.0));
	   courseChapterMapper.insert(courseChapter);
	   
	   courseChapter.setId(idUtil.nextId());
	   courseChapter.setTitle("课程总结");
	   courseChapter.setChapterIndex(3);
	   courseChapter.setDuration(new BigDecimal(0.5));
	   courseChapterMapper.insert(courseChapter);
	  });
	 }
	
	
	@Test
	@Rollback(false)
	@Repeat(200)
	public void initTeacher() {
		Teacher teacher = new Teacher();
		char randomChar = (char)(ThreadLocalRandom.current().nextInt(0,26)+'a');
		String randomFirstLetter = String.valueOf(randomChar);
		teacher.setFirstLetter(randomFirstLetter);
		teacher.setName(randomFirstLetter+"测试讲师");
		teacher.setEmail(Math.random()+"@shixun.online");
		teacher.setTitle("测试title");
		teacher.setId(idUtil.nextId());
		teacher.setImageSuffix(".jpg");
		teacherMapper.insert(teacher);
	}
	
	@Test
	public void initBlogData1() {
		BlogWithBLOBs blog = new BlogWithBLOBs();
		blog.setImageSuffix(".jpg");
		blog.setTitle("What's new in Java 10");
		blog.setBrief("Better Docker support and technologies from several projects will provide a pipeline of new capabilities in Java 10 and later vrersions");
		blog.setId(idUtil.nextId());
		blog.setContent("content of blog");
		blog.setCategory(2L);
		blogMapper.insert(blog);
		
		blog.setTitle("JAVA9模块化");
	    blog.setBrief("为了提高可靠的配置性和强大的封装性，我们将模块化看作是java程序组件的一个基本的新特性，这样它对开发者和可支持的工具更加友好。一个模块是一个被命名的，代码和数据的自描述的集合。");
	    blog.setId(idUtil.nextId());
	    blog.setContent("content of blog");
	    blog.setCategory(2L);
	    blogMapper.insert(blog);

	    blog.setTitle("浅谈程序员职业规划");
	    blog.setBrief("在中国有很多人都认为IT行业是吃青春饭的，如果过了30岁就很难有机会再发展下去！其实现实并不是这样子的，在下从事.NET及JAVA方面的开发的也有8年的时间了，在这里在下想凭借自己的亲身经历，与大家一起探讨一下。");
	    blog.setId(idUtil.nextId());
	    blog.setContent("content of blog");
	    blog.setCategory(3L);
	    blogMapper.insert(blog);
	}
	

}
