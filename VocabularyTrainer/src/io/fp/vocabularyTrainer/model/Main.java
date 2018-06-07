package io.fp.vocabularyTrainer.model;

public class Main {

	public static void main(String[] args) {
		VocabularyModel model = new VocabularyModel();
		try {
			model.addNewWordPair("Hase", Language.GERMAN, "Rabbit", Language.ENGLISH);
			model.addNewWordPair("Klar", Language.GERMAN, "Clear", Language.ENGLISH);
		   model.addNewWordPair("Klar", Language.GERMAN, "Free", Language.ENGLISH);
		   model.addNewWordPair("Frei", Language.GERMAN, "Free", Language.ENGLISH);
		   model.addNewWordPair("Umsonst", Language.GERMAN, "Free", Language.ENGLISH);
		   model.addNewWordPair("Klar", Language.GERMAN, "Evident", Language.ENGLISH);
		} catch (WordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Klar sollte (5 und 6 und 8enthalten), Free sollte (6 und 7 haben), Frei sollte (7 haben), Clear sollte 5 haben. 
		System.out.println(model.getWord1("Hase").getOrderNumbers());
		System.out.println(model.getWord1("Rabbit").getOrderNumbers());
		System.out.println(model.getWord1("Klar").getOrderNumbers());
		System.out.println(model.getWord1("Free").getOrderNumbers());
		System.out.println(model.getWord1("Frei").getOrderNumbers());
        System.out.println(model.getWord1("Clear").getOrderNumbers());
        System.out.println(model.getWord1("Evident").getOrderNumbers());
        System.out.println(model.getWord1("Umsonst").getOrderNumbers());
      
        System.out.println(model.getWord1("Klar").getLanguage());
        System.out.println(model.wordListToString());
	}

}
