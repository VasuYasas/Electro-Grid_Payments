package com;
import java.sql.*;
public class Payment
{
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");
 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/paf", "root", "123");
 }
 catch (Exception e)
 {
 e.printStackTrace();
 }
 return con;
 }
public String readUsers()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {
 return "Error while connecting to the database for reading.";
 }
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>UserName</th><th>Card_No</th><th>CVC</th><th>Exp_Date</th>"+ "<th>Update</th><th>Remove</th></tr>";
 String query = "select * from payments";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);
 // iterate through the rows in the result set
 while (rs.next())
 {
 String Bill_ID = rs.getString("Bill_ID");
 String UserName = Integer.toString(rs.getInt("UserName"));
 String Card_No = rs.getString("Card_No");
 String CVC = rs.getString("CVC"); 
 String Exp_Date = rs.getString("Exp_Date");
 // Add into the html table
output += "<tr><td><input Bill_ID='hidPaymentIDUpdate' UserName='hidPaymentIDUpdate' type='hidden' value='" + Bill_ID + "'>" + UserName + "</td>";
 output += "<td>" + Card_No + "</td>";
 output += "<td>" + CVC + "</td>";
 output += "<td>" + Exp_Date + "</td>";
 // buttons
output += "<td><input name='btnUpdate'type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"+ "<td><input name='btnRemove' type='button' value='Remove'class='btnRemove btn btn-danger' data-id='"+ UserName + "'>" + "</td></tr>";
 }
 con.close();
 // Complete the html table
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the payment.";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String insertUser(String Bill_ID, String UserName,String Card_No, String CVC, String Exp_Date)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {
 return "Error while connecting to the database for inserting.";
 }
 // create a prepared statement
 String query = " insert into users(`Bill_ID`,`UserName`,`Card_No`,`CVC`,`Exp_Date`)" + " values (?, ?, ?, ?, ?)";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, 0);
 preparedStmt.setString(2, UserName);
 preparedStmt.setString(3, Card_No);
 preparedStmt.setString(4, CVC);
 preparedStmt.setString(5, Exp_Date);
 // execute the statement
 preparedStmt.execute();
 con.close();
 String newPaymtns = readPayments();
 output = "{\"status\":\"success\", \"data\": \"" +newPaymtns + "\"}";
 }
 catch (Exception e)
 {
 output = "{\"status\":\"error\", \"data\": \"Error while inserting the payment.\"}";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String updateUser(String Bill_ID, String UserName,String Card_No, String CVC, String Exp_Date)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {
 return "Error while connecting to the database for updating.";
 }
 // create a prepared statement
 String query = "UPDATE users SET UserName=?,Card_No=?,CVC=?,Exp_Date=? WHERE Bill_ID=?";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setString(1, UserName);
 preparedStmt.setString(2, Card_No);
 preparedStmt.setString(3, CVC);
 preparedStmt.setString(4, Exp_Date);
 preparedStmt.setInt(5, Integer.parseInt(Bill_ID)); 
 
 // execute the statement
 preparedStmt.execute();
 con.close();
 String newPayment = readPayments();
 output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";
 }
 catch (Exception e)
 {
 output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String deletePayment(String id)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {
 return "Error while connecting to the database for deleting.";
 }
 // create a prepared statement
 String query = "delete from payments where Bill_ID=?";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, Integer.parseInt(id));
 // execute the statement
 preparedStmt.execute();
 con.close();
 String newPayments = readPayments();
 output = "{\"status\":\"success\", \"data\": \"" + newPayments + "\"}";
 }
 catch (Exception e)
 {
 output = "{\"status\":\"error\", \"data\":\"Error while deleting the payment.\"}";
 System.err.println(e.getMessage());
 }
 return output;
 }
}