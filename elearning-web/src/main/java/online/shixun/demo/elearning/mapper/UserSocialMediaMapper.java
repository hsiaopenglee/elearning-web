package online.shixun.demo.elearning.mapper;

import java.util.List;
import online.shixun.demo.elearning.dto.UserSocialMedia;
import online.shixun.demo.elearning.dto.UserSocialMediaExample;
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

public interface UserSocialMediaMapper {
    @SelectProvider(type=UserSocialMediaSqlProvider.class, method="countByExample")
    long countByExample(UserSocialMediaExample example);

    @DeleteProvider(type=UserSocialMediaSqlProvider.class, method="deleteByExample")
    int deleteByExample(UserSocialMediaExample example);

    @Insert({
        "insert into so_user_social_media (id, create_time, ",
        "update_time, user_id, ",
        "account, account_type)",
        "values (#{id,jdbcType=BIGINT}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP}, #{userId,jdbcType=BIGINT}, ",
        "#{account,jdbcType=VARCHAR}, #{accountType,jdbcType=BIGINT})"
    })
    int insert(UserSocialMedia record);

    @InsertProvider(type=UserSocialMediaSqlProvider.class, method="insertSelective")
    int insertSelective(UserSocialMedia record);

    @SelectProvider(type=UserSocialMediaSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="account", property="account", jdbcType=JdbcType.VARCHAR),
        @Result(column="account_type", property="accountType", jdbcType=JdbcType.BIGINT)
    })
    List<UserSocialMedia> selectByExampleWithRowbounds(UserSocialMediaExample example, RowBounds rowBounds);

    @SelectProvider(type=UserSocialMediaSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="account", property="account", jdbcType=JdbcType.VARCHAR),
        @Result(column="account_type", property="accountType", jdbcType=JdbcType.BIGINT)
    })
    List<UserSocialMedia> selectByExample(UserSocialMediaExample example);

    @UpdateProvider(type=UserSocialMediaSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UserSocialMedia record, @Param("example") UserSocialMediaExample example);

    @UpdateProvider(type=UserSocialMediaSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UserSocialMedia record, @Param("example") UserSocialMediaExample example);
}