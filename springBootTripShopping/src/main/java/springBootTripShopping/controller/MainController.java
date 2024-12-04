package springBootTripShopping.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import springBootTripShopping.command.LoginCommand;

@Controller
public class MainController {
	@RequestMapping("/")
	public String index(@ModelAttribute("loginCommand") LoginCommand loginCommand) { // public String index (@ModelAttribute("loginCommand") LoginCommand loginCommand)
		return "thymeleaf/index";
	}
	
	
}
