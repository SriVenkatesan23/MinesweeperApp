import java.awt.Graphics;
import java.util.*;

public class Board {

	Tile[][] board;
	boolean gameOver; //will be used to reset game if it ends
	boolean win; //will be used to reset game if it ends
	int noOfBombs;
	int tilesOpened;
	int x, y;
	int width, height;
	int hw;
	
	/**
	 * 
	 * @param x = x position of board
	 * @param y = y position of board
	 * @param width 
	 * @param height
	 * @param size = the height/width of the board i.e. size of 12 leads to 12x12 game
	 * 
	 * initializes tile pointers, adds bombs to the board, and increments the tiles' 
	 * bombsNearBy counter
	 */
	
	public Board(int x, int y, int width, int height, int size){
		hw=size;
		this.board=new Tile[hw][hw];
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		gameOver=false;
		noOfBombs=(int)(hw*1.5);
		tilesOpened=0;
		win=false;
		
		
		
		this.board = setTileNeighbors(); //sets each tile's right up down etc neighbor
		this.board = addBombs(); //adds bombs into the board
		this.board = incrBombs(this.board);
		
	}

	
	/**
	 * @param checkX 
	 * @param checkY
	 * ^^ used to determine which tile has been clicked by the player
	 * Sets the display of the tile either to X for mine, GRAYSQUARE for empty, or
	 * number of bombs nearby. It also reveals nearby tiles if necessary
	 * @return
	 */
	public Board updateBoard(int checkX, int checkY){
		for(int r = 0; r < hw; r++){
			for(int c = 0; c < hw; c++){
				if(board[r][c].contains(checkX, checkY)){
					board[r][c].setDisplayValue();
					tilesOpened++;
					if(board[r][c].isBomb){
						gameOver=true;
						return this;
					}
					
					else if(board[r][c].bombsNearBy==0){//if the clicked tile is empty, set values for all surrounding tiles
						board[r][c]=this.revealSurrounding(board[r][c]);
					}
					if(this.tilesOpened+this.noOfBombs==(hw*hw)){ //if all non-mine tiles have been revealed
						win=true;
					}
					return this;
				}
			}
		}
		return this;

	}

	public void display(Graphics g) { //prints out the board so the players can see it
		for(int r = 0; r < hw; r++){
			for (int c = 0; c < hw; c++){
				board[r][c].display(g);
			}
		}
	}
	
	
	/**
	 * recursively shows surrounding tiles if clicked tile is empty
	 * @param t
	 * @return
	 */
	public Tile revealSurrounding(Tile t){
		
		if(t.right!=null && !t.right.isBomb && t.right.display.equals(" ")){
			t.right.setDisplayValue(); tilesOpened++;
			
			if(t.right.bombsNearBy==0) 
				t.right=revealSurrounding(t.right);
		}

		if(t.left!=null && !t.left.isBomb && t.left.display.equals(" ")){
			t.left.setDisplayValue(); tilesOpened++;

			if(t.left.bombsNearBy==0)
				t.left=revealSurrounding(t.left);
		}

		if(t.up!=null && !t.up.isBomb && t.up.display.equals(" ")){
			t.up.setDisplayValue(); tilesOpened++;
			if(t.up.bombsNearBy==0)
				t.up=revealSurrounding(t.up);
		}

		if(t.down!=null && !t.down.isBomb && t.down.display.equals(" ")){
			t.down.setDisplayValue(); tilesOpened++;
			if(t.down.bombsNearBy==0)
				t.down=revealSurrounding(t.down);
		}
		if(t.tright!=null && !t.tright.isBomb && t.tright.display.equals(" ")){
			t.tright.setDisplayValue(); tilesOpened++;
			if(t.tright.bombsNearBy==0)
				t.tright=revealSurrounding(t.tright);
		}
		if(t.dright!=null && !t.dright.isBomb && t.dright.display.equals(" ")){
			t.dright.setDisplayValue(); tilesOpened++;
			if(t.dright.bombsNearBy==0)
				t.dright=revealSurrounding(t.dright);
		}
		if(t.tleft!=null && !t.tleft.isBomb && t.tleft.display.equals(" ")){
			t.tleft.setDisplayValue(); tilesOpened++;
			if(t.tleft.bombsNearBy==0)
				t.tleft=revealSurrounding(t.tleft);
		}
		if(t.dleft!=null && !t.dleft.isBomb && t.dleft.display.equals(" ")){
			t.dleft.setDisplayValue(); tilesOpened++;
			
			if(t.dleft.bombsNearBy==0)
				t.dleft=revealSurrounding(t.dleft);
		}

		return t;
	}
	/**
	 * goes through grid and sets "neighbor" pointers of each tile
	 */
	public Tile[][] setTileNeighbors(){
		for(int r = 0; r < hw; r++){
			for (int c = 0; c < hw; c++){
				board[r][c] = new Tile(x + c*width/hw, y + r*height/hw, width / hw, height / hw);
			}
		}
		for(int r = 0; r < hw; r++){
			for (int c = 0; c < hw; c++){
				if(c+1<hw){ //right is set if not the last column
					board[r][c].right=board[r][c+1];
				}
				if(c-1>-1){ //left
					board[r][c].left=board[r][c-1];
				}
				if(r-1>-1){ //up
					board[r][c].up=board[r-1][c];
				}
				if(r+1<hw){ //down
					board[r][c].down=board[r+1][c];
				}
				if(c+1<hw && r-1>-1){ //top-right
					board[r][c].tright=board[r-1][c+1];
				}
				if(c-1>-1 && r-1>-1 ){ //top-left 
					board[r][c].tleft=board[r-1][c-1];
				}
				if(c+1<hw && r+1<hw ){ //down-right 
					board[r][c].dright=board[r+1][c+1];
				}
				if(c-1>-1 && r+1<hw  ){ //down-left 
					board[r][c].dleft=board[r+1][c-1];
				}
			}
		}
		
		
		
		return board;
	}
	/**
	 * Inserts noOfBombs amount of bombs into the gameBoard
	 */
	public Tile[][] addBombs(){
		
		int counter=0;
		while(counter<noOfBombs){
			int a=new Random().nextInt(hw);
			int b=new Random().nextInt(hw);
			if(!this.board[a][b].isBomb){
				this.board[a][b].isBomb=true;
				counter++;
			}
		}
		
		return this.board;
		
	}
	/**
	 * fixes bombs nearby indicator for each tile
	 */
	public Tile[][] incrBombs(Tile[][] board){

		for(int r = 0; r < hw; r++){
			for (int c = 0; c < hw; c++){
				
				if(board[r][c].isBomb){ //bomb tiles have a bombsNearBy count of 0
					continue;
				}
				if( board[r][c].right!=null && board[r][c].right.isBomb) board[r][c].bombsNearBy++;
				if( board[r][c].left!=null && board[r][c].left.isBomb) board[r][c].bombsNearBy++;
				if( board[r][c].up!=null && board[r][c].up.isBomb) board[r][c].bombsNearBy++;
				if( board[r][c].down!=null && board[r][c].down.isBomb) board[r][c].bombsNearBy++;
				if( board[r][c].dright!=null && board[r][c].dright.isBomb) board[r][c].bombsNearBy++;
				if( board[r][c].dleft!=null && board[r][c].dleft.isBomb) board[r][c].bombsNearBy++;
				if( board[r][c].tright!=null && board[r][c].tright.isBomb) board[r][c].bombsNearBy++;
				if( board[r][c].tleft!=null && board[r][c].tleft.isBomb) board[r][c].bombsNearBy++;
				
			}
		}
		return this.board;
	}
}
