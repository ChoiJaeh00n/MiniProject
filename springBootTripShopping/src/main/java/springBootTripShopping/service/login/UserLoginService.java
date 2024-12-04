package springBootTripShopping.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import jakarta.servlet.http.HttpSession;
import springBootTripShopping.command.LoginCommand;
import springBootTripShopping.domain.AuthInfoDTO;
import springBootTripShopping.mapper.UserMapper;

@Service
public class UserLoginService {
	@Autowired
	UserMapper userMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public void execute(LoginCommand loginCommand, BindingResult result, HttpSession session) {
		String userId = loginCommand.getUserId();
		String userPw = loginCommand.getUserPw();
		
		// 회원 로그인 정보를 가지고 오기 위한 DTO 필요
		AuthInfoDTO dto = userMapper.loginSelect(userId);
		if(userId != "" || userId != null) {
			
			if(dto != null) {	// 회원아이디가 있으면 dto null이 아님
				
				if(dto.getUserEmailCheck() == null) {
					System.out.println("아이디는 존재하지만 이메일이 인증이 되지 않았을 때");
					result.rejectValue("userId", "loginCommand.userId", "이메일 인증이 안되었습니다.");
					
				}else {
					
					// 아이디가 존재하고 비밀번호가 일치하지 않았을 때
					if(passwordEncoder.matches(userPw, dto.getUserPw())) {
						System.out.println("비밀번호가 일치");
						// 아이디와 비밀번호가 일치하면 session에 로그인 정보 저장
						session.setAttribute("auth", dto);
						
					}else {
						System.out.println("비밀번호가 일치하지 않았을 때");
						result.rejectValue("userPw", "loginCommand.userPw", "비밀번호가 틀렸습니다.");
					}
				}
			}
		}else {
			System.out.println("아이디가 존재하지 않았을 때");
			result.rejectValue("userId", "loginCommand.userId", "아이디가 존재하지 않습니다.");
		}
	}
}



