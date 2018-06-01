package io.fp.vocabularyTrainer.model;

public class Word {
 //Klasse für die Wörter. Ist denke ich selbsterklärend.
public String word;
public int orderNumber;
public Language language;

public Word(String word, int orderNumber, Language language) {
	this.word = word;
	this.orderNumber = orderNumber;
	this.language = language;
}

public String getWord() {
	return word;
}



public void setWord(String word) {
	this.word = word;
}

public int getOrderNumber() {
	return orderNumber;
}

public void setOrderNumber(int orderNumber) {
	this.orderNumber = orderNumber;
}

public Language getLanguage() {
	return language;
}

public void setLanguage(Language language) {
	this.language = language;
}



	
}
