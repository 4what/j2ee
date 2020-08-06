package sample.mybatis.mapper;

import sample.mybatis.domain.Pojo;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PojoMapper {
	long count();

	int create(/*@Param("pojo")*/ Pojo pojo);

	void delete(Pojo pojo);

	//@Select("SELECT * FROM pojo WHERE id = #{id}")
	Pojo get(int id);

	List<Pojo> list();

	void update(Pojo pojo);


	/**/
	List<Pojo> find(@Param("id") int id);
}
