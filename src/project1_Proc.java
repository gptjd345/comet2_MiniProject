
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

class MemberSignUp extends JFrame //회원가입 창
{
	JLabel SignUpInfo = new JLabel("회원 정보",JLabel.CENTER);
	JLabel[] SignUpLabel = new JLabel[5];
	String SignUpLabelContent[] = {"아이디: ","패스워드: ","이름: ","나이: ","주소: "};
	
	//id,name,age,address 텍스트 필드
	JTextField SignUpIDtextField = new JTextField(20);
	JTextField SingUpNameTextField = new JTextField(20);
	JTextField SignUpAgeTextField= new JTextField(20);
	JTextField SignUpAddressTextField = new JTextField(20);
	
	JPasswordField SignUpPWtextField = new JPasswordField(20); //password 텍스트 필드
	
	//id중복체크, 확인, 취소 버튼
	JButton SignUpIdCheckButton = new JButton("ID중복확인");
	JButton SignUpSubmitButton = new JButton("확인");
	JButton SignUpCancelButton = new JButton("취소");
	
	//레이아웃 설정
	GridBagLayout SingUpGridBagLayout = new GridBagLayout();
	GridBagConstraints SignUpGridBagConstraints = new GridBagConstraints();
	
	JPanel SignUpButtonPanel = new JPanel();
	//버튼 Action에서 참조해야함
	Member_DAO memberDAO = new Member_DAO(); 
	DTO_Member memberDTO = new DTO_Member();
	
	MemberSignUp(String title)
	{
		super(title);
		setSize(400,350);
		setLayout(SingUpGridBagLayout);
		SignUpGridBagConstraints.fill = GridBagConstraints.BOTH;
		SignUpGridBagConstraints.weightx = 1.0;
		SignUpGridBagConstraints.weighty = 1.0;
		
		for(int i=0;i<SignUpLabel.length;i++) 
			SignUpLabel[i] = new JLabel(SignUpLabelContent[i]); 
		
		// 제목
		GridBagConstraintAdd(SignUpInfo,0,0,4,1);
		
		// 아이디
		GridBagConstraintAdd(SignUpLabel[0],0,1,1,1); 
		GridBagConstraintAdd(SignUpIDtextField,1,1,2,1); 
		GridBagConstraintAdd(SignUpIdCheckButton,3,1,1,1); //아이디 체크 버튼
		
		//아이디 체크버튼 Action
		SignUpIdCheckButton.addActionListener(new SignUpButtonActionListener());
		
		// 비밀번호
		GridBagConstraintAdd(SignUpLabel[1],0,2,1,1);
		GridBagConstraintAdd(SignUpPWtextField,1,2,2,1);
		
		// 이름
		GridBagConstraintAdd(SignUpLabel[2],0,3,1,1); 
		GridBagConstraintAdd(SingUpNameTextField,1,3,2,1); 
		
		// 나이
		GridBagConstraintAdd(SignUpLabel[3],0,4,1,1);
		GridBagConstraintAdd(SignUpAgeTextField,1,4,2,1);
		
		// 주소
		GridBagConstraintAdd(SignUpLabel[4],0,5,1,1);
		GridBagConstraintAdd(SignUpAddressTextField,1,5,4,1);
		
		// 확인 취소 버튼
		SignUpButtonPanel.add(SignUpSubmitButton);
		SignUpButtonPanel.add(SignUpCancelButton);
		GridBagConstraintAdd(SignUpButtonPanel,2,6,1,1); //확인 취소버튼 두개를 하나에 패널에 넣고 add
		
		// 확인 취소 버튼 Action
		SignUpSubmitButton.addActionListener(new SignUpButtonActionListener());
		SignUpCancelButton.addActionListener(new SignUpButtonActionListener());
			
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = (int)(screen.getWidth()/2 - super.getWidth()/2);
		int ypos = (int)(screen.getHeight()/2- super.getHeight()/2);
		setLocation(xpos,ypos);
		setResizable(false);
		
		setVisible(true);
	}


	public void GridBagConstraintAdd(JComponent JC,int x,int y ,int w,int h)
	{
		
		SignUpGridBagConstraints.gridx = x;
		SignUpGridBagConstraints.gridy = y;
		SignUpGridBagConstraints.gridwidth = w;
		SignUpGridBagConstraints.gridheight = h;
		
		SingUpGridBagLayout.setConstraints(JC,SignUpGridBagConstraints);
		SignUpGridBagConstraints.insets = new Insets(10,10,10,10);
		this.add(JC);
	}
	//SignUp 창 ActionListener
	class SignUpButtonActionListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e)
		{
			JButton jb = (JButton)e.getSource();
			
			if(jb.getText().equals("ID중복확인")) //ID유효성검사 -> ID 중복검사 수행
			{
				boolean ID_VALIDATION_CHECK = memberDTO.setMemberID(LogIn.memberSignUpWindow.SignUpIDtextField.getText()); //ID유효성 검사 수행
				
				if(ID_VALIDATION_CHECK) //ID 유효성 검사 통과 시 
					memberDAO.SingInIDcheck(memberDTO); //ID 중복검사 수행
				System.out.println("ID유효성 검사 결과: "+ID_VALIDATION_CHECK);
			}
			else if(jb.getText().equals("확인")) //확인 버튼이 눌렸을때 ID중복확인 action이 정상적으로 종료되었는지 확인한다.
			{
				//ID 중복확인 누르고나서 텍스트를 변경할 수 있으므로 다시 ID중복여부 확인 
					if(memberDTO.DTO_Setting(LogIn.memberSignUpWindow)) //유효성 검사 통과
					{
						if(memberDAO.SingInIDcheck(memberDTO)) //ID 중복검사 수행
							if(memberDAO.SingInProgress(memberDTO)) //회원데이터를 member 테이블에 저장
								dispose();
					}
			
			}
			else if(jb.getText().equals("취소"))
			{
				dispose();
			}
		}
	}
	
}

//로그인 창
class LogIn extends JFrame
{
	private JLabel LoginTitle = new JLabel("STAFF HOTDOG",JLabel.CENTER);
	private JTextField LoginID = new JTextField("아이디",20);
	private JPasswordField LoginPW = new JPasswordField("비밀번호",20);
	
	private JPanel LoginIDPanel = new JPanel(new BorderLayout());
	private JPanel LoginPWPanel = new JPanel(new BorderLayout());
	
	JButton LoginSubmit = new JButton("확인");
	JButton LoginSignIn = new JButton("회원가입");
	JPanel LoginButtonPanel = new JPanel(); 
	
	public static MemberSignUp memberSignUpWindow ; 
	
	LogIn(String title)
	{
		super(title);
		setSize(400,350);
		setLayout(new GridLayout(4,1));
		
		// LoginTitle 설정
		add(LoginTitle);
		LoginTitle.setFont(new Font("맑은 고딕",Font.BOLD,30));
		LoginTitle.setForeground(Color.RED);
		
		// LoginID/PW 텍스트 필드 이벤트 설정
 		LoginIDPanel.add(LoginID,"Center"); LoginID.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
		LoginPWPanel.add(LoginPW,"Center"); LoginPW.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
		LoginIDPanel.setBorder(BorderFactory.createEmptyBorder(10,80,10,80));
		LoginPWPanel.setBorder(BorderFactory.createEmptyBorder(10,80,10,80));
		
		// LoginID/PW 텍스트 필드 이벤트 설정
		LoginPW.setEchoChar((char) 0); //글자 보이게함
		LoginID.addMouseListener(new LoginTextFieldMouseAdaptor());
		LoginPW.addMouseListener(new LoginTextFieldMouseAdaptor());
		
		add(LoginIDPanel);
		add(LoginPWPanel); 
		
		// LoginButton
		add(LoginButtonPanel);
		LoginButtonPanel.add(LoginSubmit);
		LoginButtonPanel.add(LoginSignIn);
		LoginSubmit.setPreferredSize(new Dimension(115,30));
		LoginSignIn.setPreferredSize(new Dimension(115,30));
		
		// LoginButton Action
		LoginSubmit.addActionListener(new LoginButtonActionListener());
		LoginSignIn.addActionListener(new LoginButtonActionListener());
		
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = (int)(screen.getWidth()/2 - super.getWidth()/2);
		int ypos = (int)(screen.getHeight()/2- super.getHeight()/2);
		setLocation(xpos,ypos);
		setResizable(false);
		
		setVisible(true);
		
		
	}
	
	//로그인 텍스트 필드 마우스 이벤트
	class LoginTextFieldMouseAdaptor extends MouseAdapter
	{
		public void mouseExited(MouseEvent e) // 마우스 클릭 후 다른 컴포넌트 눌렀는데 해당필드가 비어있으면 아이디 비밀번호 필드 식별을 위해 텍스트 설정
		{
			if(e.getSource() == LoginID)
			{
				if(LoginID.getText().length() == 0)
					LoginID.setText("아이디");
			}
			else if(e.getSource() == LoginPW)
			{
				if(LoginPW.getPassword().length == 0)
				{
					LoginPW.setText("비밀번호");
					LoginPW.setEchoChar((char) 0); //글자 보이게함
				}
			}
		}
		public void mouseClicked(MouseEvent e) //마우스 클릭되면 바로 처음부터 입력가능하게 함
		{
			if(e.getSource() == LoginID)
			{
				LoginID.setText("");
			}
			else if(e.getSource() == LoginPW)
			{
				LoginPW.setText("");
				LoginPW.setEchoChar('*'); //글자 *처리
			}
		}
	}
	
	
	//로그인 버튼 ActionListener
	class LoginButtonActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JButton bt =(JButton)e.getSource();
			
			if(bt.getText().equals("확인")) //로그인창의 확인버튼 누른 경우
			{
				Member_DAO memberDAO = new Member_DAO();
				Boolean check = memberDAO.LoginProgress(LoginID.getText(),String.valueOf(LoginPW.getPassword()));
				
				if(check) //로그인이 성공적으로 이루어졌으므로 로그인 창은 닫아도됨
					dispose();
				
			}
			else if(bt.getText().equals("회원가입")) //로그인창의 회원가입 버튼 누른 경우
			{
				System.out.println("회원가입창");
				memberSignUpWindow = new MemberSignUp("회원 가입 창"); //회원 가입 창 열기
				
			}
		}
	}
	
}


//메뉴 관리창
class MenuManager extends JFrame 
{
	private JLabel MenuTitle = new JLabel("Staff Hotdog Menu",JLabel.CENTER); //메뉴관리창 로고
	private String[] MenuContent = {"메뉴","핫도그","사이드","음료"};
	private JComboBox<String> MenuBox = new JComboBox<String>(MenuContent);
	private JPanel NorthPanel = new JPanel(new GridLayout(2,1,0,10)); //가로 0 세로 10여백
	private JPanel NorthComponent = new JPanel(new GridLayout(1,2)); //콤보상자 넣을패널 좌우여백 60,10
	private JPanel NorthMenuBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel NorthButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	public JButton InsertButton = new JButton("추가");
	public JButton EditButton = new JButton("수정");
	public JButton DeleteButton = new JButton("삭제");
	//Center 패널
	private JPanel CenterPanel = new JPanel();
	
	static DefaultTableModel ManagerTableModel = new DefaultTableModel(new String[] { "제품코드", "제품명", "가격"}, 0)
	{
		@Override
		public boolean isCellEditable(int row, int column)
		{
			return false;
		}
	};
	static JTable jTable = new JTable(ManagerTableModel);
	private JScrollPane jScrollPane = new JScrollPane(jTable);
	
	//South 패널
	private JPanel SouthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,60,10)); //좌우 여백주기위한 기본패널
	static JTextField SearchTextLine = new JTextField("검색할 내용 입력"); //수정할 텍스트 라인
	static JComboBox<String> SearchBox = new JComboBox<String>(new String[] {"제품 코드","제품명","가격","전체 검색"});
	private JButton SearchButton = new JButton("검색");
	
	private JPanel SouthComponent = new JPanel(new FlowLayout()); //South컴포넌트 모두 넣음 
	
	static product_DAO productDAO = null;
	
	MenuManager(String title)
	{
		super(title);
		setSize(800,650);
		setLayout(new BorderLayout(0,10)); //가로 0픽셀 세로 10픽셀 여백
		
		productDAO = new product_DAO();
		productDAO.EveryView(ManagerTableModel); //전체데이터 받아오기
		jTable.getColumnModel().getColumn(0).setPreferredWidth(5); //컬럼 너비조절
		jTable.getColumnModel().getColumn(2).setPreferredWidth(5); 
		
		//North 설정
		add(NorthPanel,"North");
		//MenuTitle 
		MenuTitle.setFont(new Font("맑은 고딕",Font.BOLD,30));
		NorthPanel.add(MenuTitle);
		//MenuBox와 추가 수정 삭제 버튼
		NorthPanel.add(NorthComponent);
		NorthComponent.add(NorthMenuBoxPanel);
		NorthComponent.add(NorthButtonPanel);
		NorthMenuBoxPanel.add(MenuBox);
		MenuBox.setPreferredSize(new Dimension(100,30));
		MenuBox.addActionListener(new MenuManagerMenuBoxAcionHandler()); //메뉴박스 이벤트 처리는 해당 클래스에서 진행
		NorthButtonPanel.add(InsertButton); NorthButtonPanel.add(EditButton);
		NorthButtonPanel.add(DeleteButton);
		
		//ButtonListener
		InsertButton.addActionListener(new MenuManagerButtonActionHandler());
		EditButton.addActionListener(new MenuManagerButtonActionHandler());
		DeleteButton.addActionListener(new MenuManagerButtonActionHandler());
		
		//MenuBox와 추가 수정 삭제 버튼이 있는 패널들의 내부 여백 지정 (위,왼쪽,아래,오른쪽)
		NorthMenuBoxPanel.setBorder(BorderFactory.createEmptyBorder(0,161,0,0));
		NorthButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 161));
		
		//Center 설정
		// 테이블 추가
		jTable.getTableHeader().setReorderingAllowed(false); //테이블 컬럼 순서 변경 금지
		jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		add(CenterPanel,"Center");
		CenterPanel.add(jScrollPane);
		
		//South 설정
		add(SouthPanel,"South");
		SouthComponent.add(SearchBox);
		SearchTextLine.setPreferredSize(new Dimension(317,30));
		SouthComponent.add(SearchTextLine); 
		SouthComponent.add(SearchButton);
		SearchButton.addActionListener(new MenuMangerSearchBoxActionHandler());
		SouthPanel.add(SouthComponent);
		SouthComponent.setBorder(BorderFactory.createEmptyBorder(0, 97, 20, 0));
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = (int)(screen.getWidth()/2 - super.getWidth()/2);
		int ypos = (int)(screen.getHeight()/2- super.getHeight()/2);
		setLocation(xpos,ypos);
		setResizable(false);
		
		setVisible(true);
	}
}

class MenuManagerMenuBoxAcionHandler implements ActionListener //화면 North의 menubox에서 이루어지는 이벤트 처리
{
	public void actionPerformed(ActionEvent e)
	{
		JComboBox bx = (JComboBox)e.getSource(); //e는 object형이므로 변환
		
		if(bx.getSelectedItem().equals("메뉴"))
		{
			MenuManager.productDAO.EveryView(MenuManager.ManagerTableModel);
		}
		else if(bx.getSelectedItem().equals("핫도그"))
		{
			MenuManager.productDAO.hotdogView(MenuManager.ManagerTableModel);
		}
		else if(bx.getSelectedItem().equals("사이드"))
		{
			MenuManager.productDAO.sideView(MenuManager.ManagerTableModel);
		}
		else if(bx.getSelectedItem().equals("음료"))
		{
			MenuManager.productDAO.beverageView(MenuManager.ManagerTableModel);
		}
	}
}


class MenuMangerSearchBoxActionHandler implements ActionListener //searchbox에서 이루어지는 이벤트처리
{
	public void actionPerformed(ActionEvent e)
	{
		JButton bx = (JButton)e.getSource();
		
		if(bx.getText().equals("검색"))
		{
			if(MenuManager.SearchBox.getSelectedItem().equals("제품 코드")){
				System.out.println(MenuManager.SearchTextLine.getText());
				MenuManager.productDAO.ProductCodeSearch(MenuManager.ManagerTableModel, MenuManager.SearchTextLine.getText());
			}
			else if(MenuManager.SearchBox.getSelectedItem().equals("제품명"))
			{
				MenuManager.productDAO.ProductNameSearch(MenuManager.ManagerTableModel, MenuManager.SearchTextLine.getText());
			}
			else if(MenuManager.SearchBox.getSelectedItem().equals("가격"))
			{
				MenuManager.productDAO.ProductPriceSearch(MenuManager.ManagerTableModel, MenuManager.SearchTextLine.getText());
			}
			else if(MenuManager.SearchBox.getSelectedItem().equals("전체 검색"))
			{
				MenuManager.productDAO.ProductEverySearch(MenuManager.ManagerTableModel, MenuManager.SearchTextLine.getText());
			}
			
		}
	}
}

class MenuManagerButtonActionHandler implements ActionListener //추가 수정 삭제 버튼 Action처리
{
	static MenuManagerButtonWindow MW = null; //sub창
	
	public void actionPerformed(ActionEvent e)
	{
		JButton bx = (JButton)e.getSource();
		
		if(bx.getText().equals("추가"))
		{
			MW = new MenuManagerButtonWindow("추가할 내용");
		}
		else if(bx.getText().equals("수정"))
		{
			switch(MenuManager.jTable.getSelectedRowCount())//선택된 로우 개수
			{
				//컬럼 선택을 안한 경우
				case 0: JOptionPane.showMessageDialog(null, "선택된 컬럼이 없습니다."); break;
				//컬럼 선택을 하나 한경우
				case 1: String Code = 
						String.valueOf(MenuManager.ManagerTableModel.getValueAt(MenuManager.jTable.getSelectedRow(), 0));
						MW = new MenuManagerButtonWindow("수정할 내용");
						MW.subProductCodeText.setText(Code); 
						MW.subProductCodeText.setEnabled(false); //productCode는 수정 불가
						break;
				//컬럼 여러개 선택한 경우
				default:
						JOptionPane.showMessageDialog(null, "하나의 컬럼만 선택해주세요."); MW.dispose();
						break;		
			}
//			int row = MenuManager.jTable.getSelectedRow();
//			int col = MenuManager.jTable.getColumn(1);
//			String Code = (String)MenuManager.jTable.getValueAt(row, col); //productCode 저장
//			MW = new MenuManagerButtonWindow("수정할 내용");
//			MW.subProductCodeText.setText(Code); //수정할 코드 내용 알려 줌 
//			MW.subProductCodeText.setEnabled(false); //수정이므로 불가
		}
		else if(bx.getText().equals("삭제"))
		{
			switch(MenuManager.jTable.getSelectedRowCount())//선택된 로우 개수
			{
				//컬럼 선택을 안한 경우
				case 0: JOptionPane.showMessageDialog(null, "선택된 컬럼이 없습니다."); break;
				//컬럼 선택을 하나 한경우
				case 1: String Code = 
						String.valueOf(MenuManager.ManagerTableModel.getValueAt(MenuManager.jTable.getSelectedRow(), 0));
						MenuManager.productDAO.Delete(MenuManager.ManagerTableModel, Code);
						break;
				//컬럼 여러개 선택한 경우
				default:
						JOptionPane.showMessageDialog(null, "하나의 컬럼만 선택해주세요.");
						break;		
			}
		}
	}
}

class MenuManagerButtonWindow extends JFrame //추가 수정 삭제 버튼 관련창
{	
	//라벨
	static JLabel subProductTitle = null; //title은 변수를 받아 선언
	private JLabel subProductCodeLabel = new JLabel("제품 코드",Label.RIGHT);
	private JLabel subProductNameLabel = new JLabel("제품명",Label.RIGHT);
	private JLabel subProductPriceLabel = new JLabel("가격",Label.RIGHT);
	
	//텍스트 필드
	JTextField subProductCodeText = new JTextField("핫도그: H100 | 사이드: S200 | 음료: B300",20);
	JTextField subProductNameText = new JTextField("4글자 이상",20);
	JTextField subProductPriceText = new JTextField("0",20);
	
	private JPanel subCodePanel = new JPanel();
	private JPanel subNamePanel = new JPanel();
	private JPanel subPricePanel = new JPanel();
	
	private JPanel subCenterPanel = new JPanel(new GridLayout(3,1));
	
	JButton subOkButton = new JButton("확인");
	JButton subCancelButton = new JButton("취소");
	private JPanel subButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	
	
	MenuManagerButtonWindow(String title) //추가 수정 삭제
	{
		super(title);
		setSize(400,300);
		setLayout(new BorderLayout(10,20));
		
		subProductTitle  = new JLabel(title,JLabel.CENTER);
		subProductTitle.setFont(new Font("맑은 고딕",Font.BOLD,25));
		
		add(subProductTitle,"North");
		
		//3행 1열 패널에 각각의 행에 패널 하나씩 각 패널당 컴포넌트는 두개씩
		//ProductCode
		subCodePanel.add(subProductCodeLabel);
		subCodePanel.add(subProductCodeText);
		//ProductName
		subNamePanel.add(subProductNameLabel);
		subNamePanel.add(subProductNameText);
		//ProductPrice
		subPricePanel.add(subProductPriceLabel);
		subPricePanel.add(subProductPriceText);
		subProductPriceText.setHorizontalAlignment(JTextField.RIGHT);
		
		subCenterPanel.add(subCodePanel);
		subCenterPanel.add(subNamePanel);
		subCenterPanel.add(subPricePanel);
		add(subCenterPanel,"Center"); 
		
		//ButtonPanel
        subButtonPanel.add(subOkButton);
		subButtonPanel.add(subCancelButton);
		add(subButtonPanel,"South");
		
		//ButtonAction
		subOkButton.addActionListener(new subButtonListener());
		subCancelButton.addActionListener(new subButtonListener());
		//BorderFactory이용한 내부 여백 설정
		subCenterPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		//new Dimension을 이용한 텍스트 필드 크기 설정
		subProductCodeText.setPreferredSize(new Dimension(100,30));
		subProductNameText.setPreferredSize(new Dimension(100,30));
		subProductPriceText.setPreferredSize(new Dimension(100,30));
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = (int)(screen.getWidth()/2 - super.getWidth()/2);
		int ypos = (int)(screen.getHeight()/2- super.getHeight()/2);
		setLocation(xpos,ypos);
		setResizable(false);
		
		setVisible(true);
		
	}
	class subButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JButton bt = (JButton)e.getSource();
			if(bt.getText().equals("확인"))
			{
				//입력한 내용에 대한 유효성 검사 진행
				DTO_Product DTO = new DTO_Product();
				Boolean checkCode = DTO.setProductCode(subProductCodeText.getText());
				Boolean checkName = DTO.setProductName(subProductNameText.getText());
				Boolean checkPrice = DTO.setProductPrice(subProductPriceText.getText());
				
				if(checkCode && checkName && checkPrice) { //모든 필드값이 유효하다면 진행
						if(subProductTitle.getText().equals("추가할 내용"))
						{
							MenuManager.productDAO.insertProduct(DTO);
							dispose();
						}
						else if(subProductTitle.getText().equals("수정할 내용"))
						{
							MenuManager.productDAO.updateProduct(DTO);
							dispose();
						}
				}
			}
			else if(bt.getText().equals("취소"))
			{
				dispose(); //화면 닫기
			}
		}
	}
}
public class project1_Proc {

	public static void main(String[] args) {
		//MemberSignUp test = new MemberSignUp("회원 가입");
		LogIn test1 = new LogIn("로그인 창");
		//MenuManager test2 = new MenuManager("메뉴 관리 창");
		//MenuManagerButtonWindow aa = new MenuManagerButtonWindow("추가할 내용");
		
	}

}
