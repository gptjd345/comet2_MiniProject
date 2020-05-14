import javax.swing.JOptionPane;

class DTO_Product // �Ѹ��� ȸ�� ������ �����ϴ� Ŭ����
{
	private String productCode;
	private String productName;
	private String productPrice;
	
	/*���ϴ� setter getter�޼ҵ� 
	 * �ڵ�������  alt+shift+S �� 
	 * Generate Getters and Setters Ŭ��
	 * */
	
	public String getProductCode() {
		return productCode;
	}

	public boolean setProductCode(String productCode) {
		if(productCode.trim().length() == 4) //�¿� ���� ������ ���̰� 4
		{
			if(productCode.charAt(0) == 'H' || productCode.charAt(0) == 'S' || productCode.charAt(0) == 'B')
				{ 
				  this.productCode = productCode; return true;
				}
			else {
				JOptionPane.showMessageDialog(null, "��ǰ�ڵ�: �ֵ��״� H, ���̵�� S, ����� B �� �����մϴ�.","Update Failed",JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		JOptionPane.showMessageDialog(null, "��ǰ�ڵ�� �� 4���� �Դϴ�. ","Update Failed",JOptionPane.ERROR_MESSAGE);
		return false;
	}

	public String getProductName() {
		return productName;
	}

	public boolean setProductName(String productName) {
		if(productName.trim().length() > 4) //�¿� ���� ������ ���̰� 4���� ũ�� ����
		{
			this.productName = productName;
			return true;
		}
		else {
			JOptionPane.showMessageDialog(null, "��ǰ�̸��� 4�����̻��̾�� �մϴ�. ","Update Failed",JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	public String getProductPrice() {
		return productPrice;
	}

	public boolean setProductPrice(String productPrice) {
		try
		{
			Double.parseDouble(productPrice.trim()); //���� ���黩�� �ֱ� 
		}
		catch(NumberFormatException e)
		{
			JOptionPane.showMessageDialog(null, "������ ���ڷθ� �̷�����־�� �մϴ�. ","Update Failed",JOptionPane.ERROR_MESSAGE);
			return false; 
		}
		this.productPrice = productPrice; //trycatch �߳����� ���ݳֱ�
		return true;
	}
	
	//DTO ��ü Ȯ�� toString
	//�ڵ������� ��Ŭ�� -> source -> Generate toString
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
