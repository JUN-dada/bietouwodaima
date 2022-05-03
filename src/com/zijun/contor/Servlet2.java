package com.zijun.contor;

import com.zijun.dao.userdao;
import com.zijun.entity.users;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Servlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        userdao dao = new userdao();
        PrintWriter out;
        List<users> userlist=dao.findAll();
        response.setContentType("text/html;charset=utf-8");
        out=response.getWriter();
        out.println("<table border='2'>");
        out.println("<tr>");
        out.println("<td>用户编号</td>");
        out.println("<td>用户名字</td>");
        out.println("<td>用户密码</td>");
        out.println("<td>用户性别</td>");
        out.println("<td>用户邮箱</td>");
        out.println("<td>操作</td>");
        for (users users:userlist)
        {
            out.println("<tr>");
            out.println("<td>"+users.getUserid()+"</td>");
            out.println("<td>"+users.getUsername()+"</td>");
            out.println("<td>"+users.getPassword()+"</td>");
            out.println("<td>"+users.getSex()+"</td>");
            out.println("<td>"+users.getEmail()+"</td>");
            out.print("<td><a href='del?userId="+users.getUserid() +"'>删除用户</a></td>");
            out.println("<tr>");
        }
        out.println("</table>");
    }


}
