package io.fp.vocabularyTrainer.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.fp.vocabularyTrainer.dao.VocabularyTrainerDAO;
import io.fp.vocabularyTrainer.daoImpl.VocabularyTrainerDAOImpl;
import io.fp.vocabularyTrainer.model.Language;
import io.fp.vocabularyTrainer.model.VocabularyModel;
import io.fp.vocabularyTrainer.model.Word;
import io.fp.vocabularyTrainer.model.WordException;
import io.fp.vocabularyTrainer.model.Highscore;
import javafx.application.Application;
import javafx.application.Application.Parameters;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class VocabularyTrainerUI extends Application {
	// Endung V betrifft den Vokabeltrainer, Endung D betrifft das Woerterbuch
	private VocabularyModel model;
	private Label translationSentenceV;
	private Label wordV;
	private Label languageDirectionV;
	private Button confirmV;
	private TextField textInputFieldV;
	private Label resultV;
	private Button changeDirectionV;
	private Label sentenceD;
	private Label counterLabel;
	private TextField word1D;
	private TextField word2D;
	private Button addD;
	private Button persistanceD;
	private Button getDictionaryD;
	private Label showDictionaryD;
	private Button deleteD;
	private ChoiceBox<Language> choiceWord1V;
	private ChoiceBox<Language> choiceWord2V;
	private ChoiceBox<Language> choiceWord1D;
	private ChoiceBox<Language> choiceWord2D;
	private Label highscores;
	private Highscore highscore;
	// hier wird das dao als Datenfeld genannt.
	private VocabularyTrainerDAO dao;

	public void init() throws Exception {
		// //init dao
		// Parameters params = getParameters();
		// List<String> paramList = params.getRaw();
		// if (paramList.size()<1) {
		// throw new IOException("No parameter defined for file name!");
		// }
		// dao = new VocabularyTrainerDAOImpl(paramList.get(0));
		//
		// // init model
		// try {
		// model = dao.readModel();
		// } catch (IOException e) {
		// model = dao.createModel();
		// }

		// init rest
		model = new VocabularyModel();
		highscore = new Highscore();
		translationSentenceV = new Label("Uebersetze das Wort");
		textInputFieldV = new TextField();
		textInputFieldV.setPromptText("Uebersetzung");
		textInputFieldV.setPrefColumnCount(20);
		// Hier werden die Choice Boxen initialisiert. Die mit V sind die vom Trainer,
		// die mit D vom Woerterbuch
		// Am Anfang bekommen sie Deutsch und Englisch zugewiesen, um NullPointer bei
		// den Labels zu vermeiden.
		// Man muss noch schauen, dass beim aendern der ChoiceBoxen ist labels und
		// TextFelder veraendert werden.
		// Diese waeren meiner Meinung nach der PromptText von word1D und word2D, sowie
		// der Text von
		// languageDirectionV und das Wort von wordV
		choiceWord1V = new ChoiceBox<>();
		choiceWord1V.setItems(FXCollections.observableArrayList(Language.values()));
		choiceWord1V.setValue(Language.GERMAN);
		choiceWord2V = new ChoiceBox<>();
		choiceWord2V.setItems(FXCollections.observableArrayList(choiceWord1V.getItems()));
		choiceWord2V.setValue(Language.ENGLISH);
		choiceWord1D = new ChoiceBox<>();
		choiceWord1D.setItems(FXCollections.observableArrayList(Language.values()));
		choiceWord1D.setValue(Language.GERMAN);
		choiceWord2D = new ChoiceBox<>();
		choiceWord2D.setItems(FXCollections.observableArrayList(choiceWord1V.getItems()));
		choiceWord2D.setValue(Language.ENGLISH);
		// Word1D und Word2D sind die beiden Textfelder des Woerterbuchs
		word1D = new TextField();
		word1D.setPromptText(choiceWord1D.getValue().toString());
		word1D.setPrefColumnCount(20);
		word2D = new TextField();
		word2D.setPromptText(choiceWord2D.getValue().toString());
		word2D.setPrefColumnCount(20);
		
		choiceWord1D.setOnMouseClicked(e->{
			model.setRememberD1(choiceWord1D.getValue());
		});
		
		choiceWord1D.setOnAction(e -> {
			Language value = model.getRememberD1();
			
			if(choiceWord1D.getValue().equals(choiceWord2D.getValue())){
				choiceWord1D.setValue(choiceWord2D.getValue());
				choiceWord2D.setValue(value);
				
			}

			word1D.setPromptText(choiceWord1D.getValue().toString());

		});
		choiceWord2D.setOnMouseClicked(e->{
			model.setRememberD2(choiceWord2D.getValue());
		});
		
		
		choiceWord2D.setOnAction(e -> {
             Language value = model.getRememberD2();
			
			if(choiceWord1D.getValue().equals(choiceWord2D.getValue())){
				choiceWord2D.setValue(choiceWord1D.getValue());
				choiceWord1D.setValue(value);
			}
			word2D.setPromptText(choiceWord2D.getValue().toString());
		});
		
		choiceWord1V.setOnMouseClicked(e->{
			model.setRememberV1(choiceWord1V.getValue());
		});
		
		choiceWord1V.setOnAction(e -> {
            Language value = model.getRememberV1();
			
			if(choiceWord1V.getValue().equals(choiceWord2V.getValue())){
				choiceWord1V.setValue(choiceWord2V.getValue());
				choiceWord2V.setValue(value);
				
			}
			
			
			wordV.setText(model.getWordRandom(choiceWord1V.getValue()).getWord());
			languageDirectionV.setText(
					"von " + choiceWord1V.getValue().toString() + " nach " + choiceWord2V.getValue().toString());
		});

		choiceWord2V.setOnMouseClicked(e->{
		model.setRememberV2(choiceWord2V.getValue());
			
		});
		
		choiceWord2V.setOnAction(e -> {
          Language value = model.getRememberV2();
			
			if(choiceWord1V.getValue().equals(choiceWord2V.getValue())){
				choiceWord2V.setValue(choiceWord1V.getValue());
				choiceWord1V.setValue(value);
			}
			
			
			languageDirectionV.setText(
					"von " + choiceWord1V.getValue().toString() + " nach " + choiceWord2V.getValue().toString());

		});
		// sentenceD ist damit fertig
		sentenceD = new Label("Ein neues Wort in das Woerterbuch eintragen: ");
		// resultV ist die Meldung nach bestätigen beim Vokalbeltrainer
		resultV = new Label();
		// wordV ist das Wort, welches man beim Trainer übersetzen muss. Es wird ein
		// radom Wort generiert.
		// Problem ist momentan, wenn man die ChoiceBox ändert, wird noch nicht
		// automatisch das Wort in wordV geändert.
		// Das sollte es aber, da sich ja die Sprache ändert und die ChoiceBoxen als
		// Wert die Sprache geben.
		wordV = new Label();
		wordV.setText(model.getWordRandom(choiceWord1V.getValue()).getWord());
		wordV.setFont(new Font(30));
		// languageDirectionV zeigt die Übersetzungsrichtung beim Trainer an.
		// Ändert man momentan eine ChoiceBox, wird aber noch nicht automatisch das
		// dieses Label geändert.
		// Das muss noch verbessert werden
		languageDirectionV = new Label();
		languageDirectionV
				.setText("von " + choiceWord1V.getValue().toString() + " nach " + choiceWord2V.getValue().toString());
		// Counter Label
		counterLabel = new Label();
		counterLabel.setText("Richtige Antworten: " + model.getCounter());
		// Der ConfirmV button bestaetigt die Eingabe beim Trainer. Hier muss noch
		// geprüft werden, ob das Wort
		// ueberhaupt im Wörterbuch ist, sonst kommt ie NullPointerException :(
		confirmV = new Button("Bestaetigen");
		confirmV.setOnAction(e -> {
			if (!textInputFieldV.getText().toString().isEmpty()) {
				Word word = model.getWord1(textInputFieldV.getText());
				Word word2 = model.getWord1(wordV.getText());
				// Die Methode so veraendert, das sie für ArrayList funktioniert
				if (!model.getWordList().contains(model.getWord1(textInputFieldV.getText()))) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Wort nicht enthalten");
					alert.setHeaderText("Fehler");
					alert.setContentText("Dieses Wort ist nicht im Woerterbuch enthalten");
					alert.showAndWait();

				} else {
					if ((model.compareOrderNumbers(word.getOrderNumbers(), word2.getOrderNumbers()) == true)
							&& (model.compareLanguage(word.getLanguage(), choiceWord2V.getValue()) == true)) {
						resultV.setText("Die Uebersetzung war richtig! Naechstes Wort wurde zufaellig gewaehlt");
						wordV.setText(model.getWordRandom(choiceWord1V.getValue()).getWord());
						textInputFieldV.clear();
						;
						// Logik fuer Counter
						model.counter(true);
						counterLabel.setText("Richtige Antworten: " + model.getCounter());
					}
					if ((model.compareOrderNumbers(word.getOrderNumbers(), word2.getOrderNumbers()) == false)
							|| (model.compareLanguage(word.getLanguage(), choiceWord2V.getValue()) == false)) {
						resultV.setText("Die Uebersetzung war falsch! Versuche es noch einmal!");
						textInputFieldV.clear();
						;
						// Logik fuer Counter
						model.counter(false);
						counterLabel.setText("Richtige Antworten: " + model.getCounter());

					}
				}
			}

		});
		// Aendert die Uebersetzungsrichtung des Trainers
		changeDirectionV = new Button("Uebersetzungsrichtung aendern");
		changeDirectionV.setOnAction(e -> {
			Language language = choiceWord1V.getValue();
			choiceWord1V.setValue(choiceWord2V.getValue());
			choiceWord2V.setValue(language);

			// Hier wurde nun die Anpassung getroffen, dass wenn sich das Wort aeandert
			// ein neues Vokabelpaar initialisiert wird, somit funktioniert diese Methode
			// nun.
			// Die untere Zeile wurde einfach aus der init() kopiert.
			wordV.setText(model.getWordRandom(choiceWord1V.getValue()).getWord());
			languageDirectionV.setText(
					"von " + choiceWord1V.getValue().toString() + " nach " + choiceWord2V.getValue().toString());
			// Logik fuer Counter
			model.counter(false);
			counterLabel.setText("Zwecks Richtungswechselt auf: " + model.getCounter() + " gesetzt.");

		});
		// Fuegt die Woerter in das Woertbuch ein. Hier sollen keine Duplikate
		// entstehen.
		// Man kann aber mehrfach ein Wort eingeben, um Verbindungen für viele Sprachen
		// herzustellen.
		// z.B: Hallo -> Hello, Hallo-> Bonjour, Hallo-> Ave. Hallo soll aber nur einmal
		// gespeichert werden.
		// Die Probleme der Methode sollte eigentlich ausgemerzt sein. Es wird noch
		// manchmal eine Exception geworfen, aber das Programm funktioniert
		addD = new Button("Hinzufuegen");
		addD.setOnAction((ActionEvent e) -> {

			String word1 = word1D.getText().toString();
			String word2 = word2D.getText().toString();
			// Neu dabei, da bei der addwortpair es nicht funktioniert hat, zu pruefen, ob
			// eines der beiden Felder leer ist.
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
					alert.setContentText("Eintrag fuer " + word1D.getText() + " bereits vorhanden");
					alert.showAndWait();
				}
			}
			word1D.clear();
			word2D.clear();

		});
		// Das Woerterbuch kann hiermit persistent werden. Bis jetzt noch nicht
		// behandelt.
		persistanceD = new Button("Woerterbuch speichern");
		persistanceD.setOnAction(e -> {
			// try {
			// dao.updateModel(model);
			// } catch (IOException ex) {
			// showAlert("Can't write to File!");
			// ex.printStackTrace();
			// }
		});
		deleteD = new Button("Loesche Woerterbuch und HighScore!");
		deleteD.setOnAction(e -> {

		});

		// Label, um die Dictionary Woerter zu sehen.
		showDictionaryD = new Label();
		// Button der einem das Dictionary zeigt.
		getDictionaryD = new Button("Woerterbuchlist");
		getDictionaryD.setOnAction(e -> {
			showDictionaryD.setText(model.wordListToString());
		});
		
		highscores = new Label();
		//Test
		highscore.setScore(5, "Chris");
		highscores.setText(highscore.toString());

	}

	private void showAlert(String message) {
		Alert alert = new Alert(AlertType.ERROR, message);
		alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> alert.close());
	}

	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Vokabeltrainer");
		primaryStage.setScene(new Scene(createSceneGraph(), 700, 200));
		primaryStage.show();

	}

	// Der Scene Graph besteht aus einer TabPane mit zwei Tabs. Die Tabs enthalten
	// eine VBox, welche selbst
	// die einzelnen Elemente enthalten, manche in FlowPanes verpackt. Hier muss am
	// Ende noch Design und Ordnung verbessert werden.
	private Parent createSceneGraph() {
		TabPane pane = new TabPane();
		VBox box1 = new VBox();
		Tab tab1 = new Tab();
		FlowPane flow1 = new FlowPane();
		FlowPane flow2 = new FlowPane();
		flow2.getChildren().addAll(textInputFieldV, confirmV);
		flow1.getChildren().addAll(choiceWord1V, choiceWord2V);
		tab1.setText("Trainieren");
		box1.getChildren().addAll(translationSentenceV, wordV, languageDirectionV, flow1, flow2, changeDirectionV,
				resultV, counterLabel);
		box1.setAlignment(Pos.TOP_CENTER);
		tab1.setContent(box1);
		Tab tab2 = new Tab();
		tab2.setText("Woerterbuch");
		VBox box2 = new VBox();
		FlowPane flow3 = new FlowPane();
		FlowPane flow4 = new FlowPane();
		Tab tab3 = new Tab();
		tab3.setText("Highscores");
		VBox box3 = new VBox();
		tab3.setContent(box3);
		box3.getChildren().addAll(highscores);
		flow3.getChildren().addAll(choiceWord1D, choiceWord2D);
		flow4.getChildren().addAll(word1D, word2D, addD);
		box2.getChildren().addAll(sentenceD, flow3, flow4, persistanceD, showDictionaryD, getDictionaryD);
		box2.setAlignment(Pos.TOP_CENTER);
		tab2.setContent(box2);
		pane.getTabs().add(tab1);
		pane.getTabs().add(tab2);
		pane.getTabs().add(tab3);
		// Hier wird festgelegt, dass die Tabs nicht über ein x geschossen werden
		// können.
		pane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

		return pane;

	}

	public static void main(String[] args) {
		launch(args);
	}

}
