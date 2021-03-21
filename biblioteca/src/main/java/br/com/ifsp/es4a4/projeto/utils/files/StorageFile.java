package br.com.ifsp.es4a4.projeto.utils.files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;

public class StorageFile {
	
	private final String path;
	
	@Autowired
	public StorageFile(String path) {
		this.path = path;
		
		FilesOperations.createDirectory(path);
	}
	
	public void createFile(InputStream in, String filename) throws IOException {
		byte[] buffer = new byte[in.available()];
		in.read(buffer);
		
		File targetFile = new File(validatePath(path) + filename);
		OutputStream outStream = new FileOutputStream(targetFile);
		outStream.write(buffer);
		outStream.close();
	}
	
	public String validatePath (String path) {
		if (!path.endsWith("\\"))
			path = path + "\\";
		return path;
	}

}
