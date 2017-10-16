/*
A Behavioral Pattern

The Chain of Responsibility is intended to promote loose coupling 
between the sender of a request and its receiver by giving more than 
one object an opportunity to handle the request. The receiving objects 
are chained and pass the request along the chain until one of the objects handles it.
*/

public class ChainOfResponsibilityTest { 

    public static void main(String[] args){
		
		System.out.println("-------------- CHAIN_OF_RESPONSIBILITY ---------------");
		Handler h1 = new TrimHandler();
		Handler h4 = new PerfectCaseHandler();
		Handler h2 = new UpperCaseHandler();
		Handler h3 = new LowerCaseHandler();

		h1.setSuccessor(h2);
		h2.setSuccessor(h3);
		h3.setSuccessor(h4);

		Request req = new Request("ParaDise");
		h1.handle(req);

		System.out.println(req.get());
	}
}


// Request
class Request { 

    String str = null;

	Request(String str){
		this.str = str;
	}
	
	public String get(){
		return str;
	}
	public void set(String str){
		this.str = str;
	}
}

// Chain
abstract class Handler { 
	Handler successor;

	public abstract void handle(Request req);
	
	public Handler getSuccessor(){
		return successor;
	}

	public void setSuccessor(Handler handler){
		successor = handler;
	}
}

// Concrete Chain 1
class TrimHandler extends Handler { 

	public void handle(Request req){
		String str = req.get();
		if(str.startsWith(" ") || str.endsWith(" ")){
			str = str.trim();
			req.set(str);
			System.out.println("TrimHandler handled...");
		}
		else{
			if(successor != null){
				successor.handle(req);
			}
		}
	}
}


// Concrete Chain 2
class PerfectCaseHandler extends Handler { 
	
	public void handle(Request req){
		String str = req.get();
		if(!str.substring(0, 1).equals(str.toUpperCase().substring(0, 1))
			|| !str.substring(1).equals(str.toLowerCase().substring(1)) ){
			str = str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
			req.set(str);
			System.out.println("PerfectCaseHandler handled...");
		}
		else{
			if(successor != null){
				successor.handle(req);
			}
		}
	}
}

// Concrete Chain 3
class UpperCaseHandler extends Handler { 
	
	public void handle(Request req){
		String str = req.get();
		if(!str.equals(str.toUpperCase())){
			str = str.toUpperCase();
			req.set(str);
			System.out.println("UpperCaseHandler handled...");
		}
		else{
			if(successor != null){
				successor.handle(req);
			}
		}
	}
}

// Concrete Chain 4
class LowerCaseHandler extends Handler { 
	
	public void handle(Request req){
		String str = req.get();
		if(!str.equals(str.toLowerCase())){
			str = str.toLowerCase();
			req.set(str);
			System.out.println("LowerCaseHandler handled...");
		}
		else{
			if(successor != null){
				successor.handle(req);
			}
		}
	}
}
