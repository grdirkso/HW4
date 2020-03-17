import java.sql.*;
import java.util.Properties;

public class HW4 {
	private Connection connection;
	private Statement statement;
	
	public void findAandC(){}
	public void addClient(){}
	public void purchase() {}	
	public void policiesSold() {}
	public void cancelPolicy() {}
	public void addAgent() {}
	public void quit() {}
	
	public HW4() {
		connection = null;
		statement = null;
	}

	public static void main(String[] args) throws Exception{
		String Username="cggschwe";
		String Password="ARKANSAS2019!";
		HW4 db = new HW4();
		db.connect(Username, Password);
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
