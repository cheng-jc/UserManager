package com.cjc.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet(name = "Servlet", urlPatterns = "/Servlet")
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        System.out.println("Servlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    public void destroy() {
        super.destroy();
        System.out.println("退出servlet");
        String filepath = this.getServletContext().getRealPath("record.txt");
        try {
            FileWriter fileWriter = new FileWriter(filepath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String nums = (String) this.getServletContext().getAttribute("nums");
            bufferedWriter.write(nums);
            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void init() throws ServletException{
        System.out.println("进入servlet");
        String filepath = this.getServletContext().getRealPath("record.txt");
        try {
            FileReader fr = new FileReader(filepath);
            BufferedReader bufferedReader = new BufferedReader(fr);
            String nums = bufferedReader.readLine();
            this.getServletContext().setAttribute("nums",nums);
            bufferedReader.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
