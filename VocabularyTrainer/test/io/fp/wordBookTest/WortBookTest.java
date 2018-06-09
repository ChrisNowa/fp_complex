package io.fp.wordBookTest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import io.fp.vocabularyTrainer.model.Language;
import io.fp.vocabularyTrainer.model.VocabularyModel;
import io.fp.vocabularyTrainer.model.WordException;

public class WortBookTest {

	VocabularyModel modelTest;

	@Before
	public void setUp() {
		modelTest = new VocabularyModel();

	}

	@Test
	public void addTest() {

		try {
			modelTest.addNewWordPair("Affe", Language.GERMAN, "Monkey", Language.ENGLISH);
            assertTrue(modelTest.getWordList().contains(modelTest.getWord1("Affe")));
            assertTrue(modelTest.getWordList().contains(modelTest.getWord1("Monkey")));
		} catch (WordException e) {
			fail("Should not have thrown Exception");
		}

	}

}
