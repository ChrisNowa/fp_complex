package io.fp.vocabularyTrainer.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.SortedMap;

import io.fp.vocabularyTrainer.model.Word;

public interface VocabularyTrainerDao {

//Fuer das Woerterbuch	
ArrayList<Word> createWortBook() throws IOException;
	
ArrayList<Word> readWortBook() throws IOException;

void updateWortBook(ArrayList<Word> book) throws IOException;

void deleteWortBook() throws IOException;
	
//Fuer den HighScore
SortedMap<String, Integer> createHighScore() throws IOException;

SortedMap<String, Integer> readHighScore()  throws IOException;

void updateHighScore(SortedMap<String, Integer> highScore) throws IOException;

void deleteHighScore() throws IOException;


}
