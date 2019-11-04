package online.shixun.demo.elearning.mapper;

import java.util.List;
import online.shixun.demo.elearning.dto.Student;
import online.shixun.demo.elearning.dto.StudentExample;
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

public interface StudentMapper {
	@SelectProvider(type = StudentSqlProvider.class, method = "countByExample")
	long countByExample(StudentExample example);

	@DeleteProvider(type = StudentSqlProvider.class, method = "deleteByExample")
	int deleteByExample(StudentExample example);

	@Delete({ "delete from so_student", "where id = #{id,jdbcType=BIGINT}" })
	int deleteByPrimaryKey(Long id);

	@Insert({ "insert into so_student (id, create_time, ", "update_time, age, ", "birthday, disable, name, ",
			"password, sex, email, ", "phone, image_suffix, ", "role_id)",
			"values (#{id,jdbcType=BIGINT}, #{createTime,jdbcType=TIMESTAMP}, ",
			"#{updateTime,jdbcType=TIMESTAMP}, #{age,jdbcType=BIGINT}, ",
			"#{birthday,jdbcType=DATE}, #{disable,jdbcType=BIT}, #{name,jdbcType=VARCHAR}, ",
			"#{password,jdbcType=VARCHAR}, #{sex,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, ",
			"#{phone,jdbcType=VARCHAR}, #{imageSuffix,jdbcType=VARCHAR}, ", "#{roleId,jdbcType=VARCHAR})" })
	int insert(Student record);

	@InsertProvider(type = StudentSqlProvider.class, method = "insertSelective")
	int insertSelective(Student record);

	@SelectProvider(type = StudentSqlProvider.class, method = "selectByExample")
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "age", property = "age", jdbcType = JdbcType.BIGINT),
			@Result(column = "birthday", property = "birthday", jdbcType = JdbcType.DATE),
			@Result(column = "disable", property = "disable", jdbcType = JdbcType.BIT),
			@Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
			@Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
			@Result(column = "sex", property = "sex", jdbcType = JdbcType.VARCHAR),
			@Result(column = "email", property = "email", jdbcType = JdbcType.VARCHAR),
			@Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
			@Result(column = "image_suffix", property = "imageSuffix", jdbcType = JdbcType.VARCHAR),
			@Result(column = "role_id", property = "roleId", jdbcType = JdbcType.VARCHAR) })
	List<Student> selectByExampleWithRowbounds(StudentExample example, RowBounds rowBounds);

	@SelectProvider(type = StudentSqlProvider.class, method = "selectByExample")
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "age", property = "age", jdbcType = JdbcType.BIGINT),
			@Result(column = "birthday", property = "birthday", jdbcType = JdbcType.DATE),
			@Result(column = "disable", property = "disable", jdbcType = JdbcType.BIT),
			@Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
			@Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
			@Result(column = "sex", property = "sex", jdbcType = JdbcType.VARCHAR),
			@Result(column = "email", property = "email", jdbcType = JdbcType.VARCHAR),
			@Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
			@Result(column = "image_suffix", property = "imageSuffix", jdbcType = JdbcType.VARCHAR),
			@Result(column = "role_id", property = "roleId", jdbcType = JdbcType.VARCHAR) })
	List<Student> selectByExample(StudentExample example);

	@Select({ "select", "id, create_time, update_time, age, birthday, disable, name, password, sex, email, ",
			"phone, image_suffix, role_id", "from so_student", "where id = #{id,jdbcType=BIGINT}" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "age", property = "age", jdbcType = JdbcType.BIGINT),
			@Result(column = "birthday", property = "birthday", jdbcType = JdbcType.DATE),
			@Result(column = "disable", property = "disable", jdbcType = JdbcType.BIT),
			@Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
			@Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
			@Result(column = "sex", property = "sex", jdbcType = JdbcType.VARCHAR),
			@Result(column = "email", property = "email", jdbcType = JdbcType.VARCHAR),
			@Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
			@Result(column = "image_suffix", property = "imageSuffix", jdbcType = JdbcType.VARCHAR),
			@Result(column = "role_id", property = "roleId", jdbcType = JdbcType.VARCHAR) })
	Student selectByPrimaryKey(Long id);

	@UpdateProvider(type = StudentSqlProvider.class, method = "updateByExampleSelective")
	int updateByExampleSelective(@Param("record") Student record, @Param("example") StudentExample example);

	@UpdateProvider(type = StudentSqlProvider.class, method = "updateByExample")
	int updateByExample(@Param("record") Student record, @Param("example") StudentExample example);

	@UpdateProvider(type = StudentSqlProvider.class, method = "updateByPrimaryKeySelective")
	int updateByPrimaryKeySelective(Student record);

	@Update({
        "<script>update so_student",
        "set create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "age = #{age,jdbcType=BIGINT},",
          "birthday = #{birthday,jdbcType=DATE},",
          "disable = #{disable,jdbcType=BIT},",
          "name = #{name,jdbcType=VARCHAR},",
          "sex = #{sex,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "phone = #{phone,jdbcType=VARCHAR},",
          "<if test = '#{imageSuffix}!=null'>image_suffix = #{imageSuffix,jdbcType=VARCHAR},</if>",
          "role_id = #{roleId,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}</script>"
    })
    int updateByPrimaryKey(Student record);

	@Select({ "select", "id, create_time, update_time,  age, birthday, disable, name, password, ",
			"sex, email, phone, image_suffix", "from so_student", "where email = #{login}" })
	@Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.BIGINT, id = true),
			@Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "update_time", property = "updateTime", jdbcType = JdbcType.TIMESTAMP),
			@Result(column = "age", property = "age", jdbcType = JdbcType.BIGINT),
			@Result(column = "birthday", property = "birthday", jdbcType = JdbcType.DATE),
			@Result(column = "disable", property = "disable", jdbcType = JdbcType.BIT),
			@Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR),
			@Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
			@Result(column = "sex", property = "sex", jdbcType = JdbcType.VARCHAR),
			@Result(column = "email", property = "email", jdbcType = JdbcType.VARCHAR),
			@Result(column = "phone", property = "phone", jdbcType = JdbcType.VARCHAR),
			@Result(column = "image_suffix", property = "imageSuffix", jdbcType = JdbcType.VARCHAR) })
	Student loadUserByLogin(String login);

	@Select({ "select count(id) >0 as 'existed' from so_student  where email = #{email}" })
	boolean ifEmailExisted(@Param("email") String email);
}