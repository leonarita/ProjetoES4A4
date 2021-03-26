package br.com.ifsp.es4a4.projeto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
//				.apiInfo(metaInfo());
	}
	
//	private ApiInfo metaInfo() {
//		
//		ApiInfo apiInfo = new ApiInfo(
//				"Biblioteca", 
//				"API REST para gerenciamento da biblioteca", 
//				"1.0", 
//				null, 
//				"leo_narita@hotmail.com", 
//				"Apache License Version 2.0", 
//				"https://www.apache.org/license.html"
//		);
//		
//		return apiInfo;
//	}

}
