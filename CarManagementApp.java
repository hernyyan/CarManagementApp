import java.util.*;
import java.text.DecimalFormat;

public class CarManagementApp {

	public static void main(String[] args) {
		//set scanner
		Scanner sc = new Scanner(System.in);

		//create array of cars
		Car[] carList = new Car[10];

		//variable for user choice
		int c = 0;

		
		while (c != 9) {

			//call menu method
			c = menu();

			//add car
			if (c == 1) {
				addCar(carList);
			//edit car
			} else if (c == 2) {
				editCar(carList);
			//set specific car to fixed
			} else if (c == 3) {
				setFixed(carList);
			//display all cars
			} else if (c == 4) {
				displayCars(carList);
			//delete a car using id
			} else if (c == 5) {
				deleteCar(carList);
			//display cars with service cost > certain price 
			} else if (c == 6) {
				expensiveFixes(carList);
			//set specific car to not fixed 
			} else if (c == 7){
				setNotFixed(carList);
			//display profit from fixing the cars
			} else if (c == 8) {
				displayProfit(carList);
			//end program
			} else if (c == 9){
				System.out.println("Goodbye");
				break;
			//invalid input
			} else {
				System.out.println("Invalid choice");
			}
		}
		sc.close();
	}

	//method for the menu
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

	//method to add car 
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
		//when system is not full
		if (!full) {
			//name of car
			System.out.println("Enter new car name: ");
			newname = sc.nextLine();
			//car id
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
			//fixed status 
			System.out.println("Enter 1 if car is fixed, 0 if not fixed");
			if (sc.nextInt() == 1) {
				newstatus = true;
				sc.nextLine();
			}
			//service fee
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
		//when system is full
		} else {
			System.out.println("System is full, delete a car first");
		}
	}

	//method to edit car
	public static void editCar(Car[] carList) {
		
		Scanner sc = new Scanner(System.in);
		
		Car editcar = null;
		String editname = "";
		int editcarid = 0, editparam = 0, editid;
		double editfee = 0;
		boolean found = false;
		boolean repeat = false;

		//getting car ID -- repeat if id is not found
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

		//if id exists
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
		//editing car name
		if (editparam == 1) {
			System.out.println("Enter the new car name: ");
			editname = sc.nextLine();
			editcar.setName(editname);
		//editing car id
		} else if (editparam == 2) {
			do {
							
				System.out.println("Enter new car ID: ");
				editid = sc.nextInt();
				sc.nextLine();
							
				repeat = false;
							
				for (int k = 0; k < carList.length; k++) {
					if (carList[k] != null && editid == carList[k].getId()) {
						repeat = true;
						System.out.println("This ID already exists");
						break;
					}
				}
			} while(repeat);
			
			editcar.setId(editid);
		//editing service fee
		} else if (editparam == 3) {
			editfee = sc.nextDouble();
			sc.nextLine();
			editcar.setFee(editfee);
		}
		//print updated version
		System.out.println("Updated car details:");
		editcar.printme();
	}

	//method to set specific car to fixed
	public static void setFixed(Car[] carList) {
		  // initialize scanner
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

	//method to set specific car to not fixed
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

	//method to display all cars in array 
	public static void displayCars (Car [] myInventory) {
	    for (int i = 0; i < myInventory.length; i++) {
	        Car currentCar = myInventory[i];
	        if (currentCar != null) {
		//use printme in the car class
	            currentCar.printme();
	        }
	    }
	}

	//method to delete a car
	public static void deleteCar (Car [] carList) {
	    int input;
	    boolean deleteStatus = false;
	    Scanner sc = new Scanner (System.in);

	//enter id
	    System.out.println("Enter the ID of the car you wish to delete: ");
	    input = sc.nextInt();
		//if car exists in the system change it to null
	    for (int i = 0; i < carList.length; i++) {
	        Car currentCar = carList[i];
	        if (currentCar != null && currentCar.getId() == input) {
	            carList[i] = null;
	            System.out.println("Car deleted.");
	            deleteStatus = true;
	            break;
	        }
	    }
		//if car does not exist in the system
	    if (deleteStatus == false) {
	        System.out.println("A car with the entered ID does not exist in the system.");
	    }
	}

	//method to display cars with service fees that exceed specified amount
	public static void expensiveFixes(Car[] carList) {

	    Scanner sc = new Scanner(System.in);

	//get specified price
	    System.out.println("Enter specified price: ");
	    double price = sc.nextDouble();
	    //print result if price exceeds the specified price
	    for (int i = 0; i < carList.length; i++) {
	    	if (carList[i] != null && carList[i].getFee() > price) {
	    		carList[i].printme();
	    	}
	    }
	}

	//method to display profit from fixing cars
	public static void displayProfit(Car[] carList) {

		//set decimal format because it is USD 
		DecimalFormat twodp = new DecimalFormat("#.##");
		
		double total = 0;

		// if status of car is not fixed, add the service fee to total
		for (int i = 0; i < carList.length; i ++) {
			if (carList[i]!= null && !carList[i].isStatus()) {
				total += carList[i].getFee();
			}
		}

		// print total profit that would be gained after fixing all cars that have not been fixed yet 
		System.out.println("Your expected profit from fixing all the cars is $" + twodp.format(total));
	}
}
