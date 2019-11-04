package online.shixun.demo.elearning.mapper;

import java.util.List;
import online.shixun.demo.elearning.dto.Course;
import online.shixun.demo.elearning.dto.CourseChapter;
import online.shixun.demo.elearning.dto.CourseExample;
import online.shixun.demo.elearning.dto.Teacher;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;
import com.github.pagehelper.Page;

public interface CourseMapper {
	@SelectProvider(type = CourseSqlProvider.class, method = "countByExample")
	long countByExample(CourseExample example);

	@DeleteProvider(type = CourseSqlProvider.class, method = "deleteByExample")
	int deleteByExample(CourseExample example);

	@Insert({ "insert into so_course (id, create_time, ", "update_time, brief, ", "description, duration, ",
			"class_size, lecturer_id, ", "title, release_date, ", "category_id, current_enroll_no, ", "image_suffix)",
			"values (#{id,jdbcType=BIGINT}, #{createTime,jdbcType=TIMESTAMP}, ",
			"#{updateTime,jdbcType=TIMESTAMP}, #{brief,jdbcType=VARCHAR}, ",
			"#{description,jdbcType=VARCHAR}, #{duration,jdbcType=DOUBLE}, ",
			"#{classSize,jdbcType=INTEGER}, #{lecturerId,jdbcType=BIGINT}, ",
			"#{title,jdbcType=VARCHAR}, #{releaseDate,jdbcType=TIMESTAMP}, ",
			"#{categoryId,jdbcType=BIGINT}, #{currentEnrollNo,jdbcType=INTEGER}, ",
			"#{imageSuffix,jdbcType=VARCHAR})" })
	int insert(Course record);

	@InsertProvider(type = CourseSqlProvider.class, method = "insertSelective")
	int insertSelective(Course record);

	@SelectProvider(type = CourseSqlProvider.class, method = "selectByExample")
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT),
			@Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "brief", property = "brief", jdbcType = JdbcType.VARCHAR),
			@Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
			@Result(column = "duration", property = "duration", jdbcType = JdbcType.DOUBLE),
			@Result(column = "class_size", property = "classSize", jdbcType = JdbcType.INTEGER),
			@Result(column = "lecturer_id", property = "lecturerId", jdbcType = JdbcType.BIGINT),
			@Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
			@Result(column = "release_date", property = "releaseDate", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "category_id", property = "categoryId", jdbcType = JdbcType.BIGINT),
			@Result(column = "current_enroll_no", property = "currentEnrollNo", jdbcType = JdbcType.INTEGER),
			@Result(column = "image_suffix", property = "imageSuffix", jdbcType = JdbcType.VARCHAR) })
	List<Course> selectByExampleWithRowbounds(CourseExample example, RowBounds rowBounds);

	@SelectProvider(type = CourseSqlProvider.class, method = "selectByExample")
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT),
			@Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "brief", property = "brief", jdbcType = JdbcType.VARCHAR),
			@Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
			@Result(column = "duration", property = "duration", jdbcType = JdbcType.DOUBLE),
			@Result(column = "class_size", property = "classSize", jdbcType = JdbcType.INTEGER),
			@Result(column = "lecturer_id", property = "lecturerId", jdbcType = JdbcType.BIGINT),
			@Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
			@Result(column = "release_date", property = "releaseDate", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "category_id", property = "categoryId", jdbcType = JdbcType.BIGINT),
			@Result(column = "current_enroll_no", property = "currentEnrollNo", jdbcType = JdbcType.INTEGER),
			@Result(column = "image_suffix", property = "imageSuffix", jdbcType = JdbcType.VARCHAR) })
	List<Course> selectByExample(CourseExample example);

	@UpdateProvider(type = CourseSqlProvider.class, method = "updateByExampleSelective")
	int updateByExampleSelective(@Param("record") Course record, @Param("example") CourseExample example);

	@UpdateProvider(type = CourseSqlProvider.class, method = "updateByExample")
	int updateByExample(@Param("record") Course record, @Param("example") CourseExample example);

	@Update({ "update so_course", "set create_time = #{createTime,jdbcType=TIMESTAMP},",
			"update_time = #{updateTime,jdbcType=TIMESTAMP},", "brief = #{brief,jdbcType=VARCHAR},",
			"description = #{description,jdbcType=VARCHAR},", "duration = #{duration,jdbcType=DOUBLE},",
			"class_size = #{classSize,jdbcType=INTEGER},", "lecturer_id = #{lecturerId,jdbcType=BIGINT},",
			"title = #{title,jdbcType=VARCHAR},", "release_date = #{releaseDate,jdbcType=TIMESTAMP},",
			"category_id = #{categoryId,jdbcType=BIGINT},", "current_enroll_no = #{currentEnrollNo,jdbcType=INTEGER},",
			"image_suffix = #{imageSuffix,jdbcType=VARCHAR}", "where id = #{id,jdbcType=BIGINT}" })
	int updateByPrimaryKey(Course record);

	/**
	 * 查询展示的课程
	 * 
	 * @return
	 */
	@Select({ "select", "id, create_time, update_time, brief, description, duration, class_size, lecturer_id, ",
			"title, release_date, category_id, current_enroll_no, image_suffix", "from so_course",
			"order by update_time desc limit 4" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "brief", property = "brief", jdbcType = JdbcType.VARCHAR),
			@Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
			@Result(column = "duration", property = "duration", jdbcType = JdbcType.DOUBLE),
			@Result(column = "class_size", property = "classSize", jdbcType = JdbcType.INTEGER),
			@Result(column = "lecturer_id", property = "lecturerId", jdbcType = JdbcType.BIGINT),
			@Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
			@Result(column = "release_date", property = "releaseDate", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "category_id", property = "categoryId", jdbcType = JdbcType.BIGINT),
			@Result(column = "current_enroll_no", property = "currentEnrollNo", jdbcType = JdbcType.INTEGER),
			@Result(column = "image_suffix", property = "imageSuffix", jdbcType = JdbcType.VARCHAR) })
	List<Course> getHomePageCourses();

	@Select({ "select", "id, create_time, update_time, brief, description, duration, class_size, lecturer_id, ",
			"title, release_date, category_id, current_enroll_no, image_suffix", "from so_course",
			"where id = #{id,jdbcType=BIGINT}" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "brief", property = "brief", jdbcType = JdbcType.VARCHAR),
			@Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
			@Result(column = "duration", property = "duration", jdbcType = JdbcType.DOUBLE),
			@Result(column = "class_size", property = "classSize", jdbcType = JdbcType.INTEGER),
			@Result(column = "lecturer_id", property = "lecturerId", jdbcType = JdbcType.BIGINT),
			@Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
			@Result(column = "release_date", property = "releaseDate", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "category_id", property = "categoryId", jdbcType = JdbcType.BIGINT),
			@Result(column = "current_enroll_no", property = "currentEnrollNo", jdbcType = JdbcType.INTEGER),
			@Result(column = "image_suffix", property = "imageSuffix", jdbcType = JdbcType.VARCHAR) })
	Course selectByPrimaryKey(Long id);

	/**
	 * 按类别获取课程
	 *
	 * @param limit      获取数据条数限制，-1 ： 代表不限制
	 * @param categoryId 类别ID，-1：代表获取所有类别。
	 * @return
	 */
	@Select({ "<script>select", "id, create_time, update_time, brief, description, duration, class_size, lecturer_id, ",
			"title, release_date, category_id, current_enroll_no, image_suffix", "from so_course",
			"<if test='categoryId>0'>where category_id = #{categoryId}</if>",
			"<if test='limit>0'>limit #{limit}</if></script>", })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "brief", property = "brief", jdbcType = JdbcType.VARCHAR),
			@Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
			@Result(column = "duration", property = "duration", jdbcType = JdbcType.DOUBLE),
			@Result(column = "class_size", property = "classSize", jdbcType = JdbcType.INTEGER),
			@Result(column = "lecturer_id", property = "lecturerId", jdbcType = JdbcType.BIGINT),
			@Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
			@Result(column = "release_date", property = "releaseDate", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "category_id", property = "categoryId", jdbcType = JdbcType.BIGINT),
			@Result(column = "current_enroll_no", property = "currentEnrollNo", jdbcType = JdbcType.INTEGER),
			@Result(column = "image_suffix", property = "imageSuffix", jdbcType = JdbcType.VARCHAR) })
	Page<Course> getCourses(@Param("limit") int limit, @Param("categoryId") long categoryId);

	@Select({ "<script>select",
			"c.id as 'id',  c.brief, c.description, c.duration, c.class_size as 'classSize',c.category_id as 'categoryId',",
			"cc.name as 'courseCategory.name', cc.id as 'courseCategory.id',",
			"t.name as 'teacher.name', t.title as 'teacher.title',t.id as 'teacher.id',t.image_suffix as 'teacher.imageSuffix',",
			"c.title, c.current_enroll_no as 'currentEnrollNo', c.image_suffix as 'imageSuffix'",
			"from so_course c inner join so_course_category cc on c.category_id = cc.id",
			"inner join so_teacher t on c.lecturer_id = t.id", "where c.id = #{courseId}", "</script>" })
	// 配置子查询
	@Results({
			@Result(property = "courseChapterList", javaType = List.class, column = "id", many = @Many(select = "getChaptersByCourseId")),
			@Result(property = "id", column = "id", jdbcType = JdbcType.BIGINT)
			// 因为ID作为子查询的关联字段，所以要在单独配置一次
	})
	Course getCourseById(@Param("courseId") long courseId);

	// 获取课程章节列表
	@Select({ "select", "id, create_time, update_time, title ,brief ,course_id", "from so_course_chapter",
			"where course_id = #{chapterId} order by chapter_index asc" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
			@Result(column = "brief", property = "brief", jdbcType = JdbcType.VARCHAR),
			@Result(column = "course_id", property = "courseId", jdbcType = JdbcType.BIGINT) })
	CourseChapter getChaptersByCourseId(@Param("chapterId") Long chapterId);

	// 根据课程类别获取教师列表
	@Select({ "select distinct t.id, t.name, t.image_suffix as 'imageSuffix',t.title", "from so_teacher t",
			"inner join so_course c where c.lecturer_id = t.id", "and c.category_id = #{courseCategoryId}", "limit 3" })
	List<Teacher> geTeachersByCourseCategory(@Param("courseCategoryId") long courseCategoryId);

	/**
	 * 获取关联课程
	 *
	 * @param currentId  当前课程的id
	 * @param categoryId 课程类别id
	 * @return
	 */
	@Select({ "<script>select", "id, create_time, update_time, brief, description, duration, class_size, lecturer_id, ",
			"title, release_date, category_id, current_enroll_no, image_suffix", "from so_course",
			"where category_id = #{categoryId}", "and id != #{currentId}", " limit 3</script>" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "brief", property = "brief", jdbcType = JdbcType.VARCHAR),
			@Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR),
			@Result(column = "duration", property = "duration", jdbcType = JdbcType.DOUBLE),
			@Result(column = "class_size", property = "classSize", jdbcType = JdbcType.INTEGER),
			@Result(column = "lecturer_id", property = "lecturerId", jdbcType = JdbcType.BIGINT),
			@Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
			@Result(column = "release_date", property = "releaseDate", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "category_id", property = "categoryId", jdbcType = JdbcType.BIGINT),
			@Result(column = "current_enroll_no", property = "currentEnrollNo", jdbcType = JdbcType.INTEGER),
			@Result(column = "image_suffix", property = "imageSuffix", jdbcType = JdbcType.VARCHAR) })
	Page<Course> getRelatedCourse(@Param("currentId") long currentId, @Param("categoryId") long categoryId);

}