package ProiectFinalP3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * 
 *  @author Stan Mihnea
 *  
 *  functie de conectare de la baza de date apelata in fiecare pagina
 */
public class Conn {

	 private static Connection con;

	    public static Connection getConnection() {
	        try {

	            if (con == null) {
	                Class.forName("com.mysql.cj.jdbc.Driver");
	                con = DriverManager.
	                        getConnection("jdbc:mysql://localhost:3306/bazap3", "root", "");    
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return con;
	    }
	}

