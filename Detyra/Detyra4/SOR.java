
public class SOR {
	public static void main(String[] args) {
		SOR sor = new SOR();
		int number_equation = 3;
		double[][] a = { { 4, 3, 0 }, { 3, 4, -1 }, { 0, -1, 4 } };
		double[] b = { 24, 30, -24 };
		double[] X0 = { 1, 1, 1 };
		double w = 1.25;
		double TOL = 0.0001;
		int maxIterations = 20;
		double[] result = sor.sor(number_equation, a, b, X0, w, maxIterations, TOL);
		for (int i = 0; i < number_equation; i++) {
			System.out.println(i + ". " + result[i]);
		}
	}

	public double Linfinit(double[] x, double[] x0) {
		double max = Math.abs(x[0] - x0[0]);
		for (int i = 1; i < x.length; i++) {
			if (max < Math.abs(x[i] - x0[i])) {
				max = Math.abs(x[i] - x0[i]);
			}
		}
		return max;
	}

	public double[] sor(int n, double a[][], double b[], double x0[], double w, int nrMax, double TOL) {
		double x[] = new double[n];
		int k = 1;
		while (k <= nrMax) {
			for (int i = 0; i < a.length; i++) {
				double sum = 0;
				for (int j = 0; j < a.length; j++) {
					if (j!=i) 			
					{if(j<i){sum = sum + a[i][j]*x0[j];}
					else{sum=sum+a[i][j]*x[j];}}
				}
				x[i] = (1 - w) * x0[i] + (w * (-sum +b[i])) / a[i][i];
				if (Linfinit(x, x0) < TOL) {
					return x;
				}
				x0[i] = x[i];
			}
			k++;
		}
		return x;
	}
}
