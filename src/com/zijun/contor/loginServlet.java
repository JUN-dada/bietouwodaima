package com.zijun.contor;

import com.zijun.dao.userdao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class loginServlet extends HttpServlet {
    @Override
    protected void doPost(@NotNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String userName,password;
        userdao dao = new userdao();
        int result =0;
        request.setCharacterEncoding("utf-8");
        userName = request.getParameter("userName");
        password = request.getParameter("password");
        result = dao.login(userName, password);
        //4.调用响应对象，根据验证结果将不同资源文件地址写入到响应头，交给浏览器
        if(result ==1){//用户存在
            //在判定来访用户身份合法后，通过请求对象向Tomcat申请为当前用户申请一个HttpSession
            HttpSession session = request.getSession();
            response.sendRedirect("index.htmll");
        }else{
            response.sendRedirect("del.html");
        }
    }
}
