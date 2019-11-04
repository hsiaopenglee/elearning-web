package online.shixun.demo.elearning.mapper;

import java.util.List;
import online.shixun.demo.elearning.dto.Dictionary;
import online.shixun.demo.elearning.dto.DictionaryExample;
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

public interface DictionaryMapper {
    @SelectProvider(type=DictionarySqlProvider.class, method="countByExample")
    long countByExample(DictionaryExample example);

    @DeleteProvider(type=DictionarySqlProvider.class, method="deleteByExample")
    int deleteByExample(DictionaryExample example);

    @Insert({
        "insert into so_dictionary (create_time, update_time, ",
        "id, type, type_code, ",
        "description)",
        "values (#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{id,jdbcType=BIGINT}, #{type,jdbcType=VARCHAR}, #{typeCode,jdbcType=VARCHAR}, ",
        "#{description,jdbcType=VARCHAR})"
    })
    int insert(Dictionary record);

    @InsertProvider(type=DictionarySqlProvider.class, method="insertSelective")
    int insertSelective(Dictionary record);

    @SelectProvider(type=DictionarySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="type_code", property="typeCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR)
    })
    List<Dictionary> selectByExampleWithRowbounds(DictionaryExample example, RowBounds rowBounds);

    @SelectProvider(type=DictionarySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="id", property="id", jdbcType=JdbcType.BIGINT),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="type_code", property="typeCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR)
    })
    List<Dictionary> selectByExample(DictionaryExample example);

    @UpdateProvider(type=DictionarySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Dictionary record, @Param("example") DictionaryExample example);

    @UpdateProvider(type=DictionarySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Dictionary record, @Param("example") DictionaryExample example);
}