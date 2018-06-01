package io.fp.vocabularyTrainer.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

//import com.sun.org.apache.bcel.internal.generic.GOTO;

public class VocabularyModel {
	private ArrayList<Word> wordList;
	private int orderNumbers;
	private Random random;

	public VocabularyModel() {
		wordList = new ArrayList<Word>();
		//Add for Start fügt schonmal einen Wörtersatz hinzu. Siehe Methode
		addForStart();
		//Daher auch orderNumbers = 1, da addForStart
		orderNumbers = 1;
		random = new Random();
	}

	public void addNewWordPair(String l1Word, Language l1, String l2Word, Language l2) throws WordException {
		Word word1 = new Word(l1Word, orderNumbers, l1);
		Word word2 = new Word(l2Word, orderNumbers, l2);
		//Die Alternativen WordListen habe ich hinzugefügt, um zu vermeiden, dass die WortListe während des Lesens 
		//erweitert wird(durch add), um eine Exception zu vermeiden. Bei einem Sonderfall schmeißt es momentan dennoch diese
		//Exception, das Programm funktioniert aber einwandfrei.
		ArrayList<Word> alternativeWordList = wordList;
		ArrayList<Word> alternativeWordList2 = new ArrayList<>();
		//Hier verhindere ich, dass zweimal das selbe Wort eingegeben wird.
		if (l1Word.equals(l2Word)) {
			throw new WordException("Das Wort ist bereits vorhanden");

		}
		for (Word word : alternativeWordList) {
			//Wenn bereits ein Wort in der WörterListe ist, soll es nicht hinzugefügt werden,
			//dafür aber das andere, da es eventuell eine andere Sprache ist. 
			// Gibt man momentan dasselbe nochmals ein, so wird das zweite Wort nochmals eingespeichert.
			//Das muss noch behoben werden
			//Bsp.Hallo-> Hello und Hallo->Hello. Zweimal Hello 
			if (word.getWord().equals(word1.getWord())) {
				word2.setOrderNumber(word1.getOrderNumber());
				wordList.add(word2);
            //Das selbe nur für Word2 bereits enthalten. Vielleicht sind noch nicht alle Bedingugen geprüft,
				// Hier müsste man nochmal das ganze überprüfen und ein bisschen mit dem Programm spielen.
			} else if (word.getWord().equals(word2.getWord())) {
				word1.setOrderNumber(word2.getOrderNumber());
				wordList.add(word1);

			}
		}
		//Hier wird die nun aktualisierte WordList der zweiten alternativenListe hinzugefügt
		alternativeWordList2 = wordList;
		//Wenn bis jetzt noch nicht hinzugefügt wurde, sind die beiden alternativen Listen gleich.
		//Dann werden beide Wörter hinzugefügt.
		if (alternativeWordList.equals(alternativeWordList2)) {
			wordList.add(word1);
			wordList.add(word2);
			orderNumbers++;
		}

	}

	//Hat momentan keine bedeutung
	public void deleteWordList() {
		Iterator i = wordList.iterator();
		while (i.hasNext()) {
			wordList.remove(i.next());
		}

	}

	//Getter für die WordList
	public ArrayList<Word> getWordList() {
		return wordList;
	}
 //Random Generator. Stellt über While Schleife sicher, dass das Wort nur von der eingegeben Language ist.
	// Es wird solange generiert, bis Word und language stimmen. 
	public Word getWordRandom(Language language) {
		boolean rightLanguage = false;
		int index = random.nextInt(wordList.size());
		Word word = wordList.get(index);
		while (rightLanguage == false) {
			if (word.getLanguage().equals(language)) {
				rightLanguage = true;
			} else {
				index = random.nextInt(wordList.size());
				word = wordList.get(index);
			}
		}

		return word;
	}
  // Hier wird ein Wort zurueckgegeben, Ueber den Wort Namen
	public Word getWord1(String wordName) {
		Word word = null;
		for (Word word1 : wordList) {
			if (word1.getWord().equals(wordName)) {
				word = word1;
			}

		}
		return word;
	}
// Vergleicht OrderNumbers
	public boolean compareOrderNumbers(int number1, int number2) {
		boolean compare = false;
		if (number1 == number2) {
			compare = true;
		}
		return compare;
	}
//Bereits oben beschrieben
	private void addForStart() {
		wordList.add(new Word("Hallo", 0, Language.GERMAN));
		wordList.add(new Word("Hello", 0, Language.ENGLISH));
		wordList.add(new Word("Ave", 0, Language.LATIN));
		wordList.add(new Word("Bonjour", 0, Language.FRENCH));

	}
//Gibt die WortListe als String Liste wieder. Macht sie damit lesbar.
	public String wordListToString() {
		ArrayList<String> wordNames = new ArrayList<>();
		for (Word word : wordList) {
			wordNames.add(word.getWord());
		}
		return wordNames.toString();
	}

}
