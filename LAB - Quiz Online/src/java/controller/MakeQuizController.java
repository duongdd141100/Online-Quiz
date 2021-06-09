/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import dal.AnswerDAO;
import dal.QuestionDAO;
import dal.ResultDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Answer;

/**
 *
 * @author Do Duc Duong
 */
public class MakeQuizController extends BaseAuthenticationController {

    @Override
    void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AnswerDAO dbA = new AnswerDAO();
        ArrayList<Answer> listAnswer = dbA.getAllAnswer();
        request.setAttribute("listAnswer", listAnswer);
        request.getRequestDispatcher("view/make-quiz.jsp").forward(request, response);
    }

    @Override
    void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionDAO dbQ = new QuestionDAO();
        AnswerDAO dbA = new AnswerDAO();
        ResultDAO dbR = new ResultDAO();
        //question content
        String question = request.getParameter("question");
        //question id and answer id 
        String qid = String.valueOf(dbQ.getAllQuestion().size() + 1);
        String aid = request.getParameter("answerId");
        int sizeOfAnswer = dbA.getAllAnswer().size();
        //answer option 1 -> option 4
        String a1 = request.getParameter(String.valueOf(dbA.getAllAnswer().size() + 1));
        String a2 = request.getParameter(String.valueOf(dbA.getAllAnswer().size() + 2));
        String a3 = request.getParameter(String.valueOf(dbA.getAllAnswer().size() + 3));
        String a4 = request.getParameter(String.valueOf(dbA.getAllAnswer().size() + 4));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");  
        LocalDateTime now = LocalDateTime.now();  
        String today = dtf.format(now);
        dbQ.insertQuestion(qid, question, today);
        dbA.insertAnswer(sizeOfAnswer + 1, a1, qid);
        dbA.insertAnswer(sizeOfAnswer + 2, a2, qid);
        dbA.insertAnswer(sizeOfAnswer + 3, a3, qid);
        dbA.insertAnswer(sizeOfAnswer + 4, a4, qid);
        dbR.insertResult(qid, aid);
        response.sendRedirect("make-quiz");
        
    }

}
