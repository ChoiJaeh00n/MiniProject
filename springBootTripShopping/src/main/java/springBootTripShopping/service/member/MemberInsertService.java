package springBootTripShopping.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import springBootTripShopping.command.MemberCommand;
import springBootTripShopping.domain.MemberDTO;
import springBootTripShopping.mapper.MemberMapper;

@Service
public class MemberInsertService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	MemberMapper memberMapper; 
	
	public void execute(MemberCommand memberCommand) {
		MemberDTO dto = new MemberDTO();
		dto.setGender(memberCommand.getGender());
		dto.setMemberAddr(memberCommand.getMemberAddr());
		dto.setMemberAddrDetail(memberCommand.getMemberAddrDetail());
		dto.setMemberBirth(memberCommand.getMemberBirth());
		dto.setMemberEmail(memberCommand.getMemberEmail());
		dto.setMemberId(memberCommand.getMemberId());
		dto.setMemberName(memberCommand.getMemberName());
		dto.setMemberNum(memberCommand.getMemberNum());
		dto.setMemberPhone1(memberCommand.getMemberPhone1());
		dto.setMemberPhone2(memberCommand.getMemberPhone2());
		dto.setMemberPost(memberCommand.getMemberPost());
		// 비밀번호 암호화
		String encodePw = passwordEncoder.encode(memberCommand.getMemberPw());
		dto.setMemberPw(encodePw);
		dto.setMemberPwCon(memberCommand.getMemberPwCon());
		memberMapper.memberInsert(dto);
	}
}
