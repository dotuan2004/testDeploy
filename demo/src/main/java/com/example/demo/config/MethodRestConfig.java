package com.example.demo.config;


import jakarta.persistence.EntityManager;
import jakarta.persistence.metamodel.EntityType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MethodRestConfig implements RepositoryRestConfigurer {
    private String url="http://localhost:3000";

    @Autowired
    EntityManager entityManager;
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
//        cors.addMapping("/**")
//                .allowedOrigins(url)
//                .allowedMethods("GET","POST","PUT","DELETE");
//        HttpMethod[] chancacphuongthuc ={
//                HttpMethod.POST,
//                HttpMethod.PUT,
//                HttpMethod.PATCH,
//                HttpMethod.DELETE
//        };
        config.exposeIdsFor(entityManager.getMetamodel().getEntities().stream()
                .map(EntityType::getJavaType)
                .toArray(Class[]::new));

//        chanHttpMethod(TheLoai.class,config,chancacphuongthuc);
    }

//    public void chanHttpMethod(Class c,RepositoryRestConfiguration config,HttpMethod[] methods){
//        config.getExposureConfiguration().
//                forDomainType(c).
//                withItemExposure(((metdata, httpMethods) -> httpMethods.disable(methods))).
//                withCollectionExposure(((metdata, httpMethods) -> httpMethods.disable(methods)));
//    }
}
