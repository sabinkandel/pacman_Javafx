package finalPacman;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDao {
	
	
	

	
	
	
	
	
	
	
	public boolean validate(String emailId, String password) throws SQLException {
		 try{
			
			  String url = "jdbc:mysql://localhost:3306/database3";
			  String username = "hello";
			  String password2 = "hello";
			  
			  
			  Connection conn = DriverManager.getConnection(url,username,password2);
			
			 

		      String query = "SELECT * FROM LOGIN";

		      
		      Statement st = conn.createStatement();
		      
		      ResultSet rs = st.executeQuery(query);
		      
		      while (rs.next())
		      {
		if (rs.getString("USN").equalsIgnoreCase(emailId) && rs.getString("PW").equalsIgnoreCase(password)) {
			
		 UsrData.usid =  rs.getInt("ID");
			
			
			
			
			
			return true;
		}
		    
		      }
		      
		      
		      st.close();
			  
			  
			  
			  
			  
		    
			  
			  
			
			 } catch(Exception e){System.out.println(e);}
			 
			 
			
        return false;
    }

	public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
