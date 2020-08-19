package smart;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JSlider;
import com.fazecast.jSerialComm.*;
 
public class Client69 {
 
        public static void main(String[] args) {
                // create a window with a slider
                JFrame window = new JFrame();
                JSlider slider = new JSlider();
                slider.setMaximum(1023);
                window.add(slider);
                window.pack();
                window.setVisible(true);
               
                // determine which serial port to use
                SerialPort ports[] = SerialPort.getCommPorts();
                System.out.println("Select a port:");
                int i = 1;
                for(SerialPort port : ports) {
                        System.out.println(i++ + ". " + port.getSystemPortName());
                }
                Scanner s = new Scanner(System.in);
                int chosenPort = s.nextInt();

                // open and configure the port
                SerialPort port = ports[chosenPort - 1];
                if(port.openPort()) {
                        System.out.println("Successfully opened the port.");
                } else {
                        System.out.println("Unable to open the port.");
                        return;
                }
                port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
               
                // enter into an infinite loop that reads from the port and updates the GUI
                Scanner data = new Scanner(port.getInputStream());
                int c=0;
                while(data.hasNextLine()) {
                        int number = 0;
                       // System.out.println(data.nextLine());
                        float f = Float.parseFloat(data.nextLine());
                        sss(f,c);
                        try{number = Integer.parseInt(data.nextLine());
                        
                        }catch(Exception e){}
                        slider.setValue(number);
                        c++;
                }
        }
        public static void sss(float d, int k) {
            String url = "jdbc:mysql://localhost/client";
            
        	String user= "root";
        	String password="";
        	
        	try
            { 

                Connection conn = DriverManager.getConnection(url,user,password); 
                Scanner first = new Scanner(System.in);
                //String query="INSERT INTO 'numbers'(Number,x) VALUE("+a+","+b+")";
                //PreparedStatement stmt = conn.prepareStatement(query);
                //String SQL;
    			//stmt.executeUpdate(query);
                Statement s1=conn.createStatement();
                s1.executeUpdate("INSERT INTO input(Samples) VALUES ("+d+")");

                  conn.close(); 

            } catch (Exception e) { 
                System.err.println("Got an exception! "); 
                System.err.println(e.getMessage()); 
            }
        	if(k%2==0)
        		xyz();
            }
        public static void xyz()
        { 
            String sentence = null;
            String modifiedSentence = null;
            BufferedReader inFromUser =new BufferedReader(new InputStreamReader(System.in));
            Socket clientSocket = null;
    		try {
    			clientSocket = new Socket("192.168.43.76",4000);
    		} catch (UnknownHostException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            DataOutputStream outToServer = null;
    		try {
    			outToServer = new DataOutputStream(clientSocket.getOutputStream());
    		} catch (IOException e2) {
    			// TODO Auto-generated catch block
    			e2.printStackTrace();
    		}
            BufferedReader inFromServer = null;
    		try {
    			inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    		} catch (IOException e2) {
    			// TODO Auto-generated catch block
    			e2.printStackTrace();
    		}
           //sentence =inFromUser.readLine();
    		sentence=rrr(); 
            try {
    			outToServer.writeBytes(sentence + '\n');
    		} catch (IOException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		} 
            try {
    			modifiedSentence = inFromServer.readLine();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} 
            //System.out.println("FROM SERVER: " + modifiedSentence); 
            //Scanner input=new Scanner(System.in);
            //System.out.print("Do you want to enter again? Press '0' for 'yes' and '1' if 'No'.");
            //i=input.nextInt();

        }
        public static String rrr() {
        	String str="Client1/";
        	int z=0;
        	 final String DB_URL = "jdbc:mysql://localhost/client";

        	 //  Database credentials
        	 final String USER = "root";
        	 final String PASS = "";
        	 Connection conn = null;
        	 Statement stmt = null;
        	// String s[]=null;
        	 try{

        	    conn = DriverManager.getConnection(DB_URL, USER, PASS);

        	    stmt = conn.createStatement();

        	    String sql = "SELECT Time,Samples FROM input";
        	    ResultSet rs = stmt.executeQuery(sql);
        	    int c = 0;
        	    
        	    String p;
        	   
        	    //STEP 5: Extract data from result set
        	    while(rs.next()){
        	       //Retrieve by column name
        	       float Sample = rs.getFloat("Samples");
        	       String k1=rs.getString("Time");
        	    //   System.out.println(k1+"\n");
        	       p=Float.toString(Sample);
        	       str=str+p+"$"+k1+"/";
        	      
        	    }
        	   
        	    rs.close();
        	 }catch(SQLException se){
        	    //Handle errors for JDBC
        	    se.printStackTrace();
        	 }catch(Exception e){
        	    //Handle errors for Class.forName
        	    e.printStackTrace();
        	 }finally{
        	    //finally block used to close resources
        	    try{
        	       if(stmt!=null)
        	          conn.close();
        	    }catch(SQLException se){
        	    }// do nothing
        	    try{
        	       if(conn!=null)
        	          conn.close();
        	    }catch(SQLException se){
        	       se.printStackTrace();
        	    }//end finally try
        	 }
        	 String str10 = "";
        	 str10 = last(str);
        	 return str10;
        }
        public static String last(String str1)
        {
        	String str,op;
        	int m;
            m=str1.indexOf('/');
            str=str1.substring(m,str1.length());
            op=str1.substring(0,m);
            int first=0;
            int ab=0;;
            int next=0;
            int len;
            int doll;
            int last=str.length();
            String substr="";
            String s5="";
            String s6="",s7="";
            float num;
            int flag;
            s5 = reverse(str);
            first=s5.indexOf('/');
            next=s5.indexOf('/',1);
            doll=s5.indexOf('$');
            s7=s5.substring(1,doll);
            //System.out.println(s7);
            s7=reverse(s7);
            //System.out.println(s7);
            //System.out.println(s7);
            s6=s5.substring(doll+1,next);
            s6=reverse(s6);
            //System.out.println(s6);
            String final_str = op+'/'+s7+'*'+s6;
            return final_str;
        }
        public static String reverse(String a)
        {
            String reverse1 = "";
            
            
            for(int i = a.length() - 1; i >= 0; i--)
            {
                reverse1 = reverse1 + a.charAt(i);
            }
            return reverse1;
        }
        

}

