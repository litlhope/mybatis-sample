package hejairi.sample.spring.mybatis.controller;

import hejairi.sample.spring.mybatis.model.User;
import hejairi.sample.spring.mybatis.module.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by litlhope on 2014. 4. 17..
 */
@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserMapper userMapper;

	/**
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "{id:[0-9]+}", method = RequestMethod.GET)
	public User getUser(@PathVariable("id") Long id) {
		User user = userMapper.findById(id);
		return user;
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<User> getAllUsers() {
		List<User> users = userMapper.findAll();
		return users;
	}

	@RequestMapping(method = RequestMethod.POST)
	public User insert(@RequestBody User user) {
		log.info(user.toString());
		userMapper.insert(user);
		return user;
	}

	@RequestMapping(method = RequestMethod.PUT)
	public User update(@RequestBody User user) {
		log.info(user.toString());
		userMapper.update(user);
		return user;
	}
}
