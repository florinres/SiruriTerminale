package com.siruriterminale;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

class TerminalStrings{
	private final String COMP = ">";
	private Vector<String> T = new Vector<String>();
	private Vector<String> N = new Vector<String>();
	private Vector<String> S = new Vector<String>();
	private Map<String, Vector<String>> P = new HashMap<String, Vector<String>>();
	private String checkSequence = new String();
	int contor =0;
	
	TerminalStrings(){
		try {
			File f = new File("C:\\Users\\rares\\eclipse-workspace\\SiruriTerminale\\com\\siruriterminale\\multimi.txt");
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
						while(reader.hasNextLine()) {
							String key = (String.valueOf(line.charAt(0)));
							String object = (line.substring(line.indexOf(COMP) + 1, line.length()));
							Vector<String> a = new Vector<String>();
							if(P.containsKey(key)) {
								a.addAll(P.get(key));
							}
							a.add(object);
							P.put(key, a);
							line = reader.nextLine();
						}
						break;
					}
				counter++;
			}
			reader.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	//the logic should be changed in order if the same
	public void startProduction() {
		String termOfStart = S.firstElement();
		String resultOfProducts = new String();
		
		while(!productionNotReady(termOfStart)) {
			for(int i = 0; i < termOfStart.length();i++) {
				String currentChar = String.valueOf(termOfStart.charAt(i));
				if(P.containsKey(currentChar)) {
					Vector<String> productions = P.get(currentChar);
					resultOfProducts = resultOfProducts.concat(changeTerm(currentChar, productions, termOfStart));
				}
			}
			termOfStart = resultOfProducts;
			resultOfProducts = "";
		}
	}
	
	//scans for non-terminal terms
	public Boolean productionNotReady(String sequence) {
		for(int i = 0; i < sequence.length(); i++) {
			String compare = String.valueOf(sequence.charAt(i));
			if(N.contains(compare)) {
				return false;
			}
		}
		return true;
	}

	public String changeTerm(String currentChar, Vector<String> productions, String termOfStart) {
		sequenceCheck(termOfStart, productions);
		return termOfStart = termOfStart.replaceAll(currentChar, productions.firstElement());
	}
	
	public void sequenceCheck(String sequence, Vector<String> productions) {
		if(sequence.equals(checkSequence)) {
			productions.remove(0);
		}
		
		checkSequence = sequence;
	}
}

public class Program {
	public static void main(String[] args) {
		TerminalStrings sir = new TerminalStrings();
		sir.startProduction();
	}
}
