package springBootTripShopping.service.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import springBootTripShopping.command.EmployeeCommand;
import springBootTripShopping.domain.EmployeeDTO;
import springBootTripShopping.mapper.EmployeeMappper;

@Service
public class EmployeeInsertService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	EmployeeMappper employeeMappper;
	
	public void execute(EmployeeCommand employeeCommand) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setEmpAddr(employeeCommand.getEmpAddr());
		dto.setEmpAddrDetail(employeeCommand.getEmpAddrDetail());
		dto.setEmpEmail(employeeCommand.getEmpEmail());
		dto.setEmpId(employeeCommand.getEmpId());
		dto.setEmpName(employeeCommand.getEmpName());
		dto.setEmpNum(employeeCommand.getEmpNum());
		dto.setEmpPhone(employeeCommand.getEmpPhone());
		dto.setEmpPost(employeeCommand.getEmpPost());
		dto.setEmpPw(passwordEncoder.encode(employeeCommand.getEmpPw()));
		dto.setEmpJumin(employeeCommand.getEmpJumin());
		employeeMappper.employeeInsert(dto);
	}
}
