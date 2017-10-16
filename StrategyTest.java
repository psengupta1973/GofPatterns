/*
A Behavioral Pattern.

The Strategy pattern is very useful for situations where you would like 
to dynamically swap the algorithms used in an application. If you think 
of an algorithm as a strategy for accomplishing some task, you can begin 
to imagine ways to use Strategy. Strategy is intended to provide you with 
a means to define a family of algorithms, encapsulate each one as an object, 
and make them interchangeable. Strategy lets the algorithms vary independently 
from clients that use them.
 
*/

public class StrategyTest {

	public static void main(String[] args){
		System.out.println("-------------- STRATEGY ---------------");

		Calculator calculator = new Calculator();
		calculator.setA(10.0);
		calculator.setB(3);
		calculator.setMethod(new Addition());
		calculator.calculate();
		calculator.setMethod(new Subtraction());
		calculator.calculate();
		calculator.setMethod(new Multiplication());
		calculator.calculate();
		calculator.setMethod(new Division());
		calculator.calculate();
	}
}


// Context
class Calculator {
	
	double a, b;
	CalculationMethod method = null;

	Calculator(){}
	
	public void setA(double n){
		a = n;
	}
	public void setB(double n){
		b = n;
	}
	public void setMethod(CalculationMethod m){
		method = m;
	}
	public void calculate(){
		if(method == null){
			System.out.println("Please specify a calculation method.");
			return;
		}
		System.out.println(method.getClass().getName()+" of "+a+" and "+b+" = "+method.execute(a, b));
	}
};


// Strategy
interface CalculationMethod {
	public double execute(double a, double b);
};


// Strategy 1
class Addition implements CalculationMethod {
	public double execute(double a, double b){
		return a+b;
	}
};


// Strategy 2
class Division implements CalculationMethod {

	public double execute(double a, double b){
		return a/b;
	}
};


// Strategy 3
class Subtraction implements CalculationMethod {
	
	public double execute(double a, double b){
		return a-b;
	}
};

// Strategy 4
class Multiplication implements CalculationMethod {
	
	public double execute(double a, double b){
		return a*b;
	}
};
