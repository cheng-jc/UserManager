package com.cjc.view;

import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<img src='img/whale.jpg' width=1000px height=400px/><hr/>");
        out.println("<h1>用户登录</h1>");
        out.println("<form action='/UserManager/LoginDealwithServlet' method='post'>");
        out.println("用户名：<input type='text' name='username'/><br/>");
        out.println("密　码：<input type='password' name='password'/><br/>");
        out.println("<input type='submit' value='登录'><br/>");
        out.println("</form>");
        String message = (String)request.getAttribute("err");
        if(message!=null){
        out.println("<font color='red'>"+message+"</font>");}

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    this.doPost(request,response);
    }
}
