package io.fp.trainerTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import io.fp.vocabularyTrainer.model.Language;
import io.fp.vocabularyTrainer.model.VocabularyModel;
import io.fp.vocabularyTrainer.model.Word;
import io.fp.vocabularyTrainer.model.WordException;

public class TrainerTest {

	VocabularyModel modelTest;
	
	@Before
	public void setUp() {
		modelTest = new VocabularyModel();
	}
	
	@Test
	public void compareOrderNumbersTest() {
		
		try {
			modelTest.addNewWordPair("Blume", Language.GERMAN, "Flower", Language.ENGLISH);
			assertEquals(true, modelTest.compareOrderNumbers(modelTest.getWord1("Blume").getOrderNumbers(), modelTest.getWord1("Flower").getOrderNumbers()));
		} catch (WordException e) {
			fail("Should have not thrown Exception");
		}
	}
		
	@Test
	public void compareLanguage() {
		try {
			modelTest.addNewWordPair("Blume", Language.GERMAN, "Flower", Language.ENGLISH);
			Word word1 = new Word("Cat", 10, Language.ENGLISH);
			Word word2 = new Word("Frog", 20, Language.ENGLISH);
			assertEquals(false, modelTest.compareLanguage(modelTest.getWord1("Blume").getLanguage(), modelTest.getWord1("Flower").getLanguage()));
			assertEquals(true, modelTest.compareLanguage(word1.getLanguage(), word2.getLanguage()));
		} catch (WordException e) {
			fail("Should have not thrown Exception");
		}	
	
	
	
	}
						
	
	
}
