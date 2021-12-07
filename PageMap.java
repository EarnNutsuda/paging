import java.util.Scanner;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.math.*;

public class PageMap extends JPanel {
	boolean wrong=false;
	boolean mod = false;
 int LogicalAddress[] = new int[16];
 int pagenumber;
 int start_address;
 String data = "abcdefghijklmnop";
 public char PhysicalAddress[]= new char[32];
 static int pagesize = 4;
 Page page = new Page(4,5,2,2);
 int[] ptable = new int[4];
String[] initial = new String[16];
 int[] tableLength = new int[16];
 int[] tableLength2 = new int[32];
int pagePoint;
int dataPoint;
int phyPoint=20;
String add1="00";
String phy1="101";
String offset="00";

String cur;

  PageMap() {
	  initialize();
  }
  
  void initialize() {
	  ptable[0] = 5;
	  ptable[1] = 6;
	  ptable[2] = 1;
	  ptable[3] = 2;
		 initial[0] = "a";
		 initial[1] = "b";
		 initial[2] = "c";
		 initial[3] = "d";
		 initial[4] = "e";
		 initial[5] = "f";
		 initial[6] = "g";
		 initial[7] = "h";
		 initial[8] = "i";
		 initial[9] = "j";
		 initial[10] = "k";
		 initial[11] = "l";
		 initial[12] = "m";
		 initial[13] = "n";
		 initial[14] = "o";
		 initial[15] = "p";
		  PhysicalAddress[4] = 'i';
		  PhysicalAddress[5] = 'j';
		  PhysicalAddress[6] = 'k';
		  PhysicalAddress[7] = 'l';
		  PhysicalAddress[8] = 'm';
		  PhysicalAddress[9] = 'n';
		  PhysicalAddress[10] = 'o';
		  PhysicalAddress[11] = 'p';
		  PhysicalAddress[20] = 'a';
		  PhysicalAddress[21] = 'b';
		  PhysicalAddress[22] = 'c';
		  PhysicalAddress[23] = 'd';
		  PhysicalAddress[24] = 'e';
		  PhysicalAddress[25] = 'f';
		  PhysicalAddress[26] = 'g';
		  PhysicalAddress[27] = 'h';
	 }
  
  String ChangeBit(String binaryofLogical,int bit) {
	  String st = "";
	  if (bit-binaryofLogical.length() == 0) {
		   st = binaryofLogical;
		  }
	  if (bit-binaryofLogical.length() == 1) {
	   st = "0" + binaryofLogical;
	  }
	  if (bit-binaryofLogical.length() == 2) {
	   st = "00" + binaryofLogical;
	  }
	  if (bit-binaryofLogical.length() == 3) {
	   st = "0" + binaryofLogical;
	  }
	  return st;
  }
  
 public int ChangetoPhy(String binaryofLogical) {

 
  String pagenum1 = "";
  String offset1 = "";
  if (binaryofLogical.length() == 1) {
   binaryofLogical = "000" + binaryofLogical;
  }
  if (binaryofLogical.length() == 2) {
   binaryofLogical = "00" + binaryofLogical;
  }
  if (binaryofLogical.length() == 3) {
   binaryofLogical = "0" + binaryofLogical;
  }
  // pagenumber to framenumber
  char c1 = binaryofLogical.charAt(0);
  char c2 = binaryofLogical.charAt(1);
  pagenum1 = pagenum1 + c1 + c2;
  add1=pagenum1;
  int pagenum2 = Integer.parseInt(pagenum1, 2);
  pagePoint=pagenum2;
  int framenum = ptable[pagenum2];

  // binary to offset
  char c3 = binaryofLogical.charAt(2);
  char c4 = binaryofLogical.charAt(3);
  offset1 = offset1 + c3 + c4;
  offset=offset1;
  int offset2 = Integer.parseInt(offset1, 2);
  int dataLocation = (framenum * pagesize) + offset2;
  phyPoint=dataLocation;
  return dataLocation;
 }
 
 void RetrieveData(int sc2) {
	 if(sc2<16) {
		 wrong=false;
	 mod = false;
	 dataPoint=sc2;
	 String pagenum1 = "";
	  String offset1 = "";
	    int intlogiAd = sc2;
	    String binaryofLogical = Integer.toBinaryString(intlogiAd);
	    int dataLocation2=ChangetoPhy(binaryofLogical);
	 }
	 else {
		 wrong=true;
	 }
 }
 
 void ModifyData(int sc3,String sc4) {
	 if(sc3<16) {
		 wrong=false;
	 mod = true;
	 cur = initial[sc3];
	 initial[sc3]=sc4;
	 dataPoint =sc3;
	 		String pagenum1 = "";
	 		String offset1 = "";
		    int intlogiAdtoModi = sc3;
		    String modiData = sc4;
		    String binaryofLogical = Integer.toBinaryString(intlogiAdtoModi);
		    int dataLocation=ChangetoPhy(binaryofLogical);
		    char modiData2 = modiData.charAt(0);
		    PhysicalAddress[dataLocation] = modiData2;
	 }
	 else {
		 wrong=true;
	 }
 }

 
 void tableLength(int boxSize,int start,int[] x){
		int partial = (int)boxSize/x.length;
		for(int i =0;i<x.length;i++) {
			x[i]=start+partial*i;
		}
	}

 
 public void paintComponent(Graphics g) {
		page.GetOffset(page.pageSize);
		page.GetPageNumber(page.logicalSpace-page.pageSize);
		page.GetFrameNumber(page.physicalSpace-page.pageSize);
		page.PageTableLength(page.logicalSpace,200,300);
		page.phyTableLength(page.physicalSpace,500,200);
		super.paintComponent(g);
		super.setBackground(Color.white);
		g.setFont(page.font1);
		
		//------Data Table------
		tableLength(400,200,tableLength);
		g.setColor(Color.DARK_GRAY);
		g.drawLine(100, 200, 200, 200);
		g.drawLine(100, 200, 100, 600);
		g.drawLine(100, 600, 200, 600);
		g.drawLine(200, 200, 200, 600);
		for(int i =0;i<initial.length;i++) {
			if(i%4==0)g.drawLine(80, tableLength[i], 200, tableLength[i]);
			if(i<initial.length-1) {
				g.drawString(String.valueOf(i),80, (tableLength[i]+tableLength[i+1])/2);
				g.drawString(initial[i],120, (tableLength[i]+tableLength[i+1])/2);
			}
			else {
				g.drawString(String.valueOf(i),80, (tableLength[i]+600)/2);
				g.drawString(initial[i],120, (tableLength[i]+600)/2);
			}
		}
		g.drawString("Logical Memory", 100, 620);
		
		//------Page Table--------
		g.setColor(Color.blue);
		g.drawLine(400, 300, 450, 300);
		g.drawLine(400, 300, 400, 500);
		g.drawLine(400, 500, 450, 500);
		g.drawLine(450, 300, 450, 500);
		for(int i =0;i<page.ptbLength.length;i++) {
			g.drawLine(400, page.ptbLength[i], 450, page.ptbLength[i]);
			if(i<page.ptbLength.length-1) {
				g.drawString(String.valueOf(i),375, (page.ptbLength[i]+page.ptbLength[i+1])/2);
				g.drawString(String.valueOf(ptable[i]),420, (page.ptbLength[i]+page.ptbLength[i+1])/2);
			}
			else {
				g.drawString(String.valueOf(i),375, (page.ptbLength[i]+500)/2);
				g.drawString(String.valueOf(ptable[i]),420, (page.ptbLength[i]+500)/2);
			}
		}
		g.drawString("Page Table",380,530);
		g.drawString("Page Number",340,290);
		//---Green mapped highlight----
				g.setColor(Color.green);
				g.fillRect(601, (tableLength2[phyPoint]+tableLength2[phyPoint+1])/2+20*phyPoint/40, 98, 5);
		
		//----------Physical Memory ------
		g.setColor(Color.MAGENTA);
		g.drawLine(600, 200, 700, 200);
		g.drawLine(600, 200, 600, 700);
		g.drawLine(600, 700, 700, 700);
		g.drawLine(700, 200, 700, 700);
		for(int i =0;i<page.phyLength.length;i++) {
			g.drawLine(600, page.phyLength[i], 700, page.phyLength[i]);
			if(i<page.phyLength.length-1)g.drawString(String.valueOf(i),580, (page.phyLength[i]+page.phyLength[i+1])/2);
			else g.drawString(String.valueOf(i),580, (page.phyLength[i]+700)/2);
		}
		tableLength(500,200,tableLength2);
		for(int i =0;i<tableLength2.length;i++) {
			if(PhysicalAddress[i]!=0) {
			if(i<tableLength2.length-1) {
				g.drawString(String.valueOf(PhysicalAddress[i]),640, (tableLength2[i]+tableLength2[i+1])/2+30*i/40);
				if(mod) {
					g.setColor(Color.RED);
					g.drawString(cur, 710, (tableLength2[phyPoint]+tableLength2[phyPoint+1])/2+15);
					g.drawLine(650,(tableLength2[phyPoint]+tableLength2[phyPoint+1])/2+10,710,(tableLength2[phyPoint]+tableLength2[phyPoint+1])/2+10);
					g.setColor(Color.MAGENTA);
				}
			}
			else {
				g.drawString(String.valueOf(PhysicalAddress[i]),640, (tableLength2[i]+700)/2+10);
				if(mod) {
					g.setColor(Color.RED);
					g.drawString(cur, 710, (tableLength2[phyPoint]+700)/2+15);
					g.drawLine(650,(tableLength2[phyPoint]+700)/2+10,710,(tableLength2[phyPoint]+700)/2+10);
					g.setColor(Color.MAGENTA);
				}
			}
			}
		}
		g.drawString("Physical Memory",600,730);
		g.drawString("Frame Number",550,190);

	//-----Logical Adress Box------
		g.setColor(Color.BLACK);
		g.drawString("Logical Address",100,50);
		g.drawLine(80, 80, 80, 120);
		g.drawLine(80, 80, 300, 80);
		g.drawLine(300, 80, 300, 120);
		g.drawLine(80, 120, 300, 120);
		g.drawLine(180, 80, 180, 120);
		
		//-----physical Address Box------
		g.setColor(Color.BLACK);
		g.drawString("Physical Address",480,50);
		g.drawLine(480, 80, 480, 120);
		g.drawLine(480, 80, 700, 80);
		g.drawLine(700, 80, 700, 120);
		g.drawLine(480, 120, 700, 120);
		g.drawLine(580, 80, 580, 120);
		
		//----------Bit Label--------
		g.setColor(Color.RED);
		g.drawString(String.valueOf(page.physicalSpace-page.frameSize)+" bit",510,135);
		g.drawString(String.valueOf(page.logicalSpace-page.pageSize)+" bit",105,135);
		g.drawString(String.valueOf(page.frameSize)+" bit",590,135);
		g.drawString(String.valueOf(page.pageSize)+" bit",195,135);
		g.drawString(String.valueOf(page.physicalSpace)+" bit",540,70);
		g.drawString(String.valueOf(page.logicalSpace)+" bit",140,70);
		
		g.drawLine(80, 72, 300, 72);
		g.drawLine(80, 70, 80, 75);
		g.drawLine(300, 70, 300, 75);
		
		g.drawLine(480, 72, 700, 72);
		g.drawLine(480, 70, 480, 75);
		g.drawLine(700, 70, 700, 75);
		
		g.setColor(Color.BLACK);
		int i = dataPoint%4;
		int i2 = (int)dataPoint/4;
		int i3 = (int)phyPoint/4;
		String x = ChangeBit(Integer.toBinaryString(i),2);
		String x1 =ChangeBit(Integer.toBinaryString(i2),2);
		String x2 =ChangeBit(Integer.toBinaryString(i3),3);
		
		g.drawString(x1,90,100);
		g.drawString(x,190,100);
		g.drawString(x2,490,100);
		g.drawString(x,600,100);
		
		//---------Map Line-----------------
		g.setColor(Color.red);
		g.drawLine(80, 100, 70,100 );
		if(dataPoint<initial.length-1) {
			g.drawLine(70,100, 70, (tableLength[dataPoint]+tableLength[dataPoint+1])/2);
			g.drawLine(70,(tableLength[dataPoint]+tableLength[dataPoint+1])/2, 100, (tableLength[dataPoint]+tableLength[dataPoint+1])/2);
		}
		else {
			g.drawLine(70,100, 70, (tableLength[dataPoint]+600)/2);
			g.drawLine(70,(tableLength[dataPoint]+600)/2, 100, (tableLength[dataPoint]+600)/2);
		}
		
		
		if((dataPoint==initial.length-1) && (pagePoint==page.ptbLength.length-1)) {
			g.drawLine(200,(tableLength[dataPoint]+600)/2, 400, (page.ptbLength[pagePoint]+500)/2);
		}
		else if(dataPoint==tableLength.length-1) {
			g.drawLine(200,(tableLength[dataPoint]+600)/2, 400, (page.ptbLength[pagePoint]+page.ptbLength[pagePoint+1])/2);
		}
		else if (pagePoint==page.ptbLength.length-1){
			g.drawLine(200,(tableLength[dataPoint]+tableLength[dataPoint+1])/2, 400, (page.ptbLength[pagePoint]+500)/2);
		}
		else {
			g.drawLine(200,(tableLength[dataPoint]+tableLength[dataPoint+1])/2, 400, (page.ptbLength[pagePoint]+page.ptbLength[pagePoint+1])/2);
		}
		
		
		
		if((phyPoint==PhysicalAddress.length-1) && (pagePoint==page.ptbLength.length-1)) {
			g.drawLine(600,(tableLength2[phyPoint]+700)/2+10, 450, (page.ptbLength[pagePoint]+500)/2);
			
		}
		else if(phyPoint==PhysicalAddress.length-1) {
			g.drawLine(600,(tableLength2[phyPoint]+700)/2+10, 450, (page.ptbLength[pagePoint]+page.ptbLength[pagePoint+1])/2);
		}
		else if (pagePoint==page.ptbLength.length-1){
			g.drawLine(600,(tableLength2[phyPoint]+tableLength2[phyPoint+1])/2+10, 450, (page.ptbLength[pagePoint]+500)/2);
		}
		else {
			g.drawLine(600,(tableLength2[phyPoint]+tableLength2[phyPoint+1])/2+10, 450, (page.ptbLength[pagePoint]+page.ptbLength[pagePoint+1])/2);
		}
		if (wrong) {
			g.drawString("Logical Address out of range", 20, 500);
		}


		repaint();
		}

}
