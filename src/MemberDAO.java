import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

class Member_DAO //member�� �����Ͽ� ������ ���̽��� ������ Ŭ����
{
	private Connection con;
	
	private PreparedStatement ps;
	
	private ResultSet rs;
	
	Member_DAO()
	{
		getConnect(); //con���� ���� ����
		
	}
	public Connection getConnect() //****DB���� �޼ҵ� (con���� ����)***** 
	{
		try
		{
			// �� �ε�
			System.out.println("�� ����̹� �ε� \n" );
			Class.forName(DatabaseConstant.DRIVERNAME);
	
			// *** DB ���� ***
			// �� ����
			System.out.println("�� ����̹� ���� \n" );
			con = DriverManager.getConnection(DatabaseConstant.URL, DatabaseConstant.USER, DatabaseConstant.PASSWORD);
		}
		catch(Exception e)
		{
			System.out.println("���� ����");
		}
		return con;
	}
	
	/**Login Ȯ��(ID PW�� ��ġ�ϴ� ���� �ִ��� Ȯ��)**/
	public Boolean LoginProgress(String ID,String PW)
	{
		
		try {
			con = getConnect();
			String sql = "select * from member where MemberID = ? and MemberPW = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, ID.trim());
			ps.setString(2, PW.trim());
			rs = ps.executeQuery();
			
			if(rs.next()) //�� ���̶� ���� �ϸ� ����â ����
			{
				JOptionPane.showMessageDialog(null, "�α��� ����","Login Success",JOptionPane.INFORMATION_MESSAGE);
				if(ID.trim().equals("admin"))
				{
					//MenuManger��ü ���� ������ ������ ��� ��ư ��밡��
					 new MenuManager("�޴� ����");
				}
				else
				{
					//�Ϲ� ����ڴ� �߰�,����,���� ��ư�� ����� �� ����.
					MenuManager MenuWindow = new MenuManager("�޴� ����");
					MenuWindow.InsertButton.setEnabled(false);
					MenuWindow.EditButton.setEnabled(false);
					MenuWindow.DeleteButton.setEnabled(false);
				}	
				return true; //�α��ο� ���������� true
			}
			else 
					JOptionPane.showMessageDialog(null, "�������� ���� ���̵��̰ų�, �߸��� ��й�ȣ�Դϴ�.","Login Failed",JOptionPane.INFORMATION_MESSAGE);
			
		}catch(SQLException e)
		{
			System.out.println("���� ����");
			closeDatabase();
		}	
		return false; //�α��ο� �����ϸ� false
	}
	
	/**ȸ�� ���� ó��(Insert)**/
	public Boolean SingInProgress(DTO_Member DTOmember)
	{
		
		try {
			con = getConnect();
			String sql = "insert into member (MemberID, MemberPW, MemberName, MemberAge, MemberAddr) values (?, ?, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, DTOmember.getMemberID()); ps.setString(2, DTOmember.getMemberPW()); ps.setString(3, DTOmember.getMemberName());
			ps.setString(4, DTOmember.getMemberAge()); ps.setString(5, DTOmember.getMemberAddr()); 
			rs = ps.executeQuery();
			
			if(rs.next()) //�����
			{
				JOptionPane.showMessageDialog(null, "ȸ������ ����","Sign In Success",JOptionPane.INFORMATION_MESSAGE);
				return true; //ȸ�������� �����ͺ��̽��� �߰� ������ true
			}
			else 
				JOptionPane.showMessageDialog(null, "�������� ������� ����","Sign In Failed",JOptionPane.ERROR_MESSAGE);
			
		}catch(SQLException e)
		{
			System.out.println("���� ����");
			closeDatabase();
		}	
		return false; //�α��ο� �����ϸ� false
	}
	
	/**ID �ߺ� Ȯ��**/
	public Boolean SingInIDcheck(DTO_Member DTOmember)
	{
		try {
			con = getConnect();
			String sql = "select * from member where MemberId = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, DTOmember.getMemberID()); 
			rs = ps.executeQuery();
			
			if(rs.next()) //�ߺ��Ǵ� ID�� ������
			{
				JOptionPane.showMessageDialog(null, "�̹� ��� ���� ���̵��Դϴ�.","Duplicated ID",JOptionPane.ERROR_MESSAGE);
				return false; 
			}
			else 
				JOptionPane.showMessageDialog(null, "��밡���� ���̵��Դϴ�.","Available ID",JOptionPane.INFORMATION_MESSAGE);
			
		}catch(SQLException e)
		{
			System.out.println("���� ����");
			closeDatabase();
		}	
		return true; //ID�� �ߺ����� ����
	}
	
	/**
	 * �ڿ� ���� �޼ҵ�
	 * ���������� ��������� �������� �Ѵ�.
	 * null ���θ� üũ�ѵ� ������� ��� �ݾ��ش�.
	 
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
			System.out.println("[�ݱ� ����]\n" +  e.getStackTrace());
		}
	}
	
}

public class MemberDAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
