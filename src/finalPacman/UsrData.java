 


package finalPacman;
import java.sql.*; 

public class UsrData {
	static int MaxLevel=3;
	static int usid;
	static int level =1 ;
	static int speed;
	static String username;
    static int[] usrhighscore = new int[MaxLevel];
    static int[] highscore = new int[MaxLevel];

    
    public static int getMaxLevel() {
    	return MaxLevel;
    }

    public static int getlevel() {
    	return level;
    }

    public static int getspeed() {
    	return speed;
    }

    public static String getsername() {
    	return username;
    }

    public static int getusrhighscore(int i) {
    	return usrhighscore[i-1];
    }

    public static int highscore(int i) {
    	return highscore[i-1];
    }
    
    
    
    public static void setlevel(int i) {
        level = i;
    	
    }
    
    public static void setspeed(int i) {
        speed = i;
    	
    }


    public static void setusername(String i) {
   username =i;
    	
    }

    
    public static void setusrhighscore(int i, int j) {
    	if(j>usrhighscore[i-1])   usrhighscore[i-1] = j;
    	
    	    }

    
    public static void sethighscore(int i, int j) {
    	if(j>highscore[i-1])   highscore[i-1] = j;
 	    
 	    }  
    
    public void updateData() {
    	
    	
    	
    	
    }
    
    
    
    

	public static void update(int id) throws SQLException {
		 try{
			
			  String url = "jdbc:mysql://localhost:3306/database3";
			  String username = "hello";
			  String password2 = "hello";
			  
			  
			  Connection conn = DriverManager.getConnection(url,username,password2);
			
			 

		      String query = "SELECT * FROM ST";

		      
		      Statement st = conn.createStatement();
		      
		      ResultSet rs = st.executeQuery(query);
		      
		      while (rs.next())
		      {
		if (rs.getInt("ID")==id) {			
		 usid =  rs.getInt("ID");
		 setusername(rs.getString("USN"));	
		 setusrhighscore(1, rs.getInt("HS1"));
		 setusrhighscore(2, rs.getInt("HS2"));
		 setusrhighscore(3, rs.getInt("HS3"));
		 
		}  }
		      st.close();
		
			 } catch(Exception e){System.out.println(e);
			 }
		 
			 }
			 
			 

			public static void updateTB(int id) throws SQLException {
				 try{
					
					  String url = "jdbc:mysql://localhost:3306/database3";
					  String username = "hello";
					  String password2 = "hello";
					  
					  
					  Connection conn = DriverManager.getConnection(url,username,password2);
					
					 
					  int i = 0;
					 
						  String query =  "update ST set HS1  = ?, HS2 = ?, HS3 = ? where ID = ?";
				     
					  PreparedStatement preparedStmt = conn.prepareStatement(query);
                     
                   
                      
				      
				      
				      preparedStmt.setInt(1, getusrhighscore(1) );
				      preparedStmt.setInt(2, getusrhighscore(2) );
				      preparedStmt.setInt(3, getusrhighscore(3) );
				      
				      preparedStmt.setInt(4,usid);
				      
				      preparedStmt.executeUpdate();
				      
					  
				    
				     
			     
				}
					  catch(Exception e){System.out.println(e);}
					 
				
		 
		
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
