import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

class Member_DAO //member와 관련하여 데이터 베이스에 질의할 클래스
{
	private Connection con;
	
	private PreparedStatement ps;
	
	private ResultSet rs;
	
	Member_DAO()
	{
		getConnect(); //con까지 연결 수행
		
	}
	public Connection getConnect() //****DB연결 메소드 (con까지 연결)***** 
	{
		try
		{
			// ① 로드
			System.out.println("① 드라이버 로딩 \n" );
			Class.forName(DatabaseConstant.DRIVERNAME);
	
			// *** DB 생성 ***
			// ② 연결
			System.out.println("② 드라이버 연결 \n" );
			con = DriverManager.getConnection(DatabaseConstant.URL, DatabaseConstant.USER, DatabaseConstant.PASSWORD);
		}
		catch(Exception e)
		{
			System.out.println("연결 실패");
		}
		return con;
	}
	
	/**Login 확인(ID PW가 일치하는 행이 있는지 확인)**/
	public Boolean LoginProgress(String ID,String PW)
	{
		
		try {
			con = getConnect();
			String sql = "select * from member where MemberID = ? and MemberPW = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, ID.trim());
			ps.setString(2, PW.trim());
			rs = ps.executeQuery();
			
			if(rs.next()) //한 행이라도 존재 하면 관리창 띄우기
			{
				JOptionPane.showMessageDialog(null, "로그인 성공","Login Success",JOptionPane.INFORMATION_MESSAGE);
				if(ID.trim().equals("admin"))
				{
					//MenuManger객체 생성 관리자 계정은 모든 버튼 사용가능
					 new MenuManager("메뉴 관리");
				}
				else
				{
					//일반 사용자는 추가,수정,삭제 버튼을 사용할 수 없다.
					MenuManager MenuWindow = new MenuManager("메뉴 관리");
					MenuWindow.InsertButton.setEnabled(false);
					MenuWindow.EditButton.setEnabled(false);
					MenuWindow.DeleteButton.setEnabled(false);
				}	
				return true; //로그인에 성공했으면 true
			}
			else 
					JOptionPane.showMessageDialog(null, "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.","Login Failed",JOptionPane.INFORMATION_MESSAGE);
			
		}catch(SQLException e)
		{
			System.out.println("갱신 실패");
			closeDatabase();
		}	
		return false; //로그인에 실패하면 false
	}
	
	/**회원 가입 처리(Insert)**/
	public Boolean SingInProgress(DTO_Member DTOmember)
	{
		
		try {
			con = getConnect();
			String sql = "insert into member (MemberID, MemberPW, MemberName, MemberAge, MemberAddr) values (?, ?, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, DTOmember.getMemberID()); ps.setString(2, DTOmember.getMemberPW()); ps.setString(3, DTOmember.getMemberName());
			ps.setString(4, DTOmember.getMemberAge()); ps.setString(5, DTOmember.getMemberAddr()); 
			rs = ps.executeQuery();
			
			if(rs.next()) //실행됨
			{
				JOptionPane.showMessageDialog(null, "회원가입 성공","Sign In Success",JOptionPane.INFORMATION_MESSAGE);
				return true; //회원정보를 데이터베이스에 추가 성공시 true
			}
			else 
				JOptionPane.showMessageDialog(null, "쿼리문이 실행되지 않음","Sign In Failed",JOptionPane.ERROR_MESSAGE);
			
		}catch(SQLException e)
		{
			System.out.println("갱신 실패");
			closeDatabase();
		}	
		return false; //로그인에 실패하면 false
	}
	
	/**ID 중복 확인**/
	public Boolean SingInIDcheck(DTO_Member DTOmember)
	{
		try {
			con = getConnect();
			String sql = "select * from member where MemberId = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, DTOmember.getMemberID()); 
			rs = ps.executeQuery();
			
			if(rs.next()) //중복되는 ID가 존재함
			{
				JOptionPane.showMessageDialog(null, "이미 사용 중인 아이디입니다.","Duplicated ID",JOptionPane.ERROR_MESSAGE);
				return false; 
			}
			else 
				JOptionPane.showMessageDialog(null, "사용가능한 아이디입니다.","Available ID",JOptionPane.INFORMATION_MESSAGE);
			
		}catch(SQLException e)
		{
			System.out.println("갱신 실패");
			closeDatabase();
		}	
		return true; //ID가 중복되지 않음
	}
	
	/**
	 * 자원 해제 메소드
	 * 해제순서는 만든순서의 역순으로 한다.
	 * null 여부를 체크한뒤 담겨있을 경우 닫아준다.
	 
	 **/
	private void closeDatabase()
	{
		try
		{
			if( rs != null )
			{
				rs.close();
			}
			 
			if( ps != null )
			{
				ps.close();
			}
			 
			if( con != null )
			{
				con.close();
			}
		}
		catch (SQLException e)
		{
			System.out.println("[닫기 오류]\n" +  e.getStackTrace());
		}
	}
	
}

public class MemberDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
