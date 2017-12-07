package com.jcg.examples;


import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DatabaseUtility
{
		public static ComboPooledDataSource getDataSource() throws PropertyVetoException
		{
				ComboPooledDataSource cpds = new ComboPooledDataSource();
				cpds.setJdbcUrl("jdbc:mysql://mysql.jdb-dev.com/test");
				cpds.setUser("jdbc");
				cpds.setPassword("jdbctest");

				// Optional Settings
				cpds.setInitialPoolSize(3);
				cpds.setMinPoolSize(3);
				//cpds.setAcquireIncrement(5);
				cpds.setMaxPoolSize(3);
				cpds.setMaxStatements(3);
				cpds.setMaxIdleTime(3);
                cpds.setIdleConnectionTestPeriod(600);
                cpds.setTestConnectionOnCheckout(true);
				return cpds;
		}


		public static void main(String[] args) throws SQLException {
            Connection connection = null;
            PreparedStatement pstmt = null;
            ResultSet resultSet = null;

                try {
                    ComboPooledDataSource dataSource = DatabaseUtility.getDataSource();


                        for (int i = 1; i < 10000; i++) {
                            connection = dataSource.getConnection();
                            pstmt = connection.prepareStatement("SELECT * FROM account");
                            //System.out.println("The Connection Object is of Class: " + connection.getClass());
                            Thread.sleep(1000);
                            System.out.println(new Date());
                            //System.out.println(dataSource.getJdbcUrl());
                            resultSet = pstmt.executeQuery();

                            while (resultSet.next()) {
                                System.out.println(resultSet.getString(1) + "," + resultSet.getString(2) + "," + resultSet.getString(3));

                            }
                            //关闭连接
                            connection.close();

                        }

                } catch (Exception e) {
                    //connection.rollback();
                    System.out.print("ok");
                    e.printStackTrace();
               }
        }

}
