package io.fp.vocabularyTrainer.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

// Was macht dieser Import? Wenn ich Ihn auskommentiere, funktioniert die Logik bei einer Falscheingabe nicht
// Lt. Exception in Zeile 106 bei der UI
// import com.sun.org.apache.bcel.internal.generic.GOTO;
//Bei mir kommt der Import nicht vor
public class VocabularyModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3040038594094679081L;
	private ArrayList<Word> wordList;
	private int orderNumber;
	private Random random;
	private Integer counter;
	private Language rememberV1;
	private Language rememberV2;
	private Language rememberD1;
	private Language rememberD2;
	private Word prevWord;
	private Map<Integer, String> scores;

	public VocabularyModel() {
		wordList = new ArrayList<Word>();
		// Add for Start fügt schonmal 3 Woertersatz hinzu. Siehe Methode
		addForStart();
		// Daher auch orderNumbers = 4, da addForStart
		orderNumber = 4;
		random = new Random();
		counter = 0;
		prevWord = null;
		// Highscore Logik
		scores = new TreeMap<Integer, String>();
		// Evtl muss hier nachher eine If-Else Bedingung fuer das DAO
		// falls eine Liste schon existiert, darf es ja nicht ueberschrieben werden.
	}

	public void addNewWordPair(String l1Word, Language l1, String l2Word, Language l2) throws WordException {
		Word word1 = new Word(l1Word, orderNumber, l1);
		Word word2 = new Word(l2Word, orderNumber, l2);
		// Die Alternativen WordListen habe ich hinzugefuegt, um zu vermeiden, dass die
		// WortListe waehrend des Lesens
		// erweitert wird(durch add), um eine Exception zu vermeiden. Bei einem

		ArrayList<Word> alternativeWordList = new ArrayList<>();
		alternativeWordList.addAll(wordList);
		ArrayList<Word> alternativeWordList2 = new ArrayList<>();
		// Hier verhindere ich, dass zweimal das selbe Wort eingegeben wird.

		if (l1Word.equals(l2Word)) {
			throw new WordException("Das Wort ist bereits vorhanden");

		}

		for (Word word : alternativeWordList) {

			if (word.getWord().equals(word1.getWord())) {
				for (Word wort : alternativeWordList) {
					if (wort.getWord().equals(word2.getWord())) {
						throw new WordException("Beide Woerter bereits enthalten");
					}
				}
			}
			if (word.getWord().equals(word1.getWord())) {
				word.getOrderNumbers().add(orderNumber);
				wordList.add(word2);

			}
			if (word.getWord().equals(word2.getWord())) {
				word.getOrderNumbers().add(orderNumber);
				wordList.add(word1);

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

		}

		orderNumber++;
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
		boolean notPrevWord = false;
		int index = random.nextInt(wordList.size());
		Word word = wordList.get(index);
		if (prevWord != null) {
			while ((rightLanguage == false) && (notPrevWord == false)) {
				if ((word.getLanguage().equals(language)) && (!prevWord.equals(word))) {
					rightLanguage = true;
					notPrevWord = true;

				} else {
					index = random.nextInt(wordList.size());
					word = wordList.get(index);
				}
			}
		}

		else {
			while (rightLanguage == false) {
				if (word.getLanguage().equals(language)) {
					rightLanguage = true;

				} else {
					index = random.nextInt(wordList.size());
					word = wordList.get(index);
				}
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

	// Vergleicht OrderNumbers. Jetzt sind es zwei ArrayListen. Man schaut die
	// Nummern der ersten Liste einzeln an und überprueft,
	// ob die zweite ArrayListe eine dieser Nummern enthaelt.Tut sie dass, so ist
	// die Ueberprüfung richtig.
	public boolean compareOrderNumbers(ArrayList<Integer> arrayList, ArrayList<Integer> arrayList2) {
		boolean compare = false;
		for (int number : arrayList) {
			if (arrayList2.contains(number)) {
				compare = true;
			}
		}
		return compare;
	}

	public boolean compareLanguage(Language l1, Language l2) {
		boolean compare = false;
		if (l1.equals(l2)) {
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
		wordList.add(new Word("Haus", 1, Language.GERMAN));
		wordList.add(new Word("House", 1, Language.ENGLISH));
		wordList.add(new Word("Domum", 1, Language.LATIN));
		wordList.add(new Word("Maison", 1, Language.FRENCH));
		wordList.add(new Word("Bruder", 2, Language.GERMAN));
		wordList.add(new Word("Brother", 2, Language.ENGLISH));
		wordList.add(new Word("Fratris", 2, Language.LATIN));
		wordList.add(new Word("Frere", 2, Language.FRENCH));
		wordList.add(new Word("Stadt", 3, Language.GERMAN));
		wordList.add(new Word("City", 3, Language.ENGLISH));
		wordList.add(new Word("Civitas", 3, Language.LATIN));
		wordList.add(new Word("Ville", 3, Language.FRENCH));

	}

	// Gibt die WortListe als String Liste wieder. Macht sie damit lesbar.
	public String wordListToString() {
		ArrayList<String> wordNames = new ArrayList<>();
		for (Word word : wordList) {
			wordNames.add(word.getWord());
		}
		return wordNames.toString();
	}

	// ############## Counter Logik: ########################
	// Beim Trainieren soll im Label mitgezaehlt werden, wievielerichtige
	// Antworten man am Stueck schafft.

	public void counter(boolean choice) {
		if (choice == true) {
			counter++;
		}
		if (choice == false) {
			counter = 0;
		}
	}

	// ############## Highscore Logik: ########################
	public String highScoreToString() {
		int i = 1;
		ArrayList<String> highScoreList = new ArrayList<>();

		 for (Entry<Integer, String> entry : scores.entrySet())
		 {
		 highScoreList.add(i + ". Platz: " + entry.getKey() + " richtige in Folge von" + entry.getValue() + "\n");
		 i++;
		 }

//		for (Integer key : scores.keySet()) {
//			highScoreList.add(i + ". Platz: " + key + " richtige in Folge von " + scores.get(key) + "\n");
//			i++;
//		}

		return highScoreList.toString();

	}

	public void setScore(int anzahl, String name) {
		scores.put(anzahl, name);
	}

	public String getCounter() {
		return "" + counter;
	}

	public Language getRememberV1() {
		return rememberV1;
	}

	public Language getRememberV2() {
		return rememberV2;
	}

	public Language getRememberD1() {
		return rememberD1;
	}

	public Language getRememberD2() {
		return rememberD2;
	}

	public void setRememberV1(Language rememberV1) {
		this.rememberV1 = rememberV1;
	}

	public void setRememberV2(Language rememberV2) {
		this.rememberV2 = rememberV2;
	}

	public void setRememberD1(Language rememberD1) {
		this.rememberD1 = rememberD1;
	}

	public void setRememberD2(Language rememberD2) {
		this.rememberD2 = rememberD2;
	}

	public Word getPrevWord() {
		return prevWord;
	}

	public void setPrevWord(Word prevWord) {
		this.prevWord = prevWord;
	}

}
