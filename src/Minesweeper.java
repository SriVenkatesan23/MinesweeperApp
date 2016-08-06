import java.awt.Color;


import javax.swing.*;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;


public class Minesweeper extends JFrame implements MouseListener  {

	
	
	private static final long serialVersionUID = 1L;
	Board gameBoard = new Board(59,110,560,560, 12); //creating gameBoard
	boolean begin = false; //will be used to determine what to display
	
	public static void main(String[] args){
		Minesweeper m = new Minesweeper();
		m.getContentPane().setBackground(Color.darkGray);
		m.setSize(700,700);
		m.setVisible(true);
	}
	
	/**
	 * Initializes applet upon which the game will be played
	 */
	public Minesweeper() 
	{
		
		addMouseListener(this);
		setFocusable(true);
		
	}
	
	/**
	 * Displays gameBoard, as well as the MINESWEEPER title at beginning
	 * and information pertinent to the player
	 * Prints win/lose statements
	 */
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setFont(new Font("Chiller", Font.PLAIN, 70));
		gameBoard.display(g);

		if(!gameBoard.gameOver && !gameBoard.win){
			if(!begin){
				g.drawString("MINESWEEPER", 75, 100);
				begin=true;
			}else{
				g.setFont(new Font("Chiller", Font.PLAIN, 18));
				g.drawString("Tiles Opened: "+gameBoard.tilesOpened, 76, 70);
				g.drawString("Number Of Mines: "+gameBoard.noOfBombs, 76, 90);
			}
		}
		else if(gameBoard.win){
			try {
				g.setFont(new Font("Chiller", Font.PLAIN, 70));
				for(int i=0;i<6;i++){
					g.drawString("YOU WIN", 125, 95);
					g.setColor(Color.darkGray); //causes "YOU WIN" to flash on screen
					Thread.sleep(300);
					g.drawString("YOU WIN", 125, 95);
					g.setColor(Color.CYAN.darker());
					Thread.sleep(300);
				}             //1000 milliseconds is one second.
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			System.exit(0);
		}

		else if(gameBoard.gameOver){
			try {
				g.setFont(new Font("Chiller", Font.PLAIN, 70));
				
				for(int i=0;i<6;i++){
					
					g.drawString("GAME OVER", 125, 95);
					g.setColor(Color.darkGray);  //causes the "GAME OVER" to flash on screen
					Thread.sleep(300);
					g.drawString("GAME OVER", 125, 95);
					g.setColor(Color.CYAN.darker());
					Thread.sleep(300);
				}
				//1000 milliseconds is one second.
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			this.gameBoard=new Board(59,110,560,560, 12);  //reseting board to start game over
			begin=false;
			repaint();
		}
	}



	public void mouseClicked(MouseEvent e) {
		//begin=true;
		gameBoard=gameBoard.updateBoard(e.getX(), e.getY());
		repaint();
	}
	/**
	 * Methods required by imported libraries
	 */
	
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	public void windowClosing(WindowEvent e)
	{
	dispose();
	System.exit(0);
	}
	public void windowOpened(WindowEvent e)
	{ }
	public void windowIconified(WindowEvent e)
	{ }
	public void windowClosed(WindowEvent e)
	{ }
	public void windowDeiconified(WindowEvent e)
	{ }
	public void windowActivated(WindowEvent e)
	{ }
	public void windowDeactivated(WindowEvent e)
	{ }

}
