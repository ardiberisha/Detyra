import javax.swing.*;

public class DetyreShtepie {

	public double[] Mbushja_e_Vektorit() {
		int n = new Integer(JOptionPane.showInputDialog("Jepni gjatesine e vektorit: "));
		double[] vektori=new double[n];
		for (int i = 0; i < vektori.length; i++) {
			double mbushja = new Double(JOptionPane.showInputDialog("Jepni elementin e " + (i + 1) + " te vektorit"));
			vektori[i] = mbushja;
		}
		return vektori;
	}

	public double[][] Mbushja_e_Matrices() {
		double[][] matrica;
		int n = new Integer(JOptionPane.showInputDialog("Jepni gjatesine e rreshtit: "));
		int m = new Integer(JOptionPane.showInputDialog("Jepni gjatesine e shtylles: "));
		matrica = new double [n][m];
		for (int i = 0; i < matrica.length; i++) {
			for (int j = 0; j < matrica[0].length; j++) {
				double mbushja = new Double(JOptionPane.showInputDialog("Jepni elementin a[" + (i+1) + "][" + (j+1) + "]" + " te matrices"));
				matrica[i][j] = mbushja;
			}
		}
		return matrica;
	}

	public double[] Mbledhja_e_Vektoreve(double[] vektori1, double[] vektori2) {

		double[] vektori = new double[vektori1.length];
		for (int i = 0; i < vektori1.length; i++) {
			vektori[i] = vektori1[i] + vektori2[i];
		}
		return vektori;
	}

	public double[] Zbritja_e_Vektoreve(double[] vektori1, double[] vektori2) {

		double[] vektori = new double[vektori1.length];
		for (int i = 0; i < vektori1.length; i++) {
			vektori[i] = vektori1[i] - vektori2[i];
		}
		return vektori;
	}

	public double Norma_L2(double[] vektori) {
		double rezultati = 0;
		for (int i = 0; i < vektori.length; i++) {
			rezultati = rezultati + Math.pow(vektori[i], 2);
		}
		rezultati = Math.sqrt(rezultati);
		return rezultati;
	}

	public double Norma_LInfinit(double[] vektori) {
		double rezultati = 0;
		for (int i = 0; i < vektori.length; i++) {
			rezultati = Math.max(Math.abs(vektori[i]), rezultati);
		}
		return rezultati;
	}

	public double Distanca_Euklidiane(double[] vektori1, double[] vektori2) {
		double rezultati = 0;
		for (int i = 0; i < vektori1.length; i++) {
			rezultati = rezultati + Math.pow(vektori1[i] - vektori2[i], 2);
		}
		rezultati=Math.sqrt(rezultati);
		return rezultati;
	}

	public double Distanca_Maksimale(double[] vektori1, double[] vektori2) {
		double rezultati = 0;
		for (int i = 0; i < vektori1.length; i++) {
			rezultati = Math.max(Math.abs(vektori1[i] - vektori2[i]), rezultati);
		}
		return rezultati;
	}

	public double Norma_Infinit_Matricore(double[][] matrica) {
		double rezultati = 0;
		double a;
		for (int i = 0; i < matrica.length; i++) {
			a = 0;
			for (int j = 0; j < matrica[0].length; j++) {
				a = a + Math.abs(matrica[i][j]);
			}
			rezultati = Math.max(a, rezultati);
		}
		return rezultati;
	}

	public double Norma_Frobenius(double[][] matrica) {
		double rezultati = 0;
		for (int i = 0; i < matrica.length; i++) {
			for (int j = 0; j < matrica[0].length; j++) {
				rezultati =rezultati+Math.pow(matrica[i][j],2);
			}
		}
		rezultati= Math.sqrt(rezultati);
		return rezultati;
	}

	public static void main(String[] args) {

//		DetyreShtepie a = new DetyreShtepie();
//		double[] vektori_i_pare=a.Mbushja_e_Vektorit();    
//		double[] vektori_i_dyte=a.Mbushja_e_Vektorit();
//		if(vektori_i_pare.length!=vektori_i_dyte.length){
//			System.out.println("Gjatesia e vektoreve duhet te jete e njejte");
//			System.exit(0);
//		}
//		double[][] matrica = a.Mbushja_e_Matrices();    
//		 // Mledhja e vektoreve
//		double[] vektoret_e_mledhur= a.Mbledhja_e_Vektoreve(vektori_i_pare, vektori_i_dyte);
//		for(int i=0;i<vektoret_e_mledhur.length;i++){
//			System.out.print(vektoret_e_mledhur[i]+", ");
//		}
//		// Zbritja e vektoreve
//		double[] vektoret_e_zbritur= a.Zbritja_e_Vektoreve(vektori_i_pare, vektori_i_dyte);
//		for(int i=0;i<vektoret_e_zbritur.length;i++){
//			System.out.print(vektoret_e_zbritur[i]+", ");
//		}
//		System.out.println(a.Norma_L2(vektori_i_pare));   // Norma L2
//		System.out.println(a.Norma_LInfinit(vektori_i_pare));   //Norma L infinit
//		System.out.println(a.Distanca_Euklidiane(vektori_i_pare, vektori_i_dyte));	//Distanca Euklidiane
//		System.out.println(a.Distanca_Maksimale(vektori_i_pare, vektori_i_dyte));	//Distanca Maksimale
//		System.out.println(a.Norma_Infinit_Matricore(matrica)); //Normal Infinit matricore
//		System.out.println(a.Norma_Frobenius(matrica)); //Norma Frobenius
		
	}
}
