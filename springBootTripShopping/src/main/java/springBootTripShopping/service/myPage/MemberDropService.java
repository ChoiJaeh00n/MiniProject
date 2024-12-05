package springBootTripShopping.service.myPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import springBootTripShopping.domain.AuthInfoDTO;
import springBootTripShopping.mapper.MemberInfoMapper;

@Service
public class MemberDropService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	MemberInfoMapper memberInfoMapper;
	
	public int execute(String memberPw, HttpSession session, Model model) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		if(passwordEncoder.matches(memberPw, auth.getUserPw())) {
			int i = memberInfoMapper.memberDrop(auth.getUserId());
			return 1;
		}else {
			model.addAttribute("errPw", "비밀번호가 틀렸습니다.");
			return 0;
			
		}
	}
}
