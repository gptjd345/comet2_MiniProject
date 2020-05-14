import javax.swing.JOptionPane;

class DTO_Member
{
	private String memberID;
	private String memberPW;
	private String memberName;
	private String memberAge;
	private String memberAddr;
	
	public String getMemberID() {
		return memberID;
	}
	
	//���̵� ��ȿ���˻� (not null, ���ڷθ� �̷���� �� ����, �Է¼����� true ���н� false)
	public Boolean setMemberID(String memberID) { 
		if(memberID.trim().length() == 0)
		{	
			JOptionPane.showMessageDialog(null, "ID�� �Է����� �ʾҽ��ϴ�.","Sign In Failed",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			try
			{
				Double.parseDouble(memberID.trim()); //���� ���黩�� �ֱ� 
				JOptionPane.showMessageDialog(null, "ID�� ���ڷθ� �̷���� �� �����ϴ�.","Sign In Failed",JOptionPane.ERROR_MESSAGE);
			}
			catch(NumberFormatException e)
			{
				this.memberID = memberID;
				return true; //ID�Է� ����
			}
		}
		return false; //ID�Է� ����
	}
	
	
	
	public String getMemberPW() { 
		return memberPW;
	}
	
	//�н����� ��ȿ���˻� (not null, ���ڷθ� �̷���� �� ����, �Է¼����� true ���н� false)
	public boolean setMemberPW(String memberPW) { 
		if(memberPW.trim().length() == 0)
		{	
			JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է����� �ʾҽ��ϴ�.","Sign In Failed",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			try
			{
				Double.parseDouble(memberPW.trim()); //���� ���黩�� �ֱ� 
				JOptionPane.showMessageDialog(null, "��й�ȣ�� ���ڷθ� �̷���� �� �����ϴ�.","Sign In Failed",JOptionPane.ERROR_MESSAGE);
			}
			catch(NumberFormatException e)
			{
				this.memberPW = memberPW;
				return true; //PW �Է� ����
			}
		}
		return false; //PW �Է� ����
	}
	
	public String getMemberName() {
		return memberName;
	}
	
	//�̸� ��ȿ�� �˻� (not null, �Է¼����� true ���н� false)
	public boolean setMemberName(String memberName) {
		if(memberName.trim().length() == 0)
		{	
			JOptionPane.showMessageDialog(null, "�̸��� �Է����� �ʾҽ��ϴ�.","Sign In Failed",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		this.memberName = memberName;
		
		return true;
	}
	
	public String getMemberAge() {
		return memberAge;
	}
	
	//���� ��ȿ�� �˻� (not null, ���ڷθ� �̷��������, �Է¼����� true ���н� false)
	public boolean setMemberAge(String memberAge) {
		if(memberAge.trim().length() == 0)
		{
			JOptionPane.showMessageDialog(null, "���̸� �Է����� �ʾҽ��ϴ�.","Sign In Failed",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else
		{	
			try
			{  Double.parseDouble(memberAge.trim()); }  //���� ���黩�� �ֱ� 
			catch(NumberFormatException e)
			{
				JOptionPane.showMessageDialog(null, "���̴� ���ڰ� ���Ե� �� �����ϴ�.","Sign In Failed",JOptionPane.ERROR_MESSAGE);
				return false; //Age �Է� ����
			}
		}
		
		this.memberAge = memberAge;
		
		return true; //Age �Է� ����
	}
	
	public String getMemberAddr() {
		return memberAddr;
	}
	//�ּ� ��ȿ�� �˻� (not null, �Է¼����� true ���н� false)
	public boolean setMemberAddr(String memberAddr) {
		if(memberAddr.trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "�ּҰ� �Էµ��� �ʾҽ��ϴ�.","Sign In Failed",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else {
			this.memberAddr = memberAddr;
			
		}
		return true;
	}
	
	/*���ϴ� setter getter�޼ҵ� 
	 * �ڵ�������  alt+shift+S �� 
	 * Generate Getters and Setters Ŭ��
	 * */
	
	//DTO setting ��� ��ȿ���˻簡 ��� true���� true ���� �˻� �߰��� ����� false�̸� �������� �˻� ���� ����
	public boolean DTO_Setting(MemberSignUp memberSignUpWindow)
	{
		if(setMemberID(memberSignUpWindow.SignUpIDtextField.getText())) 
		{	
			if(setMemberPW(String.copyValueOf(memberSignUpWindow.SignUpPWtextField.getPassword())))//getPassword()�� ��ȯ���� char[]�� String�� �ٸ� �����ٰ�
			{	
				if(setMemberName(memberSignUpWindow.SingUpNameTextField.getText()))
				{
					if(setMemberAge(memberSignUpWindow.SignUpAgeTextField.getText()))
					{
						if(setMemberAddr(memberSignUpWindow.SignUpAddressTextField.getText()))
							return true;
					}
					else return false;
				}
				else return false;
			}
			else return false;
		}
		else return false;
				
		
		return false; 
 
	 }

	
	
}

public class MemberDTO {

	public static void main(String[] args) {
	

	}

}
