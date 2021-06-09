/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.util.ArrayList;
import model.Question;

/**
 *
 * @author Do Duc Duong
 */
public class QuestionDAO extends DBContext {

    //return a list question with number of record is numOfQ
    public ArrayList<Question> getQuestion(int numOfQ) {
        ArrayList<Question> list = new ArrayList<>();
        AnswerDAO dbA = new AnswerDAO();
        try {
            String sql = "SELECT TOP(?)* FROM QUESTION  \n"
                    + "ORDER BY NEWID()";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, numOfQ);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Question q = new Question();
                q.setId(resultSet.getInt("QID"));
                q.setContent(resultSet.getString("CONTENT"));
                q.setListAnswer(dbA.getListAnswer(q.getId()));
                q.setDateCreated("date");
                list.add(q);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    //return all record from table question in database
    public ArrayList<Question> getAllQuestion() {
        ArrayList<Question> list = new ArrayList<>();
        AnswerDAO dbA = new AnswerDAO();
        try {
            String sql = "SELECT * FROM QUESTION";
            statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Question q = new Question();
                q.setId(resultSet.getInt("QID"));
                q.setContent(resultSet.getString("CONTENT"));
                q.setListAnswer(dbA.getListAnswer(q.getId()));
                q.setDateCreated(resultSet.getString("date"));
                list.add(q);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    //insert 1 record to table question in database
    public void insertQuestion(String qid, String question, String today) {
        try {
            String sql = "INSERT INTO QUESTION VALUES (?, ?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setString(1, qid);
            statement.setString(2, question);
            statement.setString(3, today);
            statement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //each page has pageSize question for user answer
    //by pageIndex, get corresponding question for page
    //return list has pageSize record
    public ArrayList<Question> getQuestionByPage(int pageIndex, int pageSize) {
        ArrayList<Question> list = new ArrayList<>();
        AnswerDAO dbA = new AnswerDAO();
        try {
            String sql = "SELECT * FROM\n"
                    + "(select * from QUESTION) TBL\n"
                    + "WHERE\n"
                    + "QID > (? - 1) * ?\n"
                    + "AND QID < ? * ? + 1";
            statement = conn.prepareStatement(sql);
            statement.setInt(1, pageIndex);
            statement.setInt(2, pageSize);
            statement.setInt(3, pageIndex);
            statement.setInt(4, pageSize);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Question q = new Question();
                q.setId(resultSet.getInt("QID"));
                q.setContent(resultSet.getString("CONTENT"));
                q.setListAnswer(dbA.getListAnswer(q.getId()));
                q.setDateCreated(resultSet.getString("date"));
                list.add(q);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

}
