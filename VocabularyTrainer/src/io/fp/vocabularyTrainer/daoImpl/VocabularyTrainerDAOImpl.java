package io.fp.vocabularyTrainer.daoImpl;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import io.fp.vocabularyTrainer.dao.VocabularyTrainerDAO;
import io.fp.vocabularyTrainer.model.VocabularyModel;



public class VocabularyTrainerDAOImpl implements VocabularyTrainerDAO {


	private String filePath;

	public VocabularyTrainerDAOImpl(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public VocabularyModel createModel() throws IOException {

		FileInputStream fileIn1 = new FileInputStream(filePath);

		if (fileIn1.read() != -1) {
			FileInputStream fileIn2 = new FileInputStream(filePath);
			ObjectInputStream objIn = new ObjectInputStream(fileIn2);

			try {
				VocabularyModel model = (VocabularyModel) objIn.readObject();
				objIn.close();

				if (model != null) {

					throw new IOException("Es gibt schon einen Store");
				}
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}
		}
		VocabularyModel newModel = new VocabularyModel();
		FileOutputStream fileOut = new FileOutputStream(filePath);
		ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
		objOut.writeObject(newModel);
		objOut.close();
		fileIn1.close();
		return newModel;
	}


	public VocabularyModel readModel() throws IOException {
		FileInputStream fileIn = new FileInputStream(filePath);
		ObjectInputStream in = new ObjectInputStream(fileIn);

		VocabularyModel model = null;
		try {
			model = (VocabularyModel) in.readObject();
			if (model == null) {
				in.close();
				throw new IOException("Es gibt keinen Store");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		in.close();

		return model;

	}

	@Override
	public void updateModel(VocabularyModel model) throws IOException {
		FileOutputStream fileIn = new FileOutputStream(filePath);
		ObjectOutputStream in = new ObjectOutputStream(fileIn);
		in.writeObject(model);
		in.close();

	}

	@Override
	public void deleteModel() throws IOException {
		FileOutputStream out = new FileOutputStream(filePath);
		out.close();

	}
}