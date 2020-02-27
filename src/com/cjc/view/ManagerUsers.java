package com.cjc.view;

import com.cjc.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "ManagerUsers")
public class ManagerUsers extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String url = request.getHeader("Referer");
        out.println("<h1>管理用户</h1>");
        Connection ct = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
//        System.out.println("准备连上数据库了");
        //分页技术
        int pageNow = 1;
        //接收pageNow
        String sPageNow=request.getParameter("pageNow");
        if(sPageNow!=null){
        pageNow=Integer.parseInt(sPageNow);}
        int pageSize = 2;
        int pageCount = 1;
        int rowCount = 1;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            ct= DriverManager.getConnection("jdbc:mysql://localhost:3306/usermanager?useSSL=false&serverTimezone=UTC","root","root");//?useSSL=false&serverTimezone=UTC
            ps=ct.prepareStatement("select count(*) from user");
            rs=ps.executeQuery();
            rs.next();//游标下移
            rowCount = rs.getInt(1);
            pageCount = (rowCount-1)/pageSize+1;
            ps=ct.prepareStatement("SELECT * FROM usermanager.user WHERE id<=? and id>? order by id asc");
            ps.setInt(1,((pageNow)*pageSize));
            ps.setInt(2,((pageNow-1)*pageSize));
            rs=ps.executeQuery();
            out.println("<table border=1 width=500px>");
            out.println("<tr><th>id</th><th>用户名</th><th>email</th><th>级别</th></tr>");
            while(rs.next()){
            out.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getInt(4)+"</td></tr>");
            }
            out.println("</table>");
            //显示分页
            for(int i = 1;i<=pageCount;i++){
                out.println("<a href='/UserManager/ManagerUsers?pageNow="+i+"'><"+i+"></a>");
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

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
