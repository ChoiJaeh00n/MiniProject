package springBootTripShopping.service.employees;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTripShopping.domain.EmployeeDTO;
import springBootTripShopping.mapper.EmployeeMappper;

@Service
public class EmployeeListService {
	@Autowired
	EmployeeMappper employeeMappper;
	
	public void execute(Model model) {
		List<EmployeeDTO> list = employeeMappper.employeeAllSelect();
		model.addAttribute("list", list);
	}
}
