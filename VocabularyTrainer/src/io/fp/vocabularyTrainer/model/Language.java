package io.fp.vocabularyTrainer.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

//Enum mit den erlaubten Sprachen. Kann gerne noch erweitert werden.
public enum Language implements Serializable {
	GERMAN(), ENGLISH(), LATIN(), FRENCH();

	private static final List<Language> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static Language randomLanguage() {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}
}
