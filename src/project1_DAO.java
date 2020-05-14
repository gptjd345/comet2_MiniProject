



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

class DatabaseConstant { //Ŭ���� �ε�
	public final static String DRIVERNAME = "oracle.jdbc.driver.OracleDriver";
	public final static String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	public final static String USER = "system";
	public final static String PASSWORD = "oracle";
}

//CRUD : Create = insert , Read = Select , Update , Delete
//StaffMember ���̺��� DAOŬ���� ����-----
class product_DAO
{
	
	private Connection con;
	
	private PreparedStatement ps;
	
	private ResultSet rs;
	
	product_DAO()
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
//	
//	/**�� ����� ȸ�������� ��� �޼ҵ� **/
//	public DTO_Product getDTOStaffMember(String id)
//	{
//		DTO_Product dto = new DTO_Product();
//		
//		try {
//			con = getConnect();
//			String sql = "select * from staffmember where id = ?";
//			ps = con.prepareStatement(sql);
//			ps.setString(1,id);
//			
//			rs = ps.executeQuery();
//			
//			if(rs.next()) {
//				dto.setID(rs.getString("id"));
//				dto.setPASS(rs.getString("pass"));
//				dto.setNAME(rs.getString("name"));
//				dto.setAGE(rs.getString("age"));
//				dto.setADDRESS(rs.getString("address"));
//			}
//			else System.out.println(id + "�� ��ġ�ϴ� �ο찡 �����ϴ�.");//id�� �������� �ʴ� ��� ���ٰ� ���
//			
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		
//		return dto;
//	}
//	/**��� ȸ�������� ��� **/
//	public Vector getMemberList()
//	{
//		Vector MemberList = new Vector(); //Jtable���� vector�� �迭�� ���� �� �ִ�.
//		
//		try
//		{
//			con = getConnect();
//			String sql = "select * from staffmember";
//			ps = con.prepareStatement(sql);
//			rs = ps.executeQuery();
//			
//			while(rs.next())
//			{
//				String id = rs.getString("id");
//				String pass = rs.getString("pass");
//				String name = rs.getString("name");
//				String age = rs.getString("name");
//				String address = rs.getString("address");
//				
//				Vector row = new Vector();
//				row.add(id);
//				row.add(pass);
//				row.add(name);
//				row.add(age);
//				row.add(address);
//				
//				MemberList.add(row); //2���� �迭�� ����
//			}
//		}catch(Exception e)
//			{
//				e.printStackTrace();
//			}
//		
//		
//		return MemberList;
//	}
//	
//	/** ȸ����� **/
//	public boolean insertMember(DTO_StaffMember dto)
//	{
//		boolean ok = false; //insert�������� Ȯ�� ��
//		
//		try {
//			con = getConnect();
//			String sql = "insert into staffmember(id,pass,name,age,address) values (?,?,?,?,?) ";
//			ps = con.prepareStatement(sql);
//			ps.setString(1, dto.getID());
//			ps.setString(2, dto.getPASS());
//			ps.setString(3, dto.getNAME());
//			ps.setString(4, dto.getAGE());
//			ps.setString(5, dto.getADDRESS());
//			
//			int res = ps.executeUpdate();
//			
//			if(res > 0) 
//			{
//				System.out.println("insert ����");
//				ok = true;
//			}
//			else System.out.println("insert ����");
//			
//		}catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		closeDatabase();
//		return ok;
//	}
//	
//	/**ȸ������ ����**/
//	public boolean updateMember(DTO_StaffMember member)
//	{
//		System.out.println(member.toString()); // ������ ���� ��� ���� ���
//		boolean ok = false; //������Ʈ ���� ���� Ȯ�ο�
//		try {
//			con = getConnect();
//			String sql = "update staffmember set pass = ?, name = ? , age = ? , address = ? where id = ?";
//			ps = con.prepareStatement(sql);
//			ps.setString(1, member.getPASS());
//			ps.setString(2, member.getNAME());
//			ps.setString(3, member.getAGE());
//			ps.setString(4, member.getADDRESS());
//			ps.setString(5, member.getID());
//			
//			int res = ps.executeUpdate();
//			
//			if(res > 0) {
//				System.out.println("update ����");
//				ok = true;
//			}
//			else
//				System.out.println("update ����");
//				
//		}catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		closeDatabase();
//		return ok;
//	}
//	
//	/**ȸ������ ���� **/
//	public boolean deleteMember(String id,String pass)
//	{
//		boolean ok = false; //���� �������� Ȯ�ο�
//		
//		try {
//			con = getConnect();
//			String sql = "delete from staffmember where id = ? and pass = ?";
//			ps = con.prepareStatement(sql);
//			ps.setString(1, id);
//			ps.setString(2, pass);
//			
//			int res = ps.executeUpdate();
//			if(res > 0)
//			{
//				System.out.println(id+"�� ���� ���� ����");
//				ok = true;
//			}
//			else 
//				System.out.println(id+"���� ����");
//			
//		}catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		closeDatabase();
//		return false;
//	}
	
	/**��ü ����**/
	public void EveryView(DefaultTableModel model)
	{
		try {
			con = getConnect();
			String sql = "select * from product";
					
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(model.getRowCount() != 0) //�����ϱ����� row = 0 �� �ɶ� ���� model�� ���� ����
			{
				model.removeRow(0);
			}
			
			while(rs.next())
			{
				String list[] = {	rs.getString(1), rs.getString(2), rs.getString(3) };
				model.addRow(list); //������ �ֱ�
			}
			
		
		}catch(SQLException e)
		{
			System.out.println("���� ����");
			closeDatabase();
		}	
	}
	
	
	/**Hotdog�������� ���� **/
	public void hotdogView(DefaultTableModel model)
	{
		try {
			con = getConnect();
			String sql = 
					"select * from product where productCode like 'H%'"; //�ֵ��״� H
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			System.out.println("a");
			while(model.getRowCount() != 0) //�����ϱ����� row = 0 �� �ɶ� ���� model�� ���� ����
			{
				model.removeRow(0);
			}
			
			while(rs.next())
			{
				String list[] = {	rs.getString(1), rs.getString(2), rs.getString(3) };
				model.addRow(list); //������ �ֱ�
			}
			
		
		}catch(SQLException e)
		{
			System.out.println("���� ����");
			closeDatabase();
		}	
	}
	
	/**Side �������� ���� **/
	public void sideView(DefaultTableModel model)
	{
		try {
			con = getConnect();
			String sql = "select * from product where productCode like 'S%'"; //���̵�� S
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(model.getRowCount() != 0) //�����ϱ����� row = 0 �� �ɶ� ���� model�� ���� ����
			{
				model.removeRow(0);
			}
			
			while(rs.next())
			{
				String list[] = {	rs.getString(1), rs.getString(2), rs.getString(3) };
				model.addRow(list); //������ �ֱ�
			}
			
		
		}catch(SQLException e)
		{
			System.out.println("���� ����");
			closeDatabase();
		}	
	}
	
	/**Beverage �������� ���� **/
	public void beverageView(DefaultTableModel model)
	{
		try {
			con = getConnect();
			String sql = "select * from product where productCode like 'B%'"; //Beverage B 
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			System.out.println("a");
			while(model.getRowCount() != 0) //�����ϱ����� row = 0 �� �ɶ� ���� model�� ���� ����
			{
				model.removeRow(0);
			}
			
			while(rs.next())
			{
				String list[] = {	rs.getString(1), rs.getString(2), rs.getString(3) };
				model.addRow(list); //������ �ֱ�
			}
			
		
		}catch(SQLException e)
		{
			System.out.println("���� ����");
			closeDatabase();
		}	
	}
	
	/**��ǰ �ڵ�������� �˻� **/
	public void ProductCodeSearch(DefaultTableModel model,String keyword)
	{
		try {
			String value = "%"+keyword+"%";
			con = getConnect();
			String sql = "select * from product where productCode LIKE ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, value);
			rs = ps.executeQuery();
			System.out.println("e");
			while(model.getRowCount() != 0) //�����ϱ����� row = 0 �� �ɶ� ���� model�� ���� ����
			{
				model.removeRow(0);
			}
			if(rs.next()) { //���� �ϳ��� ������ ����
				do
				{
					String list[] = {	rs.getString(1), rs.getString(2), rs.getString(3) };
					model.addRow(list); //������ �ֱ�
				}while(rs.next());
			}
			else //�˻��� ������ ���ٸ�?
			{
				JOptionPane.showMessageDialog(null,"�˻��� ����� �������� �ʽ��ϴ�.","Message",JOptionPane.INFORMATION_MESSAGE);
			}
			
		
		}catch(SQLException e)
		{
			System.out.println("���� ����");
			closeDatabase();
		}	
	}
	
	
	/**��ǰ�� �������� �˻� **/
	public void ProductNameSearch(DefaultTableModel model,String keyword)
	{
		try {
			String value = "%"+keyword+"%";
			con = getConnect();
			String sql = "select * from product where productName LIKE ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, value); 
			rs = ps.executeQuery();
			System.out.println("e");
			while(model.getRowCount() != 0) //�����ϱ����� row = 0 �� �ɶ� ���� model�� ���� ����
			{
				model.removeRow(0);
			}
			if(rs.next()) { //���� �ϳ��� ������ ����
				do
				{
					String list[] = {	rs.getString(1), rs.getString(2), rs.getString(3) };
					model.addRow(list); //������ �ֱ�
				}while(rs.next());
			}
			else //�˻��� ������ ���ٸ�?
			{
				JOptionPane.showMessageDialog(null,"�˻��� ����� �������� �ʽ��ϴ�.","Message",JOptionPane.INFORMATION_MESSAGE);
			}
			
		
		}catch(SQLException e)
		{
			System.out.println("���� ����");
			closeDatabase();
		}	
	}
	
	/**���� �������� �˻� **/
	public void ProductPriceSearch(DefaultTableModel model,String keyword)
	{
		try {
			String value = "%"+keyword+"%";
			con = getConnect();
			String sql = "select * from product where productPrice LIKE ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, value);
			rs = ps.executeQuery();
			System.out.println("e");
			while(model.getRowCount() != 0) //�����ϱ����� row = 0 �� �ɶ� ���� model�� ���� ����
			{
				model.removeRow(0);
			}
			if(rs.next()) { //���� �ϳ��� ������ ����
				do
				{
					String list[] = {	rs.getString(1), rs.getString(2), rs.getString(3) };
					model.addRow(list); //������ �ֱ�
				}while(rs.next());
			}
			else //�˻��� ������ ���ٸ�?
			{
				JOptionPane.showMessageDialog(null,"�˻��� ����� �������� �ʽ��ϴ�.","Message",JOptionPane.INFORMATION_MESSAGE);
			}
			
		
		}catch(SQLException e)
		{
			System.out.println("���� ����");
			closeDatabase();
		}	
	}
	
	/**��ü �˻� **/
	public void ProductEverySearch(DefaultTableModel model,String keyword)
	{
		try {
			String value = "%"+keyword+"%";
			con = getConnect();
			String sql = "select * from product where productName LIKE ? OR productCode Like ? OR productPrice LIKE ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, value); ps.setString(2, value); ps.setString(3, value);
			rs = ps.executeQuery();
			System.out.println("e");
			while(model.getRowCount() != 0) //�����ϱ����� row = 0 �� �ɶ� ���� model�� ���� ����
			{
				model.removeRow(0);
			}
			if(rs.next()) { //���� �ϳ��� ������ ����
				do
				{
					String list[] = {	rs.getString(1), rs.getString(2), rs.getString(3) };
					model.addRow(list); //������ �ֱ�
				}while(rs.next());
			}
			else //�˻��� ������ ���ٸ�?
			{
				JOptionPane.showMessageDialog(null,"�˻��� ����� �������� �ʽ��ϴ�.","Message",JOptionPane.INFORMATION_MESSAGE);
			}
			
		
		}catch(SQLException e)
		{
			System.out.println("���� ����");
			closeDatabase();
		}	
	}
	
	
	/** ���� **/
	
	public void Delete(DefaultTableModel model,String keyword)
	{
		int result = JOptionPane.showConfirmDialog(null,"���� �����Ͻðڽ��ϱ�?","����",JOptionPane.YES_NO_OPTION); // Ȯ���� 0 ����
		
		if(result == 0) { //Ȯ�� ��ư�� ������
			try {
				con = getConnect();
				String sql = "delete from Product where productCode = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, keyword); 
				int res = ps.executeUpdate();
				
				if(res > 0) {//���� ��
					JOptionPane.showMessageDialog(null,"�����Ǿ����ϴ�.","Message",JOptionPane.INFORMATION_MESSAGE);
					EveryView(model); //���� ������ �ڵ��ʱ�ȭ
				}
				else //������������
					JOptionPane.showMessageDialog(null,"�������� �ʾҽ��ϴ�.","Message",JOptionPane.INFORMATION_MESSAGE);
				
			}catch(SQLException e)
			{
				System.out.println("���� ����");
				closeDatabase();
			}
			//return true;
		}
		//else
			//return false;
	}
	
	/** ��ǰ �߰� **/
	public boolean insertProduct(DTO_Product dto)
	{
		boolean ok = false; //insert�������� Ȯ�� ��
		
		try {
			con = getConnect();
			String sql = "insert into Product(ProductCode, ProductName, ProductPrice) values (?,?,?) ";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getProductCode());
			ps.setString(2, dto.getProductName());
			ps.setString(3, dto.getProductPrice());
			
			int res = ps.executeUpdate();
			
			if(res > 0) 
			{
				JOptionPane.showMessageDialog(null, "�߰� ����","Insert Success",JOptionPane.INFORMATION_MESSAGE);
				ok = true;
				MenuManager.productDAO.EveryView(MenuManager.ManagerTableModel); //�߰� ������ ȭ�鿡 ���̺� �ʱ�ȭ

			}
			else 
				JOptionPane.showMessageDialog(null, "�߰� ����","Insert Failed",JOptionPane.ERROR_MESSAGE);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		closeDatabase();
		return ok;
	}
	
	/** ��ǰ ������Ʈ(����) **/
	public boolean updateProduct(DTO_Product dto)
	{
		boolean ok = false; //insert�������� Ȯ�� ��
		
		try {
			con = getConnect();
			String sql = "update product set ProductName = ? , ProductPrice = ? where ProductCode = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getProductName());
			ps.setString(2, dto.getProductPrice());
			ps.setString(3, dto.getProductCode());
			
			int res = ps.executeUpdate();
			
			if(res > 0) 
			{
				JOptionPane.showMessageDialog(null, "������Ʈ ����","Update Success",JOptionPane.INFORMATION_MESSAGE);
				ok = true;
				MenuManager.productDAO.EveryView(MenuManager.ManagerTableModel); //������Ʈ ������ �ʱ�ȭ 
			}
			else 
				JOptionPane.showMessageDialog(null, "������Ʈ ����","Update Failed",JOptionPane.ERROR_MESSAGE);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		closeDatabase();
		return ok;
	}
	
	/*
	
	
	
	
	public static DAO_StaffMember sharedInstance() {
		if(obj == null)
		{
			obj = new DAO_StaffMember();
		}
		return obj;
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

public class project1_DAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}


