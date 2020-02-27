package com.cjc.view;

import com.cjc.controller.GetRequestHeader;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "DownloadServlet", urlPatterns = "/DownloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        // 告诉浏览器不要解析次文件直接下载 ，没有这一行的话 浏览器会默认打开.后面的 java.net.URLEncoder.encode(filename, "UTF-8")是把文件名子编码，让浏览器下载的时候文件名不会乱码
        response.setHeader("content-disposition", "attachment;filename=" +  java.net.URLEncoder.encode("说明.txt", "UTF-8"));
        //获取文件的绝对路径
        String path = this.getServletContext().getRealPath("//说明.txt");
        //获得该文件的输入流
        InputStream in = new FileInputStream(path);
        //获得输出流  通过response获得输出流，用于向客户端输出内容
        ServletOutputStream out = response.getOutputStream();
        //文件拷贝的模板代码
        int len = 0;
        byte[] buffer = new byte[1024];
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
  }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
