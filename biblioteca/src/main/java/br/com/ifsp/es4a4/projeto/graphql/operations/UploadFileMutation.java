package br.com.ifsp.es4a4.projeto.graphql.operations;

import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import br.com.ifsp.es4a4.projeto.utils.files.StorageFile;

@Slf4j
@Component
public class UploadFileMutation implements GraphQLMutationResolver {
	
	private final StorageFile storageFile;
	
	@Autowired
	public UploadFileMutation(@Qualifier("files") StorageFile storageFile) {
		this.storageFile = storageFile;
	}
	
	public UUID uploadFile(DataFetchingEnvironment environment) {
		DefaultGraphQLServletContext context = environment.getContext();
		
		context.getFileParts().forEach(part -> {
			log.info("uploading: {} , size: {}", part.getSubmittedFileName(), part.getSize());
			
			String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
			
			try (InputStream fileContent = part.getInputStream()) {
				storageFile.createFile(fileContent, filename);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		});
		
		return UUID.randomUUID();
	}

}
