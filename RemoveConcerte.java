package ProiectFinalP3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.*;

/**
 * 
 *  @author Stan Mihnea
 *  
 *  clasa pentru functia de remove din baza de date a concertelor
 */
public class RemoveConcerte extends JInternalFrame implements ActionListener {

    JPanel p;
    JLabel id;
    JTextField tf_id;
    JButton btn_delete;

    public RemoveConcerte() {
        super("Add Concerte", true, true, true);
        //f =  new JFrame();
        p = new JPanel();
        id = new JLabel("Concert Id : ");
        tf_id = new JTextField();
        btn_delete = new JButton("Delete Concert");
        id.setBounds(20, 20, 100, 30);
        tf_id.setBounds(140, 20, 100, 30);
        btn_delete.setBounds(80, 60, 80, 40);
        p.add(id);
        p.add(tf_id);
        p.add(btn_delete);
        btn_delete.addActionListener(this);
        add(p);
        p.setLayout(null);
        setVisible(true);
        setSize(600, 600);
    }
    /**
     * 
     *  functia de stergere a concerelor dupa un anumit ID introdus de noi 
     *  
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn_delete) {
            try {
            	Connection con ;
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bazap3", "root", "");
                PreparedStatement ps = con.prepareStatement("delete from concerte where ConcertId =" + tf_id.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(btn_delete, "Concert Sters ");
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else {
            JOptionPane.showMessageDialog(btn_delete, "Something went Wrong");
        }
    }
}
