package hejairi.sample.spring.mybatis.module;

import hejairi.sample.spring.mybatis.core.mybatis.Mapper;
import hejairi.sample.spring.mybatis.model.User;

import java.util.List;

/**
 * Created by litlhope on 2014. 4. 15..
 */
@Mapper
public interface UserMapper {
	public User findById(long id);
	public List<User> findAll();
	public void insert(User user);
	public void update(User user);
}
