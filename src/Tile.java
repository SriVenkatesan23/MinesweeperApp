import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
public class Tile {

	public boolean isBomb; //whether or not this tile contains a mine
	public int bombsNearBy; //keeps track of how many neighbors are mines
	public int x,y,width,height; //determined by board dimensions divided by # of tiles
	public String display; //what the player sees after clicking the tile
	public Tile right,left,up,down,tright,tleft,dright,dleft; //pointers to neighbors; will be used to increment bombsNearBy



	public Tile(int startX, int startY, int startWidth, int startHeight) //constructor for each tile
	{
		x = startX;
		y = startY;
		width = startWidth;
		height = startHeight;
		bombsNearBy=0;
		isBomb=false;
		display=" ";  //unclicked tiles have a " " display value
		right=left=up=down=tright=tleft=dright=dleft=null; //neighbor pointers are set by method in Board.java
	}
	/**
	 * Gets called when a tile is clicked, to see which tile has been clicked
	 */
	public boolean contains(int checkX, int checkY){
		return (checkX > x) && (checkY > y) && (checkX < x + width) && (checkY < y + height);
	}
	/**
	 * Sets the display value (bombsNearBy or X for mine) when the user clicks on a tile or when revealSurrounding is called
	 */
	public void setDisplayValue(){
		if(this.isBomb){
			this.display="X";
		}
		else{
			this.display=""+this.bombsNearBy;
		}
	}
	public void display(Graphics g) //used to actually print out the tile
	{
		g.setColor(Color.BLACK); 
		g.fillRect(x, y, width, height); //background is black
		g.setColor(Color.CYAN.darker());
		g.drawRect(x, y, width, height);
		g.drawRect(x+1, y+1, width-2, height-2);
		g.setColor(Color.CYAN.darker());
		if(display.equals(" ")){//not clicked
			return;
		}
		else if(display.equals("0")){ //blank tile
			g.setColor(Color.GRAY);
			g.fillRect(x, y, width, height);
			g.setColor(Color.CYAN.darker());
		}
		else if(display.equals("X")){ //is a mine
			g.setFont(new Font("Chiller", Font.PLAIN, 30));
			g.drawString("X", (int)(x+(double)this.width/2.3)-5 , (int)(y+(double)this.height/2.3)+15); //fitting X to tile size
		}
		else{ //has a mine nearby
			g.setFont(new Font("Chiller", Font.PLAIN, 30));
			g.drawString(""+bombsNearBy, (int)(x+(double)this.width/2.3)-5 , (int)(y+(double)this.height/2.3)+15); //fitting display to tile size
		}
	}
}
