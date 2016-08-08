import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
public class Tile {

	public boolean isBomb;
	public int bombsNearBy;
	public int x,y,width,height;
	public String display;
	public Tile right,left,up,down,tright,tleft,dright,dleft;



	public Tile(int startX, int startY, int startWidth, int startHeight) //default constructor for each tile
	{
		x = startX;
		y = startY;
		width = startWidth;
		height = startHeight;
		
		bombsNearBy=0;
		isBomb=false;
		display=" ";
		right=left=up=down=tright=tleft=dright=dleft=null;

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
		g.fillRect(x, y, width, height);
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
		}
		else if(display.equals("X")){
			g.setFont(new Font("Chiller", Font.PLAIN, 30));
			g.drawString("X",    (int)(x+(double)this.width/2.3)-5   ,   (int)(y+(double)this.height/2.3)+15);
		}
		else{
			g.setFont(new Font("Chiller", Font.PLAIN, 30));
			g.drawString(""+bombsNearBy,   (int)(x+(double)this.width/2.3)-5   ,   (int)(y+(double)this.height/2.3)+15);
		}

	}

	
}
