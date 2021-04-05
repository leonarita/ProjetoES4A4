package br.com.ifsp.es4a4.projeto.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.ifsp.es4a4.projeto.utils.files.StorageFile;
import br.com.ifsp.es4a4.projeto.utils.routes.AllowAnnonymous;

@RestController
@RequestMapping("/file")
@CrossOrigin(origins = "*")
@AllowAnnonymous
public class FileController {

	private final StorageFile storageFile;
	
	@Autowired
	public FileController(@Qualifier("files") StorageFile storageFile) {
		this.storageFile = storageFile;
	}
	
	@PostMapping(path = "/upload")
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {

		try {
			storageFile.createFile(file.getInputStream(), file.getOriginalFilename());
			return ResponseEntity.status(HttpStatus.OK).body("Sucesso");
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Erro");
		}
	}
	
	@PostMapping("/uploadFile")
	public void upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String filename = request.getParameter("arquivo");
		storageFile.createFile(request.getInputStream(), filename);
	}
	
}
