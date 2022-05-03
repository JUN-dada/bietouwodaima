package com.zijun.contor;
import com.zijun.dao.userdao;
import com.zijun.entity.users;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet({"/se"})
public class userServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String username,password,sex,email;
        userdao userdao = new userdao();
        users users=null;
        int result = 0;
        PrintWriter out = null;
        //1.【调用请求对象】读取【请求头】参数信息，得到用户的信息信息
        username = request.getParameter("username");
        password = request.getParameter("password");
        sex = request.getParameter("sex");
        email = request.getParameter("email");
        //2.【调用UserDao】将用户信息填充到INSERT命令并借助JDBC规范发送到数据库服务器
        users=new users(0,username,password,sex,email) ;
        result=userdao.add(users);
        //3.【调用响应对象】将【处理结果】以二进制形式写入到响应体
        response.setContentType("text/html;charset=utf-8");
        out = response.getWriter();
        if(result ==1){
            out.print("<iframe border: medium none height=\"100%\" width=\"100%\" src=\"zhucechenggong.html\"></iframe>");
        }else{
            out.print("<font style='color:red;font-size:40'>用户信息注册失败</font>");
        }
        out.flush();
        out.close();
    }
    }

