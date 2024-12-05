package springBootTripShopping.service.help;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTripShopping.domain.AuthInfoDTO;
import springBootTripShopping.mapper.FindMapper;
import springBootTripShopping.service.EmailSendService;

@Service
public class FindPwService {
	@Autowired
	FindMapper findMapper;
	@Autowired
	PasswordEncoder passwordEncoder; // 암호화
	@Autowired
	EmailSendService emailSendService; // 메일을 통해 임시비밀번호 전송

	public void execute(String userId, String userPhone, Model model) {
		AuthInfoDTO auth = new AuthInfoDTO();
		AuthInfoDTO dto = findMapper.userEmail(userId, userPhone);

		if (dto != null) {
			String newPw = UUID.randomUUID().toString().substring(0, 8);
			dto.setUserId(userId);

			// 새 비밀번호를 암호화
			dto.setUserPw(passwordEncoder.encode(newPw));
			if (dto.getGrade().equals("mem")) { // 회원인 경우
				dto.setTableName("members");
				dto.setPwColumName("member_pw");
				dto.setUserIdColumName("member_id");
			} else { // 직원인 경우
				dto.setTableName("employees");
				dto.setPwColumName("emp_pw");
				dto.setUserIdColumName("emp_id");
			}
			findMapper.pwUpdate(dto);

			model.addAttribute("dto", dto);
			String html = "<html><body>";
			html += dto.getUserName() + "님의 임시 비밀번호는 " + newPw + "입니다.";
			html += "</body></html>";
			String subject = dto.getUserName() + "님의 임시비밀번호입니다.";
			String fromEmail = "soongmoostudent@gmail.com";
			String toEmail = dto.getUserEmail();
			emailSendService.mailsend(html, subject, fromEmail, toEmail);
		}
	}
}
