package springBootTripShopping.domain;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("mem")
public class MemberDTO {
	String memberNum;
	String memberId;
	String memberPw;
	String memberPwCon;
	String memberName;
	String memberAddr;
	String memberAddrDetail;
	String memberPost;
	String memberPhone1;
	String memberPhone2;
	String gender;
	Date memberBirth;
	String memberEmail;
	Date memberRegist;
	Integer point;
}
