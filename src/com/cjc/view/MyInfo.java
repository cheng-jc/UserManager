package com.cjc.view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MyInfo", urlPatterns = "/MyInfo")
public class MyInfo extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<form action='/UserManager/GetRequestHeader' method='post'>");
        out.println("<input type='hidden' name='secret' value='提交信息'/>");
        out.println("用户名：<input type='text' name='username'/><br/>");
        out.println("性别：<input type='radio' name='sex' value='男'/>男 <input type='radio' name='sex' value='女'/>女<br/>");
        out.println("你的爱好：<input type='checkbox' name='hobby' value='篮球'/>篮球 <input type='checkbox' name='hobby' value='读书'/>读书 <input type='checkbox' name='hobby' value='动漫'/>动漫<br/>");
        out.println("喜欢的城市：<select name='city' multiple=\"multiple\" size=\"2\"><option value='bj'>北京</option> <option value='cd'>成都</option> <option value='gz'>广州</option></select><br/>");
        out.println("<input type='submit' value='提交信息'/>");
        out.println("</form>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
