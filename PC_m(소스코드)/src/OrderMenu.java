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
import java.util.Scanner;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class OrderMenu extends JFrame{
	int money = 0;
	
	// 주문 목록 테이블 데이터 정의
	String[] orderlist_header = {"상품명", "수량", "가격"};
	String[][] orderlist_detail = {
			{"너구리", "2", "4000"}, 
			{"육개장", "1", "2500"}
	};
	
	JLabel ordermenu_label = new JLabel("상품 주문");
	JLabel orderlist_label = new JLabel("주문 목록");
	
	JLabel total_price_label = new JLabel("총 주문 금액");
	JLabel total_price_text = new JLabel(" 0 원");
	
	JButton Food_category_btn = new JButton("음식");
	JButton Drink_category_btn = new JButton("음료");
	JButton Other_category_btn = new JButton("기타");
	
	JButton increase_item_btn = new JButton("+");
	JButton decrease_item_btn = new JButton("-");
	JButton delete_item_btn = new JButton("X");
	
	JButton home_btn = new JButton("←");
	JButton pay_btn = new JButton("결제");
	
	JTable orderlist_table = new JTable(orderlist_detail, orderlist_header);
	
	
	public OrderMenu() {
		setTitle("PC Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container Main_cp = getContentPane();	//프레임의 컨텐트팬을 알아냄
		JPanel searchStock_jp = new JPanel();
		searchStock_jp.setBounds(0, 0, 1200, 750);
		Main_cp.add(searchStock_jp);
		searchStock_jp.setLayout(null);
		
		//Bounds(오른쪽, 위, 가로, 세로)	
		// 상품 주문 라벨 설정
		ordermenu_label.setBounds(490, 20, 500, 100);
		ordermenu_label.setFont(new Font("맑은고딕", Font.BOLD, 50));
		searchStock_jp.add( ordermenu_label );
		
		// 주문목록 라벨 설정
		orderlist_label.setBounds( 100, 450, 100, 50);
		orderlist_label.setFont(new Font("맑은고딕", Font.BOLD, 20));
		searchStock_jp.add( orderlist_label );
		
		// 총 주문 금액 라벨 설정
		total_price_label.setBounds( 840, 450, 150, 50);
		total_price_label.setFont(new Font("맑은고딕", Font.BOLD, 20));
		searchStock_jp.add( total_price_label );
		
		// 총 주문 금액 텍스트 설정
		total_price_text.setBounds( 950, 550, 100, 50);
		total_price_text.setFont(new Font("맑은고딕", Font.BOLD, 20));
		searchStock_jp.add( total_price_text );
		
		// 상품 카테고리 버튼 설정
		Food_category_btn.setBounds(100, 170, 100, 30);
		Food_category_btn.setFont(new Font("맑은고딕", Font.BOLD, 20));
		Food_category_btn.setBackground(new Color(49,64,83));
		Food_category_btn.setForeground(new Color(243,240,249));
		searchStock_jp.add( Food_category_btn );
		
		Drink_category_btn.setBounds(210, 170, 100, 30);
		Drink_category_btn.setFont(new Font("맑은고딕", Font.BOLD, 20));
		Drink_category_btn.setBackground(new Color(49,64,83));
		Drink_category_btn.setForeground(new Color(243,240,249));
		searchStock_jp.add( Drink_category_btn );
		
		Other_category_btn.setBounds(320, 170, 100, 30);
		Other_category_btn.setFont(new Font("맑은고딕", Font.BOLD, 20));
		Other_category_btn.setBackground(new Color(49,64,83));
		Other_category_btn.setForeground(new Color(243,240,249));
		searchStock_jp.add( Other_category_btn );
		
		// 주문목록 수량 증감 버튼 설정
		increase_item_btn.setBounds( 640, 450, 45, 45);
		increase_item_btn.setFont(new Font("맑은고딕", Font.BOLD, 18));
		increase_item_btn.setBackground(new Color(49,64,83));
		increase_item_btn.setForeground(new Color(243,240,249));
		searchStock_jp.add( increase_item_btn );
		
		decrease_item_btn.setBounds( 695, 450, 45, 45);
		decrease_item_btn.setFont(new Font("맑은고딕", Font.BOLD, 20));
		decrease_item_btn.setBackground(new Color(49,64,83));
		decrease_item_btn.setForeground(new Color(243,240,249));
		searchStock_jp.add( decrease_item_btn );
		
		delete_item_btn.setBounds( 750, 450, 45, 45);
		delete_item_btn.setFont(new Font("맑은고딕", Font.BOLD, 16));
		delete_item_btn.setBackground(new Color(49,64,83));
		delete_item_btn.setForeground(new Color(243,240,249));
		searchStock_jp.add( delete_item_btn );
		
		// 뒤로가기 버튼
		home_btn.setBounds(100, 70, 55, 55);
		home_btn.setFont(new Font("", Font.BOLD, 20));
		home_btn.setBackground(new Color(49,64,83));
		home_btn.setForeground(new Color(243,240,249));
		searchStock_jp.add(home_btn);
		home_btn.addActionListener(new home_Listener());
		
		// 뒤로가기 버튼
		pay_btn.setBounds( 840, 650, 250, 50);
		pay_btn.setFont(new Font("", Font.BOLD, 20));
		pay_btn.setBackground(new Color(49,64,83));
		pay_btn.setForeground(new Color(243,240,249));
		searchStock_jp.add(pay_btn);
		pay_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setMoney();
			}
		});
		
		// 주문 목록 테이블 선언
		orderlist_table.setRowHeight(50);
		orderlist_table.setFont(new Font("맑은고딕", Font.BOLD, 14));
		
		JScrollPane searchresult_sp = new JScrollPane(orderlist_table);
		searchresult_sp.setBounds(100, 500, 700, 200);
		searchStock_jp.add(searchresult_sp);
		
		
		setSize(1200, 750);
		setVisible(true);
	}
	
	//event listener
	private class home_Listener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			new Main();
			setVisible(false);
		}
	}
	
	public static void main(String[] args) {
		new OrderMenu();
	}
	
	public void setMoney() {
		money = Integer.parseInt(orderlist_detail[0][2]) + Integer.parseInt( orderlist_detail[1][2]);
		total_price_text.setText( money + " 원" );
		System.out.println("가격변환");
	}
}

