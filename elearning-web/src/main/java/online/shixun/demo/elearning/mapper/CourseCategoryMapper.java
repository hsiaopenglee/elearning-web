package online.shixun.demo.elearning.mapper;

import java.util.List;
import online.shixun.demo.elearning.dto.CourseCategory;
import online.shixun.demo.elearning.dto.CourseCategoryExample;
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

public interface CourseCategoryMapper {
    @SelectProvider(type=CourseCategorySqlProvider.class, method="countByExample")
    long countByExample(CourseCategoryExample example);

    @DeleteProvider(type=CourseCategorySqlProvider.class, method="deleteByExample")
    int deleteByExample(CourseCategoryExample example);

    @Insert({
        "insert into so_course_category (id, create_time, ",
        "update_time, description, ",
        "name)",
        "values (#{id,jdbcType=BIGINT}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{description,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR})"
    })
    int insert(CourseCategory record);

    @InsertProvider(type=CourseCategorySqlProvider.class, method="insertSelective")
    int insertSelective(CourseCategory record);

    @SelectProvider(type=CourseCategorySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR)
    })
    List<CourseCategory> selectByExampleWithRowbounds(CourseCategoryExample example, RowBounds rowBounds);

    @SelectProvider(type=CourseCategorySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR)
    })
    List<CourseCategory> selectByExample(CourseCategoryExample example);

    @UpdateProvider(type=CourseCategorySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") CourseCategory record, @Param("example") CourseCategoryExample example);

    @UpdateProvider(type=CourseCategorySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") CourseCategory record, @Param("example") CourseCategoryExample example);
    
    /**
     * 获取课程分类
     */
    @Select({
    	"select cc.name, cc.id, count(c.id) as 'count'",
    	"from so_course_category cc inner join so_course c on c.category_id = cc.id",
    	"group by cc.id,cc.name"
    })
    List<CourseCategory> getCourseCategories();
}