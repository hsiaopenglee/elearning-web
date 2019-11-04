package online.shixun.demo.elearning.mapper;

import java.util.List;
import online.shixun.demo.elearning.dto.Teacher;
import online.shixun.demo.elearning.dto.TeacherExample;
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

import com.github.pagehelper.Page;

public interface TeacherMapper {
    @SelectProvider(type=TeacherSqlProvider.class, method="countByExample")
    long countByExample(TeacherExample example);

    @DeleteProvider(type=TeacherSqlProvider.class, method="deleteByExample")
    int deleteByExample(TeacherExample example);

    @Insert({
        "insert into so_teacher (id, create_time, ",
        "update_time, age, ",
        "birthday, name, sex, ",
        "email, phone, image_suffix, ",
        "title, address, ",
        "first_letter)",
        "values (#{id,jdbcType=BIGINT}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{age,jdbcType=BIGINT}, ",
        "#{birthday,jdbcType=DATE}, #{name,jdbcType=VARCHAR}, #{sex,jdbcType=VARCHAR}, ",
        "#{email,jdbcType=VARCHAR}, #{phone,jdbcType=VARCHAR}, #{imageSuffix,jdbcType=VARCHAR}, ",
        "#{title,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, ",
        "#{firstLetter,jdbcType=VARCHAR})"
    })
    int insert(Teacher record);

    @InsertProvider(type=TeacherSqlProvider.class, method="insertSelective")
    int insertSelective(Teacher record);

    @SelectProvider(type=TeacherSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="age", property="age", jdbcType=JdbcType.BIGINT),
        @Result(column="birthday", property="birthday", jdbcType=JdbcType.DATE),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex", property="sex", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="image_suffix", property="imageSuffix", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="first_letter", property="firstLetter", jdbcType=JdbcType.VARCHAR)
    })
    List<Teacher> selectByExampleWithRowbounds(TeacherExample example, RowBounds rowBounds);

    @SelectProvider(type=TeacherSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="age", property="age", jdbcType=JdbcType.BIGINT),
        @Result(column="birthday", property="birthday", jdbcType=JdbcType.DATE),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex", property="sex", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="image_suffix", property="imageSuffix", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="first_letter", property="firstLetter", jdbcType=JdbcType.VARCHAR)
    })
    List<Teacher> selectByExample(TeacherExample example);

    @UpdateProvider(type=TeacherSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Teacher record, @Param("example") TeacherExample example);

    @UpdateProvider(type=TeacherSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Teacher record, @Param("example") TeacherExample example);
    
    /**
     * 根据课程类别获取教师列表
     *
     * @param courseCategoryId
     * @return
     */
    @Select({
            "select distinct t.id, t.name, t.image_suffix as 'imageSuffix',t.title",
            "from so_teacher t",
            "inner join so_course c where c.lecturer_id = t.id",
            "and c.category_id = #{courseCategoryId}",
            "limit 3"

    })
    List<Teacher> getTeachersByCourseCategory(@Param("courseCategoryId") long courseCategoryId);
    
    /**
     * 按教师姓氏首字母查找讲师信息
     * @param firstLetter 姓氏首字母，all代表不限
     * @return
     */
    @Select({
    	"<script>select",
    	"id, create_time, update_time, age, birthday, name, sex, email, phone, image_suffix,",
    	"title, address, first_letter",
    	"from so_teacher",
    	"<if test= 'firstLetter !=\"all\"'>where first_letter = #{firstLetter}</if>",
    	"</script>"
    })
   @Results({
	   @Result(column = "id",property = "id",jdbcType = JdbcType.BIGINT, id = true),
	   @Result(column = "create_time",property = "create_time",jdbcType = JdbcType.TIMESTAMP ),
	   @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
       @Result(column = "age", property = "age", jdbcType = JdbcType.BIGINT),
       @Result(column = "birthday", property = "birthday", jdbcType = JdbcType.DATE),
       @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
       @Result(column = "sex", property = "sex", jdbcType = JdbcType.VARCHAR),
       @Result(column = "email", property = "email", jdbcType = JdbcType.VARCHAR),
       @Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
       @Result(column = "image_suffix", property = "imageSuffix", jdbcType = JdbcType.VARCHAR),
       @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
       @Result(column = "address", property = "address", jdbcType = JdbcType.VARCHAR),
       @Result(column = "first_letter", property = "firstLetter", jdbcType = JdbcType.VARCHAR)
   })
    Page<Teacher> getTeachersByFirstLetter(@Param("firstLetter") String firstLetter);
    
}