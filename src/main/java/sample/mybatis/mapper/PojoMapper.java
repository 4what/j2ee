package sample.mybatis.mapper;

import org.apache.ibatis.annotations.*;
import sample.mybatis.domain.Pojo;

import java.util.List;

public interface PojoMapper {
	//@Select({"SELECT COUNT(*) FROM pojo"})
	long count();

	//@Options(useGeneratedKeys = true, keyProperty = "id")
	//@Insert({"INSERT INTO pojo (date) VALUES (#{date})"})
	int create(/*@Param("pojo")*/ Pojo pojo);

	//@Delete({"DELETE FROM pojo WHERE id = #{id}"})
	void delete(Pojo pojo);

	//@Select({"SELECT * FROM pojo WHERE id = #{id}"})
	Pojo get(int id);

	//@Select({"SELECT * FROM pojo"})
	List<Pojo> list();

	//@Update({"UPDATE pojo SET date = #{date} WHERE id = #{id}"})
	void update(Pojo pojo);
}
