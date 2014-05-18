package tests;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Modif {

	public static void main(String[] args) {
		
		String chaine="";
		String fichier ="c:/serveur/listepays.txt";
		String sortie ="c:/serveur/sortie.txt";
		
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			FileWriter fw = new FileWriter (sortie);
			BufferedWriter bw = new BufferedWriter (fw);
			PrintWriter fichierSortie = new PrintWriter (bw); 
			String ligne;
			while ((ligne=br.readLine())!=null){
				String[] test = ligne.split(";");
				fichierSortie.println(test[1].trim());
			}
			br.close(); 
			fichierSortie.close();
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
	
		

	}

}
