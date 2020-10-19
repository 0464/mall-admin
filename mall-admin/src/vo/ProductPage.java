package vo;

import dao.*;

public class ProductPage {
	public int currentPage = 1; // 현재 페이지
	public int rowPerPage = 4; // 페이지당 리스트 표시 수
	public int naviAmount = 3; // 페이지 네비게이션에 표시할 페이지 수
	public int getLastPage() throws Exception {
		// ProductDao를 이용해 product 테이블의 행 갯수를 가져옴
		ProductDao productDao = new ProductDao();
		int totalRow = productDao.selectProductCount();
		
		// 전체 행 갯수에서 페이지당 행 표시 갯수를 나누면 소숫점 부분(나머지)이 짤린 마지막 페이지 번호를 가져올 수 있음
		int lastPage = totalRow/rowPerPage;
		if (totalRow%rowPerPage != 0) {
			lastPage += 1;
		}
		
		return lastPage;
	}

	public int getLastPageSearchByCategoryId(int categoryId) throws Exception {
		// ProductDao를 이용해 product 테이블의 행 갯수를 가져옴
		ProductDao productDao = new ProductDao();
		int totalRow = productDao.selectProductCountSearchByCategoryId(categoryId);
		int lastPage = totalRow/rowPerPage;
		if (totalRow%rowPerPage != 0) {
			lastPage += 1;
		}
		
		return lastPage;
	}
}
