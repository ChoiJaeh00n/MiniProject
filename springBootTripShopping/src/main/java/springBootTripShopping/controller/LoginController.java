package springBootTripShopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import springBootTripShopping.command.LoginCommand;
import springBootTripShopping.service.login.IdCheckService;
import springBootTripShopping.service.login.UserLoginService;

@Controller
@RequestMapping("login")
public class LoginController {
	@Autowired
	IdCheckService idCheckService;
	@Autowired
	UserLoginService userLoginService;
	
	@PostMapping("userIdCheck")
	// html문서가 아닌 텍스트를 전달하기 위해서는 @ResponseBody가 필요
	public @ResponseBody String userIdCheck(
			@RequestParam(value ="userId") String userId) {
		String resultId = idCheckService.execute(userId);
		if(resultId == null) {
			return "사용가능한 아이디입니다.";
		}else {
			return "이미 존재하는 아이디입니다.";
		}
	}
	
	@PostMapping("login")
	// 아이디와 비밀번호를 command로 받음
	public String login(@Validated LoginCommand loginCommand, BindingResult result, HttpSession session) {
		userLoginService.execute(loginCommand, result, session);
		// 오류가 있으면 index.html페이지에서 열리게 만들기
		if(result.hasErrors()) {
			return "thymeleaf/index";
		}
		return "redirect:/";
	}
}
