package com.siruriterminale;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

class TerminalStrings{
	private final String COMP = ">";
	private final String DPUNCTE = ":";
	private Vector<String> T = new Vector<String>();
	private Vector<String> N = new Vector<String>();
	private Vector<String> S = new Vector<String>();
	private Vector<String> P = new Vector<String>();
	
	TerminalStrings(){
		try {
			File f = new File("C:\\Users\\nxg06737\\eclipse-workspace-new\\SiruriTerminale\\com\\siruriterminale\\multimi.txt");
			Scanner reader = new Scanner(f);
			int counter = 0;
			while(reader.hasNextLine()) {
				String line = reader.nextLine();
					switch (counter) {
					case 0: {
						String[] a = line.split("(?!^)");
						for (int i = 0; i < a.length; i++) {
							T.add(a[i]);
						}
						break;
					}
					case 1: {
						String[] a = line.split("(?!^)");
						for (int i = 0; i < a.length; i++) {
							N.add(a[i]);
						}
						break;
					}
					case 2: {
						String[] a = line.split("(?!^)");
						for (int i = 0; i < a.length; i++) {
							S.add(a[i]);
						}
						break;
					}
					default:
						P.add(String.valueOf(line.charAt(0)));
						P.add(line.substring(line.indexOf(COMP) + 1, line.length()));
					}
				counter++;
			}
			reader.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String resultOfProduct(String a) {
		for (int i = 0; i < P.size(); i++) {
			String comp = P.get(i);
			if(a.equals(comp)){
				return P.get(i+1);
			}
		}

		return null;
	}
	
	public String startProduction(String termenNeterminal) {
		if(!P.contains(termenNeterminal)) {
			System.out.println("Productia ceruta nu exista");
			return null;
		}
		String sirNou = resultOfProduct(termenNeterminal);
		continuaProduct(sirNou);
		
		return "";
	}
	
	public void continuaProduct(String sirNou) {
		String sirProdus = new String();
		
		if(verifiyIfTerminal(sirNou)) {
			System.out.println("Sir este terminal");
			return;
		};
		System.out.println("Sir neterminal");

		//creare sir nou
		for(int i = 0; i < sirNou.length();i++) {
			String a = String.valueOf(sirNou.charAt(i));
			String product = resultOfProduct(a);
		}
		
	}

	private boolean verifiyIfTerminal(String newString) {
		Boolean []terminalStringBoolArray = new Boolean[newString.length()];
		Arrays.fill(terminalStringBoolArray, false);
		
		//verificare daca sirul contine doar termeni terminali
		for (int i = 0; i < newString.length(); i++) {
			String a = String.valueOf(newString.charAt(i));
			for (int j = 0; j < T.size(); j++) {
				if(a.equals(T.get(j))) {
					terminalStringBoolArray[i] = true;
				}
			}
		}
		for(Boolean b : terminalStringBoolArray) {
			if(b == false) { return false; }
		}
		return true;
	}
}

public class Program {
	public static void main(String[] args) {
		TerminalStrings sir = new TerminalStrings();
		sir.startProduction("S");
		
	}

}
