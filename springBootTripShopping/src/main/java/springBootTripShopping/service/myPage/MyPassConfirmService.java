package springBootTripShopping.service.myPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import springBootTripShopping.domain.AuthInfoDTO;
import springBootTripShopping.mapper.MemberInfoMapper;

@Service
public class MyPassConfirmService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	MemberInfoMapper memberInfoMapper;
	
	public boolean execute(String newPw, String oldPw, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		if(passwordEncoder.matches(oldPw, auth.getUserPw())) {
			String userPw = passwordEncoder.encode(newPw);		// 새 비밀번호 암호화
			// 암호화 된 비밀번호를 DB에 저장
			memberInfoMapper.memberPwUpdate(userPw, auth.getUserId());
			// 새 비밀번호를 session에 저장
			auth.setUserPw(userPw);
			return true;
		}else return false;
	}
}
