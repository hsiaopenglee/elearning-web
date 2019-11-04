package online.shixun.demo.elearning.mapper;

import java.util.List;
import online.shixun.demo.elearning.dto.Blog;
import online.shixun.demo.elearning.dto.BlogExample;
import online.shixun.demo.elearning.dto.BlogWithBLOBs;
import org.apache.ibatis.annotations.Delete;
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

import com.github.pagehelper.Page;

public interface BlogMapper {
    @SelectProvider(type=BlogSqlProvider.class, method="countByExample")
    long countByExample(BlogExample example);

    @DeleteProvider(type=BlogSqlProvider.class, method="deleteByExample")
    int deleteByExample(BlogExample example);

    @Delete({
        "delete from so_blog",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into so_blog (id, create_time, ",
        "update_time, brief, ",
        "image_suffix, title, ",
        "release_date, category, ",
        "author, description, ",
        "content)",
        "values (#{id,jdbcType=BIGINT}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{brief,jdbcType=VARCHAR}, ",
        "#{imageSuffix,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, ",
        "#{releaseDate,jdbcType=TIMESTAMP}, #{category,jdbcType=BIGINT}, ",
        "#{author,jdbcType=BIGINT}, #{description,jdbcType=LONGVARCHAR}, ",
        "#{content,jdbcType=LONGVARCHAR})"
    })
    int insert(BlogWithBLOBs record);

    @InsertProvider(type=BlogSqlProvider.class, method="insertSelective")
    int insertSelective(BlogWithBLOBs record);

    @SelectProvider(type=BlogSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="brief", property="brief", jdbcType=JdbcType.VARCHAR),
        @Result(column="image_suffix", property="imageSuffix", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="release_date", property="releaseDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="category", property="category", jdbcType=JdbcType.BIGINT),
        @Result(column="author", property="author", jdbcType=JdbcType.BIGINT),
        @Result(column="description", property="description", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<BlogWithBLOBs> selectByExampleWithBLOBsWithRowbounds(BlogExample example, RowBounds rowBounds);

    @SelectProvider(type=BlogSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="brief", property="brief", jdbcType=JdbcType.VARCHAR),
        @Result(column="image_suffix", property="imageSuffix", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="release_date", property="releaseDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="category", property="category", jdbcType=JdbcType.BIGINT),
        @Result(column="author", property="author", jdbcType=JdbcType.BIGINT),
        @Result(column="description", property="description", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<BlogWithBLOBs> selectByExampleWithBLOBs(BlogExample example);

    @SelectProvider(type=BlogSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="brief", property="brief", jdbcType=JdbcType.VARCHAR),
        @Result(column="image_suffix", property="imageSuffix", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="release_date", property="releaseDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="category", property="category", jdbcType=JdbcType.BIGINT),
        @Result(column="author", property="author", jdbcType=JdbcType.BIGINT)
    })
    List<Blog> selectByExampleWithRowbounds(BlogExample example, RowBounds rowBounds);

    @SelectProvider(type=BlogSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="brief", property="brief", jdbcType=JdbcType.VARCHAR),
        @Result(column="image_suffix", property="imageSuffix", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="release_date", property="releaseDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="category", property="category", jdbcType=JdbcType.BIGINT),
        @Result(column="author", property="author", jdbcType=JdbcType.BIGINT)
    })
    List<Blog> selectByExample(BlogExample example);


    @UpdateProvider(type=BlogSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") BlogWithBLOBs record, @Param("example") BlogExample example);

    @UpdateProvider(type=BlogSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") BlogWithBLOBs record, @Param("example") BlogExample example);

    @UpdateProvider(type=BlogSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Blog record, @Param("example") BlogExample example);

    @UpdateProvider(type=BlogSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(BlogWithBLOBs record);

    @Update({
        "update so_blog",
        "set create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "brief = #{brief,jdbcType=VARCHAR},",
          "image_suffix = #{imageSuffix,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "release_date = #{releaseDate,jdbcType=TIMESTAMP},",
          "category = #{category,jdbcType=BIGINT},",
          "author = #{author,jdbcType=BIGINT},",
          "description = #{description,jdbcType=LONGVARCHAR},",
          "content = #{content,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKeyWithBLOBs(BlogWithBLOBs record);

    @Update({
        "update so_blog",
        "set create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "brief = #{brief,jdbcType=VARCHAR},",
          "image_suffix = #{imageSuffix,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "release_date = #{releaseDate,jdbcType=TIMESTAMP},",
          "category = #{category,jdbcType=BIGINT},",
          "author = #{author,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Blog record);
    
    /**
     * 获取首页展示的博客
     * 
     * @return
     */
    @Select({
        "select",
        "id, create_time, update_time, brief, image_suffix, title, release_date, category, ",
        "author, description, content",
        "from so_blog",
        "order by update_time limit 3"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="brief", property="brief", jdbcType=JdbcType.VARCHAR),
        @Result(column="image_suffix", property="imageSuffix", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="release_date", property="releaseDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="category", property="category", jdbcType=JdbcType.BIGINT),
        @Result(column="author", property="author", jdbcType=JdbcType.BIGINT),
        @Result(column="description", property="description", jdbcType=JdbcType.LONGVARCHAR),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<BlogWithBLOBs> getHomePageBlogs();
    
    /**
     * 获取博客详情
     *
     * @param id 博客ID
     * @return
     */
    @Select({
            "select",
            "b.id, b.create_time as createTime, b.update_time as updateTime, b.brief, b.image_suffix as imageSuffix, b.title, b.release_date releaseDate, ",
            "b.category, b.description, b.content, t.id as 'teacher.id',t.title as 'teacher.title', t.name as 'teacher.name', t.image_suffix as 'teacher.imageSuffix' ",
            "from so_blog b inner join so_teacher t on b.author = t.id",
            "where b.id = #{id,jdbcType=BIGINT}"
    })
    BlogWithBLOBs selectByPrimaryKey(Long id);

    /**
     * 按类别获取博客
     *
     * @param categoryId 类别ID， 如果 <0 则代表获取所有
     * @return 分页的博客
     */
    @Select({
            "<script>select",
            "id, create_time, update_time, brief, image_suffix, title, release_date, ",
            "category, description, content",
            "from so_blog",
            "<if test='categoryId>0'>where category = #{categoryId}</if>",
            "</script>"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "brief", property = "brief", jdbcType = JdbcType.VARCHAR),
            @Result(column = "image_suffix", property = "imageSuffix", jdbcType = JdbcType.VARCHAR),
            @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
            @Result(column = "release_date", property = "releaseDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "category", property = "category", jdbcType = JdbcType.BIGINT),
            @Result(column = "description", property = "description", jdbcType = JdbcType.LONGVARCHAR),
            @Result(column = "content", property = "content", jdbcType = JdbcType.LONGVARCHAR)
    })
    Page<BlogWithBLOBs> getBlogByCategory(@Param("categoryId") long categoryId);


    /**
     * 获取关联博客，不包括此博客本身
     *
     * @param blogCategoryId 博客分类ID
     * @param blogId 此博客ID
     * @return
     */
    @Select({"select id ,title,brief,image_suffix as 'imageSuffix' from so_blog where category = #{blogCategoryId} and id != #{blogId} limit 3",
    })
    List<BlogWithBLOBs> getRelatedBlogs(@Param("blogCategoryId") long blogCategoryId, @Param("blogId") long blogId);
}