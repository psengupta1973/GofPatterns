/*
A Behavioral Pattern.

Provide a way to access the elements of an aggregate object 
sequentially without exposing its underlying representation. 
*/

import java.util.ArrayList;

public class IteratorTest {
    public static void main(String[] args){

		System.out.println("-------------- ITERATOR ---------------");

		Container container = new Cart();

		container.add(new Item("HarryPotter I", 100.00));
		container.add(new Item("Gupi Gyne Bagha Byne", 110.90));
		container.add(new Item("KhaaiKhaai", 12.82));
		container.add(new Item("UML User Guide", 78.87));
		container.add(new Item("Mastering EJB", 75.80));

		Iterator iter = container.getIterator();
		
		for (Item item = iter.first(); iter.hasMore(); item = iter.next())
		{
			System.out.println(item.getName()+" at "+item.getPrice());
		}
	}
}

// Item
class Item {
	String name;
	double price;
	
	Item(String nm, double prc){
		name = nm;
		price = prc;
	}
	
	public void setName(String nm){
	}
	public String getName(){
		return name;
	}
	public void setPrice(double prc){
		price = prc;
	}
	public double getPrice(){
		return price;
	}
};



// Abstract Aggregate
interface Container {
	public void add(Item i);
	public Item get(int i);
	public void remove(int i);
	public int size();
	public Iterator getIterator();
};


// Abstract Iterator
interface Iterator { 
	
	public Item first();
	public Item current();
	public Item next();
	public Item last();
	public boolean hasMore();
};


// Concrete Aggregate
class Cart implements Container {
	ArrayList list = null;

	Cart(){
		list = new ArrayList();
	}

	public void add(Item i){
		list.add(i);
	}
	public Item get(int i){
		return (Item)list.get(i);
	}
	public void remove(int i){
		list.remove(i);
	}
	public int size(){
		return list.size();
	}
	public Iterator getIterator(){
		return new ItemCollector(this);
	}
};



// Concrete Iterator
class ItemCollector implements Iterator { 
	
	Container container = null;
	int current = 0;

	ItemCollector(Container cont){
		container = cont;
	}

	public Item first(){
		return container.get(0);
	}
	public Item current(){
		return container.get(current);
	}
	public Item last(){
		return container.get(container.size()-1);
	}
	public Item next(){
		current++;
		if(hasMore()){
			return current();
		}
		return null;
	}
	public boolean hasMore(){
		if(current >= container.size()){
			return false;
		}
		return true;
	}
};


