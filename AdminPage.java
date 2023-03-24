package ProiectFinalP3;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;



/**
 * 
 *  @author Stan Mihnea
 *  
 *  clasa pentru pagina de admin
 */
public class AdminPage implements ActionListener {

    JDesktopPane desktop;
    JFrame f;
    JPanel panel, top_panel;
    JLabel l = new JLabel();
    JMenu conert, persoane, logout, exit;
    JMenuItem addConcert, viewConcerte, removeConcerte, updateConcerte, viewCustomers, exit_admin, logout_admin;

    public AdminPage() {
        f = new JFrame();
        panel = new JPanel();
        top_panel = new JPanel();
        JMenuBar mb = new JMenuBar();
        conert = new JMenu("Concerte");
        persoane = new JMenu("");
        logout = new JMenu("Logout");
        exit = new JMenu("Exit");
        mb.setBackground(Color.blue);
        conert.setForeground(Color.white);
        persoane.setForeground(Color.white);
        logout.setForeground(Color.white);
        exit.setForeground(Color.white);
        addConcert = new JMenuItem("Add");
        addConcert.setBackground(Color.WHITE);
        viewConcerte = new JMenuItem("View Concerts");
        removeConcerte = new JMenuItem("Remove Concerts");
        updateConcerte = new JMenuItem("Update Concerts");
        exit_admin = new JMenuItem("Exit");
        logout_admin = new JMenuItem("Logout");
        viewCustomers = new JMenuItem("View All Customers");
        desktop = new JDesktopPane();
        f.setContentPane(desktop);
        conert.add(addConcert);
        conert.add(viewConcerte);
        conert.add(removeConcerte);
        conert.add(updateConcerte);
        persoane.add(viewCustomers);
        exit.add(exit_admin);
        logout.add(logout_admin);
        mb.add(conert);
        mb.add(persoane);
        mb.add(logout);
        mb.add(exit);
        f.setJMenuBar(mb);
        addConcert.addActionListener(this);
        viewConcerte.addActionListener(this);
        removeConcerte.addActionListener(this);
        updateConcerte.addActionListener(this);
        viewCustomers.addActionListener(this);
        exit_admin.addActionListener(this);
        logout_admin.addActionListener(this);

        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setTitle("Welcome ");
        f.setSize(300, 300);
        f.setVisible(true);
    }

    /**
     *  apelarea functiilor de admin
     */
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addConcert) {

            AddConcert a = new AddConcert();
            desktop.add(a);

        }
        if (e.getSource() == viewConcerte) {
            ViewConcert v = new ViewConcert();
            desktop.add(v);
        }
        if (e.getSource() == removeConcerte) {
            RemoveConcerte r = new RemoveConcerte();
            desktop.add(r);
        }
        if (e.getSource() == updateConcerte) {
            UpdateConcert u = new UpdateConcert();
            desktop.add(u);
        }
        if (e.getSource() == logout_admin) {
            new Registration();
            f.dispose();

        }
        if (e.getSource() == exit_admin) {
            f.dispose();

        }
    }

}