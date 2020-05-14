import javax.swing.JOptionPane;

class DTO_Product // 한명의 회원 정보를 저장하는 클래스
{
	private String productCode;
	private String productName;
	private String productPrice;
	
	/*이하는 setter getter메소드 
	 * 자동생성은  alt+shift+S 후 
	 * Generate Getters and Setters 클릭
	 * */
	
	public String getProductCode() {
		return productCode;
	}

	public boolean setProductCode(String productCode) {
		if(productCode.trim().length() == 4) //좌우 공백 제거한 길이가 4
		{
			if(productCode.charAt(0) == 'H' || productCode.charAt(0) == 'S' || productCode.charAt(0) == 'B')
				{ 
				  this.productCode = productCode; return true;
				}
			else {
				JOptionPane.showMessageDialog(null, "제품코드: 핫도그는 H, 사이드는 S, 음료는 B 로 시작합니다.","Update Failed",JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		JOptionPane.showMessageDialog(null, "제품코드는 총 4글자 입니다. ","Update Failed",JOptionPane.ERROR_MESSAGE);
		return false;
	}

	public String getProductName() {
		return productName;
	}

	public boolean setProductName(String productName) {
		if(productName.trim().length() > 4) //좌우 공백 제거한 길이가 4보다 크면 가능
		{
			this.productName = productName;
			return true;
		}
		else {
			JOptionPane.showMessageDialog(null, "제품이름은 4글자이상이어야 합니다. ","Update Failed",JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	public String getProductPrice() {
		return productPrice;
	}

	public boolean setProductPrice(String productPrice) {
		try
		{
			Double.parseDouble(productPrice.trim()); //양쪽 공백빼고 넣기 
		}
		catch(NumberFormatException e)
		{
			JOptionPane.showMessageDialog(null, "가격은 숫자로만 이루어져있어야 합니다. ","Update Failed",JOptionPane.ERROR_MESSAGE);
			return false; 
		}
		this.productPrice = productPrice; //trycatch 잘끝나면 가격넣기
		return true;
	}
	
	//DTO 객체 확인 toString
	//자동생성은 우클릭 -> source -> Generate toString
	@Override
	public String toString() {
		return "DTO_Product [productCode=" + productCode + ", productName=" + productName + ", productPrice="
				+ productPrice + "]";
	}
	
}


public class project1_DTO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
