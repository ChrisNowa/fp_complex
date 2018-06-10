package io.fp.wordBookTest;

import static org.junit.Assert.assertEquals;
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
			assertEquals(Language.GERMAN, modelTest.getWord1("Affe").getLanguage());
			assertEquals(Language.ENGLISH, modelTest.getWord1("Monkey").getLanguage());
			assertEquals(modelTest.getWord1("Affe").getOrderNumbers(),modelTest.getWord1("Monkey").getOrderNumbers());
			
		} catch (WordException e) {
			fail("Should not have thrown Exception");
		}

	}

	@Test
	public void addEqualWordTest() {
		try {
			modelTest.addNewWordPair("Katze", Language.GERMAN, "Katze", Language.ENGLISH);
			fail("Should have thrown Exception");
		} catch (WordException e) {
			assertTrue(!modelTest.getWordList().contains(modelTest.getWord1("Katze")));
		}

	}

	@Test
	public void addContainedWords() {
		try {
			modelTest.addNewWordPair("Affe", Language.GERMAN, "Monkey", Language.ENGLISH);
			modelTest.addNewWordPair("Affe", Language.GERMAN, "Monkey", Language.ENGLISH);
			fail("Should have thrown Exception");
		} catch (WordException e) {

		}

	}

	@Test
	public void addWordTwiceFirst() {

		try {
			modelTest.addNewWordPair("Klar", Language.GERMAN, "Clear", Language.ENGLISH);
			modelTest.addNewWordPair("Klar", Language.GERMAN, "Free", Language.ENGLISH);
			assertTrue(modelTest.getWord1("Klar").getOrderNumbers().size() == 2);
			assertTrue(modelTest.getWord1("Free").getOrderNumbers().size() == 1);
			assertEquals(Integer.valueOf(4), modelTest.getWord1("Klar").getOrderNumbers().get(0));
			assertEquals(Integer.valueOf(5), modelTest.getWord1("Klar").getOrderNumbers().get(1));
			assertEquals(Integer.valueOf(4), modelTest.getWord1("Clear").getOrderNumbers().get(0));
			assertEquals(Integer.valueOf(5), modelTest.getWord1("Free").getOrderNumbers().get(0));
		} catch (WordException e) {
			fail("Should have thrown Exception");
		}
	}

	@Test
	public void addWordTwiceSecond() {
		try {
			modelTest.addNewWordPair("Clear", Language.ENGLISH, "Klar", Language.GERMAN);
			modelTest.addNewWordPair("Free", Language.ENGLISH, "Klar", Language.GERMAN);
			assertTrue(modelTest.getWord1("Klar").getOrderNumbers().size() == 2);
			assertTrue(modelTest.getWord1("Free").getOrderNumbers().size() == 1);
			assertEquals(Integer.valueOf(4), modelTest.getWord1("Clear").getOrderNumbers().get(0));
			assertEquals(Integer.valueOf(5), modelTest.getWord1("Free").getOrderNumbers().get(0));
			assertEquals(Integer.valueOf(4), modelTest.getWord1("Klar").getOrderNumbers().get(0));
			assertEquals(Integer.valueOf(5), modelTest.getWord1("Klar").getOrderNumbers().get(1));
		} catch (WordException e) {
			fail("Should have thrown Exception");
		}

	}

}
