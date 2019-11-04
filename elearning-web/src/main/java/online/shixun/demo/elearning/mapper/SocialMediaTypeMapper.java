package online.shixun.demo.elearning.mapper;

import java.util.List;
import online.shixun.demo.elearning.dto.SocialMediaType;
import online.shixun.demo.elearning.dto.SocialMediaTypeExample;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.JdbcType;

public interface SocialMediaTypeMapper {
    @SelectProvider(type=SocialMediaTypeSqlProvider.class, method="countByExample")
    long countByExample(SocialMediaTypeExample example);

    @DeleteProvider(type=SocialMediaTypeSqlProvider.class, method="deleteByExample")
    int deleteByExample(SocialMediaTypeExample example);

    @Insert({
        "insert into so_social_media_type (id, create_time, ",
        "update_time, name, ",
        "icon)",
        "values (#{id,jdbcType=BIGINT}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{name,jdbcType=BIGINT}, ",
        "#{icon,jdbcType=VARCHAR})"
    })
    int insert(SocialMediaType record);

    @InsertProvider(type=SocialMediaTypeSqlProvider.class, method="insertSelective")
    int insertSelective(SocialMediaType record);

    @SelectProvider(type=SocialMediaTypeSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="name", property="name", jdbcType=JdbcType.BIGINT),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR)
    })
    List<SocialMediaType> selectByExampleWithRowbounds(SocialMediaTypeExample example, RowBounds rowBounds);

    @SelectProvider(type=SocialMediaTypeSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="name", property="name", jdbcType=JdbcType.BIGINT),
        @Result(column="icon", property="icon", jdbcType=JdbcType.VARCHAR)
    })
    List<SocialMediaType> selectByExample(SocialMediaTypeExample example);

    @UpdateProvider(type=SocialMediaTypeSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SocialMediaType record, @Param("example") SocialMediaTypeExample example);

    @UpdateProvider(type=SocialMediaTypeSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SocialMediaType record, @Param("example") SocialMediaTypeExample example);
}