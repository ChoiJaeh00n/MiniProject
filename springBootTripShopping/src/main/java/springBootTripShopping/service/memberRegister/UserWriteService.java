package springBootTripShopping.service.memberRegister;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import springBootTripShopping.command.UserCommand;
import springBootTripShopping.domain.MemberDTO;
import springBootTripShopping.mapper.UserMapper;
import springBootTripShopping.service.EmailSendService;

@Service
public class UserWriteService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserMapper userMapper;
	@Autowired
	EmailSendService emailSendService; 
	
	public void execute(UserCommand userCommand, Model model) {
		String memberId = userCommand.getMemberId();
		String memberPw = userCommand.getMemberPw();
		String memberName = userCommand.getMemberName();
		String memberPhone1 = userCommand.getMemberPhone1();
		String memberPhone2 = userCommand.getMemberPhone2();
		String memberAddr = userCommand.getMemberAddr();
		String memberAddrDetail = userCommand.getMemberAddrDetail();
		String memberPost = userCommand.getMemberPost();
		String memberEmail = userCommand.getMemberEmail();
		Date memberBirth = userCommand.getMemberBirth();
		String gender = userCommand.getGender();
		
		MemberDTO dto = new MemberDTO();
		dto.setMemberAddr(memberAddr);
		dto.setMemberAddrDetail(memberAddrDetail);
		dto.setMemberEmail(memberEmail);
		dto.setGender(gender);
		dto.setMemberId(memberId);
		dto.setMemberName(memberName);
		dto.setMemberPhone1(memberPhone1);
		dto.setMemberPhone2(memberPhone2);
		dto.setMemberPost(memberPost);
		dto.setMemberPw(passwordEncoder.encode(memberPw)); // 비밀번호 암호화
		dto.setMemberBirth(memberBirth);
		
		// 이메일 인증
		int i = userMapper.userInsert(dto);
		model.addAttribute("userName", dto.getMemberName());
		model.addAttribute("userEmail", dto.getMemberEmail());
		
		if(i >= 1) {
			// 메일링
			String html = "<html><body>"; 
				html += dto.getMemberName() + "님 가입을 환영합니다.<br />";
				html +="가입을 완료하시려면";
				html +="<a href='http://localhost:8080/userConfirm?chk=" + dto.getMemberEmail()+"'>여기</a>를클릭하세요.";
				html +="</body></html>";
		String subject = "환영 인사입니다.";
		String fromEmail = "soongmoostudent@gmail.com";	// 보내는 사람
		String toEmail = dto.getMemberEmail();			// 받는 사람
		
		
		emailSendService.mailsend(html, subject, fromEmail, toEmail);
		}
		
		
		
		
	}
}






















