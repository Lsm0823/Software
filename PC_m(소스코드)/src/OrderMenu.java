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
	
	// �ֹ� ��� ���̺� ������ ����
	String[] orderlist_header = {"��ǰ��", "����", "����"};
	String[][] orderlist_detail = {
			{"�ʱ���", "2", "4000"}, 
			{"������", "1", "2500"}
	};
	
	JLabel ordermenu_label = new JLabel("��ǰ �ֹ�");
	JLabel orderlist_label = new JLabel("�ֹ� ���");
	
	JLabel total_price_label = new JLabel("�� �ֹ� �ݾ�");
	JLabel total_price_text = new JLabel(" 0 ��");
	
	JButton Food_category_btn = new JButton("����");
	JButton Drink_category_btn = new JButton("����");
	JButton Other_category_btn = new JButton("��Ÿ");
	
	JButton increase_item_btn = new JButton("+");
	JButton decrease_item_btn = new JButton("-");
	JButton delete_item_btn = new JButton("X");
	
	JButton home_btn = new JButton("��");
	JButton pay_btn = new JButton("����");
	
	JTable orderlist_table = new JTable(orderlist_detail, orderlist_header);
	
	
	public OrderMenu() {
		setTitle("PC Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container Main_cp = getContentPane();	//�������� ����Ʈ���� �˾Ƴ�
		JPanel searchStock_jp = new JPanel();
		searchStock_jp.setBounds(0, 0, 1200, 750);
		Main_cp.add(searchStock_jp);
		searchStock_jp.setLayout(null);
		
		//Bounds(������, ��, ����, ����)	
		// ��ǰ �ֹ� �� ����
		ordermenu_label.setBounds(490, 20, 500, 100);
		ordermenu_label.setFont(new Font("�������", Font.BOLD, 50));
		searchStock_jp.add( ordermenu_label );
		
		// �ֹ���� �� ����
		orderlist_label.setBounds( 100, 450, 100, 50);
		orderlist_label.setFont(new Font("�������", Font.BOLD, 20));
		searchStock_jp.add( orderlist_label );
		
		// �� �ֹ� �ݾ� �� ����
		total_price_label.setBounds( 840, 450, 150, 50);
		total_price_label.setFont(new Font("�������", Font.BOLD, 20));
		searchStock_jp.add( total_price_label );
		
		// �� �ֹ� �ݾ� �ؽ�Ʈ ����
		total_price_text.setBounds( 950, 550, 100, 50);
		total_price_text.setFont(new Font("�������", Font.BOLD, 20));
		searchStock_jp.add( total_price_text );
		
		// ��ǰ ī�װ� ��ư ����
		Food_category_btn.setBounds(100, 170, 100, 30);
		Food_category_btn.setFont(new Font("�������", Font.BOLD, 20));
		Food_category_btn.setBackground(new Color(49,64,83));
		Food_category_btn.setForeground(new Color(243,240,249));
		searchStock_jp.add( Food_category_btn );
		
		Drink_category_btn.setBounds(210, 170, 100, 30);
		Drink_category_btn.setFont(new Font("�������", Font.BOLD, 20));
		Drink_category_btn.setBackground(new Color(49,64,83));
		Drink_category_btn.setForeground(new Color(243,240,249));
		searchStock_jp.add( Drink_category_btn );
		
		Other_category_btn.setBounds(320, 170, 100, 30);
		Other_category_btn.setFont(new Font("�������", Font.BOLD, 20));
		Other_category_btn.setBackground(new Color(49,64,83));
		Other_category_btn.setForeground(new Color(243,240,249));
		searchStock_jp.add( Other_category_btn );
		
		// �ֹ���� ���� ���� ��ư ����
		increase_item_btn.setBounds( 640, 450, 45, 45);
		increase_item_btn.setFont(new Font("�������", Font.BOLD, 18));
		increase_item_btn.setBackground(new Color(49,64,83));
		increase_item_btn.setForeground(new Color(243,240,249));
		searchStock_jp.add( increase_item_btn );
		
		decrease_item_btn.setBounds( 695, 450, 45, 45);
		decrease_item_btn.setFont(new Font("�������", Font.BOLD, 20));
		decrease_item_btn.setBackground(new Color(49,64,83));
		decrease_item_btn.setForeground(new Color(243,240,249));
		searchStock_jp.add( decrease_item_btn );
		
		delete_item_btn.setBounds( 750, 450, 45, 45);
		delete_item_btn.setFont(new Font("�������", Font.BOLD, 16));
		delete_item_btn.setBackground(new Color(49,64,83));
		delete_item_btn.setForeground(new Color(243,240,249));
		searchStock_jp.add( delete_item_btn );
		
		// �ڷΰ��� ��ư
		home_btn.setBounds(100, 70, 55, 55);
		home_btn.setFont(new Font("", Font.BOLD, 20));
		home_btn.setBackground(new Color(49,64,83));
		home_btn.setForeground(new Color(243,240,249));
		searchStock_jp.add(home_btn);
		home_btn.addActionListener(new home_Listener());
		
		// �ڷΰ��� ��ư
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
		
		// �ֹ� ��� ���̺� ����
		orderlist_table.setRowHeight(50);
		orderlist_table.setFont(new Font("�������", Font.BOLD, 14));
		
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
		total_price_text.setText( money + " ��" );
		System.out.println("���ݺ�ȯ");
	}
}

