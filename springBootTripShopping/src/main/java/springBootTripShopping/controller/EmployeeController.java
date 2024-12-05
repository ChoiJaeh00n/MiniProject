package springBootTripShopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import springBootTripShopping.command.EmployeeCommand;
import springBootTripShopping.service.employees.EmployeeAutoNumService;
import springBootTripShopping.service.employees.EmployeeInsertService;
import springBootTripShopping.service.employees.EmployeeListService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
	@Autowired
	EmployeeAutoNumService employeeAutoNumService;
	@Autowired
	EmployeeInsertService employeeInsertService;
	@Autowired
	EmployeeListService employeeListService;
	
	@GetMapping("employeeList")
	public String empList(Model model) {
		employeeListService.execute(model);
		return "thymeleaf/employee/employeeList";
	}
	
	@GetMapping("empRegist")
	public String form(Model model, EmployeeCommand employeeCommand) {
		// 직원번호 불러오기
		employeeAutoNumService.execute(model, employeeCommand);
		return "thymeleaf/employee/employeeForm";
	}
	
	@PostMapping("empRegist")
	public String form(@Validated EmployeeCommand employeeCommand, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "thymeleaf/employee/employeeForm";
		}
		if(!employeeCommand.isEmpPwEqualsEmpPwCon()) {
			result.rejectValue("empPwCon", "employeeCommand.empPwCon", "비밀번호가 일치하지 않습니다.");
			return "thymeleaf/employee/employeeForm";
		}else {
			employeeInsertService.execute(employeeCommand);
			return "redirect:employeeList";
		}
	}
}
