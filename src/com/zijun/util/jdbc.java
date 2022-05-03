package com.zijun.util;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.Iterator;
import java.util.Map;

public class jdbc {
    final String URL="jdbc:mysql://localhost:3306/exercise?&useSSL=false&serverTimezone=UTC";
    final String USERNAME="root";
    final String PASSWORD="0638531086zzJ?";
    PreparedStatement ps= null;
    Connection con = null;

    public  Connection getCon(HttpServletRequest request)
    {
        ServletContext application = request.getServletContext();
        Map map = (Map)application.getAttribute("key1");
        Iterator it =  map.keySet().iterator();
        while(it.hasNext()){
            con = (Connection)  it.next();
            boolean flag = (boolean)map.get(con);
            if(flag == true){
                map.put(con, false);
                break;
            }
        }
        return con;
    }

    public  PreparedStatement createStatement(String sql,HttpServletRequest request){

        try {
            ps =  getCon(request).prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    public  void close(HttpServletRequest request){
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        ServletContext application = request.getServletContext();
        Map map =(Map)application.getAttribute("key1");
        map.put(con, true);

    }
    //-------------通过全局作用域对象得到Connetion-----------start

    //将jar包中driver实现类加载到JVM中
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //封装连接通道
    public  Connection   getCon(){

        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    //封装交通工具创建细节
    public  PreparedStatement createStatement(String sql){

        try {
            ps =  getCon().prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }
    // ps与con销毁细节 insert,update,delete
    public  void close(){
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    public  void close(ResultSet rs)
    {
        if(rs!=null)
        {
            try
            {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        close();
    }
}
