import java.util.ArrayList;
import java.util.Scanner;

public class Project3 {

	public static void main(String[] args) {
		String option, name = "", id = "", rank = "", dept = "";
		
		ArrayList<Person> people = new ArrayList<Person>(100);
		Scanner scnr = new Scanner(System.in);

		do {
			option = menu();

			switch (option) {

			case "1": {
				Faculty staff = new Faculty();
				//people.add(staff);

				System.out.println("\nEnter faculty's info: ");
				System.out.println("\tName of the faculty: ");
				//scnr.nextLine();

				name = scnr.nextLine();
				staff.setname(name);

				try{
				    System.out.println("\tID: ");
				    id = scnr.nextLine();
				    
				    for(int i = 0; i < 2; i++){
				        if(id.charAt(i) < 97 || id.charAt(i) > 127)
				            throw new Exception("Sorry Invalid id format");
				    }
				    for(int i = 2; i < 6; i++){
				        if(id.charAt(i) < 48 && id.charAt(i) > 57)
				            throw new Exception("Sorry Invalid id format");
				    }
				}

				catch(Exception excpt){
				    System.out.println(excpt.getMessage());
				    System.out.println("It has to be LetterLetterDigitDigitDigitDigit");
				    break;
				}
				
				staff.setID(id);
				
				System.out.println("\n\tRank: ");
				rank = scnr.next();

				for (int i = 0; i < 99; i++) {
					if (!rank.toLowerCase().equals("professor") && !rank.toLowerCase().equals("adjunct")) {
						System.out.println("\t  Sorry entered rank  ( " + rank + " )  is invalid");
						System.out.println("\n\tRank: ");
						rank = scnr.next();
					} else {
						staff.setRank(rank);
						break;
					}
				}

				System.out.println("\n\tDepartment: ");
				dept = scnr.next();

				for (int i = 0; i < 99; i++) {
					if (!dept.toLowerCase().equals("engineering") && !dept.toLowerCase().equals("mathematics")
							&& !dept.toLowerCase().equals("physics")) {
						System.out.println("\t  Sorry entered Department  ( " + dept + " )  is invalid");
						System.out.println("\n\tDepartment: ");
						dept = scnr.next();
					} else {
						staff.setDepartment(dept);
						break;
					}
				}
				System.out.println("\n\nThanks!");

				people.add(staff);
				break;
			}
			case "2": {
				Student stud = new Student();

				System.out.println("\nEnter the student's info: ");
				System.out.println("\n\tName of Student: ");
				name = scnr.nextLine();

				stud.setname(scnr.nextLine());

				System.out.println("\n\tID: ");
				stud.setID(scnr.next());

				System.out.println("\n\tGpa: ");
				stud.setGpa(scnr.nextFloat());

				System.out.println("\n\tCredit hours: ");
				stud.setCredits(scnr.nextInt());

				System.out.println("\n\nThanks!");

				people.add(stud);
				break;
			}
			case "3": {
				int p = 0;
				System.out.println("\n\tEnter the student's id: ");
				id = scnr.next();
				for (int i = 0; i < people.size(); i++) {
					if (people.get(i).getID().equals(id)) {
						people.get(i).printInvoice();
						p++;
					}
				}
				if (p == 0) {
					System.out.println("Sorry-student not found!");
				}
				break;
			}

			case "4": {
				int k = 0;
				System.out.println("\n\tEnter the faculty's id: ");
				id = scnr.next();
				for (int i = 0; i < people.size(); i++) {
					if (people.get(i).getID().equals(id.toLowerCase())) {
						people.get(i).printInvoice();
						k++;
						break;
					}
				}
				if (k == 0) {
					System.out.println("Sorry " + id + " doesn't exist");
				}
				break;
			}

			case "5": {
				System.out.println("\n Goodbye!");
				break;
			}

			default: {
				System.out.println("Invalid entry- please try again");
				break;
			}
			}

		} while (!option.equals("5"));
		//scnr.close();
	}

	private static String menu() {
		String option = "";

		System.out.println("\n\nChoose one of the options: ");
		System.out.println("1-Add a new Faculty member");
		System.out.println("2-Add a new Student");
		System.out.println("3-Print tuition invoice for a student");
		System.out.println("4-Print information of a faculty");
		System.out.println("5-Exit Program");

		System.out.print("Enter your selection: ");

		Scanner scnr = new Scanner(System.in);

		option = scnr.next();
		//scnr.close();
		return option;

	}
}

class Student extends Person {
	protected float Gpa;
	protected int credits;

	public Student(float gpa, int credits) {
		this.Gpa = gpa;
		this.credits = credits;
	}

	public Student() {
		Gpa = 0;
		credits = 0;
	}

	public float getGpa() {
		return Gpa;
	}

	public void setGpa(float gpa) {
		this.Gpa = gpa;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	@Override
	public void printInvoice() {
		System.out.println("\nHere is the tuition invoice for " + name + " :");
		System.out.println("\n-----------------------------------------------------------");
		System.out.println(name + "\t\t" + ID);
		System.out.println("Credit hours:" + credits + "  (236.45/credit hour)");
		System.out.println("Fees: $52\n\n");
		if (Gpa > 3.84) {
			double j = (((236.45 * credits) + 52) * .75);
			System.out.printf("Total payment: %.2f", j);
			double k = (((236.45 * credits) + 52) - j);
			System.out.printf("\t\t($%.2f discount applied)\n", k);
		} else {
			double j = ((236.45 * credits) + 52);
			System.out.printf("Total payment: %.2f", j);
			System.out.println("\t\t($0 discount applied)");
		}
		System.out.println("\n-----------------------------------------------------------");
	}
}

class Faculty extends Person {
	protected String department;
	protected String rank;

	public Faculty(String department, String rank) {
		this.department = department;
		this.rank = rank;
	}

	public Faculty() {
		department = "";
		rank = "";
	}

	public String toString() {
		return "Faculty [name=" + name + ", ID=" + ID + ", department=" + department + ", rank=" + rank + "]";
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	@Override
	public void printInvoice() {

		rank = rank.toLowerCase();
		department = department.toLowerCase();

		rank = rank.substring(0, 1).toUpperCase() + rank.substring(1);
		department = department.substring(0, 1).toUpperCase() + department.substring(1);
		System.out.println("Faculty found: ");
		System.out.println("---------------------------------------------------------------------------------------\n"
				+ name + "\n\n" + department + " Department, " + rank);
		System.out.println("---------------------------------------------------------------------------------------\n");

	}

}
	
	abstract class Person {
		protected String name;
		protected String ID;

		abstract void printInvoice();

		public String getname() {
				return name;
			}

		public void setname(String name) {
				this.name = name;
			}

		public String getID() {
				return ID;
			}

		public void setID(String ID) {
				this.ID = ID;
			}
			
		}	