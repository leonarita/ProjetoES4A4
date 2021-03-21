package br.com.ifsp.es4a4.projeto.utils.files;

import java.io.File;

public abstract class FilesOperations {
	
	public static void createDirectory(String path) {
		File dir = new File(path);
		
		if(!dir.exists()) {
			dir.mkdirs();
		}
	}

}
