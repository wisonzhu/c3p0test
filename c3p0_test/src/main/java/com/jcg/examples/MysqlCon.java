package com.jcg.examples;


/**
 * Created by wisonzhu on 2017/11/30.
 */
import java.sql.*;
import java.util.Date;
class MysqlCon{
    public static void main(String args[]){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            for ( int i = 0; i< 10000; i++) {
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://mysql.jdb-dev.com:3306/test","jdbc","jdbctest");

                Thread.sleep(1000);
                System.out.println(new Date());
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from account");
                while (rs.next())
                    System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            }
        }catch(Exception e){ System.out.println(e);}
    }
}

