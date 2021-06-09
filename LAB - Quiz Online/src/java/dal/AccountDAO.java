/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.util.ArrayList;
import model.Account;
import model.Group;

/**
 *
 * @author Do Duc Duong
 */
public class AccountDAO extends DBContext {

    //check username and password is true or false from database
    public boolean isEistAccount(String user, String pass) {
        try {
            //query select account from database
            String sql = "SELECT * FROM ACCOUNT WHERE USERNAME = ? AND PASSWORD = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, user);
            statement.setString(2, pass);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    //get all account
    public ArrayList<Account> getAllAccount() {
        ArrayList<Account> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ACCOUNT";
            statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Account a = new Account();
                a.setUser(resultSet.getString("username"));
                a.setPass(resultSet.getString("password"));
                a.setEmail(resultSet.getString("email"));
                Group g = new Group();
                g.setId(resultSet.getInt("gid"));
                a.setGroup(g);
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    //insert a record to table account in database
    public void insertAccount(String user, String pass, int gid, String email) {
        try {
            String sql = "INSERT INTO ACCOUNT VALUES (?, ?, ?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setString(1, user);
            statement.setString(2, pass);
            statement.setInt(3, gid);
            statement.setString(4, email);
            statement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //return 1 Account get all value from 1 user by username and password. 
    public Account getAccount(String user, String pass) {
        try {
            String sql = "SELECT * FROM ACCOUNT WHERE USERNAME = ? AND PASSWORD = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, user);
            statement.setString(2, pass);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Account a = new Account();
                a.setUser(resultSet.getString("username"));
                a.setPass(resultSet.getString("password"));
                a.setEmail(resultSet.getString("email"));
                Group g = new Group();
                g.setId(resultSet.getInt("gid"));
                a.setGroup(g);
                return a;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    //return Account can or cannot access a site(url)
    //for example Student cannot access /manage-quiz
    public boolean isAccessSite(Account account, String url) {
        try {
            String sql = "select USERNAME, PASSWORD, EMAIL, [GROUP].NAME AS [GROUP], URL from account \n"
                    + "inner join [GROUP]\n"
                    + "on ACCOUNT.GID = [GROUP].ID\n"
                    + "INNER JOIN GROUP_FEATURES\n"
                    + "ON [GROUP].ID = GROUP_FEATURES.GID\n"
                    + "INNER JOIN FEATURES\n"
                    + "ON GROUP_FEATURES.FID = FEATURES.ID\n"
                    + "WHERE USERNAME = ? \n"
                    + "AND PASSWORD = ?\n"
                    + "and URL = ?";
            statement = conn.prepareStatement(sql);
            statement.setString(1, account.getUser());
            statement.setString(2, account.getPass());
            statement.setString(3, url);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

}
