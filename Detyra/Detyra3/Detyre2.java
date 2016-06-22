import java.text.DecimalFormat;

import javax.swing.JOptionPane;

public class Detyre2 {
	public static void main(String[] args) {

		DecimalFormat f = new DecimalFormat("0.00");
		double shuma = 0;
		double a=0;
		int n = new Integer(JOptionPane.showInputDialog("Jepni numrin e faqeve: "));
		double[] vektori = new double[n];
		double[][] matrica = new double[n][n];
		double[][] matrica2=new double[n][n];
		double[] degree = new double[n];
		for (int j = 0; j < n; j++) {
			for (int i = 0;i < n; i++) {
				int input = new Integer(
						JOptionPane.showInputDialog("Shkruani 1-Nese faqja "+j+" ka lidhje ne "+i+"\nShkruani 0-Nese nuk ka lidhje"));
				matrica[i][j] = input;
				// System.out.print(matrica[i][j]+" ");
			}
			// System.out.println();
		}
		for (int j = 0; j < n; j++) {
			for (int i = 0; i < n; i++) {
				shuma = shuma + matrica[i][j];
			}
			degree[j] = shuma;
			//System.out.println(degree[j]);
			shuma = 0;
		}
		for(int j = 0;j<n;j++){
			for(int i = 0;i<n;i++){
				matrica2[i][j]=matrica[i][j]/degree[j];
			}
		}
		for(int i = 0;i<n;i++){
			for(int j = 0;j<n;j++){
				System.out.print(f.format(matrica2[i][j])+" ");
			}
			System.out.println();
		}
		
	}
}
