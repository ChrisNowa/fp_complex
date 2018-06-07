package io.fp.vocabularyTrainer.model;

import java.io.Serializable; 
import java.util.ArrayList; 
import java.util.Iterator;
import java.util.Random;

// Was macht dieser Import? Wenn ich Ihn auskommentiere, funktioniert die Logik bei einer Falscheingabe nicht
// Lt. Exception in Zeile 106 bei der UI
// import com.sun.org.apache.bcel.internal.generic.GOTO;
//Bei mir kommt der Import nicht vor
public class VocabularyModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3040038594094679081L;
	private ArrayList<Word> wordList;
	private Integer orderNumbers;
	private Random random;
	private Integer counter;

	public VocabularyModel() {
		wordList = new ArrayList<Word>();
		// Add for Start fügt schonmal einen Woertersatz hinzu. Siehe Methode
		addForStart();
		// Daher auch orderNumbers = 1, da addForStart
		orderNumbers = 1;
	    random = new Random();
		counter = 0;
	}

	public void addNewWordPair(String l1Word, Language l1, String l2Word, Language l2) throws WordException {
		Word word1 = new Word(l1Word, orderNumbers, l1);
		Word word2 = new Word(l2Word, orderNumbers, l2);
		// Die Alternativen WordListen habe ich hinzugefuegt, um zu vermeiden, dass die
		// WortListe waehrend des Lesens
		// erweitert wird(durch add), um eine Exception zu vermeiden. Bei einem
		// Sonderfall schmeißt es momentan dennoch diese
		// Exception, das Programm funktioniert aber einwandfrei. Nun mit der Methode addAll behandelt.
		ArrayList<Word> alternativeWordList = new ArrayList<>();
		alternativeWordList.addAll(wordList);
		ArrayList<Word> alternativeWordList2 = new ArrayList<>();
		// Hier verhindere ich, dass zweimal das selbe Wort eingegeben wird.
		
		if (l1Word.equals(l2Word)) {
			throw new WordException("Das Wort ist bereits vorhanden");

		}
	
		for (Word word : alternativeWordList) {
		
		if(word.getWord().equals(word1.getWord())){
			for(Word wort: alternativeWordList) {
				if(wort.getWord().equals(word2.getWord())) {
					throw new WordException("Beide Woerter bereits enthalten");
				}
			}
		}
			
		if (word.getWord().equals(word1.getWord())) {
				word1.getOrderNumbers().add((orderNumbers));
				wordList.add(word2);
				orderNumbers ++;
			} else if (word.getWord().equals(word2.getWord())) {
				word2.getOrderNumbers().add((orderNumbers));
				wordList.add(word1);
               orderNumbers++;
			}
		}
		// Hier wird die nun aktualisierte WordList der zweiten alternativenListe
		// hinzugefuegt
		alternativeWordList2.addAll(wordList);
		// Wenn bis jetzt noch nicht hinzugefuegt wurde, sind die beiden alternativen
		// Listen gleich.
		// Dann werden beide Wörter hinzugefuegt.
		if (alternativeWordList.equals(alternativeWordList2)) {
			wordList.add(word1);
			wordList.add(word2);
			orderNumbers++;
		}

	}

	

	// Hat momentan keine bedeutung
	public void deleteWordList() {
		Iterator<Word> i = wordList.iterator();
		while (i.hasNext()) {
			wordList.remove(i.next());
		}

	}

	// Getter für die WordList
	public ArrayList<Word> getWordList() {
		return wordList;
	}

	// Random Generator. Stellt über While Schleife sicher, dass das Wort nur von
	// der eingegeben Language ist.
	// Es wird solange generiert, bis Word und language stimmen.
	public Word getWordRandom(Language language) {
		boolean rightLanguage = false;
		int index =  random.nextInt(wordList.size());
		Word word =  wordList.get(index);
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

	// Vergleicht OrderNumbers. Jetzt sind es zwei ArrayListen. Man schaut die Nummern der ersten Liste einzeln an und überprueft,
	// ob die zweite ArrayListe eine dieser Nummern enthaelt.Tut sie dass, so ist die Ueberprüfung richtig.
	public boolean compareOrderNumbers(ArrayList<Integer> arrayList, ArrayList<Integer> arrayList2) {
		boolean compare = false;
		for (int number : arrayList) {
			if (arrayList2.contains(number)) {
				compare = true;
			}
		}
		return compare;
	}
	
	public boolean compareLanguage(Language l1, Language l2 ) {
		boolean compare = false;
		if(l1.equals(l2)) {
			compare = true;
		}
		return compare; 
		
	}
	

	// Bereits oben beschrieben
	private void addForStart() {
		wordList.add(new Word("Hallo", 0, Language.GERMAN));
		wordList.add(new Word("Hello", 0, Language.ENGLISH));
		wordList.add(new Word("Ave", 0, Language.LATIN));
		wordList.add(new Word("Bonjour", 0, Language.FRENCH));

	}

	// Gibt die WortListe als String Liste wieder. Macht sie damit lesbar.
	public String wordListToString() {
		ArrayList<String> wordNames = new ArrayList<>();
		for (Word word : wordList) {
			wordNames.add(word.getWord());
		}
		return wordNames.toString();
	}

	// Counter Logik: Beim Trainieren soll im Label mitgez�hlt werden, wieviele
	// richtige
	// Antworten man am St�ck schafft.

	public void counter(boolean choice) {
		if (choice == true) {
			counter++;
		}
		if (choice == false) {
			counter = 0;
		}
	}

	public String getCounter() {
		return "" + counter;
	}

}
