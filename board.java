import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;
import java.util.Random;
import java.util.Scanner;
import javax.swing.Timer;

public class board extends JFrame implements ActionListener,KeyListener{
	private static int Def_width_board=600;
	private static int Def_height_board=600;
	private JPanel buttonpanel;
	private String user1;
	private String user2;
	private JFrame temp=new JFrame();
	private JFrame win_frame=new JFrame();
	int choice;
	private TextField out_txt_box=new TextField(10);
	JButton b[][]=new JButton[3][3];
	char prev='X';
	JButton b1,b2,b3,b4,b5,b6=new JButton();
	Timer timer;
	
	public board(){
		
		setSize(Def_width_board,Def_height_board);
		b1=new JButton("User1 vs User2");
		b1.setBounds(50,20,120,50);
		b1.addActionListener(this);
		b2=new JButton("User vs CPU");
		b2.setBounds(50,80,120,50);
		b2.addActionListener(this);
		b3=new JButton("CPU vs AI Bot");
		b3.setBounds(50,140,120,50);
		b3.addActionListener(this);
		b4=new JButton("User vs AI Bot");
		b4.setBounds(50,200,120,50);
		b4.addActionListener(this);
		b5=new JButton("Exit");
		b5.addActionListener(this);
		b5.setBounds(50,260,120,50);
		b6=new JButton("Start New Game");
		b6.addActionListener(this);
		b6.setBounds(50, 320, 120, 50);
		buttonpanel=new JPanel();
		buttonpanel.add(b1);
		buttonpanel.add(b2);
		buttonpanel.add(b3);
		buttonpanel.add(b4);
		buttonpanel.add(b5);
		buttonpanel.add(b6);
		addKeyListener(this);
		buttonpanel.add(out_txt_box);
		out_txt_box.setBounds(250, 350, 300, 60);
		add(buttonpanel);
		buttonpanel.setLayout(null);
		buttonpanel.setVisible(true);
		create_grid();
		
		setVisible(true);
		buttonpanel.requestFocusInWindow();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	}
	
	public void create_grid(){
		
		
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				b[i][j]=new JButton();
			}
		}
		
		b[0][0].setBounds(250,20,100,100);
		b[1][0].setBounds(250,120,100,100);
		b[2][0].setBounds(250,220,100,100);
		b[0][1].setBounds(350,20,100,100);
		b[1][1].setBounds(350,120,100,100);
		b[2][1].setBounds(350,220,100,100);
		b[0][2].setBounds(450,20,100,100);
		b[1][2].setBounds(450,120,100,100);
		b[2][2].setBounds(450,220,100,100);
	
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				buttonpanel.add(b[i][j]);
				b[i][j].addActionListener(this);
			}
		}
			
	}
	
	public void put_in_grid(int x,int y,char val){
		
		String temp=Character.toString(val);
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(i==x && j==y){
					b[i][j].setText(temp);
					if(temp.equals("X"))
						b[i][j].setForeground(Color.RED);
					else 
						b[i][j].setForeground(Color.green);
					
				}
			}
		}
	}
	public void new_game(){
		
		setVisible(false);
		new board();
		temp=new JFrame();
		win_frame=new JFrame();
	}
	
	public void create_delay(){
		try{
			Thread.sleep(1000);
			
		}catch(InterruptedException e){
			Thread.currentThread().interrupt();
		}
	}
	
	public void generate_random(char val){
		
		Random rand_obj=new Random();
		int p=rand_obj.nextInt(3);
		int q=rand_obj.nextInt(3);
		while(b[p][q].getText().equals("O")|| b[p][q].getText().equals("X")){
			p=rand_obj.nextInt(3);
			q=rand_obj.nextInt(3);
			
		}
		
		String temp=Character.toString(val);
		b[p][q].setText(temp);
		b[p][q].setForeground(Color.RED);
		
	}
	
	public void mark_winner(){
		
		int flag1=0;
		int flag2=0;
		int counter_1=0;
		int i=-1;
		while(counter_1!=3){
			i=i+1;
			int j=0;
			if(b[i][j].getText().equals(b[i][j+1].getText()) && b[i][j+1].getText().equals(b[i][j+2].getText())){
				b[i][j].setForeground(Color.BLUE);
				b[i][j+1].setForeground(Color.BLUE);
				b[i][j+2].setForeground(Color.BLUE);
			}
			counter_1++;
		}
		
		int j=-1;
		int counter_2=0;
		while(counter_2!=3){
			j=j+1;
			i=0;
			if(b[i][j].getText().equals(b[i+1][j].getText()) && b[i+1][j].getText().equals(b[i+2][j].getText())){
				b[i+1][j].setForeground(Color.BLUE);
				b[i+2][j].setForeground(Color.BLUE);
				b[i][j].setForeground(Color.BLUE);
			}
			
			counter_2++;
		}
		
		i=0;
		if(b[i][i].getText().equals(b[i+1][i+1].getText()) && b[i+1][i+1].getText().equals(b[i+2][i+2].getText())){
				b[i][i].setForeground(Color.BLUE);
				b[i+1][i+1].setForeground(Color.BLUE);
				b[i+2][i+2].setForeground(Color.BLUE);
		}
		
		if(b[0][2].getText().equals(b[1][1].getText()) && b[1][1].getText().equals(b[2][0].getText())){
			b[0][2].setForeground(Color.BLUE);
			b[1][1].setForeground(Color.BLUE);
			b[2][0].setForeground(Color.BLUE);
		}
		if(b[0][0].getText().equals(b[1][1].getText()) && b[1][1].getText().equals(b[2][2].getText())){
			b[0][0].setForeground(Color.BLUE);
			b[1][1].setForeground(Color.BLUE);
			b[2][2].setForeground(Color.BLUE);
		}
	}
	
	public void create_pop_for_new_game(String winner){
		
		win_frame=new JFrame("Game-Over");
		Box box = Box.createVerticalBox();
	    JLabel l1=new JLabel("Game-Over "+ winner +" has won");
	    l1.setAlignmentX(Component.CENTER_ALIGNMENT);
	    box.add(l1);
	    JButton button = new JButton("Got-It");
	    button.setAlignmentX(Component.CENTER_ALIGNMENT);
	    button.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		win_frame.setVisible(false);
	    	}
	    });
	    box.add(button);
	    win_frame.add(box, BorderLayout.CENTER);
	    win_frame.setSize(200, 100);
	    win_frame.setVisible(true);

	}
	public int checkstate(){
		int j=-1;
		int flag1=0;
		int flag2=0;
		int trueval1=0;
		int has_won=0;
		int has_tied=0;
		int counter_1=0;
		
		while(counter_1!=3){
			
			flag1=0;
			flag2=0;
			j++;
			for(int i=0;i<3;i++){
				if(b[i][j].getText().equals("O")){
					flag1++;
				}
				else if(b[i][j].getText().equals("X")){
					flag2++;
				}
			}
			if(flag1==3 || flag2==3){
				trueval1++;
			}
			counter_1++;
			
		}
		
		if(trueval1>0){
			has_won=1;
		}
		int i=-1;
		int counter_2=0;
		int trueval2=0;
		while(counter_2!=3){
			i++;
			int flag3=0;
			int flag4=0;
			for(j=0;j<3;j++){
				if(b[i][j].getText().equals("O")){
					flag3++;
				}
				else if(b[i][j].getText().equals("X")){
					flag4++;
				}
			}
			if(flag3==3||flag4==3){
				trueval2++;
			}
			counter_2++;
		}
		
		if(trueval2>0){
			has_won=1;
		}
		
		int trueval3=0;
		int flag5=0,flag6=0;
		i=0;
		j=2;
		while(i<3 && j>-1){
			
			if(b[i][j].getText().equals("O")){
				flag5++;
			}
			else if(b[i][j].getText().equals("X")){
				flag6++;
			}
			i++;
			j--;
		}
		if(flag5==3||flag6==3){
			trueval3++;
		}
		if(trueval3>0){
			has_won=1;
		}
		
		int trueval4=0;
		int flag7=0,flag8=0;
		i=0;
		while(i<3){
			if(b[i][i].getText().equals("O")){
				flag7++;
			}
			else if(b[i][i].getText().equals("X")){
				flag8++;
			}
			i++;
		}
		if(flag7==3 || flag8==3){
			trueval4++;
		}
		if(trueval4>0){
			has_won=1;
		}
		
		int flag9=0;
		
		if(has_won==0){
			for(i=0;i<3;i++){
				for(j=0;j<3;j++){
					if(b[i][j].getText().equals("X")||b[i][j].getText().equals("O")){
						flag9++;
					}
				}
			}
			
			if(flag9==9){
				has_tied=1;
			}
		}
		
		if(has_won==1)
			return 1;
		else if(has_tied==1)
			return 2;
		else
			return 0;
	}
	public int is_any_left(){
		int is_left;
		
		int count=0;
		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(b[i][j].getText().isEmpty())
					count++;
			}
		}
		
		if(count>0)
			return 1;
		else 
			return 0;
	}
	
	public void artificial_intelligence(char val){
		
		String exc=null;
		String val1=Character.toString(val);
		if(val=='X')
			exc = Character.toString('O');
		else if(val=='O')
			exc=Character.toString('X');
		
		int is_changing=0;
		int i=-1;
		int counter_1=0;
		
		while(counter_1!=3){
			int flag1=0;
			int flag2=0;
			i=i+1;
			for(int j=0;j<3;j++){
				if(b[i][j].getText().equals("X")){
					flag1++;
				}
				else if(b[i][j].getText().equals("O")){
					flag2++;
				}
			}
			
			if(flag1==2||flag2==2){
				int j=0;
				if(b[i][j].getText().equals(b[i][j+1].getText()) && b[i][j+2].getText().isEmpty() && b[i][j].getText().equals(val1)){
					if(is_changing==0){
						//System.out.println("1");
						is_changing=1;
						b[i][j+2].setText(val1);
						
					}
				}
				else if(b[i][j].getText().isEmpty() && b[i][j+1].getText().equals(b[i][j+2].getText()) && b[i][j+1].getText().equals(val1)){
					if(is_changing==0){
						//System.out.println("2");
						is_changing=1;
						b[i][j].setText(val1);
						b[i][j].setForeground(Color.MAGENTA);
					}
				}
				else if(b[i][j].getText().equals(b[i][j+2].getText()) && b[i][j+1].getText().isEmpty() && b[i][j].getText().equals(val1)){
					if(is_changing==0){
						//System.out.println("3");
						is_changing=1;
						b[i][j+1].setText(val1);
						b[i][j+1].setForeground(Color.MAGENTA);
					}
				}
				else if(b[i][j].getText().equals(b[i][j+1].getText()) && b[i][j+2].getText().isEmpty() && b[i][j].getText().equals(exc)){
					if(is_changing==0){
						//System.out.println("4");
						is_changing=1;
						if(exc.equals("X")){
							b[i][j+2].setText("O");
							b[i][j+2].setForeground(Color.MAGENTA);
						}
						else if(exc.equals("O")){
							b[i][j+2].setText("X");
							b[i][j+2].setForeground(Color.MAGENTA);
						}
					}
				}
				else if(b[i][j].getText().isEmpty() && b[i][j+1].getText().equals(b[i][j+2].getText()) && b[i][j+1].getText().equals(exc)){
					if(is_changing==0){
						//System.out.println("5");
						is_changing=1;
						if(exc.equals("X"))
							b[i][j].setText("O");
						
						else if(exc.equals("O"))
							b[i][j].setText("X");
						b[i][j].setForeground(Color.MAGENTA);
					}
				}
				else if(b[i][j].getText().equals(b[i][j+2].getText()) && b[i][j+1].getText().isEmpty() && b[i][j].getText().equals(exc)){
					if(is_changing==0){
						//System.out.println("6");
						is_changing=1;
						if(exc.equals("X"))
							b[i][j+1].setText("O");
						else if(exc.equals("O"))
							b[i][j+1].setText("X");
						b[i][j+1].setForeground(Color.MAGENTA);
					}
				}
			}
			counter_1++;
		}
		
		int j=-1;
		int counter_2=0;
		int p;
		while(counter_2!=3){
			int flag3=0;
			int flag4=0;
			j=j+1;
			for(i=0;i<3;i++){
				if(b[i][j].getText().equals("O"))
					flag3++;
				else if(b[i][j].getText().equals("X"))
					flag4++;
			}
			
			if(flag3==2||flag4==2){
				i=0;
				if(b[i][j].getText().equals(b[i+1][j].getText()) && b[i+2][j].getText().isEmpty() && b[i][j].getText().equals(val1)){
					if(is_changing==0){
						//System.out.println("7");
						is_changing=1;
						b[i+2][j].setText(val1);
						b[i+2][j].setForeground(Color.MAGENTA);
					}
				}
				else if(b[i][j].getText().isEmpty() && b[i+1][j].getText().equals(b[i+2][j].getText()) && b[i+1][j].getText().equals(val1)){
					if(is_changing==0){
						//System.out.println("8");
						is_changing=1;
						b[i][j].setText(val1);
						b[i][j].setForeground(Color.MAGENTA);
					}
				}
				else if(b[i][j].getText().equals(b[i+2][j].getText()) && b[i+1][j].getText().isEmpty()&& b[i][j].getText().equals(val1)){
					if(is_changing==0){
						//System.out.println("9");
						is_changing=1;
						b[i+1][j].setText(val1);
						b[i+1][j].setForeground(Color.MAGENTA);
						
					}
				}
				else if(b[i][j].getText().equals(b[i+1][j].getText()) && b[i+2][j].getText().isEmpty() && b[i][j].getText().equals(exc)){
					if(is_changing==0){
						//System.out.println("10");
						is_changing=1;
						if(exc.equals("X"))
							b[i+2][j].setText("O");
						else if(exc.equals("O"))
							b[i+2][j].setText("X");
						b[i+2][j].setForeground(Color.MAGENTA);
						
					}
				}
				else if(b[i][j].getText().isEmpty() && b[i+1][j].getText().equals(b[i+2][j].getText()) && b[i+1][j].getText().equals(exc)){
					if(is_changing==0){
						//System.out.println("11");
						is_changing=1;
						if(exc.equals("X"))
							b[i][j].setText("O");
						else if(exc.equals("O"))
							b[i][j].setText("X");
						b[i][j].setForeground(Color.MAGENTA);
					}
				}
				else if(b[i][j].getText().equals(b[i+2][j].getText()) && b[i+1][j].getText().isEmpty() && b[i][j].getText().equals(exc)){
					if(is_changing==0){
						//System.out.println("12");
						is_changing=1;
						if(exc.equals("X"))
							b[i+1][j].setText("O");
						else if(exc.equals("O"))
							b[i+1][j].setText("X");
						b[i+1][j].setForeground(Color.MAGENTA);
					}
				}
			}
			counter_2++;
		}
		int flag5=0,flag6=0;
		for(i=0;i<3;i++){
			if(b[i][i].getText().equals("X"))
				flag5++;
			else if(b[i][i].getText().equals("O"))
				flag6++;
		}
		if(flag5==2 || flag6==2){
			i=0;
			if(b[i+2][i+2].getText().isEmpty() && b[i][i].getText().equals(b[i+1][i+1].getText()) && b[i+1][i+1].getText().equals(val1)){
				if(is_changing==0){
					//System.out.println("13");
					is_changing=1;
					b[i+2][i+2].setText(val1);
					b[i+2][i+2].setForeground(Color.MAGENTA);
				}
			}
			else if(b[i][i].getText().isEmpty() && b[i+1][i+1].getText().equals(b[i+2][i+2].getText()) && b[i+1][i+1].getText().equals(val1)){
				if(is_changing==0){
					//System.out.println("14");
					is_changing=1;
					b[i][i].setText(val1);
					b[i][i].setForeground(Color.MAGENTA);
				}
			}
			else if(b[i][i].getText().equals(b[i+2][i+2].getText()) && b[i+1][i+1].getText().isEmpty()&& b[i][i].getText().equals(val1)){
				if(is_changing==0){
					//System.out.println("15");
					is_changing=1;
					b[i+1][i+1].setText(val1);
					b[i+1][i+1].setForeground(Color.MAGENTA);
				}
			}
			if(b[i+2][i+2].getText().isEmpty() && b[i][i].getText().equals(b[i+1][i+1].getText()) && b[i+1][i+1].getText().equals(exc)){
				if(is_changing==0){
					//System.out.println("16");
					is_changing=1;
					if(exc.equals("O"))
						b[i+2][i+2].setText("X");
					else if(exc.equals("X"))
						b[i+2][i+2].setText("O");
					b[i+2][i+2].setForeground(Color.MAGENTA);
				}
			}
			else if(b[i][i].getText().isEmpty() && b[i+1][i+1].getText().equals(b[i+2][i+2].getText()) && b[i+1][i+1].getText().equals(val1)){
				if(is_changing==0){
					//System.out.println("17");
					is_changing=1;
					if(exc.equals("O"))
						b[i][i].setText("X");
					else if(exc.equals("X"))
						b[i][i].setText("O");
					b[i][i].setForeground(Color.MAGENTA);

				}
			}
			else if(b[i][i].getText().equals(b[i+2][i+2].getText()) && b[i+1][i+1].getText().isEmpty()&& b[i][i].getText().equals(val1)){
				if(is_changing==0){
					//System.out.println("18");
					is_changing=1;
					if(exc.equals("O"))
						b[i+1][i+1].setText("X");
					else if(exc.equals("X"))
						b[i+1][i+1].setText("O");
					b[i+1][i+1].setForeground(Color.MAGENTA);

				}
			}

		}
		int flag7=0,flag8=0;
		for(i=0;i<3;i++){
			for(j=2;j>-1;--j){
				if(b[i][j].getText().equals("X"))
					flag7++;
				else if(b[i][j].getText().equals("O"))
					flag8++;
			}
		}
		if(flag7==2||flag8==2){
			int r=0;
			int s=2;
			if(b[r][s].getText().equals(b[r+1][s-1].getText()) && b[r+2][s-2].getText().isEmpty() && b[r][s].getText().equals(val1)){
				if(is_changing==0){
					//System.out.println("19");
					b[r+2][s-2].setText(val1);
					is_changing=1;
					b[r+2][s-2].setForeground(Color.MAGENTA);
				}
			}
			else if(b[r+2][s-2].getText().equals(b[r+1][s-1].getText()) && b[r+2][s-2].getText().equals(val1) && b[r][s].getText().isEmpty()){
				if(is_changing==0){
					//System.out.println("20");
					b[r][s].setText(val1);
					is_changing=1;
					b[r][s].setForeground(Color.MAGENTA);
				}
			}
			else if(b[r][s].getText().equals(b[r+2][s-2]) && b[r+1][s-1].getText().isEmpty() && b[r][s].getText().equals(val1)){
				if(is_changing==0){
					//System.out.println("21");
					is_changing=1;
					b[r+1][s-1].setText(val1);
					b[r+1][s-1].setForeground(Color.MAGENTA);
					
				}
				
			}
			else if(b[r][s].getText().equals(b[r+2][s-2]) && b[r+1][s-1].getText().isEmpty() && b[r][s].getText().equals(exc)){
				if(is_changing==0){
					//System.out.println("22");
					is_changing=1;
					if(exc.equals("O"))
						b[r+1][s-1].setText("X");
					else if(exc.equals("X"))
						b[r+1][s-1].setText("O");
					b[r+1][s-1].setForeground(Color.MAGENTA);
				}
			}
			if(b[r][s].getText().equals(b[r+1][s-1].getText()) && b[r+2][s-2].getText().isEmpty() && b[r][s].getText().equals(exc)){
				if(is_changing==0){
					//System.out.println("23");
					is_changing=1;
					if(exc.equals("O"))
						b[r+2][s-2].setText("X");
					else if(exc.equals("X"))
						b[r+2][s-2].setText("O");
					b[r+2][s-2].setForeground(Color.MAGENTA);

				}
			}
			else if(b[r+2][s-2].getText().equals(b[r+1][s-1].getText()) && b[r+2][s-2].getText().equals(exc) && b[r][s].getText().isEmpty()){
				if(is_changing==0){
					//System.out.println("24");
					is_changing=1;
					if(exc.equals("O"))
						b[r][s].setText("X");
					else if(exc.equals("X"))
						b[r][s].setText("O");
					b[r][s].setForeground(Color.MAGENTA);

				}
			}
			
		}
		int check_center=0;
		int any_empty=0;
		int move=1;
		if(is_changing==0){
			for(i=0;i<3;i++){
				for(j=0;j<3;j++){
					if(i==1 && j==1){
						if(b[i][j].getText().equals("O") || b[i][j].getText().equals("X")){
							check_center=1;
						}
					}
				}
			}
			
			if(check_center==0 && is_changing==0){
				for(i=0;i<3;i++){
					for(j=0;j<3;j++){
						if(i==1 && j==1){
							b[i][j].setText(val1);
							check_center=1;
							is_changing=1;
							b[i][j].setForeground(Color.MAGENTA);
						}
					}
				}
			}
			
			if(b[0][0].getText().isEmpty()||b[0][2].getText().isEmpty()||b[2][0].getText().isEmpty()||b[2][2].getText().isEmpty()){
				move=0;
			}
			
			if(b[0][0].getText().equals("O")&&b[1][1].getText().equals("X")&&b[2][2].getText().equals("O")&&is_changing==0){
				b[0][1].setText(val1);
				is_changing=1;
				b[0][1].setForeground(Color.MAGENTA);
				//System.out.println("25");
			}
			if(b[0][2].getText().equals("O")&&b[1][1].getText().equals("X")&&b[2][0].getText().equals("O")&&is_changing==0){
				b[0][1].setText(val1);
				is_changing=1;
				b[0][1].setForeground(Color.MAGENTA);
				//System.out.println("26");
			}
			if(check_center==1 && is_changing==0 && move==0){
				if(b[0][0].getText().isEmpty()){
					if(is_changing==0){
						b[0][0].setText("X");
						is_changing=1;
						b[0][0].setForeground(Color.MAGENTA);
						//System.out.println("27");
					}
				}
				else if(b[0][2].getText().isEmpty()){
					if(is_changing==0){
						b[0][2].setText("X");
						is_changing=1;
						b[0][2].setForeground(Color.MAGENTA);
						//System.out.println("28");
					}
				}
				else if(b[2][0].getText().isEmpty()){
					if(is_changing==0){
						b[2][0].setText("X");
						is_changing=1;
						b[2][0].setForeground(Color.MAGENTA);
						//System.out.println("29");
					}
				}
				else if(b[2][2].getText().isEmpty()){
					if(is_changing==0){
						b[2][2].setText("X");
						is_changing=1;
						b[2][2].setForeground(Color.MAGENTA);
						//System.out.println("30");
					}
				}
			}
			if(check_center==1 && is_changing==0 && move==1){
				for(i=0;i<3;i++){
					for(j=0;j<3;j++){
						if(b[i][j].getText().isEmpty()){
							if(any_empty==0){
								b[i][j].setText(val1);
								any_empty=1;
								is_changing=1;
								b[i][j].setForeground(Color.MAGENTA);
								//System.out.println("31");
							}
						}
					}
				}
			}
		}
		
		
	}
	
	public void keyPressed(KeyEvent e){
		int key=e.getKeyCode();
		System.out.println("you pressed"+e.getKeyChar());
		if(key==KeyEvent.VK_1)
		{	
			Frame f=new JFrame();
			JPanel p=new JPanel();
			JButton a=new JButton();
			a.setText("hello");
			p.add(a);
			f.add(p);
			f.setVisible(true);
		}	
		
	}
	public void keyTyped(KeyEvent e){
		System.out.println("you pressed"+e.getKeyChar());
		

	}
	public void keyReleased(KeyEvent e){
		System.out.println("you pressed"+e.getKeyChar());

	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("Exit")){
			b5.setBackground(Color.BLUE);
			b5.setForeground(Color.BLUE);
			System.exit(0);	
			
		}
		else if(e.getActionCommand().equals("User1 vs User2")){
			
			choice=1;
			b1.setBackground(Color.BLUE);
			b1.setForeground(Color.BLUE);
			temp = new JFrame("Enter Details");
		    temp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    Box box = Box.createVerticalBox();
		    JLabel l1=new JLabel("user1");
		    l1.setAlignmentX(Component.CENTER_ALIGNMENT);
		    JTextField txt1=new JTextField(15);
		    txt1.setMaximumSize( txt1.getPreferredSize() );
		    txt1.setAlignmentX(Component.CENTER_ALIGNMENT);
		    box.add(l1);
		    box.add(txt1);
		    JLabel l2 = new JLabel("user2");
		    l2.setAlignmentX(Component.CENTER_ALIGNMENT);
		    JTextField txt2=new JTextField(15);
		    txt2.setMaximumSize(txt2.getPreferredSize());
		    box.add(l2);
		    box.add(txt2);
		    JButton button = new JButton("Ok");
		    button.addActionListener(new ActionListener(){
		    	public void actionPerformed(ActionEvent e){
		    		if(txt1.getText().isEmpty()||txt2.getText().isEmpty()){
		    			JFrame pop=new JFrame("Empty String Alert");
		    			Box box1=Box.createVerticalBox();
		    			JButton exit_this_pop=new JButton("Exit This Alert");
		    			exit_this_pop.setAlignmentX(Component.CENTER_ALIGNMENT);
		    			exit_this_pop.addActionListener(new ActionListener(){
		    				public void actionPerformed(ActionEvent e){
		    					pop.setVisible(false);
		    				}
		    			});
		    			JLabel label=new JLabel("dont enter empty string");
		    			label.setAlignmentX(Component.CENTER_ALIGNMENT);
		    			box1.add(label);
		    			box1.add(exit_this_pop);
		    			pop.setSize(200, 200);
		    			pop.add(box1,BorderLayout.CENTER);
		    			pop.setVisible(true);
		    		}
		    		else{
		    			user1=txt1.getText();
		    			user2=txt2.getText();
		    			String data=user1+" plays against "+user2;
		    			out_txt_box.setText(data);
		    			temp.setVisible(false);
		    		}
		    	}
		    });
		    button.setAlignmentX(Component.CENTER_ALIGNMENT);
		    box.add(button);
		    temp.add(box, BorderLayout.CENTER);
		    temp.setSize(300, 300);
		    temp.setVisible(true);
		    
		    
		    //create_delay();
			//out_txt_box.setText(user1+" will move first");

			
		}
		else if(e.getActionCommand().equals("User vs CPU")){
			choice=2;
			b2.setBackground(Color.BLUE);
			b2.setForeground(Color.BLUE);
			temp = new JFrame("Enter Details");
		    temp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    Box box = Box.createVerticalBox();
		    JLabel l1=new JLabel("user name");
		    l1.setAlignmentX(Component.CENTER_ALIGNMENT);
		    JTextField txt1=new JTextField(15);
		    txt1.setMaximumSize( txt1.getPreferredSize() );
		    txt1.setAlignmentX(Component.CENTER_ALIGNMENT);
		    box.add(l1);
		    box.add(txt1);
		    JButton button = new JButton("Ok");
		    button.addActionListener(new ActionListener(){
		    	public void actionPerformed(ActionEvent e){
		    		if(txt1.getText().isEmpty()){
		    			JFrame pop=new JFrame("Empty String Alert");
		    			Box box1=Box.createVerticalBox();
		    			JButton exit_this_pop=new JButton("Exit This Alert");
		    			exit_this_pop.setAlignmentX(Component.CENTER_ALIGNMENT);
		    			exit_this_pop.addActionListener(new ActionListener(){
		    				public void actionPerformed(ActionEvent e){
		    					pop.setVisible(false);
		    				}
		    			});
		    			JLabel label=new JLabel("dont enter empty string");
		    			label.setAlignmentX(Component.CENTER_ALIGNMENT);
		    			box1.add(label);
		    			box1.add(exit_this_pop);
		    			pop.setSize(200, 200);
		    			pop.add(box1,BorderLayout.CENTER);
		    			pop.setVisible(true);
		    		}
		    		else{
		    			user1=txt1.getText();
		    			user2="CPU";
		    			String data=user1+" plays against "+user2;
		    			out_txt_box.setText(data);
		    			temp.setVisible(false);
		    		}
		    	}
		    });
		    button.setAlignmentX(Component.CENTER_ALIGNMENT);
		    box.add(button);
		    temp.add(box, BorderLayout.CENTER);
		    temp.setSize(300, 300);
		    temp.setVisible(true);

			
		}
		else if(e.getActionCommand().equals("CPU vs AI Bot")){
			choice=3;
			b3.setBackground(Color.BLUE);
			b3.setForeground(Color.BLUE);
		}
		else if(e.getActionCommand().equals("User vs AI Bot")){
			choice=4;
			b4.setBackground(Color.BLUE);
			b4.setForeground(Color.BLUE);
			temp = new JFrame("Enter Details");
		    temp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    Box box = Box.createVerticalBox();
		    JLabel l1=new JLabel("Enter username");
		    l1.setAlignmentX(Component.CENTER_ALIGNMENT);
		    JTextField txt1=new JTextField(15);
		    txt1.setMaximumSize( txt1.getPreferredSize() );
		    txt1.setAlignmentX(Component.CENTER_ALIGNMENT);
		    box.add(l1);
		    box.add(txt1);
		    JButton button = new JButton("Ok");
		    button.addActionListener(new ActionListener(){
		    	public void actionPerformed(ActionEvent e){
		    		if(txt1.getText().isEmpty()){
		    			JFrame pop=new JFrame("Empty String Alert");
		    			Box box1=Box.createVerticalBox();
		    			JButton exit_this_pop=new JButton("Exit This Alert");
		    			exit_this_pop.setAlignmentX(Component.CENTER_ALIGNMENT);
		    			exit_this_pop.addActionListener(new ActionListener(){
		    				public void actionPerformed(ActionEvent e){
		    					pop.setVisible(false);
		    				}
		    			});
		    			JLabel label=new JLabel("dont enter empty string");
		    			label.setAlignmentX(Component.CENTER_ALIGNMENT);
		    			box1.add(label);
		    			box1.add(exit_this_pop);
		    			pop.setSize(200, 200);
		    			pop.add(box1,BorderLayout.CENTER);
		    			pop.setVisible(true);
		    		}
		    		else{
		    			user1=txt1.getText();
		    			user2="AI";
		    			String data=user1+" plays against "+user2;
		    			out_txt_box.setText(data);
		    			temp.setVisible(false);
		    		}
		    	}
		    });
		    button.setAlignmentX(Component.CENTER_ALIGNMENT);
		    box.add(button);
		    temp.add(box, BorderLayout.CENTER);
		    temp.setSize(300, 300);
		    temp.setVisible(true);

			
			

		}
		else if(e.getActionCommand().equals("Start New Game")){
			b6.setBackground(Color.BLUE);
			b6.setForeground(Color.BLUE);
			this.new_game();
		}
		
		if(choice==1){
			
			int l,m;
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					if(e.getSource().equals(this.b[i][j])){
						l=i;
						m=j;
						if(prev=='X'){
							
							out_txt_box.setText(user1+"'s"+ "move");
							put_in_grid(l,m,'O');
							prev='O';
						}	
						
						else{
							
							out_txt_box.setText(user2+"'s"+"move");
							put_in_grid(l,m,'X');
							prev='X';
						}	
						if(this.checkstate()==1)
						{
							if(prev=='X')
							{
								out_txt_box.setText(user2+" wins");
								this.mark_winner();
								this.create_pop_for_new_game(user2);
							}	
							else if(prev=='O')
							{	
								out_txt_box.setText(user1+" wins");
								this.mark_winner();
								this.create_pop_for_new_game(user1);

							}	
						}	
						else if(this.checkstate()==2)
							out_txt_box.setText("Game -Tie");

					}
						
				}
			}

		}
		else if(choice==2){
			int l,m;
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					if(e.getSource().equals(this.b[i][j])){
						l=i;
						m=j;		
						int flag=0;	
						
						this.put_in_grid(l, m, 'O');
						if(this.checkstate()==1)
							flag=1;

						prev='O';
						int f=this.is_any_left();
						if(f>0 && flag==0){
								this.generate_random('X');
								out_txt_box.setText("computers move");
								prev='X';
						}
						
						if(this.checkstate()==1){
							if(prev=='X'){
								//System.out.println("ti");	
								out_txt_box.setText("computer"+" wins");
								this.mark_winner();
								this.create_pop_for_new_game("computer");
							}	
							else if(prev=='O'){
								//System.out.println("tj");
								out_txt_box.setText(user1+" wins");
								this.mark_winner();
								this.create_pop_for_new_game(user1);
							}	

								
						}	
						else if(this.checkstate()==2)
							out_txt_box.setText("Game -Tie");

					}
				}
			}
		}
		else if(choice==3){
			
			timer=new Timer(1000,new ActionListener(){
				public void actionPerformed(ActionEvent e){
		
					if(prev=='X')
					{
						generate_random('O');
						out_txt_box.setText("computers move");
						prev='O';
						
					}
					else if(prev=='O')
					{
						artificial_intelligence('X');
						out_txt_box.setText("AI's move");
						prev='X';
	
					}
					if(checkstate()==2){
						out_txt_box.setText("Game -Tie");
						timer.stop();
					}
					else if(checkstate()==1){
						if(prev=='X'){
							out_txt_box.setText("AI won");
							create_pop_for_new_game("AI");
							mark_winner();
							timer.stop();
						}	
						else if(prev=='O'){
							out_txt_box.setText("computer won");
							//create_pop_for_new_game("computer");
							mark_winner();
							timer.stop();
						}
						
						
					}

				}
				
			});
			timer.start();
			
			
		}
		else if(choice==4){
			int l,m;
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					if(e.getSource().equals(this.b[i][j])){
						l=i;
						m=j;
						
						this.put_in_grid(l, m, 'O');
						out_txt_box.setText(user1+"'s"+"move");
						if(this.checkstate()==1){
							out_txt_box.setText(user1+" wins");
							this.mark_winner();
							this.create_pop_for_new_game(user1);

						}	
						else if(this.checkstate()==2)
							out_txt_box.setText("Game -Tie");
						
						this.artificial_intelligence('X');
						if(this.checkstate()==1){
							out_txt_box.setText("AI"+" wins");
							this.mark_winner();
							this.create_pop_for_new_game("AI");

						}	
						else if(this.checkstate()==2)
							out_txt_box.setText("Game -Tie");
						
						
					}
				}
			}
		}
		
				
	}
	
	public static void main(String[] args){
		
		
		board obj=new board();
		//obj.disp();
	}

}
