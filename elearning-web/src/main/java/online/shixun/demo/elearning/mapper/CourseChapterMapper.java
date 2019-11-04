package online.shixun.demo.elearning.mapper;

import java.util.List;
import online.shixun.demo.elearning.dto.CourseChapter;
import online.shixun.demo.elearning.dto.CourseChapterExample;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;

public interface CourseChapterMapper {
    @SelectProvider(type=CourseChapterSqlProvider.class, method="countByExample")
    long countByExample(CourseChapterExample example);

    @DeleteProvider(type=CourseChapterSqlProvider.class, method="deleteByExample")
    int deleteByExample(CourseChapterExample example);

    @Insert({
        "insert into so_course_chapter (create_time, update_time, ",
        "id, title, brief, ",
        "course_id, duration, ",
        "chapter_index)",
        "values (#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{id,jdbcType=BIGINT}, #{title,jdbcType=VARCHAR}, #{brief,jdbcType=VARCHAR}, ",
        "#{courseId,jdbcType=BIGINT}, #{duration,jdbcType=DECIMAL}, ",
        "#{chapterIndex,jdbcType=INTEGER})"
    })
    int insert(CourseChapter record);

    @InsertProvider(type=CourseChapterSqlProvider.class, method="insertSelective")
    int insertSelective(CourseChapter record);

    @SelectProvider(type=CourseChapterSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="brief", property="brief", jdbcType=JdbcType.VARCHAR),
        @Result(column="course_id", property="courseId", jdbcType=JdbcType.BIGINT),
        @Result(column="duration", property="duration", jdbcType=JdbcType.DECIMAL),
        @Result(column="chapter_index", property="chapterIndex", jdbcType=JdbcType.INTEGER)
    })
    List<CourseChapter> selectByExampleWithRowbounds(CourseChapterExample example, RowBounds rowBounds);

    @SelectProvider(type=CourseChapterSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="brief", property="brief", jdbcType=JdbcType.VARCHAR),
        @Result(column="course_id", property="courseId", jdbcType=JdbcType.BIGINT),
        @Result(column="duration", property="duration", jdbcType=JdbcType.DECIMAL),
        @Result(column="chapter_index", property="chapterIndex", jdbcType=JdbcType.INTEGER)
    })
    List<CourseChapter> selectByExample(CourseChapterExample example);

    @UpdateProvider(type=CourseChapterSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") CourseChapter record, @Param("example") CourseChapterExample example);

    @UpdateProvider(type=CourseChapterSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") CourseChapter record, @Param("example") CourseChapterExample example);
    
    /**
     * 获取课程章节详情
     *
     * @param id
     * @return
     */
    @Select({
            "select",
            "id, create_time, update_time, title, brief, course_id, duration, chapter_index",
            "from so_course_chapter",
            "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "brief", property = "brief", jdbcType = JdbcType.VARCHAR),
            @Result(column = "course_id", property = "courseId", jdbcType = JdbcType.BIGINT),
            @Result(column = "duration", property = "duration", jdbcType = JdbcType.DECIMAL),
            @Result(column = "chapter_index", property = "chapterIndex", jdbcType = JdbcType.INTEGER)
    })
    CourseChapter selectByPrimaryKey(Long id);
}