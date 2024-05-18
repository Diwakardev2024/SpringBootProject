package com.nexgenvision;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableOpenApi
public class SwaggerConfig<LinkDiscoverer> {

//    @Bean
//     Docket apiDocket() {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.nexgenvision"))
//                .paths(PathSelectors.any())
//                .build();
//    }
	
//    @Bean
    /*public LinkDiscoverers discovers() {
    	
    	List<LinkDiscoverer> plugins =new ArrayList<>();
    	plugins.add(new CollectionJsonLinkDiscoverer());
    	return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
    	
    }*/
}
