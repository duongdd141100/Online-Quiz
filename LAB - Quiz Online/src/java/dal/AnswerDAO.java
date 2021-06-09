/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.util.ArrayList;
import model.Answer;

/**
 *
 * @author Do Duc Duong
 */
public class AnswerDAO extends DBContext{

    //get list answer by question id from table answer and retrun a list Answer
    ArrayList<Answer> getListAnswer(int id) {
        ArrayList<Answer> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ANSWER WHERE QID = ?";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Answer a = new Answer();
                a.setContent(resultSet.getString("CONTENT"));
                a.setId(resultSet.getInt("AID"));
                
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    //get all answer from table answer and return a list answer
    public ArrayList<Answer> getAllAnswer() {
        ArrayList<Answer> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ANSWER";
            statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Answer a = new Answer();
                a.setId(resultSet.getInt("AID"));
                a.setContent(resultSet.getString("CONTENT"));
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    //insert a record to table answer in database
    public void insertAnswer(int id, String answer, String qid) {
        try {
            String sql = "INSERT INTO ANSWER VALUES (?, ?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setString(2, answer);
            statement.setString(3, qid);
            statement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
