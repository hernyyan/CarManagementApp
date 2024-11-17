public class Car {

	private String name;
	private int id;
	private boolean status;
	private double fee;
	
	public Car() {
		name = "";
		id = 0;
		status = false;
		fee = 0;
	}
	
	public Car(String namee, int carid, boolean s, double sf) {
		this.name = namee;
		this.id = carid;
		this.status = s;
		this.fee = sf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}
	
	public void printme() {
		System.out.println("----------------------------------------");
		System.out.println("Car name: " + name);
		System.out.println("Car ID: " + id);
		if (status) {
			System.out.println("Car status: fixed");
		} else {
			System.out.println("Car status: not fixed");
		}
		System.out.println("Car service fee: " + fee);
		System.out.println("----------------------------------------");
	}
}
