package com.cjc.controller;

import com.cjc.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


@WebServlet(name = "LoginDealwithServlet")
public class LoginDealwithServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        request.setCharacterEncoding("utf-8");

        //接收用户提交的用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//        System.out.println("获得输入");
        //到数据库验证
        Connection ct = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
//        System.out.println("准备连上数据库了");
        try{
            //加载驱动得到连接，创建preparedStatemen，执行操作，根据结果处理
//            System.out.println("加载驱动");
            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("得到连接");
            ct= DriverManager.getConnection("jdbc:mysql://localhost:3306/usermanager?useSSL=false&serverTimezone=UTC","root","root");//?useSSL=false&serverTimezone=UTC
//            System.out.println("连上数据库了");
            ps=ct.prepareStatement("select * from user where username = ? and password = ?");
            ps.setString(1,username);
            ps.setString(2,password);
            rs=ps.executeQuery();
            if(rs.next()){
                User user = new User();
                user.setName(username);
                user.setPwd(password);
                request.setAttribute("userobj",user);
                String nums = (String) this.getServletContext().getAttribute("nums");

                    Integer numsTemp = Integer.parseInt(this.getServletContext().getAttribute("nums").toString());
                    nums = (numsTemp+1)+"";
                    this.getServletContext().setAttribute("nums",nums);


                request.getRequestDispatcher("/MainFrame").forward(request,response);
//                System.out.println("连上数据库了");
//                response.sendRedirect("/UserManager/MainFrame");

            }else{
                request.setAttribute("err","用户名或密码有误");
                request.getRequestDispatcher("/LoginServlet").forward(request,response);
            }

        }catch (Exception e){
            //handle exception
            System.out.println("出现异常");

        }finally {
            //关闭资源
            if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }}
            if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }}
            if(ct!=null){
            try {
                ct.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }}
        }


        //本地验证
//        if("123".equals(password)){
//            response.sendRedirect("/UserManager/MainFrame");
//            //request.getSession().setAttribute("username",username);
//            User user = new User();
//            user.setName(username);
//            user.setPwd(password);
//            request.getSession().setAttribute("userobj",user);
//        }else{
//            response.sendRedirect("/UserManager/LoginServlet");
//        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
