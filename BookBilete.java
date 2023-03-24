package ProiectFinalP3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JInternalFrame;
import javax.swing.*;

/**
 * 
 *  @author Stan Mihnea
 *  
 *  clasa pentru cumparare de bilete
 */
public class BookBilete extends JFrame implements ActionListener {

    JLabel customer_name, nr_bilete, pretb;
    JTextField txt_c_name;
    JPanel seat_panel;
    JPanel p;
    JLabel nume, oras, nrbilete, numep, pret;
    JTextField tf_nume, tf_oras, tf_pretb, tf_numep, tf_pret;
    JButton btn_book;
    String ID;

    public BookBilete(String id, String Oras, String Nume, String totalseats) {
        //super("Book Ticket", true, true, true);
        p = new JPanel();
        ID = id;
        nume = new JLabel("Nume");
        nume.setText(Oras);
        oras = new JLabel("Oras ");	
        oras.setText(Nume);
        numep = new JLabel("Nume cumparator");
        nrbilete = new JLabel(" Numar Bilete ");
        pret = new JLabel("Price : ");
        tf_nume = new JTextField();
        tf_numep = new JTextField();
        tf_nume.setText(Oras);
        tf_oras = new JTextField();
        tf_oras.setText(Nume);
        tf_pretb = new JTextField();
        tf_pret = new JTextField();
        String y = "1000";
        tf_pret.setText(y);
        btn_book = new JButton("Ia bilet");
        nume.setBounds(20, 20, 100, 30);
        oras.setBounds(20, 60, 100, 30);
        nrbilete.setBounds(20, 100, 100, 30);
        numep.setBounds(20, 140, 100, 30);
        pret.setBounds(20, 180, 100, 30);
        tf_nume.setBounds(140, 20, 100, 30);
        tf_oras.setBounds(140, 60, 100, 30);
        tf_pretb.setBounds(140, 100, 100, 30);
        tf_numep.setBounds(140, 140, 100, 30);
        tf_pret.setBounds(140, 180, 100, 30);

        btn_book.setBounds(80, 220, 120, 30);
        p.add(nume);
        p.add(oras);
        p.add(nrbilete);
        p.add(tf_nume);
        p.add(tf_oras);
        p.add(tf_pretb);
        p.add(btn_book);
        p.add(numep);
        p.add(tf_numep);
        p.add(pret);
        p.add(tf_pret);
        btn_book.addActionListener(this);
        add(p);
        p.setLayout(null);
        setVisible(true);
        setSize(600, 600);
        setVisible(true);
        setSize(600, 600);
    }
/**
 *  functie de adaugare a biletelor in baza de date dupa cumparare
 */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_book) {

            int x = 0;
            String Pret = tf_pret.getText();
            String Nrbilete = tf_pretb.getText();
            String Numep = tf_numep.getText();

            try {
            	Connection con;
            	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bazap3", "root", "");
                PreparedStatement ps = con.prepareStatement("insert into bilete(ConcertId,Nrbilete,Numep,Pret) values(?,?,?,?)");
                ps.setString(1, ID);
                ps.setString(2, Nrbilete);
                ps.setString(3, Numep);
                ps.setString(4, Pret);
                ps.executeUpdate();
                x++;
                if (x > 0) {
                    JOptionPane.showMessageDialog(btn_book, "Bilet luat");
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else {
            JOptionPane.showMessageDialog(btn_book, "Eroare");
        }
    }
}
