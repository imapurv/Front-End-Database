/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package frontend;
import java.sql.*;  
/**
 *
 * @author Apurv Singh <apurv@cgossip.in>
 */
public class Connector {

    /**
     * @param args the command line arguments
     */
    Connection con;
    
    public void setConnection(){

        try{  
         Class.forName("oracle.jdbc.driver.OracleDriver");  

         con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger"); 
         Statement stmt=con.createStatement();  

        }
        catch(Exception e){ System.out.println(e);} 
    }
    String fire(String input){
       
        String output = "";
        try{ 
            Statement stmt=con.createStatement();
            ResultSet resultSet = stmt.executeQuery(input);
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (resultSet.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = resultSet.getString(i);
                    output+=columnValue+" ";
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                   }
                output+="\n";
                System.out.println("");
            }
            con.close();  
        
        }catch(Exception e){ System.out.println(e);} 
        return output;
    
    }
    
}
