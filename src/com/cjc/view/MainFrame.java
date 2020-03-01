package com.cjc.view;

import com.cjc.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MainFrame")
public class MainFrame extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String url = request.getHeader("Referer");
        if(url!=null){
        User user = (User) request.getAttribute("userobj");
        String nums = this.getServletContext().getAttribute("nums").toString();
        out.println("<img src='img/bird.jpg' width=400px height=200px/>");
        out.println("欢迎 "+" "+user.getName()+" "+" 登陆<hr/>");
        out.println("你是第 "+nums+" 个访问者<hr/>");
        out.println("<h1>管理页面</h1>");
        out.println("</br><a href='/UserManager/LoginServlet'>重新返回登录页面</a></br>");
        out.println("<h3>请选择要进行的操作</h3>");
        out.println("<a href='/UserManager/ManagerUsers'>管理用户</a></br>");
        out.println("<a href=''>添加用户</a></br>");
        out.println("<a href=''>查找用户</a></br>");
        out.println("<a href='/UserManager/DownloadServlet'>说明下载</a></br>");
        out.println("<a href=''>退出系统</a></br>");
        }
        else{
            System.out.println("小伙子不要乱来");
            out.println("走开！");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
