package io.fp.vocabularyTrainer.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Highscore {
	private TreeMap<Integer, ArrayList<String>> scores;
	
	
	public Highscore() {
		scores = new TreeMap<Integer, ArrayList<String>>();
		//Evtl muss hier nachher eine If-Else Bedingung fuer das DAO
		//falls eine Liste schon existiert, darf es ja nicht ueberschrieben werden.
	}
	
	public String highScoreToString() {
		int i = 1;
		ArrayList<String> highScoreList = new ArrayList<>();
		
		for (Entry<Integer, ArrayList<String>> entry : scores.entrySet())
				{
				    highScoreList.add(i + ". Platz: " + entry.getKey() + " richtige in Folge von " + entry.getValue() + "\n");
				    i++;
				}
		
//		for(Integer key : scores.keySet()) {
//			highScoreList.add(i + ". Platz: " + key.getKey() + " richtige in Folge von " + key.getValue() + "\n");
//		    i++;
//		}
		
		
		return highScoreList.toString();
		
	}
	
	public void setScore(int anzahl, String name) {
		if(scores.containsKey(anzahl)) {
			// Wenn bereits Namen drin stehen, muss ja nur der neue hinzugefuegt werden,
			// die alten duerfen jedoch nicht geloescht werden. 
			
			// Daher neue Liste die nachher alle Namen hat.
			ArrayList<String> neueNamen = new ArrayList<>();
			// Die Liste mit den bisherigen Namen
			ArrayList<String> alteNamen = scores.get(anzahl);
			
			// Nun werden alle Namen der neuen Liste hinzugefuegt. 
			for(int i = 0; i < alteNamen.size(); i++) {
				neueNamen.add(alteNamen.get(i));
			}
			// Und der neue Name natuerlich auch. 
			neueNamen.add(name);
			
			// Nun wird alles der TreeMap hinzugefuegt:
			scores.put(anzahl, neueNamen);
		} else {
			ArrayList<String> neueNamen = new ArrayList<>();
			neueNamen.add(name);
			scores.put(anzahl, neueNamen);
		}
	}
	
}
