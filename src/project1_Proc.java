
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

class MemberSignUp extends JFrame //ȸ������ â
{
	JLabel SignUpInfo = new JLabel("ȸ�� ����",JLabel.CENTER);
	JLabel[] SignUpLabel = new JLabel[5];
	String SignUpLabelContent[] = {"���̵�: ","�н�����: ","�̸�: ","����: ","�ּ�: "};
	
	//id,name,age,address �ؽ�Ʈ �ʵ�
	JTextField SignUpIDtextField = new JTextField(20);
	JTextField SingUpNameTextField = new JTextField(20);
	JTextField SignUpAgeTextField= new JTextField(20);
	JTextField SignUpAddressTextField = new JTextField(20);
	
	JPasswordField SignUpPWtextField = new JPasswordField(20); //password �ؽ�Ʈ �ʵ�
	
	//id�ߺ�üũ, Ȯ��, ��� ��ư
	JButton SignUpIdCheckButton = new JButton("ID�ߺ�Ȯ��");
	JButton SignUpSubmitButton = new JButton("Ȯ��");
	JButton SignUpCancelButton = new JButton("���");
	
	//���̾ƿ� ����
	GridBagLayout SingUpGridBagLayout = new GridBagLayout();
	GridBagConstraints SignUpGridBagConstraints = new GridBagConstraints();
	
	JPanel SignUpButtonPanel = new JPanel();
	//��ư Action���� �����ؾ���
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
		
		// ����
		GridBagConstraintAdd(SignUpInfo,0,0,4,1);
		
		// ���̵�
		GridBagConstraintAdd(SignUpLabel[0],0,1,1,1); 
		GridBagConstraintAdd(SignUpIDtextField,1,1,2,1); 
		GridBagConstraintAdd(SignUpIdCheckButton,3,1,1,1); //���̵� üũ ��ư
		
		//���̵� üũ��ư Action
		SignUpIdCheckButton.addActionListener(new SignUpButtonActionListener());
		
		// ��й�ȣ
		GridBagConstraintAdd(SignUpLabel[1],0,2,1,1);
		GridBagConstraintAdd(SignUpPWtextField,1,2,2,1);
		
		// �̸�
		GridBagConstraintAdd(SignUpLabel[2],0,3,1,1); 
		GridBagConstraintAdd(SingUpNameTextField,1,3,2,1); 
		
		// ����
		GridBagConstraintAdd(SignUpLabel[3],0,4,1,1);
		GridBagConstraintAdd(SignUpAgeTextField,1,4,2,1);
		
		// �ּ�
		GridBagConstraintAdd(SignUpLabel[4],0,5,1,1);
		GridBagConstraintAdd(SignUpAddressTextField,1,5,4,1);
		
		// Ȯ�� ��� ��ư
		SignUpButtonPanel.add(SignUpSubmitButton);
		SignUpButtonPanel.add(SignUpCancelButton);
		GridBagConstraintAdd(SignUpButtonPanel,2,6,1,1); //Ȯ�� ��ҹ�ư �ΰ��� �ϳ��� �гο� �ְ� add
		
		// Ȯ�� ��� ��ư Action
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
	//SignUp â ActionListener
	class SignUpButtonActionListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e)
		{
			JButton jb = (JButton)e.getSource();
			
			if(jb.getText().equals("ID�ߺ�Ȯ��")) //ID��ȿ���˻� -> ID �ߺ��˻� ����
			{
				boolean ID_VALIDATION_CHECK = memberDTO.setMemberID(LogIn.memberSignUpWindow.SignUpIDtextField.getText()); //ID��ȿ�� �˻� ����
				
				if(ID_VALIDATION_CHECK) //ID ��ȿ�� �˻� ��� �� 
					memberDAO.SingInIDcheck(memberDTO); //ID �ߺ��˻� ����
				System.out.println("ID��ȿ�� �˻� ���: "+ID_VALIDATION_CHECK);
			}
			else if(jb.getText().equals("Ȯ��")) //Ȯ�� ��ư�� �������� ID�ߺ�Ȯ�� action�� ���������� ����Ǿ����� Ȯ���Ѵ�.
			{
				//ID �ߺ�Ȯ�� �������� �ؽ�Ʈ�� ������ �� �����Ƿ� �ٽ� ID�ߺ����� Ȯ�� 
					if(memberDTO.DTO_Setting(LogIn.memberSignUpWindow)) //��ȿ�� �˻� ���
					{
						if(memberDAO.SingInIDcheck(memberDTO)) //ID �ߺ��˻� ����
							if(memberDAO.SingInProgress(memberDTO)) //ȸ�������͸� member ���̺� ����
								dispose();
					}
			
			}
			else if(jb.getText().equals("���"))
			{
				dispose();
			}
		}
	}
	
}

//�α��� â
class LogIn extends JFrame
{
	private JLabel LoginTitle = new JLabel("STAFF HOTDOG",JLabel.CENTER);
	private JTextField LoginID = new JTextField("���̵�",20);
	private JPasswordField LoginPW = new JPasswordField("��й�ȣ",20);
	
	private JPanel LoginIDPanel = new JPanel(new BorderLayout());
	private JPanel LoginPWPanel = new JPanel(new BorderLayout());
	
	JButton LoginSubmit = new JButton("Ȯ��");
	JButton LoginSignIn = new JButton("ȸ������");
	JPanel LoginButtonPanel = new JPanel(); 
	
	public static MemberSignUp memberSignUpWindow ; 
	
	LogIn(String title)
	{
		super(title);
		setSize(400,350);
		setLayout(new GridLayout(4,1));
		
		// LoginTitle ����
		add(LoginTitle);
		LoginTitle.setFont(new Font("���� ���",Font.BOLD,30));
		LoginTitle.setForeground(Color.RED);
		
		// LoginID/PW �ؽ�Ʈ �ʵ� �̺�Ʈ ����
 		LoginIDPanel.add(LoginID,"Center"); LoginID.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
		LoginPWPanel.add(LoginPW,"Center"); LoginPW.setBorder(BorderFactory.createEmptyBorder(0,10,0,10));
		LoginIDPanel.setBorder(BorderFactory.createEmptyBorder(10,80,10,80));
		LoginPWPanel.setBorder(BorderFactory.createEmptyBorder(10,80,10,80));
		
		// LoginID/PW �ؽ�Ʈ �ʵ� �̺�Ʈ ����
		LoginPW.setEchoChar((char) 0); //���� ���̰���
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
	
	//�α��� �ؽ�Ʈ �ʵ� ���콺 �̺�Ʈ
	class LoginTextFieldMouseAdaptor extends MouseAdapter
	{
		public void mouseExited(MouseEvent e) // ���콺 Ŭ�� �� �ٸ� ������Ʈ �����µ� �ش��ʵ尡 ��������� ���̵� ��й�ȣ �ʵ� �ĺ��� ���� �ؽ�Ʈ ����
		{
			if(e.getSource() == LoginID)
			{
				if(LoginID.getText().length() == 0)
					LoginID.setText("���̵�");
			}
			else if(e.getSource() == LoginPW)
			{
				if(LoginPW.getPassword().length == 0)
				{
					LoginPW.setText("��й�ȣ");
					LoginPW.setEchoChar((char) 0); //���� ���̰���
				}
			}
		}
		public void mouseClicked(MouseEvent e) //���콺 Ŭ���Ǹ� �ٷ� ó������ �Է°����ϰ� ��
		{
			if(e.getSource() == LoginID)
			{
				LoginID.setText("");
			}
			else if(e.getSource() == LoginPW)
			{
				LoginPW.setText("");
				LoginPW.setEchoChar('*'); //���� *ó��
			}
		}
	}
	
	
	//�α��� ��ư ActionListener
	class LoginButtonActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			JButton bt =(JButton)e.getSource();
			
			if(bt.getText().equals("Ȯ��")) //�α���â�� Ȯ�ι�ư ���� ���
			{
				Member_DAO memberDAO = new Member_DAO();
				Boolean check = memberDAO.LoginProgress(LoginID.getText(),String.valueOf(LoginPW.getPassword()));
				
				if(check) //�α����� ���������� �̷�������Ƿ� �α��� â�� �ݾƵ���
					dispose();
				
			}
			else if(bt.getText().equals("ȸ������")) //�α���â�� ȸ������ ��ư ���� ���
			{
				System.out.println("ȸ������â");
				memberSignUpWindow = new MemberSignUp("ȸ�� ���� â"); //ȸ�� ���� â ����
				
			}
		}
	}
	
}


//�޴� ����â
class MenuManager extends JFrame 
{
	private JLabel MenuTitle = new JLabel("Staff Hotdog Menu",JLabel.CENTER); //�޴�����â �ΰ�
	private String[] MenuContent = {"�޴�","�ֵ���","���̵�","����"};
	private JComboBox<String> MenuBox = new JComboBox<String>(MenuContent);
	private JPanel NorthPanel = new JPanel(new GridLayout(2,1,0,10)); //���� 0 ���� 10����
	private JPanel NorthComponent = new JPanel(new GridLayout(1,2)); //�޺����� �����г� �¿쿩�� 60,10
	private JPanel NorthMenuBoxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	private JPanel NorthButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	public JButton InsertButton = new JButton("�߰�");
	public JButton EditButton = new JButton("����");
	public JButton DeleteButton = new JButton("����");
	//Center �г�
	private JPanel CenterPanel = new JPanel();
	
	static DefaultTableModel ManagerTableModel = new DefaultTableModel(new String[] { "��ǰ�ڵ�", "��ǰ��", "����"}, 0)
	{
		@Override
		public boolean isCellEditable(int row, int column)
		{
			return false;
		}
	};
	static JTable jTable = new JTable(ManagerTableModel);
	private JScrollPane jScrollPane = new JScrollPane(jTable);
	
	//South �г�
	private JPanel SouthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT,60,10)); //�¿� �����ֱ����� �⺻�г�
	static JTextField SearchTextLine = new JTextField("�˻��� ���� �Է�"); //������ �ؽ�Ʈ ����
	static JComboBox<String> SearchBox = new JComboBox<String>(new String[] {"��ǰ �ڵ�","��ǰ��","����","��ü �˻�"});
	private JButton SearchButton = new JButton("�˻�");
	
	private JPanel SouthComponent = new JPanel(new FlowLayout()); //South������Ʈ ��� ���� 
	
	static product_DAO productDAO = null;
	
	MenuManager(String title)
	{
		super(title);
		setSize(800,650);
		setLayout(new BorderLayout(0,10)); //���� 0�ȼ� ���� 10�ȼ� ����
		
		productDAO = new product_DAO();
		productDAO.EveryView(ManagerTableModel); //��ü������ �޾ƿ���
		jTable.getColumnModel().getColumn(0).setPreferredWidth(5); //�÷� �ʺ�����
		jTable.getColumnModel().getColumn(2).setPreferredWidth(5); 
		
		//North ����
		add(NorthPanel,"North");
		//MenuTitle 
		MenuTitle.setFont(new Font("���� ���",Font.BOLD,30));
		NorthPanel.add(MenuTitle);
		//MenuBox�� �߰� ���� ���� ��ư
		NorthPanel.add(NorthComponent);
		NorthComponent.add(NorthMenuBoxPanel);
		NorthComponent.add(NorthButtonPanel);
		NorthMenuBoxPanel.add(MenuBox);
		MenuBox.setPreferredSize(new Dimension(100,30));
		MenuBox.addActionListener(new MenuManagerMenuBoxAcionHandler()); //�޴��ڽ� �̺�Ʈ ó���� �ش� Ŭ�������� ����
		NorthButtonPanel.add(InsertButton); NorthButtonPanel.add(EditButton);
		NorthButtonPanel.add(DeleteButton);
		
		//ButtonListener
		InsertButton.addActionListener(new MenuManagerButtonActionHandler());
		EditButton.addActionListener(new MenuManagerButtonActionHandler());
		DeleteButton.addActionListener(new MenuManagerButtonActionHandler());
		
		//MenuBox�� �߰� ���� ���� ��ư�� �ִ� �гε��� ���� ���� ���� (��,����,�Ʒ�,������)
		NorthMenuBoxPanel.setBorder(BorderFactory.createEmptyBorder(0,161,0,0));
		NorthButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 161));
		
		//Center ����
		// ���̺� �߰�
		jTable.getTableHeader().setReorderingAllowed(false); //���̺� �÷� ���� ���� ����
		jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		add(CenterPanel,"Center");
		CenterPanel.add(jScrollPane);
		
		//South ����
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

class MenuManagerMenuBoxAcionHandler implements ActionListener //ȭ�� North�� menubox���� �̷������ �̺�Ʈ ó��
{
	public void actionPerformed(ActionEvent e)
	{
		JComboBox bx = (JComboBox)e.getSource(); //e�� object���̹Ƿ� ��ȯ
		
		if(bx.getSelectedItem().equals("�޴�"))
		{
			MenuManager.productDAO.EveryView(MenuManager.ManagerTableModel);
		}
		else if(bx.getSelectedItem().equals("�ֵ���"))
		{
			MenuManager.productDAO.hotdogView(MenuManager.ManagerTableModel);
		}
		else if(bx.getSelectedItem().equals("���̵�"))
		{
			MenuManager.productDAO.sideView(MenuManager.ManagerTableModel);
		}
		else if(bx.getSelectedItem().equals("����"))
		{
			MenuManager.productDAO.beverageView(MenuManager.ManagerTableModel);
		}
	}
}


class MenuMangerSearchBoxActionHandler implements ActionListener //searchbox���� �̷������ �̺�Ʈó��
{
	public void actionPerformed(ActionEvent e)
	{
		JButton bx = (JButton)e.getSource();
		
		if(bx.getText().equals("�˻�"))
		{
			if(MenuManager.SearchBox.getSelectedItem().equals("��ǰ �ڵ�")){
				System.out.println(MenuManager.SearchTextLine.getText());
				MenuManager.productDAO.ProductCodeSearch(MenuManager.ManagerTableModel, MenuManager.SearchTextLine.getText());
			}
			else if(MenuManager.SearchBox.getSelectedItem().equals("��ǰ��"))
			{
				MenuManager.productDAO.ProductNameSearch(MenuManager.ManagerTableModel, MenuManager.SearchTextLine.getText());
			}
			else if(MenuManager.SearchBox.getSelectedItem().equals("����"))
			{
				MenuManager.productDAO.ProductPriceSearch(MenuManager.ManagerTableModel, MenuManager.SearchTextLine.getText());
			}
			else if(MenuManager.SearchBox.getSelectedItem().equals("��ü �˻�"))
			{
				MenuManager.productDAO.ProductEverySearch(MenuManager.ManagerTableModel, MenuManager.SearchTextLine.getText());
			}
			
		}
	}
}

class MenuManagerButtonActionHandler implements ActionListener //�߰� ���� ���� ��ư Actionó��
{
	static MenuManagerButtonWindow MW = null; //subâ
	
	public void actionPerformed(ActionEvent e)
	{
		JButton bx = (JButton)e.getSource();
		
		if(bx.getText().equals("�߰�"))
		{
			MW = new MenuManagerButtonWindow("�߰��� ����");
		}
		else if(bx.getText().equals("����"))
		{
			switch(MenuManager.jTable.getSelectedRowCount())//���õ� �ο� ����
			{
				//�÷� ������ ���� ���
				case 0: JOptionPane.showMessageDialog(null, "���õ� �÷��� �����ϴ�."); break;
				//�÷� ������ �ϳ� �Ѱ��
				case 1: String Code = 
						String.valueOf(MenuManager.ManagerTableModel.getValueAt(MenuManager.jTable.getSelectedRow(), 0));
						MW = new MenuManagerButtonWindow("������ ����");
						MW.subProductCodeText.setText(Code); 
						MW.subProductCodeText.setEnabled(false); //productCode�� ���� �Ұ�
						break;
				//�÷� ������ ������ ���
				default:
						JOptionPane.showMessageDialog(null, "�ϳ��� �÷��� �������ּ���."); MW.dispose();
						break;		
			}
//			int row = MenuManager.jTable.getSelectedRow();
//			int col = MenuManager.jTable.getColumn(1);
//			String Code = (String)MenuManager.jTable.getValueAt(row, col); //productCode ����
//			MW = new MenuManagerButtonWindow("������ ����");
//			MW.subProductCodeText.setText(Code); //������ �ڵ� ���� �˷� �� 
//			MW.subProductCodeText.setEnabled(false); //�����̹Ƿ� �Ұ�
		}
		else if(bx.getText().equals("����"))
		{
			switch(MenuManager.jTable.getSelectedRowCount())//���õ� �ο� ����
			{
				//�÷� ������ ���� ���
				case 0: JOptionPane.showMessageDialog(null, "���õ� �÷��� �����ϴ�."); break;
				//�÷� ������ �ϳ� �Ѱ��
				case 1: String Code = 
						String.valueOf(MenuManager.ManagerTableModel.getValueAt(MenuManager.jTable.getSelectedRow(), 0));
						MenuManager.productDAO.Delete(MenuManager.ManagerTableModel, Code);
						break;
				//�÷� ������ ������ ���
				default:
						JOptionPane.showMessageDialog(null, "�ϳ��� �÷��� �������ּ���.");
						break;		
			}
		}
	}
}

class MenuManagerButtonWindow extends JFrame //�߰� ���� ���� ��ư ����â
{	
	//��
	static JLabel subProductTitle = null; //title�� ������ �޾� ����
	private JLabel subProductCodeLabel = new JLabel("��ǰ �ڵ�",Label.RIGHT);
	private JLabel subProductNameLabel = new JLabel("��ǰ��",Label.RIGHT);
	private JLabel subProductPriceLabel = new JLabel("����",Label.RIGHT);
	
	//�ؽ�Ʈ �ʵ�
	JTextField subProductCodeText = new JTextField("�ֵ���: H100 | ���̵�: S200 | ����: B300",20);
	JTextField subProductNameText = new JTextField("4���� �̻�",20);
	JTextField subProductPriceText = new JTextField("0",20);
	
	private JPanel subCodePanel = new JPanel();
	private JPanel subNamePanel = new JPanel();
	private JPanel subPricePanel = new JPanel();
	
	private JPanel subCenterPanel = new JPanel(new GridLayout(3,1));
	
	JButton subOkButton = new JButton("Ȯ��");
	JButton subCancelButton = new JButton("���");
	private JPanel subButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
	
	
	MenuManagerButtonWindow(String title) //�߰� ���� ����
	{
		super(title);
		setSize(400,300);
		setLayout(new BorderLayout(10,20));
		
		subProductTitle  = new JLabel(title,JLabel.CENTER);
		subProductTitle.setFont(new Font("���� ���",Font.BOLD,25));
		
		add(subProductTitle,"North");
		
		//3�� 1�� �гο� ������ �࿡ �г� �ϳ��� �� �гδ� ������Ʈ�� �ΰ���
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
		//BorderFactory�̿��� ���� ���� ����
		subCenterPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		//new Dimension�� �̿��� �ؽ�Ʈ �ʵ� ũ�� ����
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
			if(bt.getText().equals("Ȯ��"))
			{
				//�Է��� ���뿡 ���� ��ȿ�� �˻� ����
				DTO_Product DTO = new DTO_Product();
				Boolean checkCode = DTO.setProductCode(subProductCodeText.getText());
				Boolean checkName = DTO.setProductName(subProductNameText.getText());
				Boolean checkPrice = DTO.setProductPrice(subProductPriceText.getText());
				
				if(checkCode && checkName && checkPrice) { //��� �ʵ尪�� ��ȿ�ϴٸ� ����
						if(subProductTitle.getText().equals("�߰��� ����"))
						{
							MenuManager.productDAO.insertProduct(DTO);
							dispose();
						}
						else if(subProductTitle.getText().equals("������ ����"))
						{
							MenuManager.productDAO.updateProduct(DTO);
							dispose();
						}
				}
			}
			else if(bt.getText().equals("���"))
			{
				dispose(); //ȭ�� �ݱ�
			}
		}
	}
}
public class project1_Proc {

	public static void main(String[] args) {
		//MemberSignUp test = new MemberSignUp("ȸ�� ����");
		LogIn test1 = new LogIn("�α��� â");
		//MenuManager test2 = new MenuManager("�޴� ���� â");
		//MenuManagerButtonWindow aa = new MenuManagerButtonWindow("�߰��� ����");
		
	}

}
