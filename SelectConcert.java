package ProiectFinalP3;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 * 
 *  @author Stan Mihnea
 *  
 *  clasa pentru selectarea concertului dorit implementeaza ResultTableModel pentru lista concertelor dintr-un anumit oras
 */
public class SelectConcert extends JInternalFrame implements ActionListener {
    JPanel select_concert,list_of_concerts;
    JDesktopPane desktop;
    ResultSetTableModel tableModel;
    JLabel nume,oras;
    JTextField tf_nume,tf_oras;
    JButton btn_check;
    JTable available_concerts;
    DefaultTableModel dm=new DefaultTableModel();
    String[] col ={"ConertId","Nume","Oras","Nrlocuri"};
    private JPanel northPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private JLabel northLabel = new JLabel("LISTA CONCERTELOR");
    private JButton printButton;
    private JTable table;
    private TableColumn column = null;
    private JScrollPane scrollPane;
    
    public SelectConcert() {
        super("Select Concert",true,true,true);
        select_concert = new JPanel();
         list_of_concerts = new JPanel();
         nume = new JLabel("Nume ");
         oras = new JLabel("Oras ");
           tf_nume = new JTextField();
         tf_oras = new JTextField();
         desktop = new JDesktopPane();
         setContentPane(desktop); 
          dm.addColumn("ID");
        dm.addColumn("NUME");
        dm.addColumn("ORAS");
        dm.addColumn("NR LOCURI");
        dm.setColumnIdentifiers(col);
        available_concerts = new JTable(dm);
         Container cnt = getContentPane();
         btn_check = new JButton("Verifica");
        // nume.setBounds(20,20,100,20);
         oras.setBounds(20,60,100,20);
        // tf_nume.setBounds(120,20,100,20);
         tf_oras.setBounds(120,60,100,20);
         btn_check.setBounds(100,100,80,40);    
         select_concert.add(nume);
         select_concert.add(tf_nume);
         select_concert.add(oras);
         select_concert.add(tf_oras);
         select_concert.add(btn_check);
         list_of_concerts.setBackground(Color.BLUE);
         add(select_concert);    
         btn_check.addActionListener(this);
         select_concert.setSize(1000,300);
         select_concert.setLayout(null); 
         setVisible(true);
         setLayout(new GridLayout(1,2));
         setSize(600,900);     
    }
     public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_check) {
            JFrame in = new JFrame("list of available concerts");
       //     String Nume = tf_nume.getText();
            String Oras = tf_oras.getText();
            String Query = "Select ConcertId,Nume,Oras,Nrlocuri from concerte where Oras ='"+Oras +"';";
            System.out.println(Query);
           try {
        	   Connection con;
           	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bazap3", "root", "");
              
            tableModel = new ResultSetTableModel(/*JDBC_DRIVER,*/con, Query);
            try {
                tableModel.setQuery(Query);
            } catch (SQLException sqlException) {
            }
        } catch (ClassNotFoundException classNotFound) {
            System.out.println(classNotFound.toString());
        } catch (SQLException sqlException) {
            System.out.println(sqlException.toString());
        }
        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(990, 200));
        table.setFont(new Font("Tahoma", Font.PLAIN, 12));
        scrollPane = new JScrollPane(table);       
        northLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        northPanel.add(northLabel);
        in.add(northPanel);
        centerPanel.setLayout(new BorderLayout());
        printButton = new JButton("print concerte");
        printButton.setToolTipText("Print");
        printButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        centerPanel.add(printButton, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.setBorder(BorderFactory.createTitledBorder("Items:")); 
        in.add(centerPanel);
           in.setVisible(true);
           in.setSize(600, 600);
        table.addMouseListener(new MouseListener() {
        public void mouseReleased(MouseEvent e) {
        }
        @Override
        public void mousePressed(MouseEvent e) {
            int index = table.getSelectedRow();
            String id =   tableModel.getValueAt(index, 0).toString();
            String s = tableModel.getValueAt(index, 1).toString();
            String d = tableModel.getValueAt(index, 2).toString();
            String t = tableModel.getValueAt(index, 3).toString();
            System.out.println(id +" "+s+" "+d+" "+t);
           BookBilete b = new BookBilete(id,s,d,t);
        }
        @Override
        public void mouseExited(MouseEvent e) {
        }
        @Override 
        public void mouseEntered(MouseEvent e) {
        }
        @Override
        public void mouseClicked(MouseEvent e) {
        }
    });          
        }    
     }
    public static void main(String s[])  
    {  
        new SelectConcert();      
    }     
}