package springBootTripShopping.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import springBootTripShopping.domain.MemberDTO;

@Mapper
public interface MemberInfoMapper {
	public MemberDTO memberInfo(String memberId);
	public Integer memberPwUpdate(@Param("_newPw") String userPw, @Param("_memberId") String memberId);
	public int memberDrop(String memberId);
	public int memberInfoUpdate(MemberDTO dto);
}
