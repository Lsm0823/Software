import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.Container.*;
import java.awt.Component.*;
import java.util.*;
import java.util.Scanner;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import oracle.jdbc.OracleTypes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import oracle.jdbc.OracleTypes;

import oracle.jdbc.OracleTypes;

public class DB_CONNECT extends JFrame{
   Connection con = null;
   String url = "jdbc:oracle:thin:@localhost:1521:XE";
   String id = "PCManager";      String password = "1234";
   
   public DB_CONNECT() {
     try {
    	Class.forName("oracle.jdbc.driver.OracleDriver");
        System.out.println("����̹� ���� ����");
     } catch (ClassNotFoundException e) { System.out.println("No Driver."); }  
   }
   public void DB_Connect() {
     try {
         con = DriverManager.getConnection(url, id, password);
         System.out.println("DB ���� ����");
     } catch (SQLException e) { System.out.println("Connection Fail");      }
   }

public static void main(String arg[]) throws SQLException {
	    DB_CONNECT dbconquery = new  DB_CONNECT();
//		dbconquery.sqlRun();
		dbconquery.DB_Connect();      
    }
}
