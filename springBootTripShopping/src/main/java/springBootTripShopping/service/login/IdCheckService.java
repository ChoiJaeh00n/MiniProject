package springBootTripShopping.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springBootTripShopping.mapper.LoginMapper;

@Service
public class IdCheckService {
	@Autowired
	LoginMapper loginMapper;
	
	public String execute(String userId) {
		String resultId = loginMapper.selectIdCheck(userId);
		return resultId;
	}
}
