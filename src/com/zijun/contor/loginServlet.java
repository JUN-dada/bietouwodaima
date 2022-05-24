package com.zijun.contor;

import com.zijun.dao.userdao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class loginServlet extends HttpServlet {
    @Override
    protected void doPost(@NotNull HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName, password;
        userdao dao = new userdao();
        int result = 0;
        request.setCharacterEncoding("utf-8");
        userName = request.getParameter("username");
        password = request.getParameter("password");
        result = dao.login(userName, password);
        if ("子俊".equals(userName) && "root".equals(password))
        {
            response.sendRedirect("/untitled_war_exploded/user/find");
            return;
        }
            if (result != 0) {//用户存在
                //在判定来访用户身份合法后，通过请求对象向Tomcat申请为当前用户申请一个HttpSession
                HttpSession session = request.getSession();
                response.sendRedirect("index.html");
            }
            else
            {
                response.sendRedirect("del.html");
            }

        }
    }
