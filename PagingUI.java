import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class PagingUI extends JPanel implements ActionListener {
	boolean B1=true;
	boolean B2 =false;
	boolean B3 = false;
	boolean B4 = true;
	JPanel InputPanel1 = new JPanel(new GridLayout(2,2));
	JPanel InputPanel2 = new JPanel(new GridLayout(2,2));
	JPanel InputPanel = new JPanel(new GridLayout(8,2));
	JPanel OutputPanel = new JPanel(new GridLayout(5,1));
	JPanel ModePanel = new JPanel(new GridLayout(1,2));
	JPanel Display1 = new JPanel(new GridLayout(1,1));
	JPanel Display2 = new JPanel(new GridLayout(1,1));
	String t1,t2,t3,t4,t5;
	Page page = new Page();
	PageMap page2 = new PageMap();
	JTextField pageSizetf = new JTextField("10");
	JTextField logAddBittf = new JTextField("13");
	JTextField phyAddBittf = new JTextField("12");
	JLabel cal1 = new JLabel("Logical Space Size = "+page.logicalSizebyte+" byte");
	JLabel cal2 = new JLabel("Physical Space Size = "+page.physicalSizebyte+" byte");
	JLabel cal3 = new JLabel("Page Size = "+ page.pageSizeByte+" byte");
	JLabel cal4 = new JLabel("Number of Page = "+page.pageNumByte+" byte");
	JLabel cal5 = new JLabel("Number of Frame = "+page.frameNumByte+" byte");
	
	JLabel pageSizelb = new JLabel("  Page Size (2^n) n: ");
	JLabel logAddBitlb = new JLabel("  Logical Space Size (2^N) N: ");
	JLabel phyAddBitlb = new JLabel("  Physical Space Size (2^M) M: ");
	JLabel offset = new JLabel("  *Frame Size = Page Size ",JLabel.CENTER);
	JButton blank = new JButton();
	JButton blank2 = new JButton();
	JButton Submit = new JButton("Submit");
	
	JButton Mode1 = new JButton("Page and Frame Size Calculation");
	JButton Mode2 = new JButton("Modify and Retrieve Example");
	
	JPanel Mode2Panel1 = new JPanel(new GridLayout(1,2));
	JPanel Mode2Panel2 = new JPanel(new GridLayout(2,2));
	JLabel dataModelb = new JLabel("Choose your opearation",JLabel.CENTER);
	JLabel modLogAdd = new JLabel("Logical Address in Decimal =");
	JLabel data = new JLabel("New Data");
	JTextField modLogAddtf = new JTextField("0");
	JTextField datatf  = new JTextField("a");
	JButton retrive = new JButton("Retrive Data");
	JButton modify = new JButton("Modify Data");
	JButton submit2 = new JButton("Process");
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		super.setBackground(Color.WHITE);
}
	
	PagingUI(){
		
		t1="Logical Space Size = "+page.CalculateLogByte()+" byte";
		t2="Physical Space Size = "+page.CalculatePhyByte()+" byte";
		t3="Page Size = "+ page.CalculatePageByte()+" byte";
		t4="Number of Page = "+page.CalculatePageNumByte()+" byte";
		t5="Number of Frame = "+page.CalculateFrameNumByte()+" byte";
		super.setLayout(new BorderLayout());
		Display1.add(page);
		add(Display1);
		blank.setVisible(false);
		blank2.setVisible(false);
		Mode2Panel1.add(retrive);
		Mode2Panel1.add(modify);
		
		Mode2Panel2.add(modLogAdd);
		Mode2Panel2.add(modLogAddtf);
		Mode2Panel2.add(data);
		Mode2Panel2.add(datatf);
		
		InputPanel1.add(logAddBitlb);
		InputPanel1.add(logAddBittf);
		InputPanel1.add(pageSizelb);
		InputPanel1.add(pageSizetf);
		InputPanel2.add(phyAddBitlb);
		InputPanel2.add(phyAddBittf);
		InputPanel2.add(offset);
		
		OutputPanel.add(cal1);
		OutputPanel.add(cal2);
		OutputPanel.add(cal3);
		OutputPanel.add(cal4);
		OutputPanel.add(cal5);
		
		InputPanel.add(blank);
		InputPanel.add(InputPanel1);
		InputPanel.add(blank2);
		InputPanel.add(InputPanel2);
		InputPanel.add(Submit);
		InputPanel.add(OutputPanel);
		add(InputPanel,BorderLayout.WEST);
		
		
	
		ModePanel.add(Mode1);
		ModePanel.add(Mode2);
		add(ModePanel,BorderLayout.NORTH);
		Submit.addActionListener(this);
		Mode1.addActionListener(this);
		Mode2.addActionListener(this);
		retrive.addActionListener(this);
		modify.addActionListener(this);
		submit2.addActionListener(this);
		
	}
	
	
	
	@Override
	public void actionPerformed (ActionEvent e) {
		String s = e.getActionCommand();
		if(s.equals("Submit")) {
			page.logicalSpace=Integer.parseInt(logAddBittf.getText());
			page.physicalSpace=Integer.parseInt(phyAddBittf.getText());
			page.frameSize=Integer.parseInt(pageSizetf.getText());
			page.pageSize=Integer.parseInt(pageSizetf.getText());
			t1="Logical Space Size = "+page.CalculateLogByte()+" byte";
			t2="Physical Space Size = "+page.CalculatePhyByte()+" byte";
			t3="Page Size = "+ page.CalculatePageByte()+" byte";
			t4="Number of Page = "+page.CalculatePageNumByte()+" byte";
			t5="Number of Frame = "+page.CalculateFrameNumByte()+" byte";
		cal1.setText(t1);
		cal2.setText(t2);
		cal3.setText(t3);
		cal4.setText(t4);
		cal5.setText(t5);
			repaint();
		}
		else if(s.equals("Page and Frame Size Calculation")) {
			B1 = true; B2=false;
			Display1.removeAll();
			Display1.add(page);
			InputPanel.removeAll();
			InputPanel.add(blank);
			InputPanel.add(InputPanel1);
			InputPanel.add(blank2);
			InputPanel.add(InputPanel2);
			InputPanel.add(Submit);
			InputPanel.add(OutputPanel);
			page.logicalSpace=Integer.parseInt(logAddBittf.getText());
			page.physicalSpace=Integer.parseInt(phyAddBittf.getText());
			page.frameSize=Integer.parseInt(pageSizetf.getText());
			page.pageSize=Integer.parseInt(pageSizetf.getText());
			t1="Logical Space Size = "+page.CalculateLogByte();
			t2="Physical Space Size = "+page.CalculatePhyByte();
			t3="Page Size = "+ page.CalculatePageByte();
			t4="Number of Page = "+page.CalculatePageNumByte();
			t5="Number of Frame = "+page.CalculateFrameNumByte();
		cal1.setText(t1);
		cal2.setText(t2);
		cal3.setText(t3);
		cal4.setText(t4);
		cal5.setText(t5);
			repaint();
		}
		else if(s.equals("Modify and Retrieve Example")) {
			B1=false;
			B2=true;
			Display1.removeAll();
			Display1.add(page2);
			InputPanel.removeAll();
			InputPanel.add(dataModelb);
			InputPanel.add(Mode2Panel1);
			InputPanel.add(blank);
			InputPanel.add(Mode2Panel2);
			InputPanel.add(submit2);
			InputPanel.add(OutputPanel);
			page.logicalSpace=Integer.parseInt(logAddBittf.getText());
			page.physicalSpace=Integer.parseInt(phyAddBittf.getText());
			page.frameSize=Integer.parseInt(pageSizetf.getText());
			page.pageSize=Integer.parseInt(pageSizetf.getText());
			t1="Logical Space Size = "+page2.page.CalculateLogByte();
			t2="Physical Space Size = "+page2.page.CalculatePhyByte();
			t3="Page Size = "+ page2.page.CalculatePageByte();
			t4="Number of Page = "+page2.page.CalculatePageNumByte();
			t5="Number of Frame = "+page2.page.CalculateFrameNumByte();
			cal1.setText(t1);
			cal2.setText(t2);
			cal3.setText(t3);
			cal4.setText(t4);
			cal5.setText(t5);
			repaint();
		}
		else if(s.equals("Retrive Data")) {
			Mode2Panel2.removeAll();
			Mode2Panel2.add(modLogAdd);
			Mode2Panel2.add(modLogAddtf);
			B3=true;
			B4=false;
			InputPanel.removeAll();
			InputPanel.add(dataModelb);
			InputPanel.add(Mode2Panel1);
			InputPanel.add(blank);
			InputPanel.add(Mode2Panel2);
			InputPanel.add(submit2);
			InputPanel.add(OutputPanel);
			repaint();
		}
		else if(s.equals("Modify Data")) {
			Mode2Panel2.removeAll();
			Mode2Panel2.add(modLogAdd);
			Mode2Panel2.add(modLogAddtf);
			Mode2Panel2.add(data);
			Mode2Panel2.add(datatf);
			InputPanel.removeAll();
			InputPanel.add(dataModelb);
			InputPanel.add(Mode2Panel1);
			InputPanel.add(blank);
			InputPanel.add(Mode2Panel2);
			InputPanel.add(submit2);
			InputPanel.add(OutputPanel);
			B3=false;
			B4=true;
			repaint();
			
		}
		else if(s.equals("Process") && B3) {
			page2.RetrieveData(Integer.parseInt(modLogAddtf.getText()));
			repaint();
			System.out.println("Doooooo");
			
		}
		else if(s.equals("Process") && B4) {
			page2.ModifyData(Integer.parseInt(modLogAddtf.getText()),datatf.getText());
			repaint();
			System.out.println("Doooooo This");
		}
	}
}
