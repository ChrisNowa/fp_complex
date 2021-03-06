package io.fp.vocabularyTrainer.model;

import java.io.Serializable; 
import java.util.ArrayList;

public class Word implements Serializable{
	private static final long serialVersionUID = 5828823486941127715L;
//Klasse für die Wörter. 
private String word;
private ArrayList<Integer> orderNumbers = new ArrayList<>();
private Language language;


public Word(String word,int orderNumber, Language language) {
	this.word = word;
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
