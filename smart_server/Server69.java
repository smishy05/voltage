package asdfgh;
import java.io.BufferedReader;

import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.io.*; 
import java.net.*; 
public class Server69
{
	public static void main(String argv[]) throws Exception 
    {
       float value;
       String str1,str,op;
       int n,t;
       op="";
       str1="";
       str="";
       int msg=0;
       float num;
       ServerSocket Socket1 = new ServerSocket(4000);
       //ServerSocket Socket2 = new ServerSocket(4001);
      while(true)
      {
          
           BufferedReader inFromClient = null;
           Socket connectionSocket1 = Socket1.accept();
           inFromClient = new BufferedReader(new InputStreamReader(connectionSocket1.getInputStream())); 
           str1 = inFromClient.readLine();
           String url="jdbc:mysql://localhost/Server";
           String user="root";
           String password=""; 
           int m;
           m=str1.indexOf('/');
           str=str1.substring(m,str1.length());
           op=str1.substring(0,m);
           n = str.indexOf('*');
           String s7 = "";
           s7 = str.substring(0,n);
           String s6;
           s6 = str.substring(n+1,str.length()); 
           num = Float.parseFloat(s6);
    	   try
           { 

               Connection conn = DriverManager.getConnection(url,user,password); 
               Scanner sc = new Scanner(System.in);
               //String query="INSERT INTO 'numbers'(Number,x) VALUE("+a+","+b+")";
               //PreparedStatement stmt = conn.prepareStatement(query);
               //String SQL;
   			//stmt.executeUpdate(query);
               Statement s=conn.createStatement();
               s.executeUpdate("INSERT INTO Output(ID,Time_Client,RMS) VALUES ('"+op+"','"+s7+"',"+num+")");

                 conn.close(); 

           } catch (Exception e) { 
               System.err.println("Got an exception! "); 
               System.err.println(e.getMessage()); 
           }
           
           
           //outToClient.writeBytes(capitalizedSentence);
           connectionSocket1.close();
           //connectionSocket2.close();
           msg=0;
         //  welcomeSocket.close();
       }
        
     }
}
