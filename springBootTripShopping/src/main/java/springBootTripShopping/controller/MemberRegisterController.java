package springBootTripShopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springBootTripShopping.command.UserCommand;
import springBootTripShopping.service.memberRegister.UserWriteService;

@Controller
@RequestMapping("register")
public class MemberRegisterController {
	@Autowired
	UserWriteService userWriteService;
	
	/* 모든 주소에서 userCommand를 사용할 수 있게 정의 */
	@ModelAttribute
	public UserCommand userCommand() {
		return new UserCommand();
	}
	
	@GetMapping("userAgree")
	public String agree() {
		return "thymeleaf/memberRegist/userAgree";
	}
	
	@PostMapping("userWrite")
	public String userWrite(@RequestParam(value="agree", defaultValue = "false") boolean agree, UserCommand userCommand) {
		if(agree == false) {
			return "thymeleaf/memberRegist/userAgree"; // 동의하지 않으면 페이지가 넘어가지 않음
		}
		return "thymeleaf/memberRegist/userForm";
	}
	
	@PostMapping("userRegist")
	public String userRegist(
			@Validated UserCommand userCommand, Model model, BindingResult result) {
		// 오류가 있는 경우 오류 메시지 출력
		if(result.hasErrors()) {
			return "thymeleaf/memberRegist/userForm";
		}
		userWriteService.execute(userCommand, model);
		return "thymeleaf/memberRegist/welcome";
	}
}
