package springBootTripShopping.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import springBootTripShopping.domain.EmployeeDTO;

@Mapper
public interface EmployeeMappper {
	public String autoNum();
	public Integer employeeInsert(EmployeeDTO dto);
	public List<EmployeeDTO> employeeAllSelect();
}
