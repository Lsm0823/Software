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
import java.util.ArrayList;

public class SearchStock extends DB_CONNECT{
	ArrayList dataList = new ArrayList<String>();
	String[][] search_detail;
	int k;
	
	public void sqlRun() throws SQLException{   // 단순 검색
		System.out.println("sqlRun 호출");
	    String query = "select 상품번호, 상품명, 재고량, 가격 as 상품가격\r\n"
	    		+ "from 상품\r\n"
	    		+ "where 재고량 > 0";
	    
	    String[] search_result = new String[4];
	    
	    //참고 -> https://www.w3schools.com/java/java_arraylist.asp
//	    dataList.add("입력할 데이터");
//	    dataList.add("입력할 두번째 데이터");
//	    System.out.println(dataList);
//	    System.out.println(dataList.get(0));
//	    System.out.println("배열 개수 : "+dataList.size());
//	    dataList.clear();
//	    System.out.println("clear후 값 : "+dataList);
	    
	    
	    try { 
			DB_Connect();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			  
//			System.out.println("\t상품번호\t상품명\t재고량\t상품가격");
			while (rs.next()) {
				dataList.add(rs.getString("상품번호"));
				dataList.add(rs.getString("상품명"));
				dataList.add(rs.getString("재고량"));
				dataList.add(rs.getString("상품가격"));
			}
			  
			stmt.close();    rs.close();
		} 
		catch (SQLException e) {
			e.printStackTrace(); 
		} 
		finally {
			con.close();
		}  
	    search_detail = new String[dataList.size()/4][4];
   
	 }
	
	public SearchStock() throws SQLException{
		sqlRun();
		System.out.println("SearchStock 호출");
		setTitle("PC Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container Main_cp = getContentPane();	//프레임의 컨텐트팬을 알아냄
		JPanel searchStock_jp = new JPanel();
		searchStock_jp.setBounds(0, 0, 1200, 750);
		Main_cp.add(searchStock_jp);
		searchStock_jp.setLayout(null);
		
		//Bounds(오른쪽, 위, 가로, 세로)	
		JLabel searchStock_label = new JLabel("재고 조회");
		searchStock_label.setBounds(490, 100, 500, 100);
		searchStock_label.setFont(new Font("맑은고딕", Font.BOLD, 50));
		searchStock_label.setForeground(new Color(32,48,68));
		searchStock_jp.add(searchStock_label);
		
		
		JTextField search_box = new JTextField();
		search_box.setColumns(30);
		search_box.setBounds(700, 200, 250, 30);
		search_box.setFont(new Font("맑은고딕", Font.BOLD, 14));
		searchStock_jp.add(search_box);
		
		JButton search_btn = new JButton("검색");
		search_btn.setBounds(980, 200, 100, 30);
		search_btn.setFont(new Font("맑은고딕", Font.BOLD, 20));
		search_btn.setBackground(new Color(49,64,83));
		search_btn.setForeground(new Color(243,240,249));
		searchStock_jp.add(search_btn);
		
		
		String[] search_header = {"상품번호", "상품명", "재고량", "상품가격"};
		//2차원 배열 안에 넣기
	    
		
		
//		search_detail = new String[dataList.size()/4][4];
		
		k = 0;
		for(int i=0;i<dataList.size()/4;i++) {
			if(k>=24) {
				break;
			}
			else {
				for(int j=0;j<4;j++) {
					if(k>=24) {
						break;
					}
					else {
						search_detail[i][j] = (String)dataList.get(k);
					}
					k += 1;
				}
			}
		}

		
		JTable searchresult_table = new JTable(search_detail, search_header);
		searchresult_table.setRowHeight(50);
		searchresult_table.setFont(new Font("맑은고딕", Font.BOLD, 14));
		
		JScrollPane searchresult_sp = new JScrollPane(searchresult_table);
		searchresult_sp.setBounds(100, 250, 1000, 400);
		searchStock_jp.add(searchresult_sp);
		
		
		
		
		
		
		JButton home_btn = new JButton("←");
		home_btn.setBounds(100, 70, 55, 55);
		home_btn.setFont(new Font("", Font.BOLD, 20));
		home_btn.setBackground(new Color(49,64,83));
		home_btn.setForeground(new Color(243,240,249));
		searchStock_jp.add(home_btn);
		home_btn.addActionListener(new home_Listener());
		
		
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
	
	public static void main(String[] args) throws SQLException{
		SearchStock searchDB = new SearchStock();
//		searchDB.sqlRun();
//		searchDB.DB_Connect();   
		
		
	}
}
