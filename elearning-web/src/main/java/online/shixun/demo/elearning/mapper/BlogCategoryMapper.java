package online.shixun.demo.elearning.mapper;

import java.util.List;
import online.shixun.demo.elearning.dto.BlogCategory;
import online.shixun.demo.elearning.dto.BlogCategoryExample;
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

public interface BlogCategoryMapper {
    @SelectProvider(type=BlogCategorySqlProvider.class, method="countByExample")
    long countByExample(BlogCategoryExample example);

    @DeleteProvider(type=BlogCategorySqlProvider.class, method="deleteByExample")
    int deleteByExample(BlogCategoryExample example);

    @Insert({
        "insert into so_blog_category (id, create_time, ",
        "update_time, description, ",
        "name)",
        "values (#{id,jdbcType=BIGINT}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{description,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR})"
    })
    int insert(BlogCategory record);

    @InsertProvider(type=BlogCategorySqlProvider.class, method="insertSelective")
    int insertSelective(BlogCategory record);

    @SelectProvider(type=BlogCategorySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR)
    })
    List<BlogCategory> selectByExampleWithRowbounds(BlogCategoryExample example, RowBounds rowBounds);

    @SelectProvider(type=BlogCategorySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR)
    })
    List<BlogCategory> selectByExample(BlogCategoryExample example);

    @UpdateProvider(type=BlogCategorySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") BlogCategory record, @Param("example") BlogCategoryExample example);

    @UpdateProvider(type=BlogCategorySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") BlogCategory record, @Param("example") BlogCategoryExample example);
    
    /**
     * 获取分类名称，以及此分类课程数量
     * @return
     */
    @Select({
    	"select",
    	"c.id, c.name, count(b.id) as 'blogCount'",
    	"from so_blog_category c inner join so_blog b on b.category = c.id",
    	"group by c.id, c.name"
    })
    List<BlogCategory> getBlogCountOfCategories();
    
    
    
}