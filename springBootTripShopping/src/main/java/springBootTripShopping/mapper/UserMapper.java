package springBootTripShopping.mapper;

import org.apache.ibatis.annotations.Mapper;

import springBootTripShopping.domain.AuthInfoDTO;
import springBootTripShopping.domain.MemberDTO;

@Mapper
public interface UserMapper {
	 public int userInsert(MemberDTO dto);
	 public int userCheckUpdate(String email);
	 public AuthInfoDTO loginSelect(String userId);
}
