/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.QuestionDAO;
import dal.ResultDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Answer;
import model.Question;
import model.Result;

/**
 *
 * @author Do Duc Duong
 */
public class TakeQuizController extends BaseAuthenticationController {

    @Override
    void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Question> listQuestion = new ArrayList<>();
        //get account from session
        QuestionDAO dbQ = new QuestionDAO();
        Account account = (Account) request.getSession().getAttribute("account");
        String name = account.getUser();
        //get number of question and set list for test to session
        int numOfQ = 0;
        if (request.getParameter("numOfQ") != null) {
            numOfQ = Integer.parseInt(request.getParameter("numOfQ"));
            listQuestion = dbQ.getQuestion(numOfQ);
            request.getSession().setAttribute("list", listQuestion);
        }
        //get test list from session
        if (request.getSession().getAttribute("list") != null) {
            listQuestion = (ArrayList<Question>) request.getSession().getAttribute("list");
        }
        //get question for paging
        int pageQ = 0;
        Question q = new Question();
        if (request.getParameter("pageQ") != null && listQuestion.size() > 0) {
            pageQ = Integer.parseInt(request.getParameter("pageQ"));
            q = listQuestion.get(pageQ);
        } else if ((request.getParameter("pageQ") == null || Integer.parseInt(request.getParameter("pageQ")) > listQuestion.size() || Integer.parseInt(request.getParameter("pageQ")) < 0) && listQuestion.size() > 0) {
            q = listQuestion.get(0);
        }
        //get list answer from cookie
        Cookie[] cookies = request.getCookies();
        ArrayList<Question> listAnswer = new ArrayList<>();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                ArrayList<Question> listQID = dbQ.getAllQuestion();
                try {
                    //cookie name is a number - id of question
                    int id = Integer.parseInt(cookies[i].getName());
                    for (int j = 0; j < listQID.size(); j++) {
                        if (listQID.get(j).getId() == id) {
                            Question question = new Question();
                            question.setId(id);
                            Answer a = new Answer();
                            a.setId(Integer.parseInt(cookies[i].getValue()));
                            ArrayList<Answer> l = new ArrayList<>();
                            l.add(a);
                            question.setListAnswer(l);
                            
                            listAnswer.add(question);
                        }
                    }
                } catch (Exception e) {
                }

            }
        }

        request.setAttribute("gid", account.getGroup().getId());
        request.setAttribute("totalQuestion", dbQ.getAllQuestion().size());
        request.setAttribute("pageQ", pageQ);
        request.setAttribute("listAnswerFromCookie", listAnswer);
        request.setAttribute("q", q);
        request.setAttribute("list", listQuestion);
        request.setAttribute("numOfQ", numOfQ);
        request.setAttribute("name", name);
        request.getRequestDispatcher("view/take-quiz.jsp").forward(request, response);

    }

    @Override
    void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie []cookies = request.getCookies();
        double numberOfQuestion = Double.parseDouble(request.getParameter("numberOfQuestion"));
        double numberOfCorectAnswer = 0;
        ResultDAO dbResult = new ResultDAO();
        ArrayList<Result> list = dbResult.getResult();
        for(int i = 0; i < cookies.length; i++) {
            try {
                //get question id and answer id
                int qid = Integer.parseInt(cookies[i].getName());
                int aid = Integer.parseInt(cookies[i].getValue());
                //check result
                for(int j = 0; j < list.size(); j++) {
                    if(list.get(j).getAid() == aid && list.get(j).getQid() == qid) {
                        numberOfCorectAnswer += 1;
                    }
                }
                //remove cookie of question id
                cookies[i].setValue("");
                cookies[i].setMaxAge(0);
                response.addCookie(cookies[i]);
            } catch (Exception e) {
            }
            if(cookies[i].getName().equals("temp_value")) {
                cookies[i].setValue("");
                cookies[i].setMaxAge(0);
                response.addCookie(cookies[i]);
            }
        }
        Account a = (Account) request.getSession().getAttribute("account");
        double mark = numberOfCorectAnswer / numberOfQuestion * 10;
        BigDecimal d = new BigDecimal(String.valueOf(mark));
        d = d.setScale(2, RoundingMode.HALF_UP);
        
        request.setAttribute("name", a.getUser());
        request.getSession().removeAttribute("list");
        request.setAttribute("mark", d);
        request.getRequestDispatcher("view/take-quiz.jsp").forward(request, response);
    }

}
