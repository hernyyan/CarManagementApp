import java.util.*;
import java.text.DecimalFormat;

public class CarManagementApp {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		Car[] carList = new Car[10];
		int c = 0;
		
		while (c != 9) {
			
			c = menu();
			
			if (c == 1) {
				addCar(carList);
			} else if (c == 2) {
				editCar(carList);
			} else if (c == 3) {
				setFixed(carList);
			} else if (c == 4) {
				displayCars(carList);
			} else if (c == 5) {
				deleteCar(carList);
			} else if (c == 6) {
				expensiveFixes(carList);
			} else if (c == 7){
				setNotFixed(carList);
			} else if (c == 8) {
				displayProfit(carList);
			} else if (c == 9){
				System.out.println("Goodbye");
				break;
			} else {
				System.out.println("Invalid choice");
			}
		}
		sc.close();
	}

	public static int menu() {

		Scanner sc = new Scanner(System.in);
		
		System.out.println("----------------------------------------");
		System.out.println("Menu:");
		System.out.println("1. Add a car");
		System.out.println("2. Edit a car");
		System.out.println("3. Set a specific car to fixed");
		System.out.println("4. Display all cars in inventory");
		System.out.println("5. Delete a car by ID");
		System.out.println("6. Display all cars with service costs exceeding a specified price");
		System.out.println("7. Set a specific car to not fixed");
		System.out.println("8. Display expected profit from fixing all cars");
		System.out.println("9. Exit");
		System.out.println("----------------------------------------");
		
		
		System.out.println("Select an option: ");
		int choice = sc.nextInt();
		sc.nextLine();
		return choice;	
	}
	
	public static void addCar(Car[] carList) {
		
		Scanner sc = new Scanner(System.in);
		
		boolean full = true, validid = false, repeat = false, newstatus = false;
		String newname = "";
		int newid = 0;
		double newfee = 0;
		
		for (int i = 0; i < carList.length; i++) {
			if (carList[i] == null) {
				full = false;
			}
		}
		
		if (!full) {
			System.out.println("Enter new car name: ");
			newname = sc.nextLine();
			
			do {
				
				System.out.println("Enter new car ID: ");
				newid = sc.nextInt();
				sc.nextLine();
				
				repeat = false;
				
				for (int k = 0; k < carList.length; k++) {
					if (carList[k] != null && newid == carList[k].getId()) {
						repeat = true;
						System.out.println("This ID already exists");
						break;
					}
				}
			} while(repeat);
			
			System.out.println("Enter 1 if car is fixed, 0 if not fixed");
			if (sc.nextInt() == 1) {
				newstatus = true;
				sc.nextLine();
			}
			
			System.out.println("Enter new car service fee: ");
			newfee = sc.nextDouble();
			sc.nextLine();
			
			for (int j = 0; j < carList.length; j++) {
				if (carList[j] == null) {
					carList[j] = new Car(newname, newid, newstatus, newfee);
					System.out.println("Car added:");
					carList[j].printme();
					break;
				}
			}
	
		} else {
			System.out.println("System is full, delete a car first");
		}
	}
	
	public static void editCar(Car[] carList) {
		
		Scanner sc = new Scanner(System.in);
		
		Car editcar = null;
		String editname = "";
		int editcarid = 0, editparam = 0, editid;
		double editfee = 0;
		boolean found = false;
		
		while (!found) {
			System.out.println("Enter car ID: ");
			editcarid = sc.nextInt();
			
			for (int i = 0; i < carList.length; i++) {
				if (carList[i] != null && editcarid == carList[i].getId()) {
					editcar = carList[i];
					found = true;
				}
			}
			
			if (!found) {
				System.out.println("Not found, enter again");
			}
		}
		
		if (found) {
			System.out.println("The selected car:");
			editcar.printme();
			System.out.println("Select parameter to edit: ");
			System.out.println("1. Car name");
			System.out.println("2. Car ID");
			System.out.println("3. Car service fee");
			System.out.println("Enter selection: ");
			editparam = sc.nextInt();
			sc.nextLine();
		}
		
		if (editparam == 1) {
			System.out.println("Enter the new car name: ");
			editname = sc.nextLine();
			editcar.setName(editname);
		} else if (editparam == 2) {
			System.out.println("Enter the new car ID: ");
			editid = sc.nextInt();
			sc.nextLine();
			editcar.setId(editid);
		} else if (editparam == 3) {
			editfee = sc.nextDouble();
			sc.nextLine();
			editcar.setFee(editfee);
		}
		
		System.out.println("Updated car details:");
		editcar.printme();
	}

	public static void setFixed(Car[] carList) {
		  // initiliaze scanner
		  Scanner sc = new Scanner(System.in);
		  // initialize found and index variable
		  boolean found = false; 
		  int index = -1; 

		  // run loop while index has not been updated
		  while (index == -1){
			  // have user input and store car ID to search for car
			  System.out.println("Enter car ID: ");
			  int id = sc.nextInt();
			  sc.nextLine();

			  // run through carlist and search for id 
			  for (int i = 0; i < carList.length; i++) {
				  if (carList[i] != null && carList[i].getId() == id) {
					  // when id is found, update found and save index
					  found = true; 
					  index = i; 
				  }
			  }
			  // if index does not change, have user input another id 
			  if (index == -1) {
				  System.out.print("Car not found, try again");
			  } 		  
		  }
		  // if id was found 
		  if (found) {
			  // call car object
			  Car setCar = carList[index];
			  // find current status 
			  boolean currStatus = setCar.isStatus();
			  // if not fixed (false), set status to true (fixed)
			  if (currStatus == false) {
				  System.out.println("Car status has been set to fixed.");
				  setCar.setStatus(true);
			  // if status was already fixed 
			  } else {
				  System.out.println("Car status was already fixed, no changes made.");
			  }
		  }
	}

	public static void setNotFixed(Car[] carList) {
		  // same logic as setFixed method
		  Scanner sc = new Scanner(System.in);
		  boolean found = false; 
		  int index = -1; 
		  
		  while (index == -1){
			  System.out.println("Enter car ID: ");
			  int id = sc.nextInt();
			  sc.nextLine();
			  
			  for (int i = 0; i < carList.length; i++) {
				  if (carList[i] != null && carList[i].getId() == id) {
					  found = true; 
					  index = i; 
				  }
			  }
			  if (index == -1) {
				  System.out.print("Car not found, try again");
			  } 		  
		  }
		  
		  if (found) {
			  Car setCar = carList[index];
			  boolean currStatus = setCar.isStatus();
			  // if status was fixed, change to not fix
			  if (currStatus == true) {
				  System.out.println("Car status has been set to not fixed.");
				  setCar.setStatus(false);
			  // if already not fixed, make no changes and exit 
			  } else {
				  System.out.println("Car status was already not fixed, no changes made.");
			  }
		  }
	}

	public static void displayCars (Car [] myInventory) {
	    for (int i = 0; i < myInventory.length; i++) {
	        Car currentCar = myInventory[i];
	        if (currentCar != null) {
	            currentCar.printme();
	        }
	    }
	}
	
	public static void deleteCar (Car [] carList) {
	    int input;
	    boolean deleteStatus = false;
	    Scanner sc = new Scanner (System.in);
	    
	    System.out.println("Enter the ID of the car you wish to delete: ");
	    input = sc.nextInt();
	    
	    for (int i = 0; i < carList.length; i++) {
	        Car currentCar = carList[i];
	        if (currentCar != null && currentCar.getId() == input) {
	            carList[i] = null;
	            System.out.println("Car deleted.");
	            deleteStatus = true;
	            break;
	        }
	    }
	    if (deleteStatus == false) {
	        System.out.println("A car with the entered ID does not exist in the system.");
	    }
	}
	
	public static void expensiveFixes(Car[] carList) {

	    Scanner sc = new Scanner(System.in);
	    
	    System.out.println("Enter specified price: ");
	    double price = sc.nextDouble();
	    
	    for (int i = 0; i < carList.length; i++) {
	    	if (carList[i] != null && carList[i].getFee() > price) {
	    		carList[i].printme();
	    	}
	    }
	}
 
	public static void displayProfit(Car[] carList) {
	
		DecimalFormat twodp = new DecimalFormat("#.##");
		
		double total = 0;

		// if status of car is not fixed, add the service fee to totoal
		for (int i = 0; i < carList.length; i ++) {
			if (carList[i]!= null && !carList[i].isStatus()) {
				total += carList[i].getFee();
			}
		}

		// print total profit that would be gained after fixing all cars that have not been fixed yet 
		System.out.println("Your expected profit from fixing all the cars is $" + twodp.format(total));
	}
}
