/*
A Behavioral Pattern.

The Observer pattern is useful when you need to present data 
in several different forms at once. The Observer is intended 
to provide you with a means to define a one-to-many dependency 
between objects so that when one object changes state, all its 
dependents are notified and updated automatically. The object 
containing the data is separated from the objects that display 
the data and the display objects observe changes in that data.
*/

import java.util.ArrayList;

public class ObserverTest {
    public static void main(String[] args){
		System.out.println("-------------- OBSERVER ---------------");

		Stock ibm = new ITStock("IBM", 220);
		Stock oracle = new ITStock("ORA", 198);

		Investor ps = new IndividualInvestor("P.S. Sengupta");
		Investor kd = new IndividualInvestor("K.N. Dutta");
		Investor sb = new IndividualInvestor("S. Bera");
		Investor sd = new IndividualInvestor("S. Dasgupta");

		try{
			ibm.attach(ps);
			ibm.attach(sb);
			oracle.attach(kd);
			oracle.attach(sd);
			for (int i=0; i<5; i++){
				Thread.sleep(1000);
				ibm.setPrice(ibm.getPrice() + (int)(Math.random()*10));
				oracle.setPrice(oracle.getPrice() + (int)(Math.random()*10));
				System.out.println();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}


// Subject
class Stock implements Cloneable{
	
	String symbol;
	double price;
	ArrayList investors = new ArrayList();
	
	public String getSymbol(){
		return symbol;
	}
	public double getPrice(){
		return price;
	}
	public void setPrice(double p){
		price = p;
		notifyInvestors();
	}

	public void attach(Investor inv){
		investors.add(inv);
		inv.add(this);
	}
	public void detach(Investor inv){
		investors.remove(inv);
		inv.remove(this);
	}

	public void notifyInvestors(){
		for (int i=0; i<investors.size() ; i++)
		{
			Investor inv = (Investor)investors.get(i);
			inv.update(this);
		}
	}
	public Stock cloneStock(){
		try{
			return (Stock)this.clone();
		}
		catch(CloneNotSupportedException cns){
			System.out.println(cns.getMessage());
		}
		return null;
	}
};


// Concrete subject
class ITStock extends Stock {

	ITStock(String sym, double prc){
		symbol = sym;
		price = prc;
	}
	
};

// Observer
abstract class Investor { 
	String name;
	ArrayList portfolio = new ArrayList();

	public void add(Stock stock){
		portfolio.add(stock.cloneStock());
	}

	public void remove(Stock stock){
		Stock old = findStock(stock.getSymbol());
		if(old != null){
			portfolio.remove(old);
		}
	}

	public Stock findStock(String sym){
		for (int i=0; i<portfolio.size(); i++)
		{
			if(((Stock)portfolio.get(i)).getSymbol().equals(sym)){
				return (Stock)portfolio.get(i);
			}
		}
		return null;
	}

	public abstract void update(Stock stock);
};

// Concrete observer
class IndividualInvestor extends Investor { 
	
	IndividualInvestor(String nm){
		name = nm;
	}
	
	public void update(Stock stock){
		Stock old = findStock(stock.getSymbol());
		if(old != null){
			remove(old);
			add(stock);
			System.out.print(stock.getSymbol()+" changed from $"+old.getPrice()+" to $"+stock.getPrice()+" -> ");
			System.out.println(name+" is notified.");
		}
	}

};
