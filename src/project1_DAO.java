



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

class DatabaseConstant { //클래스 로드
	public final static String DRIVERNAME = "oracle.jdbc.driver.OracleDriver";
	public final static String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	public final static String USER = "system";
	public final static String PASSWORD = "oracle";
}

//CRUD : Create = insert , Read = Select , Update , Delete
//StaffMember 테이블의 DAO클래스 시작-----
class product_DAO
{
	
	private Connection con;
	
	private PreparedStatement ps;
	
	private ResultSet rs;
	
	product_DAO()
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
//	
//	/**한 사람의 회원정보를 얻는 메소드 **/
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
//			else System.out.println(id + "와 일치하는 로우가 없습니다.");//id가 존재하지 않는 경우 없다고 출력
//			
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		
//		return dto;
//	}
//	/**모든 회원정보를 출력 **/
//	public Vector getMemberList()
//	{
//		Vector MemberList = new Vector(); //Jtable에는 vector나 배열을 넣을 수 있다.
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
//				MemberList.add(row); //2차원 배열로 만듬
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
//	/** 회원등록 **/
//	public boolean insertMember(DTO_StaffMember dto)
//	{
//		boolean ok = false; //insert성공여부 확인 요
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
//				System.out.println("insert 성공");
//				ok = true;
//			}
//			else System.out.println("insert 실패");
//			
//		}catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		closeDatabase();
//		return ok;
//	}
//	
//	/**회원정보 수정**/
//	public boolean updateMember(DTO_StaffMember member)
//	{
//		System.out.println(member.toString()); // 가져온 현재 멤버 정보 출력
//		boolean ok = false; //업데이트 성공 여부 확인용
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
//				System.out.println("update 성공");
//				ok = true;
//			}
//			else
//				System.out.println("update 실패");
//				
//		}catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		closeDatabase();
//		return ok;
//	}
//	
//	/**회원정보 삭제 **/
//	public boolean deleteMember(String id,String pass)
//	{
//		boolean ok = false; //삭제 성공여부 확인용
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
//				System.out.println(id+"의 정보 삭제 성공");
//				ok = true;
//			}
//			else 
//				System.out.println(id+"삭제 실패");
//			
//		}catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//		closeDatabase();
//		return false;
//	}
	
	/**전체 보기**/
	public void EveryView(DefaultTableModel model)
	{
		try {
			con = getConnect();
			String sql = "select * from product";
					
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(model.getRowCount() != 0) //갱신하기위해 row = 0 이 될때 까지 model을 먼저 삭제
			{
				model.removeRow(0);
			}
			
			while(rs.next())
			{
				String list[] = {	rs.getString(1), rs.getString(2), rs.getString(3) };
				model.addRow(list); //데이터 넣기
			}
			
		
		}catch(SQLException e)
		{
			System.out.println("갱신 실패");
			closeDatabase();
		}	
	}
	
	
	/**Hotdog기준으로 보기 **/
	public void hotdogView(DefaultTableModel model)
	{
		try {
			con = getConnect();
			String sql = 
					"select * from product where productCode like 'H%'"; //핫도그는 H
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			System.out.println("a");
			while(model.getRowCount() != 0) //갱신하기위해 row = 0 이 될때 까지 model을 먼저 삭제
			{
				model.removeRow(0);
			}
			
			while(rs.next())
			{
				String list[] = {	rs.getString(1), rs.getString(2), rs.getString(3) };
				model.addRow(list); //데이터 넣기
			}
			
		
		}catch(SQLException e)
		{
			System.out.println("갱신 실패");
			closeDatabase();
		}	
	}
	
	/**Side 기준으로 보기 **/
	public void sideView(DefaultTableModel model)
	{
		try {
			con = getConnect();
			String sql = "select * from product where productCode like 'S%'"; //사이드는 S
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(model.getRowCount() != 0) //갱신하기위해 row = 0 이 될때 까지 model을 먼저 삭제
			{
				model.removeRow(0);
			}
			
			while(rs.next())
			{
				String list[] = {	rs.getString(1), rs.getString(2), rs.getString(3) };
				model.addRow(list); //데이터 넣기
			}
			
		
		}catch(SQLException e)
		{
			System.out.println("갱신 실패");
			closeDatabase();
		}	
	}
	
	/**Beverage 기준으로 보기 **/
	public void beverageView(DefaultTableModel model)
	{
		try {
			con = getConnect();
			String sql = "select * from product where productCode like 'B%'"; //Beverage B 
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			System.out.println("a");
			while(model.getRowCount() != 0) //갱신하기위해 row = 0 이 될때 까지 model을 먼저 삭제
			{
				model.removeRow(0);
			}
			
			while(rs.next())
			{
				String list[] = {	rs.getString(1), rs.getString(2), rs.getString(3) };
				model.addRow(list); //데이터 넣기
			}
			
		
		}catch(SQLException e)
		{
			System.out.println("갱신 실패");
			closeDatabase();
		}	
	}
	
	/**제품 코드기준으로 검색 **/
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
			while(model.getRowCount() != 0) //갱신하기위해 row = 0 이 될때 까지 model을 먼저 삭제
			{
				model.removeRow(0);
			}
			if(rs.next()) { //행이 하나라도 있으면 진행
				do
				{
					String list[] = {	rs.getString(1), rs.getString(2), rs.getString(3) };
					model.addRow(list); //데이터 넣기
				}while(rs.next());
			}
			else //검색된 내용이 없다면?
			{
				JOptionPane.showMessageDialog(null,"검색된 결과가 존재하지 않습니다.","Message",JOptionPane.INFORMATION_MESSAGE);
			}
			
		
		}catch(SQLException e)
		{
			System.out.println("갱신 실패");
			closeDatabase();
		}	
	}
	
	
	/**제품명 기준으로 검색 **/
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
			while(model.getRowCount() != 0) //갱신하기위해 row = 0 이 될때 까지 model을 먼저 삭제
			{
				model.removeRow(0);
			}
			if(rs.next()) { //행이 하나라도 있으면 진행
				do
				{
					String list[] = {	rs.getString(1), rs.getString(2), rs.getString(3) };
					model.addRow(list); //데이터 넣기
				}while(rs.next());
			}
			else //검색된 내용이 없다면?
			{
				JOptionPane.showMessageDialog(null,"검색된 결과가 존재하지 않습니다.","Message",JOptionPane.INFORMATION_MESSAGE);
			}
			
		
		}catch(SQLException e)
		{
			System.out.println("갱신 실패");
			closeDatabase();
		}	
	}
	
	/**가격 기준으로 검색 **/
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
			while(model.getRowCount() != 0) //갱신하기위해 row = 0 이 될때 까지 model을 먼저 삭제
			{
				model.removeRow(0);
			}
			if(rs.next()) { //행이 하나라도 있으면 진행
				do
				{
					String list[] = {	rs.getString(1), rs.getString(2), rs.getString(3) };
					model.addRow(list); //데이터 넣기
				}while(rs.next());
			}
			else //검색된 내용이 없다면?
			{
				JOptionPane.showMessageDialog(null,"검색된 결과가 존재하지 않습니다.","Message",JOptionPane.INFORMATION_MESSAGE);
			}
			
		
		}catch(SQLException e)
		{
			System.out.println("갱신 실패");
			closeDatabase();
		}	
	}
	
	/**전체 검색 **/
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
			while(model.getRowCount() != 0) //갱신하기위해 row = 0 이 될때 까지 model을 먼저 삭제
			{
				model.removeRow(0);
			}
			if(rs.next()) { //행이 하나라도 있으면 진행
				do
				{
					String list[] = {	rs.getString(1), rs.getString(2), rs.getString(3) };
					model.addRow(list); //데이터 넣기
				}while(rs.next());
			}
			else //검색된 내용이 없다면?
			{
				JOptionPane.showMessageDialog(null,"검색된 결과가 존재하지 않습니다.","Message",JOptionPane.INFORMATION_MESSAGE);
			}
			
		
		}catch(SQLException e)
		{
			System.out.println("갱신 실패");
			closeDatabase();
		}	
	}
	
	
	/** 삭제 **/
	
	public void Delete(DefaultTableModel model,String keyword)
	{
		int result = JOptionPane.showConfirmDialog(null,"정말 삭제하시겠습니까?","삭제",JOptionPane.YES_NO_OPTION); // 확인은 0 리턴
		
		if(result == 0) { //확인 버튼을 누르면
			try {
				con = getConnect();
				String sql = "delete from Product where productCode = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, keyword); 
				int res = ps.executeUpdate();
				
				if(res > 0) {//삭제 됨
					JOptionPane.showMessageDialog(null,"삭제되었습니다.","Message",JOptionPane.INFORMATION_MESSAGE);
					EveryView(model); //삭제 성공시 자동초기화
				}
				else //삭제되지않음
					JOptionPane.showMessageDialog(null,"삭제되지 않았습니다.","Message",JOptionPane.INFORMATION_MESSAGE);
				
			}catch(SQLException e)
			{
				System.out.println("갱신 실패");
				closeDatabase();
			}
			//return true;
		}
		//else
			//return false;
	}
	
	/** 제품 추가 **/
	public boolean insertProduct(DTO_Product dto)
	{
		boolean ok = false; //insert성공여부 확인 요
		
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
				JOptionPane.showMessageDialog(null, "추가 성공","Insert Success",JOptionPane.INFORMATION_MESSAGE);
				ok = true;
				MenuManager.productDAO.EveryView(MenuManager.ManagerTableModel); //추가 성공시 화면에 테이블 초기화

			}
			else 
				JOptionPane.showMessageDialog(null, "추가 실패","Insert Failed",JOptionPane.ERROR_MESSAGE);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		closeDatabase();
		return ok;
	}
	
	/** 제품 업데이트(수정) **/
	public boolean updateProduct(DTO_Product dto)
	{
		boolean ok = false; //insert성공여부 확인 요
		
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
				JOptionPane.showMessageDialog(null, "업데이트 성공","Update Success",JOptionPane.INFORMATION_MESSAGE);
				ok = true;
				MenuManager.productDAO.EveryView(MenuManager.ManagerTableModel); //업데이트 성공시 초기화 
			}
			else 
				JOptionPane.showMessageDialog(null, "업데이트 실패","Update Failed",JOptionPane.ERROR_MESSAGE);
			
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

public class project1_DAO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}


