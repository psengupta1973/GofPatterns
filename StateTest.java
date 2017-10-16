/*
A Behavioral Pattern.

The State pattern is useful when you want to have an object represent 
the state of an application, and you want to change the state by changing 
that object. The State pattern is intended to provide a mechanism to allow 
an object to alter its behavior in response to internal state changes. 
To the client, it appears as though the object has changed its class. 
The benefit of the State pattern is that state-specific logic is localized 
in classes that represent that state.
 
*/

public class StateTest {

	public static void main(String[] args){

		System.out.println("-------------- STATE ---------------");

		H2O h2o = new H2O(20.0, new Water());
		System.out.println("Got some: "+h2o.getState().getClass().getName()+" at "+h2o.getTemp());
	
		h2o.raiseTemp(120.0);
		System.out.println("Got some: "+h2o.getState().getClass().getName()+" at "+h2o.getTemp());

		h2o.lowerTemp(150.0);
		System.out.println("Got some: "+h2o.getState().getClass().getName()+" at "+h2o.getTemp());
	}
}


// Context
class H2O {

	double temp;
	State currentState;

	H2O(double t, State st){
		temp = t;
		currentState = st;
	}

	public double getTemp(){
		return temp;
	}
	public State getState(){
		return currentState;
	}

	public void raiseTemp(double t){
		temp = temp+t;
		currentState = currentState.setTemp(temp);
	}
	public void lowerTemp(double t){
		temp = temp-t;
		currentState = currentState.setTemp(temp);
	}
};


// State
abstract class State {
	
	double uTemp;
	double lTemp;

	public abstract State setTemp(double t);
};


// State 1
class Ice extends State {

	Ice(){
		uTemp = 0.0;
		lTemp = -100;
	}
	public State setTemp(double t){
		if(t > uTemp){
			State st = new Water();
			return st.setTemp(t);
		}
		return this;
	}
};


// State 2
class Water extends State {

	Water(){
		uTemp = 100.0;
		lTemp = 0.0;
	}
	public State setTemp(double t){
		if(t > uTemp){
			return new Vapour();
		}
		if(t < lTemp){
			return new Ice();
		}
		return this;
	}
};

// State 3
class Vapour extends State {
	
	Vapour(){
		uTemp = 1000;
		lTemp = 100;
	}
	public State setTemp(double t){
		if(t < lTemp){
			State st = new Water();
			return st.setTemp(t);
		}
		return this;
	}
};

