import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class PageRank extends JApplet implements ActionListener {
	static int width = 500;
	static int height = 500;
	static int nr_pages;
	static File input;
	static FileReader fileReader;
	static BufferedReader bufferin;
	boolean[][] pageConn;
	double[][] transitionMatrix;
	double[] pageRankVector;
	JButton butoni;
	ArrayList<String> finalResult = new ArrayList<String>();
	JTextField inputSearch;
	String[] output;

	public void paint(Graphics g) {

		super.paint(g);
		this.setSize(new Dimension(width, height));
		Container c = getContentPane();
		c.setLayout(new BorderLayout(3, 1));
		c.setBackground(Color.GRAY);
		inputSearch = new JTextField();
		JButton butoni = new JButton("Kerko");
		butoni.addActionListener(this);
		inputSearch.setEditable(true);
		inputSearch.setEnabled(true);
		JPanel panel = new JPanel();
		panel.removeAll();
		panel.setBackground(Color.WHITE);
		g.clearRect(0, 50, width, 400);
		for (int i = 0; i < output.length; i++) {
			if (output[i] != null)
				g.drawString(output[i], 10, 50 + 20 * i);
		}

		inputSearch.setBackground(Color.WHITE);
		inputSearch.setPreferredSize(new Dimension(100, 30));
		c.add(panel, BorderLayout.CENTER);
		c.add(inputSearch, BorderLayout.NORTH);
		c.add(butoni, BorderLayout.SOUTH);
	}

	public void init() {

		readConnections();
		transitionMatrix = transition();
		importance();
		output = new String[nr_pages];

	}

	public String[] generate(String search) {
		String[] site = { "ballina", "aksion", "aventure", "dokumentare", "scifi", "komedi", "indianajones",
				"meninblack", "earth", "starwars" };

		// gjej indeksin e faqes se kerkuar
		int index = 0;
		for (int i = 0; i < site.length; i++) {
			if (search.equals(site[i]))
				index = i;
		}

		// gjej indeksat e faqeve te lidhura per faqen e kerkuar
		ArrayList<Integer> connectedTo = new ArrayList<Integer>();
		for (int i = 0; i < nr_pages; i++) {
			if (pageConn[i][index])
				connectedTo.add(i);
		}

		// konverto arraylisten ne array per faqet e sortimin e faqeve te
		// lidhura
		Integer[] connectedToArray = new Integer[connectedTo.size()];
		connectedToArray = connectedTo.toArray(connectedToArray);

		// merr vlerat e rendesisse per faqet e lidhura
		double[] connected_values = new double[connectedTo.size()];
		for (int i = 0; i < connected_values.length; i++) {
			connected_values[i] = pageRankVector[connectedTo.get(i)];
		}

		// sortimi i indekseve te faqeve sipas rendesise
		for (int i = 0; i < connectedToArray.length - 1; i++) {
			for (int j = i + 1; j < connectedToArray.length; j++) {
				if (connected_values[i] < connected_values[j]) {
					int c = connectedToArray[i];
					connectedToArray[i] = connectedToArray[j];
					connectedToArray[j] = c;
					double temp = connected_values[i];
					connected_values[i] = connected_values[j];
					connected_values[j] = temp;

				}
			}
		}

		// gjjej indeksat e faqeve qe nuk jane te lidhura per faqen e kerkuar
		ArrayList<Integer> notconnectedTo = new ArrayList<Integer>();
		for (int i = 0; i < nr_pages; i++) {
			if (pageConn[i][index] == false)
				notconnectedTo.add(i);
		}

		// konverto arraylisten ne array per faqet e sortimin e faqeve te
		// lidhura
		Integer[] notconnectedToArray = new Integer[notconnectedTo.size()];
		notconnectedToArray = notconnectedTo.toArray(notconnectedToArray);

		// merr vlerat e rendesisse per faqet e lidhura
		double[] notconnected_values = new double[notconnectedTo.size()];
		for (int i = 0; i < notconnected_values.length; i++) {
			notconnected_values[i] = pageRankVector[notconnectedTo.get(i)];
		}

		// sortimi i indekseve te faqeve jo te lidhura sipas rendesise
		for (int i = 0; i < notconnectedToArray.length - 1; i++) {
			for (int j = i + 1; j < notconnectedToArray.length; j++) {
				if (notconnected_values[i] < notconnected_values[j]) {
					int c1 = notconnectedToArray[i];
					notconnectedToArray[i] = notconnectedToArray[j];
					notconnectedToArray[j] = c1;
					double temp1 = notconnected_values[i];
					notconnected_values[i] = notconnected_values[j];
					notconnected_values[j] = temp1;

				}
			}
		}
		// lista e indekseve te faqeve te lidhura dhe jo te lidhura te
		// rendistura sipas rendesise
		Integer[] list = new Integer[connectedToArray.length + notconnectedToArray.length];
		for (int i = 0; i < list.length; i++) {
			if (i < connectedToArray.length)
				list[i] = connectedToArray[i];
			else
				list[i] = notconnectedToArray[i - connectedToArray.length];
		}
		String[] res = new String[site.length];
		for (int i = 0; i < res.length; i++) {
			res[i] = site[list[i]];
		}
		return res;
	}

	public void importance() {

		double[] distribution = new double[nr_pages];
		for (int i = 0; i < distribution.length; i++) {
			distribution[i] = 1.0 / nr_pages;

		}
		double[][] A = multiply(transitionMatrix, transitionMatrix);
		for (int i = 0; i < 10; i++) {
			A = multiply(A, A);
		}
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length; j++) {
				System.out.print(A[i][j]);
			}
			System.out.println();

		}
		pageRankVector = multiplyMV(A, distribution);
		for (int i = 0; i < pageRankVector.length; i++) {
			System.out.println(pageRankVector[i]);
		}

	}

	public static double[][] multiply(double[][] A, double[][] B) {
		int mA = A.length;
		int nA = A[0].length;
		int mB = B.length;
		int nB = B[0].length;
		if (nA != mB)
			throw new RuntimeException("Shumezimi nuk mund te behet");
		double[][] C = new double[mA][nB];
		for (int i = 0; i < mA; i++)
			for (int j = 0; j < nB; j++)
				for (int k = 0; k < nA; k++)
					C[i][j] += A[i][k] * B[k][j];
		return C;
	}

	public static double[] multiplyMV(double[][] A, double[] x) {
		int m = A.length;
		int n = A[0].length;
		if (x.length != n)
			throw new RuntimeException("Shumezimi nuk mund te behet");
		double[] y = new double[m];
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				y[i] += A[i][j] * x[j];
		return y;
	}

	public void readConnections() {

		// JOptionPane.showMessageDialog(null, "Hello");
		nr_pages = 0;

		try {
			input = new File("lidhjet.txt");
			fileReader = new FileReader(input);
			bufferin = new BufferedReader(fileReader);
		} catch (Exception e) {
			System.out.println("Fajlli nuk u gjet");
		}
		String line;
		int page = -1;
		try {
			while ((line = bufferin.readLine()) != null) {
				// System.out.println(line);

				if (nr_pages == 0) {
					// System.out.println("rreshti i pare");

					nr_pages = new Integer(line);
					pageConn = new boolean[nr_pages][nr_pages];
					for (int i = 0; i < nr_pages; i++)
						for (int j = 0; j < nr_pages; j++)
							pageConn[i][j] = false;
					page++;
					// System.out.println("mbushja e matrices");

				} else {
					for (int i = 0; i < line.length(); i++) {
						if (line.charAt(i) == '/') {
							page++;
						} else {
							int n = new Integer(line.charAt(i) + "");
							pageConn[n][page] = true;
							// System.out.println(page + " " + n + true);
						}

					}

				}

			}
		} catch (Exception e) {
		}
		for (int i = 0; i < pageConn.length; i++) {
			for (int j = 0; j < pageConn.length; j++) {
				if (pageConn[i][j])
					System.out.print("X ");
				else
					System.out.print("O ");

			}
			System.out.println();
		}

	}

	public double[][] transition() {
		DecimalFormat format = new DecimalFormat("0.00");
		double[][] probability = new double[nr_pages][nr_pages];
		double[] faqet = new double[nr_pages];

		for (int i = 0; i < nr_pages; i++) {
			int p = 0;
			for (int j = 0; j < nr_pages; j++) {
				if (pageConn[j][i] == true)
					p++;
			}
			faqet[i] = p;
		}
		for (int j = 0; j < nr_pages; j++) {
			for (int i = 0; i < nr_pages; i++) {
				if (pageConn[i][j] == true)
					probability[i][j] = 1 / faqet[j];
			}
		}
		for (int i = 0; i < probability.length; i++) {
			for (int j = 0; j < probability.length; j++) {
				System.out.print(format.format(probability[i][j]) + "  ");
			}
			System.out.println();
		}

		return probability;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		String search = JOptionPane.showInputDialog("Kerko");
		System.out.println(search);
		// JOptionPane.showInputDialog("Kerko");
		// "aksion";
		// inputSearch.getText().trim().toLowerCase();
		String[] rez = generate(search);
		finalResult.clear();
		finalResult.add(search);
		for (int i = 0; i < rez.length; i++) {
			if (!rez[i].equals(search))
				finalResult.add(rez[i]);
		}
		for (int i = 0; i < finalResult.size(); i++) {

			System.out.println(finalResult.get(i));
		}
		output = finalResult.toArray(output);
		this.repaint();
	}

}