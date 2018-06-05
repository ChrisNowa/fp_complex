package io.fp.vocabularyTrainer.model;

import java.io.Serializable; 
import java.util.ArrayList;

public class Word implements Serializable{
 /**
	 * 
	 */
	private static final long serialVersionUID = 5828823486941127715L;
//Klasse für die Wörter. Ist denke ich selbsterklärend.
private String word;
private ArrayList<Integer> orderNumbers;
private Language language;


public Word(String word, Integer orderNumber, Language language) {
	this.word = word;
	orderNumbers = new ArrayList<>();
	orderNumbers.add(orderNumber);
	this.language = language;
}

public String getWord() {
	return word;
}

public void setWord(String word) {
	this.word = word;
}

public ArrayList<Integer> getOrderNumbers() {
	return orderNumbers;
}

public Language getLanguage() {
	return language;
}

public void setLanguage(Language language) {
	this.language = language;
}



	
}
