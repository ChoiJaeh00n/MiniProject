package springBootTripShopping.service.myPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import springBootTripShopping.domain.AuthInfoDTO;
import springBootTripShopping.domain.MemberDTO;
import springBootTripShopping.mapper.MemberInfoMapper;

@Service
public class MemberInfoService {
	@Autowired
	MemberInfoMapper memberInfoMapper; 
	
	public void execute(HttpSession session, Model model) {
								// 로그인 할 때 만든 session
		AuthInfoDTO authInfo = (AuthInfoDTO)session.getAttribute("auth");
		String memberId = authInfo.getUserId();
		
		// 아이디를 이용하여 회원정보 가져오기
		MemberDTO dto = memberInfoMapper.memberInfo(memberId);
		
		// dto를 html에 보내기 위해 model에 저장
		// 나중에 command로 사용해야 할 수 도 있어서  command에 dto를 옮기도록 함
		// 만약 dto와 command의 멤버필드가 같다면 dto를 model에 저장
		model.addAttribute("memberCommand", dto);
	}
}
