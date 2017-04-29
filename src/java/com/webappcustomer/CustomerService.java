/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webappcustomer;

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
@WebService(serviceName = "CustomerService")
public class CustomerService {
    
    DBConnect db = new DBConnect();
    Connection conn = db.connect();
    Statement stat = null;
    public List<Customers> cusList = new ArrayList<Customers>();
    public Customers cus = null;
    private String sample = "";

    /**
     * This is a sample web service operation
     */
    
    
    
    public String getSample() {
        return sample;
    }

    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    public List<Customers> viewCustomers(){
   ResultSet rs = null;
        stat = null;
        try {
            stat = conn.createStatement();
            String query = "SELECT * FROM customers";
            rs = stat.executeQuery(query);
            cusList.clear();
            while (rs.next()) {
                cus = new Customers(rs.getInt("cusID"),rs.getString("cusName"),rs.getString("cusBD"),rs.getString("cusAdd"),rs.getInt("mobile"),rs.getString("email"),rs.getString("accType"),rs.getInt("accNumber"),rs.getString("sortCode"),rs.getFloat("balance"),rs.getString("card"));
                cusList.add(cus);
            }

            stat.close();
            rs.close();

            return cusList;

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    
    }
    
    public void addCustomer(String name,String bd,String add,long mobile,String email,String acctype,long accNum,String sort,double bal,String card){
          stat = null;
        try {
            stat = conn.createStatement();
            String sql = "INSERT INTO customers (cusName,cusBD,cusAdd,mobile,email,accType,accNumber,sortCode,balance,card)"+
                    " VALUES ('"+name+"','"+bd+"','"+add+"',"+mobile+",'"+email+"','"+acctype+"',"+accNum+",'"+sort+"',"+bal+",'"+card+"');";
            sample = sql;
            stat.executeUpdate(sql);
            stat.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    public void removeCustomer(int id) {
        stat = null;
        try {
            stat = conn.createStatement();
            String sql = "DELETE FROM customers WHERE cusID = "+id+";";
            stat.executeUpdate(sql);
            stat.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
}
    
    
    
