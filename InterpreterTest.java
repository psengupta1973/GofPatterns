/*
A Behavioral Pattern.

Some applications provide support for built-in scripting and 
macro languages so users can describe operations they can 
perform in the application. The Interpreter is intended to 
provide you with a way to define a representation of the grammar 
of a language with an interpreter that uses the representation 
to interpret sentences in the language.
*/

import java.util.ArrayList;
import java.util.Hashtable;

public class InterpreterTest {

    public static void main(String[] args) throws Exception{
		System.out.println("-------------- INTERPRETER ---------------");
		if(args.length == 0){
			args = new String[]{"44"};
		}

		Expression con = new Expression(Integer.parseInt(args[0]));

		ArrayList interpreters = new ArrayList();
		
		interpreters.add(new _10thPlaceInterpreter()); 
		interpreters.add(new _1stPlaceInterpreter()); 

		for (int i=0; i<interpreters.size(); i++)
		{
			((Interpreter)interpreters.get(i)).interpret(con);
		}
		System.out.println(con.getInput()+" = "+con.getOutput());
	}
}


// Context
class Expression {
	int input;
	String output;
	
	Expression(int in){
		setInput(in);
		setOutput("");
	}
	
	public void setInput(int in){
		input = in;
	}
	public int getInput(){
		return input;
	}
	public void setOutput(String out){
		output = out;
	}
	public String getOutput(){
		return output;
	}
	public String getInputStr(){
		return ""+input;
	}
};


// Abstract Interpreter
abstract class Interpreter {
	Hashtable numbers = new Hashtable();
	public void init(){
		numbers.put("2+", "Twen");
		numbers.put("3+", "Thir");
		numbers.put("4+", "For");
		numbers.put("5+", "Fif");
		numbers.put("6+", "Six");
		numbers.put("7+", "Seven");
		numbers.put("8+", "Eigh");
		numbers.put("9+", "Nine");
	}
	public abstract void interpret(Expression con);
};


// Terminal Interpreter
class _1stPlaceInterpreter extends Interpreter { 

	_1stPlaceInterpreter(){
		super.init();
		numbers.put("0", "Zero");
		numbers.put("1", "One");
		numbers.put("2", "Two");
		numbers.put("3", "Three");
		numbers.put("4", "Four");
		numbers.put("5", "Five");
		numbers.put("6", "Six");
		numbers.put("7", "Seven");
		numbers.put("8", "Eight");
		numbers.put("9", "Nine");
	}

	public void interpret(Expression con){
		String words = con.getInputStr();
		if(words.length() > 1 && (words.startsWith("1") || words.endsWith("0"))){
			return;
		}
		words = words.substring(words.length()-1);
		words = (String)numbers.get( words );
		con.setOutput(con.getOutput()+words);
	}
};


// Terminal expression
class _10thPlaceInterpreter extends Interpreter { 
	
	_10thPlaceInterpreter(){
		super.init();
		numbers.put("10", "Ten");
		numbers.put("11", "Eleven");
		numbers.put("12", "Twelve");
	}

	public void interpret(Expression con){
		if(con.getInput() < 10){
			return;
		}
		String words = con.getInputStr();
		words = words.substring(words.length()-2);
		if(words.endsWith("00")){
			return;
		}
		String key = null;

		if(con.getInput() > 9 && con.getInput() < 13 ){
			key = (String)numbers.get( con.getInputStr() );
			con.setOutput(con.getOutput()+key );
		}
		else{
			if(con.getInput() > 12 && con.getInput() < 20 ){
				words = words.substring(words.length()-1);
				key = (String)numbers.get( words+"+" );
				if(key != null){
					con.setOutput(con.getOutput()+key +"teen" );
				}
			}
			else{
				if(con.getInput() > 19){
					key = (String)numbers.get( words.substring(words.length()-2, words.length()-1)+"+" );
					if(key != null){
						con.setOutput(con.getOutput()+key+"ty ");
					}
				}
			}
		}
	}
};

