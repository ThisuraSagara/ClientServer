/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cscwwebapp;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author buwaneka
 */
@WebService(serviceName = "EmployeeService")
public class EmployeeService {

    private String currentUser = "";
    private int currentUserId = 0;
    private boolean loginStatus = false;
    public List<Employee> empList = new ArrayList<Employee>();
    public Employee emp = null;
    private String sample = "";

    DBConnect db = new DBConnect();
    Connection conn = db.connect();
    Statement stat = null;

    public String getCurrentUser() {
        return currentUser;
    }
    
    public String getSample(){
    return sample;
    }

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }
    
    

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    public boolean validateLogin(String usrGiven, String passGiven) {

        try {
            stat = conn.createStatement();
            String query = "SELECT * FROM employee WHERE username = '" + usrGiven + "' AND password = '" + passGiven + "';";
            ResultSet rs = stat.executeQuery(query);
            int noOfRows = rs.last() ? rs.getRow() : 0;

            if (noOfRows > 0) {
                System.out.println("login successfull!");
                currentUser = rs.getString("empName");
                currentUserId = rs.getInt("empID");
                loginStatus = true;

                return true;
            }
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }
        return false;
    }

    public List<Employee> viewEmployees() {

        ResultSet rs = null;
        stat = null;
        try {
            stat = conn.createStatement();
            String query = "SELECT * FROM employee";
            rs = stat.executeQuery(query);
            empList.clear();
            while (rs.next()) {
                emp = new Employee(rs.getInt("empID"), rs.getString("empName"), rs.getString("position"));
                empList.add(emp);
            }

            stat.close();
            rs.close();

            return empList;

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    public void removeEmployee(int id) {
        stat = null;
        try {
            stat = conn.createStatement();
            String sql = "DELETE FROM employee WHERE empID = "+id+";";
            stat.executeUpdate(sql);
            stat.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void editEmployee(int id,String name,String position){
          stat = null;
        try {
            sample = ""+id+""+name+""+position;
            stat = conn.createStatement();
            String sql = "UPDATE employee SET empName = '"+name+"',position = '"+position+"' WHERE empID = "+id+";";
            stat.executeUpdate(sql);
            stat.close();
            
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void addEmployee(String name,String position,String username,String password){
        
        stat = null;
        try {
            stat = conn.createStatement();
            String sql = "INSERT INTO employee (empName,position,username,password) VALUES ('"+name+"','"+position+"','"+username+"','"+password+"')";
            stat.executeUpdate(sql);
            stat.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
