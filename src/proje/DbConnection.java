/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package proje;

import java.sql.*;

/**
 *
 * @author Vatan-OEM
 */
public class DbConnection {

    Connection con = null;
    
    public static Connection ConnectionDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:MyDb.db");
            return con;
        } catch (Exception e) {
            System.out.println("con failed"+e);
            return null;
        }
    }
    
    public static void main(String args[]) {
        
    }
}
