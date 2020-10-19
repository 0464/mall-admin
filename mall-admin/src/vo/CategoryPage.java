package vo;

import dao.*;

public class CategoryPage {
	public int currentPage = 1; // 현재 페이지
	public int rowPerPage = 4; // 페이지당 리스트 표시 수
	public int naviAmount = 3; // 페이지 네비게이션에 표시할 페이지 수
	
	public int getLastPage() throws Exception {
		CategoryDao categoryDao = new CategoryDao();
		int totalRow = categoryDao.selectCategoryCount();
		
		int lastPage = totalRow/rowPerPage;
		if (totalRow%rowPerPage != 0) {
			lastPage += 1;
		}
		
		return lastPage;
	}
	public int getLastPageSearchByCategoryName(String categoryName) throws Exception {
		// CategoryDao를 이용해 product 테이블의 행 갯수를 가져옴
		CategoryDao categoryDao = new CategoryDao();
		int totalRow = categoryDao.selectCategoryCountSearchByCategoryName(categoryName);
		
		// 전체 행 갯수에서 페이지당 행 표시 갯수를 나누면 소숫점 부분(나머지)이 짤린 마지막 페이지 번호를 가져올 수 있음
		int lastPage = totalRow/rowPerPage;
		if (totalRow%rowPerPage != 0) {
			// rowPerPage로 나눴을 때 나머지가 존재하면 나머지 행들을 보여주기 위해 하나의 페이지가 더 필요함
			lastPage += 1;
		}
		
		return lastPage;
	}
}
