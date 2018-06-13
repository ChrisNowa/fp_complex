
package io.fp.vocabularyTrainer.viewmodel;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import io.fp.vocabularyTrainer.dao.VocabularyTrainerDAO;
import io.fp.vocabularyTrainer.daoImpl.VocabularyTrainerDAOImpl;
import io.fp.vocabularyTrainer.model.Language;
import io.fp.vocabularyTrainer.model.VocabularyModel;
import io.fp.vocabularyTrainer.model.Word;
import io.fp.vocabularyTrainer.model.WordException;
import javafx.application.Application.Parameters;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;

public class VocabularyController {

	private VocabularyModel model;
<<<<<<< HEAD
=======
	private VocabularyTrainerDAO dao;
	
	public VocabularyController() {
		
		dao = new VocabularyTrainerDAOImpl("Model.txt");
>>>>>>> branch 'master' of https://github.com/ChrisNowa/fp_complex.git

	public VocabularyController() {
		model = new VocabularyModel();
	}

	@FXML
	private Label translationSentenceV;
	// fertig

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
		languageDirectionV
				.setText("von " + choiceWord1V.getValue().toString() + " nach " + choiceWord2V.getValue().toString());
	}

	@FXML
	private Button confirmV;

	@FXML
	public void handleClickConfirmV(ActionEvent event) {

		if (!textInputFieldV.getText().toString().isEmpty()) {
			Word word = model.getWord1(textInputFieldV.getText(), choiceWord2V.getValue());
			Word word2 = model.getWord1(wordV.getText(), choiceWord1V.getValue());
			// Wenn Wort nicht im Woerterbuch enthalten ist.
			if (!model.getWordList().contains(model.getWord1(textInputFieldV.getText(), choiceWord2V.getValue()))) {

				// Wenn Anzahl der Worte im Highscore drin ist, soll nach dem Namen gefragt
				// werden.
				if (model.checkScore(model.getCounter_int()) == true) {

					TextInputDialog dialog_wb = new TextInputDialog("Name");
					dialog_wb.setTitle("Highscore!!");
					dialog_wb.setHeaderText("Du hast den Highscore!");
					dialog_wb.setContentText("Trag hier deinen Namen ein:");

					// Antwort abholen und eintragen.
					Optional<String> result_wb = dialog_wb.showAndWait();
					if (result_wb.isPresent()) {
						model.setScore(model.getCounter_int(), result_wb.get());
						highscores.setText(model.highScoreToString());

						// Speichern im DAO

					}

				}

				model.counter(false);
				counterLabel.setText("Richtige Antworten: " + model.getCounter());
				resultV.setText("Die Uebersetzung war falsch! Versuch es noch einmal.");
				textInputFieldV.clear();

			}

			else {
				if ((model.compareOrderNumbers(word.getOrderNumbers(), word2.getOrderNumbers()) == true)
						&& (model.compareLanguage(word.getLanguage(), choiceWord2V.getValue()) == true)) {
					resultV.setText("Die Uebersetzung war richtig! Naechstes Wort wurde zufaellig gewaehlt");
					wordV.setText(model.getWordRandom(choiceWord1V.getValue()).getWord());
					model.setPrevWord(model.getWord1(wordV.getText(), choiceWord1V.getValue()));
					textInputFieldV.clear();
					;
					// Logik fuer Counter
					model.counter(true);
					counterLabel.setText("Richtige Antworten: " + model.getCounter());
				}
				if ((model.compareOrderNumbers(word.getOrderNumbers(), word2.getOrderNumbers()) == false)
						|| (model.compareLanguage(word.getLanguage(), choiceWord2V.getValue()) == false)) {

					// Wenn Anzahl der Worte im Highscore drin ist, soll nach dem Namen gefragt
					// werden.
					if (model.checkScore(model.getCounter_int()) == true) {

						TextInputDialog dialog = new TextInputDialog("Name");
						dialog.setTitle("Highscore!!");
						dialog.setHeaderText("Du hast den Highscore!");
						dialog.setContentText("Trag hier deinen Namen ein:");
						// Antwort abholen und eintragen.
						Optional<String> result = dialog.showAndWait();
						if (result.isPresent()) {
							model.setScore(model.getCounter_int(), result.get());
							highscores.setText(model.highScoreToString());
							// Speichern im DAO

						}

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

	}

	@FXML
	private TextField textInputFieldV;

	@FXML
	public void setTextInputFieldV() {
		textInputFieldV.setPromptText("Uebersetzung");
		textInputFieldV.setPrefColumnCount(20);
	}

	@FXML
	private Label resultV;
	// fertig
	@FXML
	private Button changeDirectionV;

	@FXML
	public void handleClickChangeDirectionV(ActionEvent event) {
		model.setRememberV1(choiceWord1V.getValue());
		model.setRememberV2(choiceWord2V.getValue());
		choiceWord1V.setValue(model.getRememberV2());
		choiceWord2V.setValue(model.getRememberV1());

		wordV.setText(model.getWordRandom(choiceWord1V.getValue()).getWord());
		languageDirectionV
				.setText("von " + choiceWord1V.getValue().toString() + " nach " + choiceWord2V.getValue().toString());
		// Logik fuer Counter
		model.counter(false);
		counterLabel.setText("Zwecks Richtungswechselt auf: " + model.getCounter() + " gesetzt.");

	}

	@FXML
	private Label sentenceD;
	// fertig
	@FXML
	private Label counterLabel;
	// fertig
	@FXML
	private TextField word1D;

	@FXML
	public void setWord1D() {
		word1D.setPromptText(choiceWord1D.getValue().toString());
		word1D.setPrefColumnCount(20);
	}

	@FXML
	private TextField word2D;

	@FXML

	public void setWord2D() {
		word2D.setPromptText(choiceWord2D.getValue().toString());
		word2D.setPrefColumnCount(20);
	}

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
	public void handlePersistanceD() {
	}

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
	public void setChoiceWord1V() {
		choiceWord1V.setItems(FXCollections.observableArrayList(Language.values()));
		choiceWord1V.setValue(Language.GERMAN);
	}

	public void setChoiceWord1VMouseAction(MouseEvent mouse) {
		model.setRememberV1(choiceWord1V.getValue());
	}

	public void mouseClicked1V(ActionEvent event) {
		Language value = model.getRememberV1();

		if (choiceWord1V.getValue().equals(choiceWord2V.getValue())) {
			choiceWord1V.setValue(choiceWord2V.getValue());
			choiceWord2V.setValue(value);
			model.counter(false);
			counterLabel.setText("Zwecks Richtungswechselt auf: " + model.getCounter() + " gesetzt.");
		}

		wordV.setText(model.getWordRandom(choiceWord1V.getValue()).getWord());
		languageDirectionV
				.setText("von " + choiceWord1V.getValue().toString() + " nach " + choiceWord2V.getValue().toString());

	}

	@FXML
	private ChoiceBox<Language> choiceWord2V;

	@FXML
	public void setChoiceWord2V() {
		choiceWord2V.setItems(FXCollections.observableArrayList(choiceWord1V.getItems()));
		choiceWord2V.setValue(Language.ENGLISH);
	}

	public void mouseClicked2V(MouseEvent mouse) {
		model.setRememberD2(choiceWord2D.getValue());
	}

	public void setChoiceWord2VSetOnAction(ActionEvent event) {
		Language value = model.getRememberV2();

		if (choiceWord1V.getValue().equals(choiceWord2V.getValue())) {
			choiceWord2V.setValue(choiceWord1V.getValue());
			choiceWord1V.setValue(value);
			model.counter(false);
			counterLabel.setText("Zwecks Richtungswechselt auf: " + model.getCounter() + " gesetzt.");
		}

		languageDirectionV
				.setText("von " + choiceWord1V.getValue().toString() + " nach " + choiceWord2V.getValue().toString());

	}

	@FXML
	private ChoiceBox<Language> choiceWord1D;

	@FXML
	public void setChoiceWord1D() {
		choiceWord1D.setItems(FXCollections.observableArrayList(Language.values()));
		choiceWord1D.setValue(Language.GERMAN);
	}

	public void mouseClicked1D(MouseEvent mouse) {
		model.setRememberD1(choiceWord1D.getValue());
	}

	public void setChoiceWord1DSetOnAction(ActionEvent event) {
		Language value = model.getRememberD1();

		if (choiceWord1D.getValue().equals(choiceWord2D.getValue())) {
			choiceWord1D.setValue(choiceWord2D.getValue());
			choiceWord2D.setValue(value);
			model.counter(false);
			counterLabel.setText("Zwecks Richtungswechselt auf: " + model.getCounter() + " gesetzt.");
		}

		word1D.setPromptText(choiceWord1D.getValue().toString());
	}

	@FXML
	private ChoiceBox<Language> choiceWord2D;

	@FXML
	public void setChoiceWord2D() {
		choiceWord2D.setItems(FXCollections.observableArrayList(choiceWord1V.getItems()));
		choiceWord2D.setValue(Language.ENGLISH);
	}

	public void mouseClicked2D(MouseEvent mouse) {
		model.setRememberD2(choiceWord2D.getValue());
	}

	public void setChoiceWord2DSetOnAction(ActionEvent event) {
		Language value = model.getRememberD2();

		if (choiceWord1D.getValue().equals(choiceWord2D.getValue())) {
			choiceWord2D.setValue(choiceWord1D.getValue());
			choiceWord1D.setValue(value);
			model.counter(false);
			counterLabel.setText("Zwecks Richtungswechselt auf: " + model.getCounter() + " gesetzt.");
		}
		word2D.setPromptText(choiceWord2D.getValue().toString());
	}

	@FXML
	private Label highscores;

	@FXML
	public void highscoreSetText() {
		highscores.setText(model.highScoreToString());
	}

}
