package com.cjc.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "GetRequestHeader", urlPatterns = "/GetRequestHeader")
public class GetRequestHeader extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String username = request.getParameter("username");
        out.println(username+"</br>");
        String sex = request.getParameter("sex");
        out.println(sex+"</br>");
        String[] hobby = request.getParameterValues("hobby");
        String[] city = request.getParameterValues("city");
        String secret = request.getParameter("secret");
        out.println(secret+"</br>");
        if(hobby!=null&&city!=null){
        for(int i=0;i<hobby.length;i++){
            out.println(hobby[i]+"</br>");
        }

        for(int i=0;i<city.length;i++){
            out.println(city[i]+"</br>");
        }}else{
            out.println("没选好");
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
