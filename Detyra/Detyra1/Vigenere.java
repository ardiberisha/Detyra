import javax.swing.*;
public class Vigenere {
 
	char vigenereTable[][]=new char[26][26]; 
	
	public void createTable(String keyword){
		String row=""; 
		char table[][] = new char[26][26];
		int a=65;
		for(int i=0;row.length()<26;i++)
		{
			if(i<keyword.length()){
				row=row+keyword.charAt(i);
			}	
			else{
				if(row.contains(""+(char)a))
					a++;
				else
					{row=row+(char)a;
					a++;}
					
			}
		}
		for(int i=0;i<26;i++){
			int k=i; 
			for(int j=0;j<26;j++){
				table[i][j]=row.charAt(k++);
				if(k>25)
					k=0;
			}
		}
		
		vigenereTable=table;
	}
	
	public void encrypt(String text,String secret){
		String encrypted="";
		String temporary = "";int k=0;
		for(int i =0;i<text.length();i++){
			temporary=temporary+secret.charAt(k);
			if(k==secret.length()-1)
				k=0;
			else k++;
		}

		for(int i=0;i<text.length();i++){
			encrypted = encrypted + vigenereTable[(int)(text.charAt(i))-65][(int)(temporary.charAt(i))-65];
		}
		
		System.out.println(text);
		System.out.println(encrypted);
	}
	
	public void decrypt(String ciphertext,String secret){
		String decrypted="";
		String temporary = "";int k=0;
		for(int i =0;i<ciphertext.length();i++){
			temporary=temporary+secret.charAt(k);
			if(k==secret.length()-1)
				k=0;
			else k++;
		}

		for(int i=0;i<ciphertext.length();i++){
			int row = (int)(temporary.charAt(i)-65);
				
				for(int j=0;j<26;j++){
					if(vigenereTable[row][j]==ciphertext.charAt(i))
					{decrypted =decrypted + (char)(j+65);
					break;
					}
		         }
		}
		System.out.println(ciphertext);
		System.out.println(decrypted);
	}
	
	public static void main(String[] args){
		Vigenere a = new Vigenere();
		String keyword = JOptionPane.showInputDialog("Shkruaj fjalen kyqe per gjenerimin e tabeles se Vineger-it").toUpperCase();
		String text = JOptionPane.showInputDialog("Shkruaj tekstin qe doni te enkriptoni /dekriptoni").toUpperCase();
		String secret = JOptionPane.showInputDialog("Shkruaj fjalen sekrete(qelesin)").toUpperCase();
		String encrypt_or_decrypt =  JOptionPane.showInputDialog("E - Enkripto : D - Dekripto").toUpperCase();
		a.createTable(keyword);
		if(encrypt_or_decrypt.equals("E"))
			a.encrypt(text,secret);
		else if(encrypt_or_decrypt.equals("D")){}
			a.decrypt(text,secret);
	}
}
