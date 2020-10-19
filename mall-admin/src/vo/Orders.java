package vo;

public class Orders {
	public int ordersId; // 주문의 고유한 번호, ID
	public String ordersDate; // 주문한 날짜
	public int productId; // 주문한 상품 (product 테이블의 productId와 매칭되는 외래
	public int ordersAmount; // 주문한 상품 갯수
	public int ordersPrice; // 주문했을 당시의 상품의 TOTAL 가격
	public String memberEmail; // 주문자의 E-mail
	public String ordersAddr; // 주문시킨 배송지
	public String ordersState; // 주문 상태 ("결제완료", "배송준비중", "배송완료", "주문취소" 4개중 한개만 선택가능한 ENUM)
}
