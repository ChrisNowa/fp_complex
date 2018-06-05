package io.fp.vocabularyTrainer.model;

import java.io.Serializable;

//Enum mit den erlaubten Sprachen. Kann gerne noch erweitert werden.
public enum Language implements Serializable{
GERMAN(),
ENGLISH(),
LATIN(),
FRENCH();

private Language() {
	
}

}
