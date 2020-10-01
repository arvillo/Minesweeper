import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
public class Sweep extends JFrame{
	
	
	JLabel imgLbl = new JLabel(new ImageIcon("mine.jpg"));
	final int row = 7, col = 7, noOfBombs = 10;
    JToggleButton[][] blocks = new JToggleButton[row][col];
    int[][] blox = new int[row][col];
   
    //blox -1 = ada bom
    //blox 0 = blom dipencet
    //blox -2 = kosong belum terpencet
    //blox -3 = flag
    //blox -4 = flag bomb
    boolean first = true, canPlay = true;
    

	private JPanel jPanel1;
//	private JMenuBar jMenuBar1;
//	private JMenu jMenu1;
//	private JMenuItem jMenuItem1;
	
	MouseListener mouseClick = new MouseListener() {
		
		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(SwingUtilities.isLeftMouseButton(e) || e.isControlDown())
			{
				int i=0,j=0;
				boolean found = false;
				for(i = 0; i < col; i++)
				{
					for(j = 0; j < row; j++)
					{
						if(e.getSource() == blocks[i][j])
						{
							found = true;
							break;
						}
					}
					if(found) break;
				}
				if(blox[i][j] != -3 && blox[i][j] != -4)
				{					
					if(canPlay)
					{
						blocks[i][j].setSelected(true);
						if(first)
						{
							spawn(i,j);
							first = false;
						}
						if(blox[i][j]!= -1 ){
							open(i,j);
							reval();
						}
						else lose();
						checkWin();
					}
				}
				else
				{
					blocks[i][j].setSelected(false);
				}
			}
			else if(SwingUtilities.isRightMouseButton(e) || e.isControlDown())
			{
				int i=0,j=0;
				boolean found = false;
				for(i = 0; i < col; i++)
				{
					for(j = 0; j < row; j++)
					{
						if(e.getSource() == blocks[i][j])
						{
							found = true;
							if(blox[i][j] == -1)
							{								
								blocks[i][j].setText("Flag");
								blox[i][j] = -4;
							}
							else if(blox[i][j] == 0)
							{								
								blocks[i][j].setText("Flag");
								blox[i][j] = -3;
							}
							else if(blox[i][j] == -4)
							{								
								blocks[i][j].setText("");
								blox[i][j] = -1;
							}
							else if(blox[i][j] == -3)
							{
								blocks[i][j].setText("");
								blox[i][j] = 0;
							}
							break;
						}
					}
					if(found) break;
				}
			}
		}
	};
	
	private void open(int x, int y)
	{
		if(x < 0 || y < 0 || x > col - 1 || y > row - 1 || blox[x][y] != 0) return;
		int bombs = 0;
        	for(int i = x - 1; i <= x + 1;i++)
        	{
            		for(int j = y - 1; j <= y + 1;j++)
            		{
                		if(!(i < 0 || j < 0 || i > row - 1 || j > col - 1) && blox[i][j] == -1) bombs++;
            		}
        	}
        	if(bombs == 0)
        	{
            		blox[x][y] = -2;
            		for(int i = x - 1; i <= x + 1;i++)
            		{
                		for(int j = y - 1; j <= y + 1;j++)
                		{
                    			if(!(i < 0 || j < 0 || i > row - 1 || j > col - 1))
                    				if(i != x || j != y)
                    					if(blox[i][j] != -3)
                    						open(i,j);
                		}
            		}
        	} else blox[x][y] = bombs;
	}
	
	protected void checkWin() {
		// TODO Auto-generated method stub
		boolean win = true;
		int countBF = 0;
		for(int i=0; i<row; i++)
		{
			for(int j=0; j<col; j++)
			{
				if(blox[i][j]==0)
				{
					win = false;
					break;
				}
				if(blox[i][j] == -1 || blox[i][j] == -3 || blox[i][j] == -4)
				{
					countBF++;
				}
				if(!win) break;
			}
		}
		if(win && countBF == noOfBombs)
		{
			JOptionPane.showMessageDialog(null, "You Win !!", "You Win", JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}
	}

	protected void lose() {
		// TODO Auto-generated method stub
		canPlay = false;
		for(int i=0; i<row; i++)
		{
			for(int j=0; j<col; j++)
			{
				if(blox[i][j]==-1 || blox[i][j] == -4)
				{
					blocks[i][j].setIcon(new ImageIcon(
							((new ImageIcon("mine.jpg")).getImage())
							.getScaledInstance(jPanel1.getWidth()/row, jPanel1.getHeight()/col, Image.SCALE_SMOOTH)));
					blocks[i][j].setSelected(true);
				}
			}
		}
		JOptionPane.showMessageDialog(null, "You Lose!!", "Lose" , JOptionPane.INFORMATION_MESSAGE);
		dispose();
		
	}

	private void reval()
	{
		for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                if(blox[i][j] == 0){
                	blocks[i][j].setText("");
                	blocks[i][j].setSelected(false);
                }
                if(blox[i][j] == -2){
                    blocks[i][j].setText("");
                    blocks[i][j].setSelected(true);
                }
                if(blox[i][j] > 0){
                    blocks[i][j].setText(""+blox[i][j]);
                    blocks[i][j].setSelected(true);
                }
            }
        }
        jPanel1.repaint();
	}
	
	private void spawn(int x,int y)
	{
		for(int k = 1; k <= noOfBombs;k++){
            int i, j;
            do{
                i = (int)(Math.random()*(col - .01));
                j = (int)(Math.random()*(row - .01));
            }
            while(blox[i][j] == -1 || (i == x && j == y));
            blox[i][j] = -1;
        }
	}
	
	public Sweep() {
		setTitle("Kalo Error Salah Jeje");
		initComponents();
		for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
            	blocks[i][j] = new JToggleButton();
                blocks[i][j].setSize(jPanel1.getWidth()/row, jPanel1.getHeight()/col);
                jPanel1.add(blocks[i][j]);
                blocks[i][j].setLocation(i*jPanel1.getWidth()/row, j*jPanel1.getHeight()/col);
                blocks[i][j].addMouseListener(mouseClick);
            }
        }
	}
	
	private void resiz(){
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
            	blocks[i][j].setSize(jPanel1.getWidth()/row, jPanel1.getHeight()/col);
                jPanel1.add(blocks[i][j]);
                blocks[i][j].setLocation(i*jPanel1.getWidth()/row, j*jPanel1.getHeight()/col);
            }
        }
    }

	private void initComponents() {
		// TODO Auto-generated method stub
		jPanel1 = new JPanel();
//        jMenuBar1 = new JMenuBar();
//        jMenu1 = new JMenu();
//        jMenuItem1 = new JMenuItem();
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        jPanel1.addComponentListener(new ComponentAdapter() {
        	public void componentResized(ComponentEvent e)
        	{
        		jPanel1ComponentResized(e);
        	}
		});
        
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 473, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 328, Short.MAX_VALUE)
        );
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
        );
        
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
        );
	}

	protected void jPanel1ComponentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		resiz();
	}

}
