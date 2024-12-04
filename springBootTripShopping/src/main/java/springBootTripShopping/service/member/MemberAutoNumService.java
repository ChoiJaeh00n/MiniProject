package springBootTripShopping.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTripShopping.command.MemberCommand;
import springBootTripShopping.mapper.MemberMapper;

@Service
public class MemberAutoNumService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(Model model) {
		String memberNum = memberMapper.memberAutoNum();
		MemberCommand dto = new MemberCommand();
		dto.setMemberNum(memberNum);
		model.addAttribute("memberCommand", dto);
	}
}
