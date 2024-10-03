package com.siruriterminale;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;

class Siruri{
	private final String COMP = ">";
	private final String DPUNCTE = ":";
	private Vector<String> T = new Vector<String>();
	private Vector<String> N = new Vector<String>();
	private Vector<String> S = new Vector<String>();
	private Vector<String> P = new Vector<String>();
	
	Siruri(){
		try {
			File fisier = new File("C:\\Users\\nxg06737\\eclipse-workspace-new\\SiruriTerminale\\com\\siruriterminale\\multimi.txt");
			Scanner cititor = new Scanner(fisier);
			int counter = 0;
			while(cititor.hasNextLine()) {
				String linie = cititor.nextLine();
					switch (counter) {
					case 0: {
						String[] a = linie.split("(?!^)");
						for (int i = 0; i < a.length; i++) {
							T.add(a[i]);
						}
						break;
					}
					case 1: {
						String[] a = linie.split("(?!^)");
						for (int i = 0; i < a.length; i++) {
							N.add(a[i]);
						}
						break;
					}
					case 2: {
						String[] a = linie.split("(?!^)");
						for (int i = 0; i < a.length; i++) {
							S.add(a[i]);
						}
						break;
					}
					default:
						P.add(String.valueOf(linie.charAt(0)));
						P.add(linie.substring(linie.indexOf(COMP) + 1, linie.length()));
					}
				counter++;
			}
			cititor.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String returneazaProductia(String a) {
		for (int i = 0; i < P.size(); i++) {
			String comp = P.get(i);
			if(a.equals(comp)){
				return P.get(i+1);
			}
		}

		return null;
	}
	
	public String startProductie(String termenNeterminal) {
		if(!P.contains(termenNeterminal)) {
			System.out.println("Productia ceruta nu exista");
			return null;
		}
		String sirNou = returneazaProductia(termenNeterminal);
		continuaProductia(sirNou);
		
		return "";
	}
	
	public void continuaProductia(String sirNou) {
		if(verificaTerminal(sirNou)) {
			System.out.println("Sirul este terminal");
		};
		System.out.println("Sirul neterminal");
		
	}

	private boolean verificaTerminal(String sirNou) {
		Boolean []sirTerminal = new Boolean[sirNou.length()];
		Arrays.fill(sirTerminal, false);
		
		//verificare daca sirul contine doar termeni terminali
		for (int i = 0; i < sirNou.length(); i++) {
			String a = String.valueOf(sirNou.charAt(i));
			for (int j = 0; j < T.size(); j++) {
				if(a.equals(T.get(j))) {
					sirTerminal[i] = true;
				}
			}
		}
		for(Boolean b : sirTerminal) {
			if(b == false) { return false; }
		}
		return true;
	}
}

public class Program {
	public static void main(String[] args) {
		Siruri siruri = new Siruri();
		siruri.startProductie("S");
		
	}

}
