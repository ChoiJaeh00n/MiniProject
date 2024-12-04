package springBootTripShopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springBootTripShopping.command.MemberCommand;
import springBootTripShopping.service.member.MemberAutoNumService;
import springBootTripShopping.service.member.MemberDeleteService;
import springBootTripShopping.service.member.MemberDetailService;
import springBootTripShopping.service.member.MemberInsertService;
import springBootTripShopping.service.member.MemberListService;
import springBootTripShopping.service.member.MemberUpdateService;
import springBootTripShopping.service.member.MembersDeleteService;

@Controller
@RequestMapping("member")
public class MemberController {
	@Autowired
	MemberInsertService memberInsertService; 
	@Autowired
	MemberAutoNumService memberAutoNumService;
	@Autowired
	MemberListService memberListService;
	@Autowired
	MembersDeleteService membersDeleteService;
	@Autowired
	MemberDetailService memberDetailService;
	@Autowired
	MemberUpdateService memberUpdateService;
	@Autowired
	MemberDeleteService memberDeleteService;
	
	@RequestMapping("memberList")
	public String list(
			// 처음 페이지가 열릴 때 searchWord가 없으므로 페이지가 오류가 생긴다.
			// 오류를 방지하기 위해 필수가 아니라고 해줘야 함. 'required = false'
			@RequestParam(value = "searchWord", required = false) String searchWord,
			// 처음 페이지가 열릴 때는 page에 기본값은 1
			@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			Model model) {
		// 회원들의 정보를 담아 memberList.html에 보낼 수 있게 Model이 필요
		memberListService.execute(model, searchWord, page);
		return "thymeleaf/member/memberList";
	}
	
	@GetMapping("memberRegist")
	public String form(Model model) {
		// 회원번호 불러오기
		memberAutoNumService.execute(model);
		return "thymeleaf/member/memberForm";
	}
	
	@PostMapping("memberRegist")
	public String form(@Validated MemberCommand memberCommand, BindingResult result) {
		if(result.hasErrors()) {
			return "thymeleaf/member/memberForm";
		}
		if(!memberCommand.isMemberPwEqualMemberPwCon()) {
			// 비밀번호와 비밀번호 확인이 다른 경우에도 메시지 보내기
			// result.rejectValue(필드명, 에러코드, 메세지)
			result.rejectValue("memberPwCon", "memberCommand.memberPwCon", "비밀번호가 일치하지 않습니다.");
			return "thymeleaf/member/memberForm";
		}else {
			memberInsertService.execute(memberCommand);
			return "redirect:memberList";
		}
	}
	
	@PostMapping("membersDelete")
	public String dels(@RequestParam(value = "memDels") String memDels[]) {
		membersDeleteService.execute(memDels);
		return "redirect:memberList";
	}
	
	@GetMapping("memberDetail")
	public String memberDetail(@RequestParam(value = "memberNum") String memberNum, Model model) {
		memberDetailService.execute(memberNum, model);
		return "thymeleaf/member/memberInfo";
	}
	
	@RequestMapping("memberUpdate")
	public String memberUpdate(@RequestParam(value="memberNum") String memberNum, Model model) {
		memberDetailService.execute(memberNum, model);
		return "thymeleaf/member/memberModify";
	}
	
	@PostMapping("memberModify")
	public String memberModify(@Validated MemberCommand memberCommand, BindingResult result) {
		// html에서 넘어온 값은 MemberCommand가 받으며, 이때 MemberCommand에 넘어오지 않은 경우에는 오류를 검사 함.
		if(result.hasErrors()) {
			// 오류가 있다면 다시 memberModify 페이지가 열림
			// memberModify페이지에 MemberCommand가 가진 값을 전달
			// MemberCommand는 값만 전달하는 것이 아니라 오류메시지도 전달.
			return "thymeleaf/member/memberModify";
		}
		memberUpdateService.execute(memberCommand);
		return "redirect:memberDetail?memberNum=" + memberCommand.getMemberNum();
	}
	
	@GetMapping("memberdelete/{memberNum}")
	public String memberdelete(@PathVariable(value="memberNum") String memberNum) {
		memberDeleteService.execute(memberNum);
		return "redirect:../memberList";
	}
	
	
	
	
	
	
	
	
}
