/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.util.ArrayList;
import model.Result;

/**
 *
 * @author Do Duc Duong
 */
public class ResultDAO extends DBContext{
    //get question id and answer id from table result in database
    public ArrayList<Result> getResult() {
        ArrayList<Result> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM RESULT";
            statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Result r = new Result();
                r.setAid(resultSet.getInt("AID"));
                r.setQid(resultSet.getInt("QID"));
                list.add(r);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    //insert a record to table result in database
    public void insertResult(String qid, String aid) {
        try {
            String sql = "INSERT INTO RESULT VALUES (?, ?)";
            statement = conn.prepareStatement(sql);
            statement.setString(1, qid);
            statement.setString(2, aid);
            statement.execute();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
