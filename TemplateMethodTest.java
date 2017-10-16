/*
A Behavioral Pattern.

Define the skeleton of an algorithm in an operation, deferring some 
steps to subclasses. Template Method lets subclasses redefine certain 
steps of an algorithm without changing the algorithm's structure. 
 
*/

public class TemplateMethodTest {

	public static void main(String[] args){
		System.out.println("-------------- TEMPLATE_METHOD ---------------");

		DataAccessObject customer = new CustomerDAO();
		DataAccessObject order = new OrderDAO();

//		customer.save(DataAccessObject.INSERT);
		System.out.println("\nLoading customer..."); 
		customer.load();
		System.out.println("\nSaving customer..."); 
		customer.save(DataAccessObject.UPDATE);
		System.out.println("\nSaving customer..."); 
		customer.save(DataAccessObject.DELETE);
//		order.save(DataAccessObject.INSERT);
		System.out.println("\nLoading order..."); 
		order.load();
		System.out.println("\nSaving order..."); 
		order.save(DataAccessObject.UPDATE);
		System.out.println("\nSaving order..."); 
		order.save(DataAccessObject.DELETE);
	}
}


// Abstract Class
abstract class DataAccessObject {
	
	public static final int INSERT = 1;
	public static final int UPDATE = 2;
	public static final int DELETE = 3;

	public abstract boolean connect();
	public abstract boolean exists();
	public abstract String[] select();
	protected abstract void assignValues();
	public abstract boolean insert();
	public abstract boolean update();
	public abstract boolean delete();
	public abstract void disconnect();

	public boolean load(){
		boolean success = false;
		if(connect()){
			if(select() != null){
				assignValues();
				success = true;
			}
			disconnect();
		}
		return success;
	}

	public boolean save(int type){
		boolean success = false;
		if(connect()){
			switch(type){
				case INSERT:
					if(!exists()){
						if(insert()){
							success = true;
						}
					}
					break;
				case UPDATE:
					if(exists()){
						if(update()){
							success = true;
						}
					}
					break;
				case DELETE:
					if(exists()){
						if(delete()){
							success = true;
						}
					}
					break;
				default:
					disconnect();
			}
		}
		return success;
	}
};


// Concrete Class 1
class CustomerDAO extends DataAccessObject {

	public boolean connect(){
		System.out.println("Conneted to OrderSupply database for customer."); 
		return true;
	}
	public boolean exists(){return true;}
	public void assignValues(){}
	public String[] select(){
		System.out.println("Selected customer from database."); 
		return new String[]{"cust0021", "Partha"};
	}
	public boolean insert(){
		System.out.println("Inserted customer."); 
		return true;
	}
	public boolean update(){
		System.out.println("Updated customer."); 
		return true;
	}
	public boolean delete(){
		System.out.println("Deleted customer."); 
		return true;
	}
	public void disconnect(){
		System.out.println("Disconnected from OrderSupply database."); 
	}

};


// Concrete Class 2
class OrderDAO extends DataAccessObject {
	public boolean connect(){
		System.out.println("Conneted to OrderSupply database for order."); 
		return true;
	}
	public boolean exists(){return true;}
	public void assignValues(){}
	public String[] select(){
		System.out.println("Selected order from database."); 
		return new String[]{"ord0001", "cust0021", "1229.00"};
	}
	public boolean insert(){
		System.out.println("Inserted order."); 
		return true;
	}
	public boolean update(){
		System.out.println("Updated order."); 
		return true;
	}
	public boolean delete(){
		System.out.println("Deleted order."); 
		return true;
	}
	public void disconnect(){
		System.out.println("Disconnected from OrderSupply database."); 
	}
};


