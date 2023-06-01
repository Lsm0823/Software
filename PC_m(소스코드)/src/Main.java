import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.event.*;
//import javax.swing.border.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.Container.*;
import java.awt.Component.*;
import java.util.Scanner;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class Main extends JFrame{
	public Main() {
		setTitle("PC Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container Main_cp = getContentPane();	//프레임의 컨텐트팬을 알아냄
		JPanel main_jp = new JPanel();
		main_jp.setBounds(0, 0, 1200, 750);
		Main_cp.add(main_jp);
		main_jp.setLayout(null);
		
		//Bounds(오른쪽, 위, 가로, 세로)	
		JLabel main_label = new JLabel("PC Manager");
		main_label.setBounds(430, 80, 500, 100);
		main_label.setFont(new Font("HY헤드라인M", Font.PLAIN, 65));
		main_label.setForeground(new Color(32,48,68));
		main_jp.add(main_label);
		
		JButton user_btn = new JButton("손님");
		user_btn.setBounds(150, 200, 400, 450);
		user_btn.setFont(new Font("맑은고딕", Font.BOLD, 36));
		user_btn.setForeground(new Color(243,240,249));
		user_btn.setBackground(new Color(22,75,144));
		user_btn.setBorder(new LineBorder(new Color(28,92,170), 10));
		main_jp.add(user_btn);
		user_btn.addActionListener(new user_Listener());
		
		JButton manager_btn = new JButton("관리자");
		manager_btn.setBounds(650, 200, 400, 450);
		manager_btn.setFont(new Font("맑은고딕", Font.BOLD, 36));
		manager_btn.setForeground(new Color(243,240,249));
		manager_btn.setBackground(new Color(49,64,83));
		manager_btn.setBorder(new LineBorder(new Color(60,81,107), 10));
		main_jp.add(manager_btn);
		manager_btn.addActionListener(new manager_Listener());
		
		
		
		setSize(1200, 750);
		setVisible(true);
	}
	
	//event listener
	private class manager_Listener implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			try {
				new SearchStock();
		     } catch (SQLException f) { System.out.println("Connection Fail");      }
			setVisible(false);
		}
	}
	private class user_Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new OrderMenu(); 
			setVisible(false);
			System.out.println("뀩");
			System.out.println("쀽");
			System.out.println("ㅋㅋㅋㅋㅋ이걸 받아주네");
		}
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
