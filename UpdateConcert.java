package ProiectFinalP3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 *  @author Stan Mihnea
 *  
 *  clasa pentru updatarea datelor unui concert valabila doar din pagina de administrator
 */

public class UpdateConcert extends JInternalFrame implements ActionListener {

    JFrame f;
    JPanel p;
    JLabel numec, oras, nrbilete;
    JTextField tf_numec, tf_oras, tf_nrbilete;
    JButton btn_update;
    JLabel id;
    JTextField tf_id;
    JButton btn_search;

    public UpdateConcert() {
        super("Add Concert", true, true, true);
        p = new JPanel();
        numec = new JLabel("Nume : ");
        oras = new JLabel("Oras : ");
        nrbilete = new JLabel(" Locuri : ");
        tf_numec = new JTextField();
        tf_oras = new JTextField();
        tf_nrbilete = new JTextField();
        btn_update = new JButton("Update Concert");
        id = new JLabel("Concert Id : ");
        tf_id = new JTextField();
        btn_search = new JButton("Cauta Concert");
        id.setBounds(20, 20, 100, 30);
        tf_id.setBounds(140, 20, 100, 30);
        btn_search.setBounds(80, 60, 140, 40);
        numec.setBounds(20, 100, 100, 30);
        oras.setBounds(20, 140, 100, 30);
        nrbilete.setBounds(20, 180, 100, 30);
        tf_numec.setBounds(140, 100, 100, 30);
        tf_oras.setBounds(140, 140, 100, 30);
        tf_nrbilete.setBounds(140, 180, 100, 30);
        btn_update.setBounds(80, 220, 100, 40);

        p.add(id);
        p.add(tf_id);
        p.add(btn_search);
        p.add(numec);
        p.add(oras);
        p.add(nrbilete);
        p.add(tf_numec);
        p.add(tf_oras);
        p.add(tf_nrbilete);
        p.add(btn_update);
        btn_update.addActionListener(this);
        btn_search.addActionListener(this);
        add(p);
        p.setLayout(null);
        setVisible(true);
        setSize(600, 600);

    }
    /**
     * 
     * functie de conectare la baza de date si cautarea unui concert dupa ID
     */

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_search) {

            int x = 0;
            String _id = tf_id.getText();

            try {
                Connection con;
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bazap3", "root", "");
                PreparedStatement ps = con.prepareStatement("select * from Concerte where ConcertId = ?");
                ps.setString(1, _id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    tf_numec.setText(rs.getString("Nume"));
                    tf_oras.setText(rs.getString("Oras"));
                    tf_nrbilete.setText(rs.getString("Nrlocuri"));
                }

            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        /**
         * 
         * functia de schimbare a datelor concertului cautat
         */
        if (e.getSource() == btn_update) {

            int x = 0;
            
            String Nume = tf_numec.getText();
            String Oras = tf_oras.getText();
            String Nrlocuri = tf_nrbilete.getText();

            try {
            	Connection con;
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bazap3", "root", "");
                PreparedStatement ps = con.prepareStatement("update concerte set Nume ='" + Nume + "',Oras ='" + Oras + "',Nrlocuri='" + Nrlocuri + "'where ConcertId = " + tf_id.getText());
                ps.executeUpdate();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }
    public static void main(String s[]) {
        new UpdateConcert();
    }
}
