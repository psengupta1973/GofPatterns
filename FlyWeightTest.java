/*
A Structural Pattern.

The Flyweight pattern is useful for situations where you have 
a small number of different objects that might be needed 
a very large number of times—with slightly different data 
that can be externalized outside those objects. 
*/

import java.util.Hashtable;

public class FlyWeightTest {
	
	FlyWeightTest(){
	}

	public static void main(String[] args) throws Exception{
		
		System.out.println("-------------- FLY_WEIGHT ---------------");

		ChessPieceFactory factory = new ChessPieceFactory();
		
		// creating fresh instnces
		King wk = (King)factory.getChessPiece("king", true);
		wk.move(12, 3, 6, 6);
		Rook br = (Rook)factory.getChessPiece("rook", false);
		br.move(12, 3, 6, 6);
		Queen wq = (Queen)factory.getChessPiece("queen", true);
		wq.move(12, 3, 6, 6);
		
		// trying to use existing instnces
		King kx = (King)factory.getChessPiece("king", true);
		kx.move(12, 3, 6, 6);
		Rook rx = (Rook)factory.getChessPiece("rook", true);
		rx.move(12, 3, 6, 6);
		Queen qx = (Queen)factory.getChessPiece("queen", true);
		qx.move(12, 3, 6, 6);
	}
};

class ChessPieceFactory {
	
	private Hashtable pieces = new Hashtable();
	
	public ChessPiece getChessPiece(String name, boolean white){
		String col = "White";
		if(!white){
			col = "Black";
		}

		ChessPiece piece = (ChessPiece)pieces.get(name);
		
		if(piece == null){
			System.out.println("Creating new "+col+" "+name);
			if(name.equals("rook")){
				piece = new Rook(col);
			}
			if(name.equals("king")){
				piece = new King(col);
			}
			if(name.equals("queen")){
				piece = new Queen(col);
			}
			pieces.put(name, piece);
		}
		else{
			System.out.println("Using existing "+name+"...");
			if(!piece.getColor().equals(col)){
				System.out.println("(Changing color to "+col+")");
				piece.setColor(col);
			}
		}

		return piece;
	}
};


// Flyweight
abstract class ChessPiece {
	String color;
	
	public String getColor(){
		return color;
	}
	public void setColor(String col){
		color = col;
	}
	public abstract void move(int x1, int y1, int x2, int y2);
};


// Concrete flyweight 1
class Rook extends ChessPiece {

	Rook(String col){
		color = col;
	}
	
	public void move(int x1, int y1, int x2, int y2){
		System.out.println(color+" rook moved from ("+x1+", "+y1+") to ("+x2+", "+y2+") straight.\n");
	}
};


// Concrete flyweight 2
class King extends ChessPiece {

	King(String col){
		color = col;
	}
	
	public void move(int x1, int y1, int x2, int y2){
		System.out.println(color+" king moved from ("+x1+", "+y1+") to ("+x2+", "+y2+") By 1 grid.\n");
	}
};

// Concrete flyweight 3
class Queen extends ChessPiece {

	Queen(String col){
		color = col;
	}
	
	public void move(int x1, int y1, int x2, int y2){
		System.out.println(color+" queen moved from ("+x1+", "+y1+") to ("+x2+", "+y2+").");
	}
};

// Unshared Concrete Flyweight
class UnsharedChessPiece extends ChessPiece 
{
	public void move(int x1, int y1, int x2, int y2){
	}
};