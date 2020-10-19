package vo;

public class Product {
	public int productId; // 상품의 고유한 번호, ID
	public int categoryId; // 상품의 카테고리 (category 테이블의 categoryId와 매칭되는 외래 키)
	public String productName; // 상품명
	public int productPrice; // 상품 가격
	public String productContent; // 상품 설명
	public String productSoldout; // 상품이 판매중인지 여부 ("Y": 재고없음, "N": 판매중 둘 중 하나만 선택가능한 ENUM)
}
