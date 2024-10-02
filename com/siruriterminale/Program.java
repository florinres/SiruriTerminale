package com.siruriterminale;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Siruri{
	private final String ARROW = "->";
	private final String DASH = "-";
	private final String COMP = ">";
	private final char DPUNCTE = ':';
	private ArrayList<String> T = new ArrayList<String>();
	private ArrayList<String> N = new ArrayList<String>();
	private ArrayList<String> S = new ArrayList<String>();
	private ArrayList<String> P = new ArrayList<String>();
	
	Siruri(){
		try {
			File fisier = new File("C:\\Users\\nxg06737\\eclipse-workspace-new\\SiruriTerminale\\com\\siruriterminale\\multimi.txt");
			Scanner cititor = new Scanner(fisier);
			while(cititor.hasNextLine()) {
				String linie = cititor.nextLine();
				System.out.print(linie);
						if(linie.contains("T")) {
								String secventa = linie.substring(linie.indexOf(DPUNCTE)+1, linie.length());
								char []secventaArray = secventa.toCharArray();
								for (char c : secventaArray) {
									String a = String.valueOf(c);
									T.add(a);
								}
						}
						if(linie.contains("N")) {
							String secventa = linie.substring(linie.indexOf(DPUNCTE)+1, linie.length());
							char []secventaArray = secventa.toCharArray();
							for (char c : secventaArray) {
								String a = String.valueOf(c);
								N.add(a);
							}
						}
						if(linie.contains("X")) {
							String secventa = linie.substring(linie.indexOf(DPUNCTE)+1, linie.length());
							char []secventaArray = secventa.toCharArray();
							for (char c : secventaArray) {
								String a = String.valueOf(c);
								S.add(a);
							}
						}
						if(linie.contains(ARROW)) {
								String key = linie.substring(0, linie.indexOf(DASH));
								String value = linie.substring(linie.indexOf(COMP)+1, linie.length());
								P.add(key);
								P.add(value);
						}
				}
			cititor.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}

public class Program {
	public static void main(String[] args) {
		Siruri siruri = new Siruri();
	}

}
