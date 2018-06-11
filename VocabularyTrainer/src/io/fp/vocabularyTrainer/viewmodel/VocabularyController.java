package io.fp.vocabularyTrainer.viewmodel;

import io.fp.vocabularyTrainer.model.Language;
import io.fp.vocabularyTrainer.model.VocabularyModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
	private Label languageDirectionV;
	@FXML
	private Button confirmV;
	@FXML
	private TextField textInputFieldV;
	@FXML
	private Label resultV;
	@FXML
	private Button changeDirectionV;
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
	private Button persistanceD;
	@FXML
	private Button getDictionaryD;
	@FXML
	private Label showDictionaryD;
	@FXML
	private Button deleteD;
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
