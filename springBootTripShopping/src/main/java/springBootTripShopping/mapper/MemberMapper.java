package springBootTripShopping.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import springBootTripShopping.domain.MemberDTO;
import springBootTripShopping.domain.StartEndPageDTO;

@Mapper
public interface MemberMapper {
	public void memberInsert(MemberDTO dto);
	public String memberAutoNum();
	public List<MemberDTO> selectAll(StartEndPageDTO sepDTO);
	public int memberCount(String searchWord);
	public int membersDelete(@Param("membersNum") String[] memDels);
	public MemberDTO memberSelectOne(String memberNum);
	public int memberUpdate(MemberDTO dto);
	public int memberDelete(String memberNum);
}
