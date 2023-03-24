package ProiectFinalP3;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 * 
 *  @author Stan Mihnea
 *  
 *  clasa pentru vederea listei de concerte valabile apelata din pagina de administrator
 */
public class ViewConcert extends JInternalFrame {

    private JPanel northPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private JLabel northLabel = new JLabel("THE LIST FOR THE ITEMS");
    private JButton printButton;
    private JTable table;
    private TableColumn column = null;
    private JScrollPane scrollPane;

    private ResultSetTableModel tableModel;
    private Connection DATABASE_URL = Conn.getConnection();
    
    private static final String DEFAULT_QUERY = "select * from Concerte";

    public ViewConcert() {
        super("All Concerts", true, true, true);
        Container cp = getContentPane();
        try {
            tableModel = new ResultSetTableModel(/*JDBC_DRIVER,*/DATABASE_URL, DEFAULT_QUERY);
            try {
                tableModel.setQuery(DEFAULT_QUERY);
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
        cp.add("North", northPanel);
        centerPanel.setLayout(new BorderLayout());
        printButton = new JButton("print concerts");
        printButton.setToolTipText("Print");
        printButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
        centerPanel.add(printButton, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.setBorder(BorderFactory.createTitledBorder("Items:"));
        cp.add("Center", centerPanel);
        
        printButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Thread runner = new Thread() {
					public void run() {
						try {
							PrinterJob prnJob = PrinterJob.getPrinterJob();
							prnJob.setPrintable(new PrintingConcert(DEFAULT_QUERY, DATABASE_URL));
							if (!prnJob.printDialog())
								return;
							setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
							prnJob.print();
							setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
						}
						catch (PrinterException ex) {
							System.out.println("Printing error: " + ex.toString());
						}
					}
				};
				runner.start();
			}
		});
        setVisible(true);
        pack();
    }

    public static void main(String s[]) {
        System.out.println("connection" + Conn.getConnection());

        new SelectConcert();

    }

}