/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mac
 */
public class DBContext {
    Connection conn;
    PreparedStatement statement;
    ResultSet resultSet;
    
    

    /*USE BELOW METHOD FOR YOUR DATABASE CONNECTION FOR BOTH SINGLE AND MULTILPE SQL SERVER INSTANCE(s)*/
 /*DO NOT EDIT THE BELOW METHOD, YOU MUST USE ONLY THIS ONE FOR YOUR DATABASE CONNECTION*/
    public DBContext() {
        try {
            String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, userID, password);
        } catch (Exception e) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    /*Insert your other code right after this comment*/
 /*Change/update information of your database connection, DO NOT change name of instance variables in this class*/
    private final String serverName = "DESKTOP-MQMEV2R";
    private final String dbName = "ONLINE_QUIZ";
    private final String portNumber = "1433";
    private final String userID = "sa1";
    private final String password = "123123";

    public static void main(String[] args) {
        DBContext db = new DBContext();
        try {
            String sql = "SELECT * FROM ACCOUNT";
            db.statement = db.conn.prepareStatement(sql);
            db.resultSet = db.statement.executeQuery();
            if(db.resultSet.next()) {
                System.out.println("asd");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
