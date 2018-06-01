package io.fp.vocabularyTrainer.model;

import java.util.ArrayList;

public class Word {
 //Klasse für die Wörter. Ist denke ich selbsterklärend.
private String word;
private ArrayList<Integer> orderNumbers;
private Language language;


public Word(String word, int oderNumber, Language language) {
	this.word = word;
	orderNumbers = new ArrayList<>();
	orderNumbers.add(oderNumber);
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
