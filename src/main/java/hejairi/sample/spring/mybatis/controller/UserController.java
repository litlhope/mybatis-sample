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

//	@RequestMapping(method = RequestMethod.POST)
//	public User insert(@RequestParam User user) {
//		return user;
//	}
	@RequestMapping(method = RequestMethod.POST)
	public User insert(@RequestParam("email") String email,
					   @RequestParam("user_name") String userName,
					   @RequestParam("tel") String tel,
					   @RequestParam("sex") String sex) {
		User user = new User();
		user.setEmail(email);
		user.setUserName(userName);
		user.setTel(tel);
		user.setSex(sex);
		userMapper.insert(user);
		return user;
	}
}
