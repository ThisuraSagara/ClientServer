/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cscwwebapp;

import java.sql.*;

/**
 *
 * @author buwaneka
 */
public class DBConnect {
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/cscw";
    
    static final String username = "username";
    static final String password = "password";
    
    public static Connection connect(){
         Connection conn = null;
        try {

    
    Class.forName("com.mysql.jdbc.Driver");

    conn = DriverManager.getConnection(DB_URL,username,password);
    
        }catch(SQLException se){
      se.printStackTrace();
      return null;
   }catch(Exception e){
      e.printStackTrace();
      return null;
   }
        
    return conn;
        
    
    }
}
