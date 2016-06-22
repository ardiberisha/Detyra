import javax.swing.JOptionPane;
import java.text.*;

public class Detyre {
	public static void main(String[] args){
		
		DecimalFormat f = new DecimalFormat("0.00");
		double shuma=0;
		int n= new Integer(JOptionPane.showInputDialog("Jepni numrin e faqeve: "));
		double[][] matrica = new double[n][n];
		double[][] matrica2=new double[n][n];
		double[] degree = new double[n];
		for(int i = 0;i<n;i++){
			for(int j = 0;j<n;j++){
				int input = new Integer(JOptionPane.showInputDialog("Jepni lidhjet per faqen "+i+" linku "+j+":"));
				matrica[i][j]=input;
				//System.out.print(matrica[i][j]+" ");
			}
			//System.out.println();
		}
		for(int i = 0;i<n;i++){
			for(int j = 0;j<n;j++){
				shuma = shuma+matrica[i][j];
			}
			degree[i]=shuma;
			//System.out.println(degree[i]);
			shuma=0;
		}
		for(int i = 0;i<n;i++){
			for(int j = 0;j<n;j++){
				matrica2[i][j]=(matrica[i][j]/degree[i])*(90/100.0)+0.02;
				System.out.print(f.format(matrica2[i][j])+" ");
			}
			System.out.println();
		}
		
		
	}
}
