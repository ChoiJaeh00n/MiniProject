package springBootTripShopping.service.member;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTripShopping.domain.MemberDTO;
import springBootTripShopping.domain.StartEndPageDTO;
import springBootTripShopping.mapper.MemberMapper;
import springBootTripShopping.service.StartEndPageService;

@Service
public class MemberListService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	StartEndPageService startEndPageService;
	
	String searchWord;
	public void execute(Model model, String searchWord, int page) {
		// searchWord에 양옆에 공백문자가 올 수 있으므로 제거해야 함
		if(searchWord != null) {
			this.searchWord = searchWord.trim();
		}
		
		StartEndPageDTO sepDTO = startEndPageService.execute(page, this.searchWord);
		List<MemberDTO> list = memberMapper.selectAll(sepDTO);
		
		// 전체 회원수를 가지고 오기
		int count = memberMapper.memberCount(this.searchWord);
		startEndPageService.execute(page, count, model, list, this.searchWord);
	}
}
