package springBootTripShopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import springBootTripShopping.command.MemberCommand;
import springBootTripShopping.service.myPage.MemberDropService;
import springBootTripShopping.service.myPage.MemberInfoService;
import springBootTripShopping.service.myPage.MemberInfoUpdateService;
import springBootTripShopping.service.myPage.MemberPwUpdateService;
import springBootTripShopping.service.myPage.MyPassConfirmService;

@Controller
@RequestMapping("mypage")
public class MemberMyPageController {
	@Autowired
	MemberInfoService memberInfoService;
	@Autowired
	MemberPwUpdateService memberPwUpdateService;
	@Autowired
	MyPassConfirmService myPassConfirmService;
	@Autowired
	MemberDropService memberDropService;
	@Autowired
	MemberInfoUpdateService memberInfoUpdateService;
	
	@GetMapping("myDetail")
	public String myDetail(HttpSession session, Model model) {
		// 로그인 할때 저장한 session을 가져와서 내정보를 디비에서 가져오도록 하기
		// 디비로 가져온 정보를 model을 이용해서 myInfo.html에 보내도록 하기
		memberInfoService.execute(session, model);
		return "thymeleaf/memberShip/myInfo";
	}
	
	@GetMapping("memberPwModify")
	public String memberPwModify() {
		return "thymeleaf/memberShip/myPwCon";
	}
	
	@PostMapping("memberPwModify")
	public String newPw(@RequestParam("memberPw") String memberPw, HttpSession session, Model model ) {
		return memberPwUpdateService.execute(session, model, memberPw);
	}
	
	@PostMapping("memberPwUpdate")
	@ResponseBody // html이 아닌 값을 전달할 때 사용 
	public boolean memberPwUpdate(
			@RequestParam("oldPw") String oldPw,
			@RequestParam(value="newPw") String newPw,
			HttpSession session) {
		return myPassConfirmService.execute(newPw, oldPw, session);
	}
	
	@GetMapping("memberDrop")
	public String memberDrop() {
		return "thymeleaf/memberShip/memberDrop";
	}
	
	@PostMapping("memberDropOk")
	public String memberDrop(
			@RequestParam("memberPw") String memberPw, Model model,
			HttpSession session) {
		int i = memberDropService.execute(memberPw, session, model);
		if(i == 1)
			return "redirect:/login/logout";	// 탈퇴하면 로그아웃하기
		else return "thymeleaf/memberShip/memberDrop";		// 비밀번호가 틀리면 현재 페이지
	}
	
	@GetMapping("memberUpdate")
	public String memberUpdate(HttpSession session, Model model) {
		memberInfoService.execute(session, model);
		return "thymeleaf/memberShip/myModify";
	}
	
	@PostMapping("memberUpdate")
	public String memberUpdate(@Validated MemberCommand memberCommand, HttpSession session, BindingResult result) {
		memberInfoUpdateService.execute(memberCommand, session, result);
		if(result.hasErrors()) {
			return "thymeleaf/memberShip/myModify";
		}
		return "redirct:myDetail";
	}
	
}

















