package ff14Res.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;


import ff14Res.Util.ff14shanghaijisuan;


public class Gui {
	int[] yuanshuxing=new int[5];
	int[] yuanzhuangbei=new int[5];
	int[] houzhuangbei=new int[5];
	int mojingshi=0;
    public void setgui(){
	    	JFrame frame = new JFrame("ff14伤害计算");
	    	frame.setSize(1000, 620);
	        JPanel panel = new JPanel(); 
	        frame.add(panel);
	        placeComponents1(panel);
	        frame.setVisible(true);
    }
    private void placeComponents1(JPanel panel) {

        panel.setLayout(null);

        //��ǩ
        
        
        JLabel Label22 = new JLabel("��������������:");
        Label22.setBounds(10,0,140,20);
        panel.add(Label22);
        
        JLabel Label1 = new JLabel("����:");
        Label1.setBounds(10,20,45,20);
        panel.add(Label1);
        JLabel Label2 = new JLabel("����:");
        Label2.setBounds(100,20,45,20);
        panel.add(Label2);
        JLabel Label3 = new JLabel("ֱ��:");
        Label3.setBounds(190,20,45,20);
        panel.add(Label3);
        JLabel Label4 = new JLabel("������:");
        Label4.setBounds(280,20,45,20);
        panel.add(Label4);
        JLabel Label30 = new JLabel("����:");
        Label30.setBounds(370,20,45,20);
        panel.add(Label30);
        
        
        JLabel Label6 = new JLabel("�����˺�ֵ:");
        Label6.setBounds(10,60,80,20);
        panel.add(Label6);
        JLabel Label5 = new JLabel("�˺�ֵ:");
        Label5.setBounds(10,100,45,20);
        panel.add(Label5);
        
        
        JLabel Label7 = new JLabel("����ħ��ʯ����(������ħ��ʯ����):");
        Label7.setBounds(10,140,300,20);
        panel.add(Label7);
        JLabel Label8 = new JLabel("ħ��ʯ��ֵ:");
        Label8.setBounds(10,160,80,20);
        panel.add(Label8);
        JLabel Label9 = new JLabel("��������:");
        Label9.setBounds(10,200,60,20);
        panel.add(Label9);
        JLabel Label10 = new JLabel("��������:");
        Label10.setBounds(190,200,60,20);
        panel.add(Label10);
        JLabel Label11 = new JLabel("ֱ������:");
        Label11.setBounds(370,200,60,20);
        panel.add(Label11);
        JLabel Label12 = new JLabel("����������:");
        Label12.setBounds(550,200,80,20);
        panel.add(Label12);
        JLabel Label31 = new JLabel("��������:");
        Label31.setBounds(730,200,80,20);
        panel.add(Label31);
        
        JLabel Label13 = new JLabel("��������ֱ�����ٷ���:");
        Label13.setBounds(10,240,160,20);
        panel.add(Label13);
        JLabel Label14 = new JLabel("�������Ž�:");
        Label14.setBounds(10,280,100,20);
        panel.add(Label14);
        JLabel Label15 = new JLabel("ֱ�����Ž�:");
        Label15.setBounds(150,280,100,20);
        panel.add(Label15);
        JLabel Label32 = new JLabel("�������Ž�:");
        Label32.setBounds(290,280,100,20);
        panel.add(Label32);
        
        JLabel Label16 = new JLabel("����װ���滻����:");
        Label16.setBounds(10,320,160,20);
        panel.add(Label16);
        JLabel Label21 = new JLabel("������ԭװ������:");
        Label21.setBounds(10,340,140,20);
        panel.add(Label21);
        JLabel Label17 = new JLabel("����:");
        Label17.setBounds(10,360,45,20);
        panel.add(Label17);
        JLabel Label18 = new JLabel("����:");
        Label18.setBounds(100,360,45,20);
        panel.add(Label18);
        JLabel Label19 = new JLabel("ֱ��:");
        Label19.setBounds(190,360,45,20);
        panel.add(Label19);
        JLabel Label20 = new JLabel("������:");
        Label20.setBounds(280,360,45,20);
        panel.add(Label20);
        JLabel Label33 = new JLabel("����:");
        Label33.setBounds(370,360,45,20);
        panel.add(Label33);
        JLabel Label23 = new JLabel("�������滻װ������:");
        Label23.setBounds(10,380,140,20);
        panel.add(Label23);
        JLabel Label24 = new JLabel("����:");
        Label24.setBounds(10,400,45,20);
        panel.add(Label24);
        JLabel Label25 = new JLabel("����:");
        Label25.setBounds(100,400,45,20);
        panel.add(Label25);
        JLabel Label26 = new JLabel("ֱ��:");
        Label26.setBounds(190,400,45,20);
        panel.add(Label26);
        JLabel Label27 = new JLabel("������:");
        Label27.setBounds(280,400,45,20);
        panel.add(Label27);
        JLabel Label34 = new JLabel("����:");
        Label34.setBounds(370,400,45,20);
        panel.add(Label34);
        JLabel Label28 = new JLabel("�滻����:");
        Label28.setBounds(10,440,60,20);
        panel.add(Label28);
        JLabel Label29 = new JLabel("by:����ͤ ����֮��");
        Label29.setBounds(10,540,900,20);
        panel.add(Label29);
        JLabel Label35 = new JLabel("ע1:�����ٶ����������˺�����ֻ�������FCD���������� Ϊ����dot/hot/�Զ������ٶȵ�����");
        Label35.setBounds(10,480,900,20);
        panel.add(Label35);
        JLabel Label36 = new JLabel("ע2:�˺�����Ϊ����Ϥ&����&����&ֱ��&���ٵ������˺�ֵ(δ�����������ܴ���������)");
        Label36.setBounds(10,500,900,20);
        panel.add(Label36);
        JLabel Label37 = new JLabel("ע3:�˺�ֵ������ �������޼��㹫ʽӦΪ �˺�ֵ&ƽ����������&��������&�Զ�����");
        Label37.setBounds(10,520,900,20);
        panel.add(Label37);
        
        
        
        
        
        //��ǩ
        JTextField userText1 = new JTextField(20);
        userText1.setBounds(55,20,40,20);
        panel.add(userText1);
        JTextField userText2 = new JTextField(20);
        userText2.setBounds(145,20,40,20);
        panel.add(userText2);
        JTextField userText3 = new JTextField(20);
        userText3.setBounds(235,20,40,20);
        panel.add(userText3);
        JTextField userText4 = new JTextField(20);
        userText4.setBounds(325,20,40,20);
        panel.add(userText4);
        JTextField userText22 = new JTextField(20);
        userText22.setBounds(415,20,40,20);
        panel.add(userText22);
        
        JTextField userText5 = new JTextField(20);
        userText5.setBounds(70,100,120,20);
        panel.add(userText5);
        
        
        JTextField userText6 = new JTextField(20);
        userText6.setBounds(90,160,40,20);
        panel.add(userText6);
        JTextField userText7 = new JTextField(20);
        userText7.setBounds(70,200,120,20);
        panel.add(userText7);
        JTextField userText8 = new JTextField(20);
        userText8.setBounds(250,200,120,20);
        panel.add(userText8);
        JTextField userText9 = new JTextField(20);
        userText9.setBounds(430,200,120,20);
        panel.add(userText9);
        JTextField userText10 = new JTextField(20);
        userText10.setBounds(610,200,120,20);
        panel.add(userText10);
        JTextField userText24 = new JTextField(20);
        userText24.setBounds(790,200,120,20);
        panel.add(userText24);
        
        JTextField userText11 = new JTextField(20);
        userText11.setBounds(110,280,40,20);
        panel.add(userText11);
        JTextField userText12 = new JTextField(20);
        userText12.setBounds(250,280,40,20);
        panel.add(userText12);
        JTextField userText23 = new JTextField(20);
        userText23.setBounds(390,280,40,20);
        panel.add(userText23);

        JTextField userText13 = new JTextField(20);
        userText13.setBounds(55,360,40,20);
        panel.add(userText13);
        JTextField userText14 = new JTextField(20);
        userText14.setBounds(145,360,40,20);
        panel.add(userText14);
        JTextField userText15 = new JTextField(20);
        userText15.setBounds(235,360,40,20);
        panel.add(userText15);
        JTextField userText16 = new JTextField(20);
        userText16.setBounds(325,360,40,20);
        panel.add(userText16);
        JTextField userText25 = new JTextField(20);
        userText25.setBounds(415,360,40,20);
        panel.add(userText25);
        
        JTextField userText17 = new JTextField(20);
        userText17.setBounds(55,400,40,20);
        panel.add(userText17);
        JTextField userText18 = new JTextField(20);
        userText18.setBounds(145,400,40,20);
        panel.add(userText18);
        JTextField userText19 = new JTextField(20);
        userText19.setBounds(235,400,40,20);
        panel.add(userText19);
        JTextField userText20 = new JTextField(20);
        userText20.setBounds(325,400,40,20);
        panel.add(userText20);
        JTextField userText26 = new JTextField(20);
        userText26.setBounds(415,400,40,20);
        panel.add(userText26);
        
        JTextField userText21 = new JTextField(20);
        userText21.setBounds(70,440,160,20);
        panel.add(userText21);
       
        
        ActionListener actionListene1 =new ActionListener(){
        	ff14shanghaijisuan f1=new ff14shanghaijisuan();
        	public void actionPerformed(ActionEvent e) {
        		getText1_4();
        	}
        	private void getText1_4() {
    			try{
    				yuanshuxing[0]=Integer.parseInt(userText1.getText().trim());
    			}
    			catch(NumberFormatException e){
    				yuanshuxing[0]=0;
    			}
    			try{
    				yuanshuxing[1]=Integer.parseInt(userText2.getText().trim());
    			}
    			catch(NumberFormatException e){
    				yuanshuxing[1]=0;
    			}
    			try{
    				yuanshuxing[2]=Integer.parseInt(userText3.getText().trim());
    			}
    			catch(NumberFormatException e){
    				yuanshuxing[2]=0;
    			}
    			try{
    				yuanshuxing[3]=Integer.parseInt(userText4.getText().trim());
    			}
    			catch(NumberFormatException e){
    				yuanshuxing[3]=0;
    			}
    			try{
    				yuanshuxing[4]=Integer.parseInt(userText22.getText().trim());
    			}
    			catch(NumberFormatException e){
    				yuanshuxing[4]=364;
    			}	
    			userText5.setText(String.valueOf(f1.fun_1(yuanshuxing[0], yuanshuxing[1], yuanshuxing[2], yuanshuxing[3], yuanshuxing[4])));
    		}		
        };
        
        ActionListener actionListene2 =new ActionListener(){
        	ff14shanghaijisuan f2=new ff14shanghaijisuan();
        	public void actionPerformed(ActionEvent e) {
        		getText1_4();
        	}
        	private void getText1_4() {
    			try{
    				yuanshuxing[0]=Integer.parseInt(userText1.getText().trim());
    			}
    			catch(NumberFormatException e){
    				yuanshuxing[0]=0;
    			}
    			try{
    				yuanshuxing[1]=Integer.parseInt(userText2.getText().trim());
    			}
    			catch(NumberFormatException e){
    				yuanshuxing[1]=0;
    			}
    			try{
    				yuanshuxing[2]=Integer.parseInt(userText3.getText().trim());
    			}
    			catch(NumberFormatException e){
    				yuanshuxing[2]=0;
    			}
    			try{
    				yuanshuxing[3]=Integer.parseInt(userText4.getText().trim());
    			}
    			catch(NumberFormatException e){
    				yuanshuxing[3]=0;
    			}
    			try{
    				yuanshuxing[4]=Integer.parseInt(userText22.getText().trim());
    			}
    			catch(NumberFormatException e){
    				yuanshuxing[4]=364;
    			}	
    			try{
    				mojingshi=Integer.parseInt(userText6.getText().trim());
    			}
    			catch(NumberFormatException e){
    				mojingshi=0;
    			}
    			userText7.setText(String.valueOf(f2.fun_2(yuanshuxing[0], yuanshuxing[1], yuanshuxing[2], yuanshuxing[3], yuanshuxing[4],mojingshi,0,0,0,0)));
    			userText8.setText(String.valueOf(f2.fun_2(yuanshuxing[0], yuanshuxing[1], yuanshuxing[2], yuanshuxing[3], yuanshuxing[4],0,mojingshi,0,0,0)));
    			userText9.setText(String.valueOf(f2.fun_2(yuanshuxing[0], yuanshuxing[1], yuanshuxing[2], yuanshuxing[3], yuanshuxing[4],0,0,mojingshi,0,0)));
    			userText10.setText(String.valueOf(f2.fun_2(yuanshuxing[0], yuanshuxing[1], yuanshuxing[2], yuanshuxing[3], yuanshuxing[4],0,0,0,mojingshi,0)));
    			userText24.setText(String.valueOf(f2.fun_2(yuanshuxing[0], yuanshuxing[1], yuanshuxing[2], yuanshuxing[3], yuanshuxing[4],0,0,0,0,mojingshi)));
    		}		
        };
        
        ActionListener actionListene3 =new ActionListener(){
        	ff14shanghaijisuan f3=new ff14shanghaijisuan();
        	public void actionPerformed(ActionEvent e) {
        		getText1_4();
        	}
        	private void getText1_4() {
    			try{
    				yuanshuxing[0]=Integer.parseInt(userText1.getText().trim());
    			}
    			catch(NumberFormatException e){
    				yuanshuxing[0]=0;
    			}
    			try{
    				yuanshuxing[1]=Integer.parseInt(userText2.getText().trim());
    			}
    			catch(NumberFormatException e){
    				yuanshuxing[1]=0;
    			}
    			try{
    				yuanshuxing[2]=Integer.parseInt(userText3.getText().trim());
    			}
    			catch(NumberFormatException e){
    				yuanshuxing[2]=0;
    			}
    			try{
    				yuanshuxing[3]=Integer.parseInt(userText4.getText().trim());
    			}
    			catch(NumberFormatException e){
    				yuanshuxing[3]=0;
    			}
    			try{
    				yuanshuxing[4]=Integer.parseInt(userText22.getText().trim());
    			}
    			catch(NumberFormatException e){
    				yuanshuxing[4]=364;
    			}	
    			
    			int []a={0,0};
    			a=f3.fun_3(yuanshuxing[0], yuanshuxing[1], yuanshuxing[2], yuanshuxing[3], yuanshuxing[4]);
    			userText11.setText(String.valueOf(a[0]));
    			userText12.setText(String.valueOf(a[1]));
    			userText23.setText(String.valueOf(yuanshuxing[1]+yuanshuxing[2]+yuanshuxing[4]-a[0]-a[1]));
    		}		
        };
        
        ActionListener actionListene4 =new ActionListener(){
        	ff14shanghaijisuan f4=new ff14shanghaijisuan();
        	public void actionPerformed(ActionEvent e) {
        		getText13_16();
        		getText17_20();
        		userText21.setText(String.valueOf(f4.fun_4(yuanshuxing[0], yuanshuxing[1], yuanshuxing[2], yuanshuxing[3], yuanshuxing[4],yuanzhuangbei[0],yuanzhuangbei[1],yuanzhuangbei[2],yuanzhuangbei[3],yuanzhuangbei[4],houzhuangbei[0],houzhuangbei[1],houzhuangbei[2],houzhuangbei[3],houzhuangbei[4])));
    			
        	}
        	private void getText13_16() {
    			try{
    				yuanzhuangbei[0]=Integer.parseInt(userText13.getText().trim());
    			}
    			catch(NumberFormatException e){
    				yuanzhuangbei[0]=0;
    			}
    			try{
    				yuanzhuangbei[1]=Integer.parseInt(userText14.getText().trim());
    			}
    			catch(NumberFormatException e){
    				yuanzhuangbei[1]=0;
    			}
    			try{
    				yuanzhuangbei[2]=Integer.parseInt(userText15.getText().trim());
    			}
    			catch(NumberFormatException e){
    				yuanzhuangbei[2]=0;
    			}
    			try{
    				yuanzhuangbei[3]=Integer.parseInt(userText16.getText().trim());
    			}
    			catch(NumberFormatException e){
    				yuanzhuangbei[3]=0;
    			}
    			try{
    				yuanzhuangbei[4]=Integer.parseInt(userText25.getText().trim());
    			}
    			catch(NumberFormatException e){
    				yuanzhuangbei[4]=364;
    			}
    		}
        	private void getText17_20() {
    			try{
    				houzhuangbei[0]=Integer.parseInt(userText17.getText().trim());
    			}
    			catch(NumberFormatException e){
    				houzhuangbei[0]=0;
    			}
    			try{
    				houzhuangbei[1]=Integer.parseInt(userText18.getText().trim());
    			}
    			catch(NumberFormatException e){
    				houzhuangbei[1]=0;
    			}
    			try{
    				houzhuangbei[2]=Integer.parseInt(userText19.getText().trim());
    			}
    			catch(NumberFormatException e){
    				houzhuangbei[2]=0;
    			}
    			try{
    				houzhuangbei[3]=Integer.parseInt(userText20.getText().trim());
    			}
    			catch(NumberFormatException e){
    				houzhuangbei[3]=0;
    			}
    			try{
    				houzhuangbei[4]=Integer.parseInt(userText26.getText().trim());
    			}
    			catch(NumberFormatException e){
    				houzhuangbei[4]=364;
    			}
    		}	
        };
        
        
        JButton Button1 = new JButton("����");
        Button1.setBounds(10, 80, 80, 20);
        Button1.addActionListener(actionListene1);
        panel.add(Button1);
        JButton Button2 = new JButton("����");
        Button2.setBounds(10, 180, 80, 20);
        Button2.addActionListener(actionListene2);
        panel.add(Button2);
        JButton Button3 = new JButton("����");
        Button3.setBounds(10, 260, 80, 20);
        Button3.addActionListener(actionListene3);
        panel.add(Button3);
        JButton Button4 = new JButton("����");
        Button4.setBounds(10, 420, 80, 20);
        Button4.addActionListener(actionListene4);
        panel.add(Button4);

    }
   
}