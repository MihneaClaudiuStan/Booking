package ProiectFinalP3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

/**
 * 
 *  @author Stan Mihnea
 *  
 *  clasa pentru adaugare a concertelor din pagina de administrator direct in baza de date
 */
public class AddConcert extends JInternalFrame implements ActionListener {
    JPanel p;
    JLabel Nume, Oras, Nrlocuri,Pretb;
    JTextField tf_nume, tf_oras, tf_nrlocuri,tf_pretb;
    JButton btn_add;
    Connection con;
    public AddConcert() {
        super("Add Concert", true, true, true);
        p = new JPanel();
        Nume = new JLabel("Name : ");
        Oras = new JLabel("Town : ");
        Nrlocuri = new JLabel("Nr Bilete : ");
        Pretb = new JLabel("Pret : ");
        tf_nume = new JTextField();
        tf_oras = new JTextField();
        tf_nrlocuri = new JTextField();
        tf_pretb = new JTextField();
        btn_add = new JButton("Add Concert");
        Nume.setBounds(20, 20, 100, 30);
        Oras.setBounds(20, 60, 100, 30);
        Nrlocuri.setBounds(20, 100, 100, 30);
        Pretb.setBounds(20,140,100,30);
        tf_nume.setBounds(140, 20, 100, 30);
        tf_oras.setBounds(140, 60, 100, 30);
        tf_nrlocuri.setBounds(140, 100, 100, 30);
        tf_pretb.setBounds(140,140,100,30);
        btn_add.setBounds(80, 180, 120, 30);
        p.add(Nume);
        p.add(Oras);
        p.add(Nrlocuri);
        p.add(Pretb);
        p.add(tf_nume);
        p.add(tf_oras);
        p.add(tf_nrlocuri);
        p.add(tf_pretb);
        p.add(btn_add);
        btn_add.addActionListener(this);
        add(p);
        p.setLayout(null);
        setVisible(true);
        setSize(600, 600);
        

    }
    
    /**
     * functie pentru adaugare in baza de date prin apasare de buton
     * 
     */

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_add) {

            int x = 0;
            String Nume= tf_nume.getText();
            String Oras = tf_oras.getText();
            String Nrlocuri = tf_nrlocuri.getText();
            String Pretb = tf_pretb.getText();

            try {
            	Connection con;
            	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bazap3", "root", "");
                PreparedStatement ps = con.prepareStatement("insert into concerte(Nume,Oras,Nrlocuri,Pretb) values(?,?,?,?)");
                ps.setString(1, Nume);
                ps.setString(2, Oras);
                ps.setString(3, Nrlocuri);
                ps.setString(4, Pretb);
                ps.executeUpdate();
                x++;
                if (x > 0) {
                    JOptionPane.showMessageDialog(btn_add, "Concert Adaugat ");
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else {
            JOptionPane.showMessageDialog(btn_add, "Something went Wrong");
        }
    }
}