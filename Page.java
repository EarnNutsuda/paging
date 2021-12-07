import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.math.*;

public class Page extends JPanel{
	int logAdd =0;
	int phyAdd = 0;
	int offset = 0;
	int pageNumber = 1;
	int frameNumber =0;
	String Soffset="";
	String SpageNumber="";
	String SframeNumber="";
	public int logicalSizebyte=0;
	public int physicalSizebyte=0;
	public int pageSizeByte=0;
	public int pageNumByte=0;
	public int frameNumByte=0;
	public int physicalSpace = 0;
	public int logicalSpace = 0;
	int pageSize=0;
	int frameSize =0;
	int pageNum=0;
	int frameNum =0;
	int n;
	int m;
	int[] ptbLength;	
	int[] phyLength;
	Font font1 = new Font("Courier", Font.BOLD,15);
	int input = 0;
	int map;

	
	Page(){
		this.logicalSpace=13;
		this.physicalSpace = 12;
		this.pageSize = 10;
		this.frameSize = 10;
		pageNum = logicalSpace-pageSize;
		frameNum = physicalSpace-frameSize;
		n= (int) log2(pageNum);
		m=(int) log2(frameNum);
		logicalSizebyte = (int) Math.pow(2,logicalSpace);
		physicalSizebyte = (int) Math.pow(2, physicalSpace);
		pageSizeByte = (int) Math.pow(2, pageSize);
		pageNumByte=(int) Math.pow(2, pageNum);
		frameNumByte=(int) Math.pow(2, frameNum);
		
	}
	
	Page(int logSpa,int phySpa,int pageSize,int frameSize){
		this.logicalSpace=logSpa;
		this.physicalSpace = phySpa;
		this.pageSize = pageSize;
		this.frameSize = frameSize;
		pageNum = logicalSpace-pageSize;
		frameNum = physicalSpace-frameSize;
		n= (int) log2(pageNum);
		m=(int) log2(frameNum);
		logicalSizebyte = (int) Math.pow(2,logicalSpace);
		physicalSizebyte = (int) Math.pow(2, physicalSpace);
		pageSizeByte = (int) Math.pow(2, pageSize);
		pageNumByte=(int) Math.pow(2, pageNum);
		frameNumByte=(int) Math.pow(2, frameNum);
		
	}
	
	
	public static int log2(int N)
    {
  
        int result = (int)(Math.log(N) / Math.log(2));
  
        return result;
    }
	
	void PageTableLength(int space,int boxSize,int start){
		int total = (int) Math.pow(2, (space-pageSize));
		ptbLength = new int[total];
		int partial = (int)boxSize/total;
		for(int i =0;i<ptbLength.length;i++) {
			ptbLength[i]=start+partial*i;
		}
	}
	
	void phyTableLength(int space,int boxSize,int start){
		int total = (int) Math.pow(2, (space-pageSize));
		phyLength = new int[total];
		int partial = (int)boxSize/total;
		for(int i =0;i<phyLength.length;i++) {
			phyLength[i]=start+partial*i;
		}
	}
	
public int CalculateLogByte(){
	return (int) Math.pow(2,logicalSpace);
}
public int CalculatePhyByte(){
	return (int) Math.pow(2, physicalSpace);
}
public int CalculatePageByte(){
	return (int) Math.pow(2, pageSize);
}
public int CalculatePageNumByte(){
	return (int) Math.pow(2, pageNum);
}
public int CalculateFrameNumByte(){
	return (int) Math.pow(2, frameNum);
}

void GetOffset(int frameSize) {
	Soffset="";
	for(int i=0;i<frameSize-1;i++) {
		Soffset+=0;
	}
	Soffset+=1;
	
}

void GetPageNumber(int pageNum) {
	SpageNumber="";
	for(int i=0;i<pageNum-1;i++) {
		SpageNumber+=0;
	}
	
	SpageNumber+=1;
}

void GetFrameNumber(int frameNum) {
	SframeNumber="";
	for(int i=0;i<frameNum-1;i++) {
		SframeNumber+=0;
	}
	
	SframeNumber+=0;
}

	public void paintComponent(Graphics g) {
	
		if(logicalSpace>pageSize && physicalSpace>frameSize) {
		GetOffset(pageSize);
		GetPageNumber(logicalSpace-pageSize);
		GetFrameNumber(physicalSpace-pageSize);
		PageTableLength(logicalSpace,300,400);
		phyTableLength(physicalSpace,300,400);
		super.paintComponent(g);
		super.setBackground(Color.white);
		g.setFont(font1);
		
		//-------PageTable---------

		g.setColor(Color.blue);
		g.drawLine(150, 400, 350, 400);
		g.drawLine(150, 400, 150, 700);
		g.drawLine(150, 700, 350, 700);
		g.drawLine(350, 400, 350, 700);
		g.drawString("Page Table",190,730);
		g.drawString("Page Number",110,390);
		
		if((logicalSpace-pageSize)<5) {
		for(int i =0;i<ptbLength.length;i++) {
			g.drawLine(150, ptbLength[i], 350, ptbLength[i]);
			if(i<ptbLength.length-1)g.drawString(String.valueOf(i),135, (ptbLength[i]+ptbLength[i+1])/2);
			else g.drawString(String.valueOf(i),135, (ptbLength[i]+700)/2);
		}
		g.setColor(Color.orange);
		g.drawString("0",180,(ptbLength[1]+ptbLength[2])/2);
		g.setColor(Color.orange);
		g.drawLine(90, 150, 90, (ptbLength[1]+ptbLength[2])/2);
		g.drawLine(90, (ptbLength[1]+ptbLength[2])/2, 150, (ptbLength[1]+ptbLength[2])/2);
		g.setColor(Color.red);
		g.drawLine(300,(ptbLength[1]+ptbLength[2])/2,480,(phyLength[0]+phyLength[0+1])/2);
		g.drawLine(480,(phyLength[0]+phyLength[0+1])/2-10,500,150);}
		
		else {
			int[] b = {460,640};
			for(int i =0;i<b.length;i++) {
				g.drawLine(150, b[i], 350, b[i]);
				if(i==0)g.drawString("0",135, (b[i]+400)/2);
				if(i==b.length-1)g.drawString(String.valueOf(ptbLength.length-1),135, (b[i]+700)/2);
			}
			g.drawLine(150,(b[0]+40),350,(b[0]+40));
			g.fillOval(250, 530, 10, 10);
			g.fillOval(250, 570, 10, 10);
			g.fillOval(250, 600, 10, 10);
			g.drawString("1", 135, (b[0]+20));
			g.setColor(Color.orange);
			g.drawString("0",180,(b[0]+20));
			g.setColor(Color.orange);
			g.drawLine(90, 150, 90, b[0]+20);
			g.drawLine(90, b[0]+20, 150, b[0]+20);
			g.setColor(Color.red);
			g.drawLine(300,(b[0]+20),480,(phyLength[0]+phyLength[0+1])/2);
			g.drawLine(480,(phyLength[0]+phyLength[0+1])/2-10,500,150);
			
			
		}
		
		
		//-----Physical Memory -----------
		g.setColor(Color.MAGENTA);
		g.drawLine(500, 400, 700, 400);
		g.drawLine(500, 400, 500, 700);
		g.drawLine(500, 700, 700, 700);
		g.drawLine(700, 400, 700, 700);
		if((physicalSpace-pageSize)<5) {
		for(int i =0;i<phyLength.length;i++) {
			g.drawLine(500, phyLength[i], 700, phyLength[i]);
			if(i<phyLength.length-1)g.drawString(String.valueOf(i),480, (phyLength[i]+phyLength[i+1])/2);
			else g.drawString(String.valueOf(i),480, (phyLength[i]+700)/2);
		}
		}
		else {
			int[] b = {460,640};
			for(int i =0;i<b.length;i++) {
				g.drawLine(500, b[i], 700, b[i]);
				if(i==0)g.drawString("0",480, (b[i]+400)/2);
				if(i==b.length-1)g.drawString(String.valueOf(phyLength.length-1),480, (b[i]+700)/2);

			}
			g.fillOval(600, 530, 10, 10);
			g.fillOval(600, 565, 10, 10);
			g.fillOval(600, 600, 10, 10);
			
			
		
			
		}
		
		g.drawString("Physical Memory",520,730);
		g.drawString("Frame Number",450,390);

		//-------Logical Address Box-----
		g.setColor(Color.BLACK);
		g.drawString("Logical Address",100,60);
		g.drawLine(80, 100, 80, 150);
		g.drawLine(80, 100, 300, 100);
		g.drawLine(300, 100, 300, 150);
		g.drawLine(80, 150, 300, 150);
		g.drawLine(180, 100, 180, 150);
		//-------Physical Address Box-----
		g.setColor(Color.BLACK);
		g.drawString("Physical Address",480,60);
		g.drawLine(480, 100, 480, 150);
		g.drawLine(480, 100, 700, 100);
		g.drawLine(700, 100, 700, 150);
		g.drawLine(480, 150, 700, 150);
		g.drawLine(580, 100, 580, 150);
		//-------Bit Label-----
		g.setColor(Color.RED);
		g.drawString(String.valueOf(physicalSpace-frameSize)+" bit",510,175);
		g.drawString(String.valueOf(logicalSpace-pageSize)+" bit",105,175);
		g.drawString(String.valueOf(frameSize)+" bit",590,175);
		g.drawString(String.valueOf(pageSize)+" bit",195,175);
		g.drawString(String.valueOf(physicalSpace)+" bit",540,80);
		g.drawString(String.valueOf(logicalSpace)+" bit",140,80);
		
		g.drawLine(80, 90, 300, 90);
		g.drawLine(80, 85, 80, 95);
		g.drawLine(300, 85, 300, 95);
		
		g.drawLine(480, 90, 700, 90);
		g.drawLine(480, 85, 480, 95);
		g.drawLine(700, 85, 700, 95);
		//-----Address Label----
		g.setColor(Color.BLACK);
		g.drawString(SpageNumber,90,130);
		g.drawString(Soffset,190,130);
		g.drawString(SframeNumber,490,130);
		g.setColor(Color.green);
		g.fillRect(501, 408, 198, 5);
		g.drawString(Soffset,705,408);
		g.drawString(Soffset,590,130);
		
		repaint();
		}
		//Wrong Input
		else {g.drawString("Frame size must less than physical space.",140,80);
		g.drawString("Page size must less than logical space",140,100);}

}
	
}