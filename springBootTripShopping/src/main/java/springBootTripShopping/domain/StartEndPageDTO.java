package springBootTripShopping.domain;

import lombok.Data;

@Data
public class StartEndPageDTO {
	int startRow;
	int endRow;
	String searchWord;
}