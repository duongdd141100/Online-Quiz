/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.QuestionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Question;

/**
 *
 * @author Do Duc Duong
 */
public class ManageQuizController extends BaseAuthenticationController {

    @Override
    void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        QuestionDAO dbQ = new QuestionDAO();
        
        int pageIndex = 1;
        if(request.getParameter("page") != null) {
            pageIndex = Integer.parseInt(request.getParameter("page"));
        }
        int pageSize = 5;
        int totalQuestion = dbQ.getAllQuestion().size();
        int totalPage = (totalQuestion % pageSize == 0) ? totalQuestion / pageSize : totalQuestion / pageSize + 1;
        ArrayList<Question> list = dbQ.getQuestionByPage(pageIndex, pageSize);
        
        request.setAttribute("totalQuestion", totalQuestion);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("list", list);
        request.getRequestDispatcher("view/manage-quiz.jsp").forward(request, response);
    }

    @Override
    void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
