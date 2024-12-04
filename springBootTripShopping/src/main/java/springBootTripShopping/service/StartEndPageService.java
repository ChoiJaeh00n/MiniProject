package springBootTripShopping.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import springBootTripShopping.domain.StartEndPageDTO;

@Service
public class StartEndPageService {
	int limit;

	public StartEndPageDTO execute(int page, String searchWord) {
		// 회원들을 10명씩 보여주는 페이징
		limit = 10;
		int startRow = ((page - 1) * limit) + 1; // page를 이용해서 시작 번호를 가져오기
		int endRow = startRow + limit - 1;
		// startRow와 endRow 그리고 searchWord를 mybatis에게 넘기기 위해 DTO 생성 - StartEndPageDTO
		StartEndPageDTO sepDTO = new StartEndPageDTO();
		sepDTO.setEndRow(endRow);
		sepDTO.setSearchWord(searchWord);
		sepDTO.setStartRow(startRow);
		return sepDTO;
	}

	public void execute(int page, int count, Model model, List list, String searchWord) {
		
		// 페이지에 보여줄 페이지 번호의 갯수를 정해줌
		int limitPage = 10;
		// 시작 페이지 번호와 마지막 페이지 번호를 가지고 온다.
		int startPage = (int) ((double) page / limitPage + 0.95 - 1) * limitPage + 1;
		int endPage = startPage + limitPage - 1;
		// 전체 페이지 갯수
		int maxPage = (int) ((double) count / limit + 0.95);
		// endPage가 maxPage보다 크지 않게 만들어 줘야함
		if (maxPage < endPage)
			endPage = maxPage;
		// 리스트 페이지로 넘겨준다

		model.addAttribute("dtos", list);
		// 검색할 때 사용한 단어를 html에 출력
		model.addAttribute("searchWord", searchWord);
		model.addAttribute("page", page);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("count", count);
		model.addAttribute("maxPage", maxPage);
	}
}
