package springBootTripShopping.service.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTripShopping.command.EmployeeCommand;
import springBootTripShopping.mapper.EmployeeMappper;

@Service
public class EmployeeAutoNumService {
	@Autowired
	EmployeeMappper employeeMappper;
	
	public void execute(Model model, EmployeeCommand employeeCommand) {
		String empNum = employeeMappper.autoNum();
		employeeCommand = new EmployeeCommand();
		employeeCommand.setEmpNum(empNum);
		model.addAttribute("empNum", employeeCommand);
	}
}
