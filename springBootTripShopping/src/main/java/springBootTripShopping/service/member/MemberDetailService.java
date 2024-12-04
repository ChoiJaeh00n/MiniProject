package springBootTripShopping.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTripShopping.command.MemberCommand;
import springBootTripShopping.domain.MemberDTO;
import springBootTripShopping.mapper.MemberMapper;

@Service
public class MemberDetailService {
	@Autowired
	MemberMapper memberMapper;
	
	public void execute(String memberNum, Model model) {
		MemberDTO dto = memberMapper.memberSelectOne(memberNum);
		MemberCommand memberCommand = new MemberCommand();
		memberCommand.setGender(dto.getGender());
		memberCommand.setMemberAddr(dto.getMemberAddr());
		memberCommand.setMemberAddrDetail(dto.getMemberAddrDetail());
		memberCommand.setMemberBirth(dto.getMemberBirth());
		memberCommand.setMemberEmail(dto.getMemberEmail());
		memberCommand.setMemberId(dto.getMemberId());
		memberCommand.setMemberName(dto.getMemberName());
		memberCommand.setMemberNum(dto.getMemberNum());
		memberCommand.setMemberPhone1(dto.getMemberPhone1());
		memberCommand.setMemberPhone2(dto.getMemberPhone2());
		memberCommand.setMemberPost(dto.getMemberPost());
		memberCommand.setMemberRegist(dto.getMemberRegist());
		memberCommand.setPoint(dto.getPoint());
		model.addAttribute("memberCommand", memberCommand);
		
		
		
	}
}
