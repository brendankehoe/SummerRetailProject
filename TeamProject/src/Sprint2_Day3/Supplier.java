package Sprint2_Day3;

public class Supplier {

	private int id;
	private String name;

	public Supplier(int id, String name){
	this.id=id;
	this.name=name;
	
	}

	public int getID() {
		return id;
	}

	public void setSupplierID(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
