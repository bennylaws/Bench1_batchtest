package bench1;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class DBI42Bench1 {
	
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int n;
		double start, stop, diff;
		
		System.out.print("Jetzt n eingeben: ");
		n = scanner.nextInt();
		scanner.close();
		
		Connection conni = null;
		Statement stmt = null;
		PreparedStatement prepStmt = null;
		
/*		System.out.println("Versuche Treiber zu laden");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			System.err.println("etwas ist schief gelaufen :(");
			System.err.println(e);
			System.exit(-1);
		}

		System.out.println("Treiber geladen");
*/		
		//Verbindung herstellen
		try {
//			conni = DriverManager.getConnection(
//					"jdbc:mysql://192.168.122.46", "dbi", "dbi_pass");
		    conni = DriverManager.getConnection(
				"jdbc:mysql://localhost", "dbi", "dbi_pass");
//			prepStmt = conni.prepareStatement();
		} 
		
		catch (SQLException e) {
			String err = e.getMessage();
			System.out.println("etwas ist schief gelaufen :(");
			System.out.println(err);
			System.exit(-1);
		}

		System.out.println("Sehr verbunden");

		try {
			stmt = conni.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Init.init(stmt);
		
		start = System.nanoTime();
		
		try{
			Fill.fill(conni, prepStmt, n);
		}
		catch(Exception e){
			System.out.println("..naja");
			System.err.println(e);
			
		}
		stop = System.nanoTime();
		diff = stop-start;
		System.out.printf("Fertig, es hat %.2fs gedauert", diff/1000000000);
				
		//Verbindung beenden, Programm verlassen
		try{
			conni.close();
		}
		catch(Exception e){
			System.out.println(e);
			
		}
	}
}