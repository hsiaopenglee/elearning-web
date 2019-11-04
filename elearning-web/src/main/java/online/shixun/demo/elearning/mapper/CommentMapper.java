package online.shixun.demo.elearning.mapper;

import java.util.List;
import online.shixun.demo.elearning.dto.Comment;
import online.shixun.demo.elearning.dto.CommentExample;
import online.shixun.demo.elearning.util.DictionaryConstant;

import org.apache.ibatis.annotations.Delete;
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

public interface CommentMapper {
    @SelectProvider(type=CommentSqlProvider.class, method="countByExample")
    long countByExample(CommentExample example);

    @DeleteProvider(type=CommentSqlProvider.class, method="deleteByExample")
    int deleteByExample(CommentExample example);

    @Delete({
        "delete from so_comment",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into so_comment (id, create_time, ",
        "update_time, content, ",
        "reference_id, reply_comment_id, ",
        "reply_user_id, student_id, ",
        "type_code)",
        "values (#{id,jdbcType=BIGINT}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{content,jdbcType=VARCHAR}, ",
        "#{referenceId,jdbcType=BIGINT}, #{replyCommentId,jdbcType=BIGINT}, ",
        "#{replyUserId,jdbcType=BIGINT}, #{studentId,jdbcType=BIGINT}, ",
        "#{typeCode,jdbcType=VARCHAR})"
    })
    int insert(Comment record);

    @InsertProvider(type=CommentSqlProvider.class, method="insertSelective")
    int insertSelective(Comment record);

    @SelectProvider(type=CommentSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="reference_id", property="referenceId", jdbcType=JdbcType.BIGINT),
        @Result(column="reply_comment_id", property="replyCommentId", jdbcType=JdbcType.BIGINT),
        @Result(column="reply_user_id", property="replyUserId", jdbcType=JdbcType.BIGINT),
        @Result(column="student_id", property="studentId", jdbcType=JdbcType.BIGINT),
        @Result(column="type_code", property="typeCode", jdbcType=JdbcType.VARCHAR)
    })
    List<Comment> selectByExampleWithRowbounds(CommentExample example, RowBounds rowBounds);

    @SelectProvider(type=CommentSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="reference_id", property="referenceId", jdbcType=JdbcType.BIGINT),
        @Result(column="reply_comment_id", property="replyCommentId", jdbcType=JdbcType.BIGINT),
        @Result(column="reply_user_id", property="replyUserId", jdbcType=JdbcType.BIGINT),
        @Result(column="student_id", property="studentId", jdbcType=JdbcType.BIGINT),
        @Result(column="type_code", property="typeCode", jdbcType=JdbcType.VARCHAR)
    })
    List<Comment> selectByExample(CommentExample example);

    @Select({
        "select",
        "id, create_time, update_time, content, reference_id, reply_comment_id, reply_user_id, ",
        "student_id, type_code",
        "from so_comment",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
        @Result(column="reference_id", property="referenceId", jdbcType=JdbcType.BIGINT),
        @Result(column="reply_comment_id", property="replyCommentId", jdbcType=JdbcType.BIGINT),
        @Result(column="reply_user_id", property="replyUserId", jdbcType=JdbcType.BIGINT),
        @Result(column="student_id", property="studentId", jdbcType=JdbcType.BIGINT),
        @Result(column="type_code", property="typeCode", jdbcType=JdbcType.VARCHAR)
    })
    Comment selectByPrimaryKey(Long id);

    @UpdateProvider(type=CommentSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Comment record, @Param("example") CommentExample example);

    @UpdateProvider(type=CommentSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Comment record, @Param("example") CommentExample example);

    @UpdateProvider(type=CommentSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Comment record);

    @Update({
        "update so_comment",
        "set create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "content = #{content,jdbcType=VARCHAR},",
          "reference_id = #{referenceId,jdbcType=BIGINT},",
          "reply_comment_id = #{replyCommentId,jdbcType=BIGINT},",
          "reply_user_id = #{replyUserId,jdbcType=BIGINT},",
          "student_id = #{studentId,jdbcType=BIGINT},",
          "type_code = #{typeCode,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Comment record);
    /**
     * 按条件查询评论
     *
     * @param typeCode  null表示不限
     * @param subjectId 评论对象id, 博客ID或者课程ID，小于0表示不限
     * @param replyId   被回复评论的ID 小于0表示不限
     * @return
     */
    @Select({
            "<script> select",
            "c.id, c.create_time, c.update_time, content, reference_id, reply_comment_id, ",
            "reply_user_id,  student_id, type_code, u.id as 'student.id', u.name as 'student.name',u.image_suffix as 'student.imageSuffix',",
            "user.name as replyUserName,user.image_suffix as replyUserImageSuffix",
            "from so_comment c inner join so_student u on c.student_id = u.id",
            "left join so_user user on c.reply_user_id = user.id",
            "where 1 = 1",
            "<if test='typeCode!=null'>and type_code = #{typeCode}</if>",
            "<if test='subjectId>-1'>and reference_id = #{subjectId}</if>",
            "<choose> <when test='replyId>0'>and reply_comment_id = #{replyId}</when>",
            "<otherwise> and reply_comment_id is null</otherwise> </choose>",
            "</script>"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "content", property = "content", jdbcType = JdbcType.VARCHAR),
            @Result(column = "reference_id", property = "referenceId", jdbcType = JdbcType.BIGINT),
            @Result(column = "reply_comment_id", property = "replyCommentId", jdbcType = JdbcType.BIGINT),
            @Result(column = "reply_user_id", property = "replyUserId", jdbcType = JdbcType.BIGINT),
            @Result(column = "student_id", property = "studentId", jdbcType = JdbcType.BIGINT),
            @Result(column = "type_code", property = "typeCode", jdbcType = JdbcType.VARCHAR)
    })
    List<Comment> getComments(@Param("typeCode") String typeCode, @Param("subjectId") Long subjectId, @Param("replyId") long replyId);

    /**
     * 获取评论数量
     *
     * @param typeCode 评论的类型
     * @param subjectId 评论关联的博客或者课程章节id
     * @return
     */
    @Select({
            "select count(id)",
            "from so_comment where type_code = #{typeCode} and reference_id = #{subjectId}"
    })
    int getSubjectCommentCount(@Param("typeCode") String typeCode, @Param("subjectId") Long subjectId);

    /**
     * 根据ID获取评论
     *
     * @param commentId 评论ID
     * @return
     */
    @Select({
            "<script> select",
            "c.id, c.create_time, c.update_time, content,  reference_id, reply_comment_id, ",
            "reply_user_id,  student_id, type_code, u.id as 'student.id', u.name as 'student.name',u.image_suffix as 'student.imageSuffix'",
            "from so_comment c inner join so_student u where c.student_id = u.id",
            "and c.id = #{commentId}",
            "</script>"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "content", property = "content", jdbcType = JdbcType.VARCHAR),
            @Result(column = "reference_id", property = "referenceId", jdbcType = JdbcType.BIGINT),
            @Result(column = "reply_comment_id", property = "replyCommentId", jdbcType = JdbcType.BIGINT),
            @Result(column = "reply_user_id", property = "replyUserId", jdbcType = JdbcType.BIGINT),
            @Result(column = "student_id", property = "studentId", jdbcType = JdbcType.BIGINT),
            @Result(column = "type_code", property = "typeCode", jdbcType = JdbcType.VARCHAR)
    })
    Comment getCommentById(@Param("commentId") long commentId);


    /**
     * 获取用户所有的评论
     *
     * @param studentId          用户ID
     * @param dictionaryConstant 评论类型
     * @return
     */
    @Select({
            "<script>",
            "select c.id, c.update_time as 'updateTime', c.content, c.reference_id as 'referenceId', c.type_code as 'typeCode',",
            "case when b.id is not null and c.type_code = 'COMMENT_TYPE_BLOG' then b.title else co.title end as 'replySubjectTitle'",
            "from so_comment c",
            "left join so_course_chapter co on co.id =  c.reference_Id",
            "left join so_blog b on b.id = c.reference_Id ",
            "where c.student_id = #{studentId} and  isnull(c.reply_comment_id)",
            "</script>"
    })
    @Results({
            @Result(property = "subCommentList", javaType = List.class, column = "id", many = @Many(select = "getSubCommentsByCommentId")),
            @Result(property = "id", javaType = Long.class, column = "id")
    })
    List<Comment> getCommentsByUserId(@Param("studentId") long studentId, DictionaryConstant dictionaryConstant);

    /**
     * 获取评论的子评论
     *
     * @param commentId 父评论的id
     * @return
     */
    @Select({
            "<script> select",
            "id, create_time, update_time, content,  reference_id, reply_comment_id, ",
            "reply_user_id,  student_id, type_code",
            "from so_comment where reply_comment_id = #{commentId}",
            "</script>"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "content", property = "content", jdbcType = JdbcType.VARCHAR),
            @Result(column = "reference_id", property = "referenceId", jdbcType = JdbcType.BIGINT),
            @Result(column = "reply_comment_id", property = "replyCommentId", jdbcType = JdbcType.BIGINT),
            @Result(column = "reply_user_id", property = "replyUserId", jdbcType = JdbcType.BIGINT),
            @Result(column = "student_id", property = "studentId", jdbcType = JdbcType.BIGINT),
            @Result(column = "type_code", property = "typeCode", jdbcType = JdbcType.VARCHAR)
    })
    List<Comment> getSubCommentsByCommentId(@Param("commentId") long commentId);
    
   
    
}