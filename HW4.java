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
		db.menu();
	}


	void findAandC() {
		System.out.println("Option 1: Find all agents and clients in a given city.");
		System.out.print("What city would you like to search? ");
		Scanner scan = new Scanner(System.in);
		String city = scan.nextLine();
		System.out.println("We will search for agents and clients in " + city + ".");
		try {
			statement = connection.createStatement();
			statement.executeUpdate("USE cggschwe;");
			System.out.println("Clients in " + city + ":");
			ResultSet results = statement.executeQuery("SELECT * FROM CLIENTS WHERE C_CITY='"+city+"';");
			print(results);
			System.out.println("Agents in "+ city + ":");
			results = statement.executeQuery("SELECT * FROM AGENTS WHERE A_CITY='"+city+"';");
			print(results);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		}
	void addClient(){
		System.out.println("Option 2: Add a new client, then purchase an available policy from a particular agent.");
		Scanner scan = new Scanner(System.in);
		String cName;
		String cCity;
		String cZip;
		System.out.println("Enter client’s name");
		cName = scan.nextLine();
		System.out.println("Enter client's city");
		cCity = scan.nextLine();
		System.out.println("Enter client’s zip");
		cZip = scan.nextLine();
		try {
                        
                        statement = connection.createStatement();
                        statement.executeUpdate("USE cggschwe;");
			ResultSet result = statement.executeQuery("SELECT MAX(C_ID) FROM CLIENTS;");
			result.first();
			int C_ID = result.getInt(1)+1; 
			String query = "INSERT into CLIENTS values ("+ C_ID + ",'" + cName + "','" + cCity + "'," +  cZip +  ");" ;
                        statement.executeUpdate(query);
			System.out.println("We will add "+cName+" to our database as a client.");
                }
                catch(SQLException e) {
                        e.printStackTrace();
                }

	}
	void purchase() {
		System.out.println("Option 2 pt 2");
	}	
	void policiesSold() {
		System.out.println("Option 3");
	}
	void cancelPolicy() {
		System.out.println("Option 4: cancel a policy.");
		try {
			statement = connection.createStatement();
			statement.executeUpdate("use cggschwe;");
			System.out.println("Policies sold:");
			ResultSet result = statement.executeQuery("SELECT * FROM POLICIES_SOLD;");
			print(result);
			System.out.println("Enter the PURCHASE_ID of the policy to cancel: ");
			Scanner scan = new Scanner(System.in);
			String id = scan.nextLine();
			statement.executeUpdate("DELETE FROM POLICIES_SOLD WHERE PURCHASE_ID = " + id + ";");
			result = statement.executeQuery("SELECT * FROM POLICIES_SOLD;");
			System.out.println("Updated POLICIES_SOLD table");
			print(result);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	void addAgent() {
		System.out.println("Option 5: add a new agent for a city.");
		System.out.println("What city are you adding an agent for? ");
		Scanner scan = new Scanner(System.in);
		String city = scan.nextLine();
		System.out.print("What is the agent's id (A_ID starts with 2)? ");
		String a_id = scan.next();
		System.out.print("What is the agent's name (A_NAME)? ");
		String a_name = scan.next();
		System.out.print("What is the agent's city (A_CITY)? ");
		String a_city = scan.next();
		System.out.print("What is the agent's zip (A_ZIP)? ");
		String a_zip = scan.next();
		try {
			statement = connection.createStatement();
			statement.executeUpdate("use cggschwe;");
			statement.executeUpdate("INSERT INTO AGENTS VALUES ("+a_id+",'"+a_name+"','"+a_city+"',"+a_zip+");");
			ResultSet result = statement.executeQuery("SELECT * FROM AGENTS;");
			System.out.println("Updated Agents table:");
			print(result);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
	}
	void quit() throws SQLException {
		if(statement != null && connection != null) { 
		statement.close();
		connection.close();
		System.out.println("Database successfully closed. See ya.");
		}
		else {
			System.out.println("Your connection has not been opened yet. Sorry to see you leave so soon :'(");
		}
	}
	void menu(){
		boolean quit = false;
		System.out.println("Select and option from the menu below");
		System.out.println("1) Find all agents and clients in a given city");
		System.out.println("2) Add a new client, then purchase an available policy from a particular client");
		System.out.println("3) List all policies sold by a particular agent");
		System.out.println("4) Cancel  a policy");
		System.out.println("5) Add a new agent");
		System.out.println("6) Quit");
		Scanner scanner = new Scanner(System.in);
		String userInput = "";
		while(!quit){
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
				case "6": 
					  quit = true;
				 	  try {
						  quit();
					  } catch(SQLException e) {
						  e.printStackTrace();
					  }
					  break;
				default: System.out.println("That is not an accepted option, plese refere to the menu"); 
			}
		}		
	}
	public void print(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int numColumns = metaData.getColumnCount();

        printRecords(resultSet, numColumns);
    }
    // Print the attribute names
    public void printHeader(ResultSetMetaData metaData, int numColumns) throws SQLException {
        for (int i = 1; i <= numColumns; i++) {
            if (i > 1)
                System.out.print(",  ");
            System.out.print(metaData.getColumnName(i));
        }
        System.out.println();
    }

    // Print the attribute values for all tuples in the result
    public void printRecords(ResultSet resultSet, int numColumns) throws SQLException {
        String columnValue;
        while (resultSet.next()) {
            for (int i = 1; i <= numColumns; i++) {
                if (i > 1)
                    System.out.print(",  ");
                columnValue = resultSet.getString(i);
                System.out.print(columnValue);
            }
            System.out.println("");
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
