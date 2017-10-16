/*
A Structural Pattern.

The Bridge is intended to decouple an abstraction from its implementation 
so both can vary independently. For example, a significant maintenance headache 
entails the coupling that occurs between custom classes and the class libraries they use. 
Bridges are useful for minimizing this coupling by providing an abstract description 
of the class libraries to the client.
*/

import java.util.StringTokenizer;

public class BridgeTest { 

    public static void main(String[] args){
		System.out.println("-------------- BRIDGE ---------------");
		View view = setupAbstraction("Twinkle;Twinkle;little;star;How;I;wonder;what;you;are;");
		view.display();
	}

    public static View setupAbstraction(String data){
		View view = new RefinedView(data);
//		ViewImplementor impl = new ColumnWiseView();
		ViewImplementor impl = new LineWiseView();
		view.setImplementor(impl);
		return view;
	}
}


// abstraction
abstract class View { 
    ViewImplementor implementor = null;

	public ViewImplementor getImplementor(){
		return implementor;
	}
	public void setImplementor(ViewImplementor impl){
		implementor = impl;
	}

	public abstract void display();
}


// concrete abstraction
class  RefinedView extends View {
	String str = null;
	
	RefinedView(String str){
		this.str = str;
	}
	public void display(){
		implementor.implementView(str);
	}
}


// Implementor interface
abstract class ViewImplementor { 
	public abstract void implementView(String str);
}


// concrete implementator 1
class ColumnWiseView extends ViewImplementor { 
	public void implementView(String str){
		StringTokenizer tokens = new StringTokenizer(str, ";");
		System.out.println("ColumnWiseViewer: ");
		while (tokens.hasMoreTokens())
		{
			System.out.println(tokens.nextToken());
		}
	}
}


// concrete implementator 2
class LineWiseView extends ViewImplementor { 
	public void implementView (String str){
		StringTokenizer tokens = new StringTokenizer(str, ";");
		System.out.println("LineWiseViewer: ");
		while (tokens.hasMoreTokens())
		{
			System.out.print(tokens.nextToken()+" ");
		}
	}
}

