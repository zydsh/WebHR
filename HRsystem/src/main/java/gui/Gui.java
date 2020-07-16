package gui;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import ui.shared.IEP;
import ui.shared.ILeave;
import io.ciera.runtime.summit.interfaces.IMessage;

@Path("/page")
public class Gui {
	int i = 0;
   
    ApplicationConnection server = null;
    ConnectionHandler connHandler = null;
   
    public void setApplicationConnection(ApplicationConnection server) {
        this.server = server;
    }

    public void sendSignal(IMessage message) {
        server.sendSignal(message);
    }

    


    @Context ServletContext servletContext;
	
	@GET
	@Path("/web")
	@Produces({MediaType.TEXT_HTML})
	public InputStream viewHome()
	{	
		 if(i == 0) {
			 connHandler = new ConnectionHandler(this);
			 connHandler.start();
			 
			 try {
			 
				 Thread.sleep(5000);
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HR", "root", "");
			 DatabaseMetaData dbm = conn.getMetaData();
             ResultSet rs = dbm.getTables(null, "APP", "EMPLOYEE", null);
             ResultSet rsL = dbm.getTables(null, "APP", "LISTOFLEAVES", null);
             if (!rs.next()) {
            	 this.createEmployeeTable();
             }else{
            	 Statement stmt = conn.createStatement();
                 rs = stmt.executeQuery("SELECT * FROM EMPLOYEE");
            	 while(rs.next()) {
            		 int id = rs.getInt("id");
                     String fname = rs.getString("first");
                     String lname = rs.getString("last");
                     String state = rs.getString("state");
                     if(state.equals("Working")) {
                    	 server.sendSignal(new IEP.New( fname, lname , id ));
                    	 server.sendSignal(new IEP.Start( id ));
                     }else {
                    	 server.sendSignal(new IEP.New( fname, lname , id ));
                     }
            	 }
                 System.out.println("Employee table already exists");
             }
             if(!rsL.next()) {
            	 this.createLeaveTable();
             }else {
            	 Statement stmt = conn.createStatement();
                 rs = stmt.executeQuery("SELECT * FROM LISTOFLEAVES");
            	 while(rs.next()) {
            		 String name = rs.getString("name");
                     int numberOfDays = rs.getInt("numberOfDays");
                     server.sendSignal(new ILeave.New_Leave( name, numberOfDays));
            	 }
            	 System.out.println("Leave table already exists");
             }
             i++;
			 }catch(Exception e) {
				i = 0;
				 System.out.print(e.getMessage());
			 }
		 }
	   return servletContext.getResourceAsStream("/WEB-INF/classes/home.html");
	}
	
	@GET
	@Path("/addEmployee")
	@Produces({MediaType.TEXT_HTML})
	public InputStream addEmployee()
	{	
	   return servletContext.getResourceAsStream("/WEB-INF/classes/addEmployee.html");
	}
	
	@GET
	@Path("/addNewLeave")
	@Produces({MediaType.TEXT_HTML})
	public InputStream addLeave()
	{	
	   return servletContext.getResourceAsStream("/WEB-INF/classes/addNewLeave.html");
	}
	
	@GET
	@Path("/addEmployeeStyle")
	@Produces({MediaType.TEXT_HTML})
	public InputStream addEmployeeStyle()
	{	
	   return servletContext.getResourceAsStream("/WEB-INF/classes/employeeStyle.css");
	}
	
	
	
	@GET
	@Path("/startEmployee")
	@Produces({MediaType.TEXT_HTML})
	public InputStream startEmployee()
	{	
	   return servletContext.getResourceAsStream("/WEB-INF/classes/startEmployee.html");
	}
	
	
	public String FName;
	public String LName;
	public int nID;
	
	
	@POST
	@Path("/employee")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public InputStream sentID(@FormParam("NID") String NID) {
		nID = Integer.parseInt(NID);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HR", "root", "");
	        String query = "UPDATE EMPLOYEE " + 
	        	"SET state = ? " +
	            "WHERE id = ?";
	        PreparedStatement preparedStmt = conn.prepareStatement(query);
	        preparedStmt.setString (1, "Working");
	        preparedStmt.setInt   (2, nID);

	       
	        preparedStmt.execute();
	        server.sendSignal(new IEP.Start( nID ));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return servletContext.getResourceAsStream("/WEB-INF/classes/home.html");	
	} 
	
	
	@POST
	@Path("/saveEmployee")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public InputStream saveEmployee(@FormParam("FName")String fName, @FormParam("LName") String lName,@FormParam("NID") String NID) {
		FName = fName;
		LName = lName;
		nID = Integer.parseInt(NID);
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HR", "root", "");
        String query = "INSERT INTO EMPLOYEE " + 
        	"(id, first, last, state)" +
            "VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setInt (1, nID);
        preparedStmt.setString (2, FName);
        preparedStmt.setString   (3, LName);
        preparedStmt.setString   (4, "Recruited");

        
        preparedStmt.execute();
        
        server.sendSignal(new IEP.New( FName, LName , nID ));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return servletContext.getResourceAsStream("/WEB-INF/classes/home.html");	
	} 
	
	
	@POST
	@Path("/saveNewLeave")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public InputStream saveLeave(@FormParam("Name")String name, @FormParam("num") String num) {
		int number = Integer.parseInt(num);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HR", "root", "");
	        String query = "INSERT INTO LISTOFLEAVES " + 
	        	"(name, numberOfDays)" +
	            "VALUES (?, ?)";
	        PreparedStatement preparedStmt = conn.prepareStatement(query);
	        preparedStmt.setString (1, name);
	        preparedStmt.setInt   (2, number);

	        
	        preparedStmt.execute();

	        server.sendSignal(new ILeave.New_Leave(name, number));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
        
		
		return servletContext.getResourceAsStream("/WEB-INF/classes/home.html");
	}
	
	@POST
	@Path("/saveRequest")
	@Produces(MediaType.TEXT_HTML)
	public InputStream saveRequest(@FormParam("start")String start,@FormParam("end")String end, @FormParam("NID")String NID ,@FormParam("leave")String leave){
		int nID = Integer.parseInt(NID);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HR", "root", "");
	        String query = "UPDATE EMPLOYEE " + 
	        	"SET state = ? " +
	            "WHERE id = ?";
	        PreparedStatement preparedStmt = conn.prepareStatement(query);
	        preparedStmt.setString (1, "On Leave");
	        preparedStmt.setInt   (2, nID);

	        
	        preparedStmt.execute();
		
	        server.sendSignal(new ILeave.Request(start, end, Integer.parseInt(NID), leave));
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		return servletContext.getResourceAsStream("/WEB-INF/classes/home.html");
	}
   
	
	
	
	
	
	public void createEmployeeTable() {
		Connection conn = null;
		Statement stmt = null;
		   try{
		      
		      Class.forName("com.mysql.jdbc.Driver");

		      
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HR", "root", "");
		      System.out.println("Connected database successfully...");
		      
		      
		      System.out.println("Creating table in given database...");
		      stmt = conn.createStatement();
		      
		      String sql = "CREATE TABLE EMPLOYEE " +
		                   "(id INTEGER not NULL, " +
		                   " first VARCHAR(255), " + 
		                   " last VARCHAR(255), " + 
		                   " state VARCHAR(255)," +
		                   " PRIMARY KEY ( id ))"; 

		      stmt.executeUpdate(sql);
		      System.out.println("Created table in given database...");
		   }catch(SQLException se){
		      se.printStackTrace();
		   }catch(Exception e){
		      e.printStackTrace();
		   }
	}
	
	
	public void createLeaveTable() {
		Connection conn = null;
		Statement stmt = null;
		   try{
		      
		      Class.forName("com.mysql.jdbc.Driver");
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HR", "root", "");
		      System.out.println("Connected database successfully...");
		      
		 
		      System.out.println("Creating table in given database...");
		      stmt = conn.createStatement();
		      
		      String sql = "CREATE TABLE LISTOFLEAVES " +
		                   " (name VARCHAR(255) not NULL," +
		                   " numberOfDays INTEGER, " + 
		                   " PRIMARY KEY ( name ))"; 

		      stmt.executeUpdate(sql);
		      System.out.println("Created table in given database...");
		   }catch(SQLException se){
		      se.printStackTrace();
		   }catch(Exception e){
		      e.printStackTrace();
		   }
	}
	
	@GET
	@Path("/requestLeave")
	@Produces(MediaType.TEXT_HTML)
	public InputStream requestPage() {
		return servletContext.getResourceAsStream("/WEB-INF/classes/requestLeave.html");
	}
	
	@GET
	@Path("/listLeave")
	@Produces(MediaType.APPLICATION_JSON)
	public Response allLeave() {
		ArrayList<String> name = new ArrayList<>();
		try {
			 Connection conn = null;
			 Statement stmt = null;
			 Class.forName("com.mysql.jdbc.Driver");
		     conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HR", "root", "");
			 stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery("SELECT * FROM LISTOFLEAVES ");
			 while(rs.next()) {
				 name.add(rs.getString("name"));
			 }
			 return Response.ok(name).build();
		}catch(Exception e) {
			System.out.print(e.getMessage());
		}
		return Response.ok().build();
	}
	
	
	
	@GET
	@Path("/allEmployees")
	@Produces(MediaType.TEXT_HTML)
	public InputStream allEmployees() {
		return servletContext.getResourceAsStream("/WEB-INF/classes/allEmployees.html");
	}
	
	
	@GET
	@Path("/listEmployees")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listEmployees() {
		ArrayList<ArrayList> employees = new ArrayList<>();
		try {
			Connection conn = null;
			 Statement stmt = null;
			 Class.forName("com.mysql.jdbc.Driver");
		     conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HR", "root", "");
			 stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery("SELECT * FROM EMPLOYEE ");
			 while(rs.next()) {
				 ArrayList<String> employee = new ArrayList<>();
				 employee.add(""+rs.getInt("id"));
				 employee.add(rs.getString("first"));
				 employee.add(rs.getString("last"));
				 employee.add(rs.getString("state"));
				 employees.add(employee);
			 }
			 return Response.ok(employees).build();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return Response.ok().build();
	}
	
	private String reply;
	
	public void setReply(String m) {
		reply = m;
	}
	
	public String getReply() {
		return reply;
	}
	
	
	
	
	
	

}
