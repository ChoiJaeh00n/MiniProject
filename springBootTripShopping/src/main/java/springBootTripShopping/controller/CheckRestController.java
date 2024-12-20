package springBootTripShopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import springBootTripShopping.service.EmailCheckService;
import springBootTripShopping.service.UserEmailCheckService;

@RestController
public class CheckRestController {
	@Autowired
	EmailCheckService emailCheckService;
	@Autowired
	UserEmailCheckService userEmailCheckService;
	
	@PostMapping("userEmailCheck")
	public String userEmailCheck(@RequestParam(value = "userEmail") String userEmail) {
		String resultEmail = emailCheckService.execute(userEmail);
		if(resultEmail == null) {
			return "사용가능한 Email입니다.";
		}else {
			return "사용중인 Email입니다.";
		}
	}
	
	@RequestMapping("userConfirm")
	public String userConfirm(@RequestParam(value="chk") String chk) {
		int i = userEmailCheckService.execute(chk);
		if(i == 0)
			return "이미 인증되었습니다.";
		else	return "인증되었습니다.";
	}
}
