/*
A Creational Pattern.

Specify the kind of objects to create using a prototypical instance, 
and create new objects by copying this prototype 
*/

public class PrototypeTest { 

    public static void main(String[] args){
		System.out.println("-------------- PROTOTYPE ---------------");

		PrototypeColor c1 = new Color("Red", 255, 0, 0);
		PrototypeColor c2 = c1.cloneMe();

		System.out.println("c1: "+c1.get());
		
		c2.set("Green", 0, 255, 0);
		System.out.println("c2: "+c2.get());
		System.out.println("c1: "+c1.get());
	}
}


// Prototype
abstract class PrototypeColor implements Cloneable { 

    String name = null;
	int red;
	int green;
	int blue;
	
	public abstract PrototypeColor cloneMe();

	public String get(){
		return "Color: "+name+" [R:"+red+", G:"+green+", B:"+blue+"]";
	}

	public void set(String str, int r, int g, int b){
		name = str;
		red = r;
		green = g;
		blue = b;
	}
}


// Concrete prototype
class Color extends PrototypeColor { 

	Color(String str, int r, int g, int b){
		name = str;
		red = r;
		green = g;
		blue = b;
	}

	public PrototypeColor cloneMe(){
		try{
			return (PrototypeColor)this.clone();
		}
		catch(CloneNotSupportedException cns){
			System.out.println(cns.getMessage());
		}
		return null;
	}

}
