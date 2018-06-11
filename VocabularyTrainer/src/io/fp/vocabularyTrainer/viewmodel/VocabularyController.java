
package io.fp.vocabularyTrainer.viewmodel;

import io.fp.vocabularyTrainer.model.Language;
import io.fp.vocabularyTrainer.model.VocabularyModel;
import io.fp.vocabularyTrainer.model.Word;
import io.fp.vocabularyTrainer.model.WordException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class VocabularyController {

	private VocabularyModel model;

	public VocabularyController() {
		model = new VocabularyModel();
	}

	@FXML
	private Label translationSentenceV;
	

	@FXML
	private Label wordV;
		
	
	@FXML
	public void setWordV() {
	wordV.setText(model.getWordRandom(choiceWord1V.getValue()).getWord());
	}
	@FXML
	private Label languageDirectionV;
	
	@FXML
	private void setLanguageDirectionV() {
		languageDirectionV.setText(
				"von " + choiceWord1V.getValue().toString() + " nach " + choiceWord2V.getValue().toString());
	}

	
	
	@FXML
	private Button confirmV;

	@FXML
	public void handleClickConfirmV(ActionEvent event) {

		if (!textInputFieldV.getText().toString().isEmpty()) {
			Word word = model.getWord1(textInputFieldV.getText());
			Word word2 = model.getWord1(wordV.getText());
			// Wenn Wort nicht im Woerterbuch enthalten ist.
			if (!model.getWordList().contains(model.getWord1(textInputFieldV.getText()))) {
				model.counter(false);
				counterLabel.setText("Richtige Antworten: " + model.getCounter());
				resultV.setText("Die Uebersetzung war falsch! Versuch es noch einmal.");
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Wort nicht enthalten");
				alert.setHeaderText("Fehler");
				alert.setContentText("Dieses Wort ist nicht im Woerterbuch enthalten");
				alert.showAndWait();
				textInputFieldV.clear();
			}
			// Wenn Wort im Woerterbuch enthalten ist.
			else {
				if ((model.compareOrderNumbers(word.getOrderNumbers(), word2.getOrderNumbers()) == true)
						&& (model.compareLanguage(word.getLanguage(), choiceWord2V.getValue()) == true)) {
					resultV.setText("Die Uebersetzung war richtig! Naechstes Wort wurde zufaellig gewaehlt");
					wordV.setText(model.getWordRandom(choiceWord1V.getValue()).getWord());
					model.setPrevWord(model.getWord1(wordV.getText()));
					textInputFieldV.clear();
					;
					// Logik fuer Counter
					model.counter(true);
					counterLabel.setText("Richtige Antworten: " + model.getCounter());
				}
				if ((model.compareOrderNumbers(word.getOrderNumbers(), word2.getOrderNumbers()) == false)
						|| (model.compareLanguage(word.getLanguage(), choiceWord2V.getValue()) == false)) {
					resultV.setText("Die Uebersetzung war falsch! Versuche es noch einmal.");
					textInputFieldV.clear();
					;
					// Logik fuer Counter
					model.counter(false);
					counterLabel.setText("Richtige Antworten: " + model.getCounter());

				}
			}
		}
	}

	@FXML
	private TextField textInputFieldV;
	@FXML
	private Label resultV;
	@FXML
	private Button changeDirectionV;

	@FXML
	public void handleClickChangeDirectionV(ActionEvent event) {
		model.setRememberV1(choiceWord1V.getValue());
		model.setRememberV2(choiceWord2V.getValue());
		choiceWord1V.setValue(model.getRememberV2());
		choiceWord2V.setValue(model.getRememberV1());

		// Hier wurde nun die Anpassung getroffen, dass wenn sich das Wort aeandert
		// ein neues Vokabelpaar initialisiert wird, somit funktioniert diese Methode
		// nun.
		// Die untere Zeile wurde einfach aus der init() kopiert.
		wordV.setText(model.getWordRandom(choiceWord1V.getValue()).getWord());
		languageDirectionV
				.setText("von " + choiceWord1V.getValue().toString() + " nach " + choiceWord2V.getValue().toString());
		// Logik fuer Counter
		model.counter(false);
		counterLabel.setText("Zwecks Richtungswechselt auf: " + model.getCounter() + " gesetzt.");

	}

	@FXML
	private Label sentenceD;
	@FXML
	private Label counterLabel;
	@FXML
	private TextField word1D;
	@FXML
	private TextField word2D;
	@FXML
	private Button addD;

	@FXML
	public void handleAddD(ActionEvent event) {

		String word1 = word1D.getText().toString();
		String word2 = word2D.getText().toString();
		// Prueft, ob eines der beiden Felder leer ist.
		if (word1D.getText().isEmpty() || word2D.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Wort hinzufuegen");
			alert.setHeaderText("Fehler");
			alert.setContentText("Bitte beide Felder eintragen");
			alert.showAndWait();
		} else {
			try {
				model.addNewWordPair(word1, choiceWord1D.getValue(), word2, choiceWord2D.getValue());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Wort hinzufuegen");
				alert.setHeaderText("Erfolg");
				alert.setContentText("Wort wurde erfolgreich hinzugefuegt");
				alert.showAndWait();

			} catch (WordException e1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Wort hinzufuegen");
				alert.setHeaderText("Fehler");
				alert.setContentText("Eintrag nicht möglich! "
						+ "Bedeutet ein Wort in einer anderen Sprache dasselbe, so sollte der Anfangsbuchstabe der Sprache dem zweiten Wort "
						+ "hinzugefuegt werden. Bsp.: Hamster -> E:Hamster");
				alert.showAndWait();
			}
		}
		word1D.clear();
		word2D.clear();
	}

	@FXML
	private Button persistanceD;
	@FXML
	private Button getDictionaryD;

	@FXML
	public void handleGetDictionaryD(ActionEvent event) {
		showDictionaryD.setText(model.wordListToString());
	}

	@FXML
	private Label showDictionaryD;

	@FXML
	private ChoiceBox<Language> choiceWord1V;
	@FXML
	private ChoiceBox<Language> choiceWord2V;
	@FXML
	private ChoiceBox<Language> choiceWord1D;
	@FXML
	private ChoiceBox<Language> choiceWord2D;
	@FXML
	private Label highscores;

}
