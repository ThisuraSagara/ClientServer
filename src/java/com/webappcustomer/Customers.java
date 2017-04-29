/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webappcustomer;

/**
 *
 * @author buwaneka
 */
public class Customers {
    public int id;
    public String name;
    public String birthDate;
    public String address;
    public int mobile;
    public String email;
    public String accountType;
    public int accountNumber;
    public String sortCode;
    public double balance;
    public String card;

    
    public Customers(int id,String name,String bd,String add,int mobile,String email,String acctype,int accNum,String sort,double bal,String card){
        this.id = id;
        this.name = name;
        this.address = add;
        this.birthDate = bd;
        this.mobile = mobile;
        this.balance = bal;
        this.accountType = acctype;
        this.accountNumber = accNum;
        this.email = email;
        this.sortCode = sort;
        this.card = card;
    }
    
    
    
}
