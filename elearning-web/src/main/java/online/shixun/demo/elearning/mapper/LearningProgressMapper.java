package online.shixun.demo.elearning.mapper;

import java.util.List;
import online.shixun.demo.elearning.dto.LearningProgress;
import online.shixun.demo.elearning.dto.LearningProgressExample;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;

public interface LearningProgressMapper {
    @SelectProvider(type=LearningProgressSqlProvider.class, method="countByExample")
    long countByExample(LearningProgressExample example);

    @DeleteProvider(type=LearningProgressSqlProvider.class, method="deleteByExample")
    int deleteByExample(LearningProgressExample example);

    @Insert({
        "insert into so_learning_progress (create_time, update_time, ",
        "id, course_id, chapter_id, ",
        "student_id)",
        "values (#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{id,jdbcType=BIGINT}, #{courseId,jdbcType=BIGINT}, #{chapterId,jdbcType=BIGINT}, ",
        "#{studentId,jdbcType=BIGINT})"
    })
    int insert(LearningProgress record);

    @InsertProvider(type=LearningProgressSqlProvider.class, method="insertSelective")
    int insertSelective(LearningProgress record);

    @SelectProvider(type=LearningProgressSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT),
        @Result(column="course_id", property="courseId", jdbcType=JdbcType.BIGINT),
        @Result(column="chapter_id", property="chapterId", jdbcType=JdbcType.BIGINT),
        @Result(column="student_id", property="studentId", jdbcType=JdbcType.BIGINT)
    })
    List<LearningProgress> selectByExampleWithRowbounds(LearningProgressExample example, RowBounds rowBounds);

    @SelectProvider(type=LearningProgressSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT),
        @Result(column="course_id", property="courseId", jdbcType=JdbcType.BIGINT),
        @Result(column="chapter_id", property="chapterId", jdbcType=JdbcType.BIGINT),
        @Result(column="student_id", property="studentId", jdbcType=JdbcType.BIGINT)
    })
    List<LearningProgress> selectByExample(LearningProgressExample example);

    @UpdateProvider(type=LearningProgressSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") LearningProgress record, @Param("example") LearningProgressExample example);

    @UpdateProvider(type=LearningProgressSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") LearningProgress record, @Param("example") LearningProgressExample example);
    
    /**
     * 检查用户的该课程的学习进度是否为空
     *
     * @param studentId
     * @param courseId
     * @return
     */
    @Select({
            "select id from so_learning_progress where student_id = #{studentId} and course_id = #{courseId}"
    })
    Long ifProgressExits(@Param("studentId") long studentId, @Param("courseId") long courseId);
    
    /**
     * 获取课程章节序号，用于更新进度时判断用户是否学习了进度之后的章节。
     *
     * @param chapterId
     * @return
     */
    @Select({
            "select chapter_index from so_course_chapter where id = #{chapterId}"
    })
    int getChapterIndexByChapterId(@Param("chapterId") long chapterId);
    
    /**
     * 更新用户的学习进度，如果当前章节序号小于进度中的学习进度，则只更新"进度更新时间"字段
     *
     * @param record
     * @return
     */
    @Update({
            "update so_learning_progress l inner join so_course c on l.course_id = c.id",
            "inner join so_course_chapter cc on c.id = cc.course_id and l.chapter_id = cc.id",
            "set l.update_time = now() , l.chapter_id  = case when #{chapterIndex} > cc.chapter_index then #{record.chapterId} else l.chapter_id end",
            "where l.student_id = #{record.studentId}"
    })
    int updateLearningProgress(@Param("record") LearningProgress record, @Param("chapterIndex") long chapterIndex);
    
    /**
     * 查询用户所有课程的学习进度
     * @param studentId
     */
    @Select({
        "select  c.id as 'course.id',c.image_suffix as 'course.imageSuffix',c.title as 'course.title', ",
        "cc.chapter_index/(select count(id) from so_course_chapter where course_id = l.course_id) as 'progress',",
        "cc.title as 'chapterTitle', l.update_time as 'updateTime'",
        "from so_learning_progress l inner join so_course c on l.course_id = c.id",
        "inner join so_course_chapter cc on c.id = cc.course_id and l.chapter_id = cc.id",
        "where #{studentId} = l.student_id"
})
List<LearningProgress> getUserLearningProgresses(@Param("studentId") long studentId);
    
    /**
     * 获取用户学习课程的总数量
     *
     * @param studentId
     * @return
     */
    @Select({
            "select count(id) from so_learning_progress where student_id = #{studentId}"
    })
    int getUserLearnedCourseCount(@Param("studentId") long studentId);
    
}