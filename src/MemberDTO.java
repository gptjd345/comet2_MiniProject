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
	
	//아이디 유효성검사 (not null, 숫자로만 이루어질 수 없음, 입력성공시 true 실패시 false)
	public Boolean setMemberID(String memberID) { 
		if(memberID.trim().length() == 0)
		{	
			JOptionPane.showMessageDialog(null, "ID를 입력하지 않았습니다.","Sign In Failed",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			try
			{
				Double.parseDouble(memberID.trim()); //양쪽 공백빼고 넣기 
				JOptionPane.showMessageDialog(null, "ID는 숫자로만 이루어질 수 없습니다.","Sign In Failed",JOptionPane.ERROR_MESSAGE);
			}
			catch(NumberFormatException e)
			{
				this.memberID = memberID;
				return true; //ID입력 성공
			}
		}
		return false; //ID입력 실패
	}
	
	
	
	public String getMemberPW() { 
		return memberPW;
	}
	
	//패스워드 유효성검사 (not null, 숫자로만 이루어질 수 없음, 입력성공시 true 실패시 false)
	public boolean setMemberPW(String memberPW) { 
		if(memberPW.trim().length() == 0)
		{	
			JOptionPane.showMessageDialog(null, "비밀번호를 입력하지 않았습니다.","Sign In Failed",JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			try
			{
				Double.parseDouble(memberPW.trim()); //양쪽 공백빼고 넣기 
				JOptionPane.showMessageDialog(null, "비밀번호는 숫자로만 이루어질 수 없습니다.","Sign In Failed",JOptionPane.ERROR_MESSAGE);
			}
			catch(NumberFormatException e)
			{
				this.memberPW = memberPW;
				return true; //PW 입력 성공
			}
		}
		return false; //PW 입력 실패
	}
	
	public String getMemberName() {
		return memberName;
	}
	
	//이름 유효성 검사 (not null, 입력성공시 true 실패시 false)
	public boolean setMemberName(String memberName) {
		if(memberName.trim().length() == 0)
		{	
			JOptionPane.showMessageDialog(null, "이름을 입력하지 않았습니다.","Sign In Failed",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		this.memberName = memberName;
		
		return true;
	}
	
	public String getMemberAge() {
		return memberAge;
	}
	
	//나이 유효성 검사 (not null, 숫자로만 이루어져야함, 입력성공시 true 실패시 false)
	public boolean setMemberAge(String memberAge) {
		if(memberAge.trim().length() == 0)
		{
			JOptionPane.showMessageDialog(null, "나이를 입력하지 않았습니다.","Sign In Failed",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else
		{	
			try
			{  Double.parseDouble(memberAge.trim()); }  //양쪽 공백빼고 넣기 
			catch(NumberFormatException e)
			{
				JOptionPane.showMessageDialog(null, "나이는 문자가 포함될 수 없습니다.","Sign In Failed",JOptionPane.ERROR_MESSAGE);
				return false; //Age 입력 실패
			}
		}
		
		this.memberAge = memberAge;
		
		return true; //Age 입력 성공
	}
	
	public String getMemberAddr() {
		return memberAddr;
	}
	//주소 유효성 검사 (not null, 입력성공시 true 실패시 false)
	public boolean setMemberAddr(String memberAddr) {
		if(memberAddr.trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "주소가 입력되지 않았습니다.","Sign In Failed",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else {
			this.memberAddr = memberAddr;
			
		}
		return true;
	}
	
	/*이하는 setter getter메소드 
	 * 자동생성은  alt+shift+S 후 
	 * Generate Getters and Setters 클릭
	 * */
	
	//DTO setting 모든 유효성검사가 모두 true여야 true 리턴 검사 중간에 결과가 false이면 나머지는 검사 진행 안함
	public boolean DTO_Setting(MemberSignUp memberSignUpWindow)
	{
		if(setMemberID(memberSignUpWindow.SignUpIDtextField.getText())) 
		{	
			if(setMemberPW(String.copyValueOf(memberSignUpWindow.SignUpPWtextField.getPassword())))//getPassword()의 반환형은 char[]임 String과 다름 맞춰줄것
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
