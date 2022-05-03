package com.zijun.dao;
import com.zijun.entity.users;
import com.zijun.util.jdbc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class userdao
{
    private jdbc util=new jdbc();
     public int add(users users)
     {
         int result=0;
         String sql="insert into users (username, sex, password, email) VALUES (?,?,?,?);";
         PreparedStatement ps= util.createStatement(sql);
         try
         {
             ps.setString(1, users.getUsername());
             ps.setString(2,users.getSex());
             ps.setString(3, users.getPassword());
             ps.setString(4,users.getEmail());
             result = ps.executeUpdate();
         } catch (SQLException e)
         {
             throw new RuntimeException(e);
         } finally
         {
         util.close();
         }
         return result;
     }

     public List findAll() {
         String sql = "select * from users";
         PreparedStatement ps = util.createStatement(sql);
         ResultSet rs = null;
         List userlist;
         try {
             rs = ps.executeQuery();
             userlist = new ArrayList();
             while (rs.next()) {
                 Integer userid = rs.getInt("userid");
                 String name = rs.getString("username");
                 String password = rs.getString("password");
                 String sex = rs.getString("sex");
                 String email = rs.getString("email");
                 users users = new users(userid, name, password, sex, email);
                 userlist.add(users);
             }
         } catch (SQLException e)
         {
             throw new RuntimeException(e);
         } finally
         {
             util.close();
         }
         return userlist;
     }

    public int del(String userId) throws SQLException {
        String sql="delete from users where userId=?";
        PreparedStatement ps = util.createStatement(sql);
        ps.setInt(1, Integer.parseInt(userId));
        int result = 0;
        try {
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close();
        }
        return result;
    }

    public int login(String username, String password)
    {
        String sql="select * from users where username=? and password=?";
        PreparedStatement ps = util.createStatement(sql);
        ResultSet rs = null;
        int result = 0;
        try {
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while(rs.next()){
                result =  rs.getInt("*");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            util.close(rs);
        }
        return result;
    }
}
