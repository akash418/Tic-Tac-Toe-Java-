import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;

public class Main extends JFrame implements ActionListener{
	private JPanel buttonpanel;
	private static int Def_Width=400;
	private static int Def_Height=400;
	private Label msglabel;
	private Frame mainFrame;
	JButton Start_Game=new JButton();
	JButton Exit =new JButton();
	public void Main_Menu_Frame(){
		
		
		setSize(Def_Width,Def_Height);
		Start_Game=new JButton("Start-Game");
		Start_Game.setBounds(150, 100, 100, 70);
		Start_Game.addActionListener(this);
		Exit=new JButton("Exit-Game");
		Exit.addActionListener(this);
		Exit.setBounds(150,200,100,70);
		buttonpanel=new JPanel();
		JLabel label1 = new JLabel();
		label1.setText("Tic-Tac-Toe-Game");
		label1.setBounds(150, 30, 150, 100);
		buttonpanel.add(label1);
		buttonpanel.add(Start_Game);
		buttonpanel.add(Exit);
		add(buttonpanel);
		buttonpanel.setLayout(null);
		buttonpanel.setVisible(true);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
				
		
		
		
		
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getActionCommand().equals("Exit-Game")){
			Exit.setForeground(Color.BLUE);
			System.exit(0);
		}
		else if(e.getActionCommand().equals("Start-Game")){
			Start_Game.setForeground(Color.BLUE);
			board obj=new board();
			this.setVisible(false);
			obj.setVisible(true);
			
		}
		
	}
	
	public static void main(String[] args){
		
		Main obj=new Main();
		obj.Main_Menu_Frame();
	}
}
