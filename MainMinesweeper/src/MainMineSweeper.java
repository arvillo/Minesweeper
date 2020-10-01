import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMineSweeper extends JFrame implements ActionListener{
	
	JPanel mainPnl;
	JButton easyBtn, mediumBtn, hardBtn;
	JLabel mainLbl, imgLbl;
	
	public MainMineSweeper() {
		initComponents();
		setComponents();
		viewComponents();
		//Sweep sweep = new Sweep();
		//sweep.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //sweep.show();
	}

	public void initComponents(){
		mainPnl = new JPanel();
		easyBtn = new JButton("Easy");
		mediumBtn = new JButton("Medium");
		hardBtn = new JButton("Hard");
		mainLbl = new JLabel("MainSweeper");
		imgLbl = new JLabel(new ImageIcon("mine.jpg"));
		
	}
	
	public void setComponents(){
		setSize(300,400);
		setTitle("MainSweeper");
		setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainLbl.setFont(new Font("Arial",Font.PLAIN,24));
		
		imgLbl.setBounds(100,20,80,80);
		add(imgLbl);
		
		mainLbl.setBounds(67,110,150,30);
		add(mainLbl);
		easyBtn.setBounds(90, 150, 100, 30);
		add(easyBtn);
		mediumBtn.setBounds(90, 200, 100, 30);
		add(mediumBtn);
		hardBtn.setBounds(90, 250, 100, 30);
		add(hardBtn);
		
		easyBtn.addActionListener(this);
		mediumBtn.addActionListener(this);
		hardBtn.addActionListener(this);
		
		
	}
	
	public void viewComponents(){
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new MainMineSweeper();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if(obj == easyBtn){
			dispose();
			Sweep sweep = new Sweep();
			sweep.setSize(400,400);
	        sweep.setVisible(true);
		}
		else if(obj == mediumBtn){
			dispose();
			Sweep2 sweep2 = new Sweep2();
			sweep2.setSize(400,400);
	        sweep2.setVisible(true);
		}
		if(obj == hardBtn){
			dispose();
			Sweep3 sweep3 = new Sweep3();
			sweep3.setSize(400,400);
	        sweep3.setVisible(true);
		}
		
	}

}
