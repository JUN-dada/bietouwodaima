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
        //得到用户的信息信息
        username = request.getParameter("username");
        password = request.getParameter("password");
        sex = request.getParameter("sex");
        email = request.getParameter("email");
        //发送到数据库服务器
        users=new users(0,username,password,sex,email) ;
        result=userdao.add(users);
        response.setContentType("text/html;charset=utf-8");
        out = response.getWriter();
        if(result ==1){
            out.print("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "  <meta charset=\"UTF-8\">\n" +
                    "  <title>Title</title>\n" +
                    "  <link rel=\"stylesheet\" href=\"../css/login.css\">\n" +
                    "  <style>\n" +
                    "    body\n" +
                    "    {\n" +
                    "      font-family: -apple-system;\n" +
                    "      margin: 0;\n" +
                    "    }\n" +
                    "\n" +
                    "    .centerlogin\n" +
                    "    {\n" +
                    "      z-index: 1;\n" +
                    "      width: 100%;\n" +
                    "      height: 100vh;\n" +
                    "      position: absolute;\n" +
                    "    }\n" +
                    "\n" +
                    "    .incenterlogin\n" +
                    "    {\n" +
                    "      width: 50%;\n" +
                    "      height: 60vh;\n" +
                    "      margin-left: 25%;\n" +
                    "      margin-top: 10%;\n" +
                    "      background: rgba( 255, 255, 255, 0 );\n" +
                    "      box-shadow: 0 8px 32px 0 rgba( 31, 38, 135, 0.37 );\n" +
                    "      backdrop-filter: blur( 15.5px );\n" +
                    "      -webkit-backdrop-filter: blur( 15.5px );\n" +
                    "      border-radius: 10px;\n" +
                    "      border: 1px solid rgba( 255, 255, 255, 0.18 );\n" +
                    "    }\n" +
                    "\n" +
                    "    .incenterlogin form input\n" +
                    "    {\n" +
                    "      text-align: center;\n" +
                    "      font-size: 20px;\n" +
                    "      border-radius: 20px ;\n" +
                    "      margin-top: 9%;\n" +
                    "      width: 40%;\n" +
                    "      height: 4vh;\n" +
                    "      background: rgba( 255, 255, 255, 0 );\n" +
                    "    }\n" +
                    "\n" +
                    "    #top\n" +
                    "    {\n" +
                    "      width: 100%;\n" +
                    "      height: 100vh;\n" +
                    "      min-height: 100%;\n" +
                    "      position: absolute;\n" +
                    "      z-index: -1;\n" +
                    "\n" +
                    "    }\n" +
                    "  </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div class=\"centerlogin\">\n" +
                    "  <div class=\"incenterlogin\">\n" +
                    "    <center>\n" +
                    "      <span style=\"font-size: 6vh;color: azure;line-height: 9\">注册成功</span>\n" +
                    "      </form>\n" +
                    "    </center>\n" +
                    "  </div>\n" +
                    "</div>\n" +
                    "\n" +
                    "<div id=\"top\"></div>\n" +
                    "</body>\n" +
                    "</html>\n" +
                    "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/three.js/r121/three.min.js\"></script>\n" +
                    "<script src=\"https://cdn.jsdelivr.net/npm/vanta@latest/dist/vanta.halo.min.js\"></script>\n" +
                    "<script>\n" +
                    "  VANTA.HALO({\n" +
                    "    el: \"#top\",\n" +
                    "    mouseControls: true,\n" +
                    "    touchControls: true,\n" +
                    "    gyroControls: false,\n" +
                    "    minHeight: 200.00,\n" +
                    "    minWidth: 200.00,\n" +
                    "    baseColor: 0x786a6a,\n" +
                    "    backgroundColor: 0x1fd9f2,\n" +
                    "    amplitudeFactor: 1.50\n" +
                    "  })\n" +
                    "</script>\n");
        }else{
            out.print("<font style='color:red;font-size:40'>用户信息注册失败</font>");
        }
        out.flush();
        out.close();
    }
    }

