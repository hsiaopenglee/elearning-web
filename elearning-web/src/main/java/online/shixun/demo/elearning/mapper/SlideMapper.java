package online.shixun.demo.elearning.mapper;

import java.util.List;
import online.shixun.demo.elearning.dto.Slide;
import online.shixun.demo.elearning.dto.SlideExample;
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

public interface SlideMapper {
    @SelectProvider(type=SlideSqlProvider.class, method="countByExample")
    long countByExample(SlideExample example);

    @DeleteProvider(type=SlideSqlProvider.class, method="deleteByExample")
    int deleteByExample(SlideExample example);

    @Insert({
        "insert into so_slide (create_time, update_time, ",
        "id, type_code, reference_id, ",
        "reference_url, title, ",
        "brief, image_suffix)",
        "values (#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{id,jdbcType=BIGINT}, #{typeCode,jdbcType=VARCHAR}, #{referenceId,jdbcType=BIGINT}, ",
        "#{referenceUrl,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, ",
        "#{brief,jdbcType=VARCHAR}, #{imageSuffix,jdbcType=VARCHAR})"
    })
    int insert(Slide record);

    @InsertProvider(type=SlideSqlProvider.class, method="insertSelective")
    int insertSelective(Slide record);

    @SelectProvider(type=SlideSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT),
        @Result(column="type_code", property="typeCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="reference_id", property="referenceId", jdbcType=JdbcType.BIGINT),
        @Result(column="reference_url", property="referenceUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="brief", property="brief", jdbcType=JdbcType.VARCHAR),
        @Result(column="image_suffix", property="imageSuffix", jdbcType=JdbcType.VARCHAR)
    })
    List<Slide> selectByExampleWithRowbounds(SlideExample example, RowBounds rowBounds);

    @SelectProvider(type=SlideSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT),
        @Result(column="type_code", property="typeCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="reference_id", property="referenceId", jdbcType=JdbcType.BIGINT),
        @Result(column="reference_url", property="referenceUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="brief", property="brief", jdbcType=JdbcType.VARCHAR),
        @Result(column="image_suffix", property="imageSuffix", jdbcType=JdbcType.VARCHAR)
    })
    List<Slide> selectByExample(SlideExample example);

    @UpdateProvider(type=SlideSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Slide record, @Param("example") SlideExample example);

    @UpdateProvider(type=SlideSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Slide record, @Param("example") SlideExample example);
    
    /**
     * 获取显示在首页轮播图列表
      *
      * @return
      */
     @Select({
             "select",
             "id, create_time, update_time, type_code, reference_id, reference_url, title, ",
             "brief, image_suffix",
             "from so_slide",
             "order by update_time desc limit 4"
     })
     @Results({
             @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
             @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
             @Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
             @Result(column = "type_code", property = "typeCode", jdbcType = JdbcType.VARCHAR),
             @Result(column = "reference_id", property = "referenceId", jdbcType = JdbcType.BIGINT),
             @Result(column = "reference_url", property = "referenceUrl", jdbcType = JdbcType.VARCHAR),
             @Result(column = "title", property = "title", jdbcType = JdbcType.VARCHAR),
             @Result(column = "brief", property = "brief", jdbcType = JdbcType.VARCHAR),
             @Result(column = "image_suffix", property = "imageSuffix", jdbcType = JdbcType.VARCHAR)
     })
     List<Slide> getHomePageSlides();
}