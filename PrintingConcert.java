package ProiectFinalP3;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.sql.*;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * 
 *  @author Stan Mihnea
 *  
 *  clasa pentru a printa lista de concerte
 */
public class PrintingConcert extends JDialog implements Printable {
	   
	private Connection connection = null;
	private Statement statement = null;
	private ResultSet resultset = null;

	private JTextArea textArea = new JTextArea();
	private Vector lines;
	public static final int TAB_SIZE = 5;

	public PrintingConcert(String query, Connection url) {
		Container cp = getContentPane();
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 9));
		cp.add(textArea);
		try {
			connection = url;
			statement = connection.createStatement();
			resultset = statement.executeQuery(query);
			textArea.append("=============== Concert Info ===============\n\n");
			while (resultset.next()) {
				textArea.append("Conert Id: " + resultset.getString("ConcertId") + "\n" +
				        "Nume Concert: " + resultset.getString("Nume") + "\n" +
				        "Oras: " + resultset.getString("Oras") + "\n" +
				        "Nr Bilete : " + resultset.getString("Nrlocuri") + "\n" +
				        "Pret: " + resultset.getString("pretb") + "\n\n");
			}
			textArea.append("=============== Concert Info ===============");
			resultset.close();
			statement.close();
			connection.close();
		}
		catch (SQLException SQLe) {
			System.out.println(SQLe.toString());
		}
		setVisible(true);
                setBounds(200,100,400,400);
		pack();
	}

	public int print(Graphics pg, PageFormat pageFormat, int pageIndex) throws PrinterException {
		pg.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());
		int wPage = (int) pageFormat.getImageableWidth();
		int hPage = (int) pageFormat.getImageableHeight();
		pg.setClip(0, 0, wPage, hPage);

		pg.setColor(textArea.getBackground());
		pg.fillRect(0, 0, wPage, hPage);
		pg.setColor(textArea.getForeground());

		Font font = textArea.getFont();
		pg.setFont(font);
		FontMetrics fm = pg.getFontMetrics();
		int hLine = fm.getHeight();

		if (lines == null)
			lines = getLines(fm, wPage);

		int numLines = lines.size();
		int linesPerPage = Math.max(hPage / hLine, 1);
		int numPages = (int) Math.ceil((double) numLines / (double) linesPerPage);
		if (pageIndex >= numPages) {
			lines = null;
			return NO_SUCH_PAGE;
		}
		int x = 0;
		int y = fm.getAscent();
		int lineIndex = linesPerPage * pageIndex;
		while (lineIndex < lines.size() && y < hPage) {
			String str = (String) lines.get(lineIndex);
			pg.drawString(str, x, y);
			y += hLine;
			lineIndex++;
		}
		return PAGE_EXISTS;
	}

	protected Vector getLines(FontMetrics fm, int wPage) {
		Vector v = new Vector();

		String text = textArea.getText();
		String prevToken = "";
		StringTokenizer st = new StringTokenizer(text, "\n\r", true);
		while (st.hasMoreTokens()) {
			String line = st.nextToken();
			if (line.equals("\r"))
				continue;
			if (line.equals("\n") && prevToken.equals("\n"))
				v.add("");
			prevToken = line;
			if (line.equals("\n"))
				continue;

			StringTokenizer st2 = new StringTokenizer(line, " \t", true);
			String line2 = "";
			while (st2.hasMoreTokens()) {
				String token = st2.nextToken();
				if (token.equals("\t")) {
					int numSpaces = TAB_SIZE - line2.length() % TAB_SIZE;
					token = "";
					for (int k = 0; k < numSpaces; k++)
						token += " ";
				}
				int lineLength = fm.stringWidth(line2 + token);
				if (lineLength > wPage && line2.length() > 0) {
					v.add(line2);
					line2 = token.trim();
					continue;
				}
				line2 += token;
			}
			v.add(line2);
		}
		return v;
	}
}