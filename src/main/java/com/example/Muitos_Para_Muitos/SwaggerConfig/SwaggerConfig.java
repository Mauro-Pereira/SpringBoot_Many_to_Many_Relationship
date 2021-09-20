package com.example.Muitos_Para_Muitos.SwaggerConfig;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;



@Configuration
public class SwaggerConfig {
	
	@Bean
    public Docket api() { 
		
		/**
		 return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.basePackage("com.example.Muitos_Para_Muitos"))
	                .paths(PathSelectors.ant("/**"))
	                .build()
	                .ignoredParameterTypes(Student.class)
	                .globalRequestParameters(
	                        singletonList(new springfox.documentation.builders.RequestParameterBuilder()
	                                .name("Authorization")
	                                .description("Header para token JWT")
	                                .in(ParameterType.HEADER)
	                                .required(false)
	                                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
	                                .build()));
		
		**/
		
		    return new Docket(DocumentationType.SWAGGER_2)
		    	.apiInfo(apiInfo())
		    	.securityContexts(Arrays.asList(securityContext()))
		    	.securitySchemes(Arrays.asList(apiKey()))
		        .select()
		        .apis(RequestHandlerSelectors.basePackage("com.example.Muitos_Para_Muitos"))
		        .paths(PathSelectors.any())
		        .build();
	}
	
	private ApiKey apiKey() { 
	    return new ApiKey("JWT", "Authorization", "header"); 
	}
	
	private SecurityContext securityContext() { 
	    return SecurityContext.builder().securityReferences(defaultAuth()).build(); 
	} 

	private List<SecurityReference> defaultAuth() { 
	    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything"); 
	    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1]; 
	    authorizationScopes[0] = authorizationScope; 
	    return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	    
	}
	
	private ApiInfo apiInfo() {
	    return new ApiInfoBuilder()
	    		.title("Many to Many Project")
	    		.description("This project was made like a way to learn about many to many relationship in Spring Boot")
	    		.version("1.0")
	    		.license("Apache Lincense Version 2.0")
	    		.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
	    		.build();
	}
	

}
	

