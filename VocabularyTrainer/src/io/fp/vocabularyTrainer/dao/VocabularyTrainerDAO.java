package io.fp.vocabularyTrainer.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.SortedMap;

import io.fp.vocabularyTrainer.model.VocabularyModel;
import io.fp.vocabularyTrainer.model.Word;

public interface VocabularyTrainerDAO {

	VocabularyModel createModel() throws IOException;

	VocabularyModel readModel() throws IOException;

	void updateModel(VocabularyModel model) throws IOException;

	void deleteModel() throws IOException;

}