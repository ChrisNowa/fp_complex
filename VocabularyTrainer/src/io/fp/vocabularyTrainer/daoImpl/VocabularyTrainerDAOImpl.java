package io.fp.vocabularyTrainer.daoImpl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;
import io.fp.vocabularyTrainer.dao.VocabularyTrainerDao;
import io.fp.vocabularyTrainer.model.Word;

public class VocabularyTrainerDAOImpl implements VocabularyTrainerDao {

	private String wordBookFilePath;
	private String highScoreFilePath;
	
	public VocabularyTrainerDAOImpl(String wordBookFilePath, String highScoreFilePath ) {
		this.wordBookFilePath = wordBookFilePath;
		this.highScoreFilePath = highScoreFilePath;	
	}
	
	@Override
	public ArrayList<Word> createWortBook() throws IOException {
		
		FileInputStream fileIn1= new FileInputStream(wordBookFilePath);

		if (fileIn1.read() != -1) {
			FileInputStream fileIn2 = new FileInputStream(wordBookFilePath);
			ObjectInputStream objIn = new ObjectInputStream(fileIn2);

			try {
				ArrayList<Word> wordBook = (ArrayList<Word>) objIn.readObject();
				objIn.close();

				if (wordBook != null) {

					throw new IOException("Es gibt schon einen Store");
				}
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}
		}
		ArrayList<Word> newWordBook = new ArrayList<Word>();
		FileOutputStream fileOut = new FileOutputStream(wordBookFilePath);
		ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
		objOut.writeObject(newWordBook);
		objOut.close();
		fileIn1.close();
		return newWordBook;
	}

	@Override
	public ArrayList<Word> readWortBook() throws IOException {
		FileInputStream fileIn = new FileInputStream(wordBookFilePath);
		ObjectInputStream in = new ObjectInputStream(fileIn);

		ArrayList<Word> wordBook = null;
		try {
			wordBook = (ArrayList<Word>) in.readObject();
			if (wordBook == null) {
				in.close();
				throw new IOException("Es gibt keinen Store");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		in.close();

		return wordBook;
		
	}

	@Override
	public void updateWortBook(ArrayList<Word> book) throws IOException {
		FileOutputStream fileIn = new FileOutputStream(wordBookFilePath);
		ObjectOutputStream in = new ObjectOutputStream(fileIn);
		in.writeObject(book);
		in.close();
		
	}

	@Override
	public void deleteWortBook() throws IOException {
		FileOutputStream out = new FileOutputStream(wordBookFilePath);
		out.close();
		
		
	}

	@Override
	public SortedMap<String, Integer> createHighScore() throws IOException {
		
		FileInputStream fileIn1= new FileInputStream(highScoreFilePath);

		if (fileIn1.read() != -1) {
			FileInputStream fileIn2 = new FileInputStream(highScoreFilePath);
			ObjectInputStream objIn = new ObjectInputStream(fileIn2);

			try {
				TreeMap<String,Integer> highScore = (TreeMap<String,Integer>) objIn.readObject();
				objIn.close();

				if (highScore != null) {

					throw new IOException("Es gibt schon einen Store");
				}
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}
		}
		TreeMap<String,Integer> newHighScore = new TreeMap<String,Integer>();
		FileOutputStream fileOut = new FileOutputStream(highScoreFilePath);
		ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
		objOut.writeObject(newHighScore);
		objOut.close();
		fileIn1.close();
		return newHighScore;
	}
	

	@Override
	public SortedMap<String, Integer> readHighScore() throws IOException {
		FileInputStream fileIn = new FileInputStream(highScoreFilePath);
		ObjectInputStream in = new ObjectInputStream(fileIn);

		TreeMap<String,Integer> highScore = null;
		try {
			highScore = (TreeMap<String, Integer>) in.readObject();
			if (highScore == null) {
				in.close();
				throw new IOException("Es gibt keinen Store");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		in.close();

		return highScore;
		
	}

	@Override
	public void updateHighScore(SortedMap<String, Integer> highScore) throws IOException {
		FileOutputStream fileIn = new FileOutputStream(highScoreFilePath);
		ObjectOutputStream in = new ObjectOutputStream(fileIn);
		in.writeObject(highScore);
		in.close();
		
	}

	@Override
	public void deleteHighScore() throws IOException {
		FileOutputStream out = new FileOutputStream(highScoreFilePath);
		out.close();
		
	}

}
