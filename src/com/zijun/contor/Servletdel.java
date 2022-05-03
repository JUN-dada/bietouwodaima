package com.zijun.contor;

import com.zijun.dao.userdao;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
public class Servletdel extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int result=0;
        String userid;
        PrintWriter out=null;
        userid=request.getParameter("userId");
        System.out.println(userid);
        userdao userdao=new userdao();
        try {
            result = userdao.del(userid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.setContentType("text/html;charset=utf-8");
        out=response.getWriter();
        if (result==1)
        {
            out.print("<font style='color:balck;font-size:40'>删除成功了.....吧</font>");
        }

        else
        {
            out.print("<font style='color:balck;font-size:40'>出现错误</font>");
        }

    }


}
