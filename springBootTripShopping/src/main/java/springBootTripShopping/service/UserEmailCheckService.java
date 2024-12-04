package springBootTripShopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootTripShopping.mapper.UserMapper;

@Service
public class UserEmailCheckService {
	@Autowired
	UserMapper userMapper;
	
	public int execute(String email) {
		int i = userMapper.userCheckUpdate(email);
		return i;
	}
}
