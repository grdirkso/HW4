import java.sql.*;
import java.util.Properties;
import java.util.Scanner;
public class HW4 {
	private Connection connection;
	private Statement statement;
	
	public HW4() {
		connection = null;
		statement = null;
	}

	public static void main(String[] args) throws Exception{
		String Username="cggschwe";
		String Password="ARKANSAS2019!";
		HW4 db = new HW4();
		db.connect(Username, Password);
		menu();
	}


	static void findAandC(){
		System.out.println("Option 1");
	}
	static void addClient(){
		System.out.println("Option 2 part 1");
	}
	static void purchase() {
		System.out.println("Option 2 pt 2");
	}	
	static void policiesSold() {
		System.out.println("Option 3");
	}
	static void cancelPolicy() {
		System.out.println("Option 4");
	}
	static void addAgent() {
		System.out.println("Option 5");	
	}
	static void quit() {
		System.out.println("Option 6");
	}
	static void menu(){
		System.out.println("Select and option from the menu below");
		System.out.println("1) Find all agents and clients in a given city");
		System.out.println("2) Add a new client, then purchase an available policy from a particular client");
		System.out.println("3) List all policies sold by a particular agent");
		System.out.println("4) Cancel  a policy");
		System.out.println("5) Add a new agent");
		System.out.println("6) Quit");
		Scanner scanner = new Scanner(System.in);
		String userInput = "";
		while(userInput != "6"){
			userInput = scanner.nextLine();
			switch(userInput){
				case "1": findAandC();
					  break;
				case "2": addClient();
					  purchase();
				  	  break;
				case "3": policiesSold();
					  break;
				case "4": cancelPolicy();
					  break;
				case "5": addAgent();
				 	  break;
				case "6": quit();
				 	  break;
				default: System.out.println("That is not an accepted option, plese refere to the menu"); 
			}
		}		
	}
	public void connect(String Username, String mysqlPassword) throws Exception {
        try {
	  Class.forName("com.mysql.jdbc.Driver"); 	
	  connection = DriverManager.getConnection("jdbc:mysql://localhost/" + Username + "?user=" + Username + "&password=" + mysqlPassword);
        }
        catch (Exception e) {
            System.out.println("error");
		throw e;
        }
    }
}	
